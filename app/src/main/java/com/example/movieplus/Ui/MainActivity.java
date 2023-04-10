package com.example.movieplus.Ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.movieplus.Adapter.MovieSearchAdapter;
import com.example.movieplus.Adapter.PersonSearchAdapter;
import com.example.movieplus.BuildConfig;
import com.example.movieplus.Client.RetrofitClient;
import com.example.movieplus.Interfaces.RetrofitService;
import com.example.movieplus.Model.MovieResponse;
import com.example.movieplus.Model.MovieResponseResults;
import com.example.movieplus.Model.PersonResponse;
import com.example.movieplus.Model.PersonResponseResults;
import com.example.movieplus.R;
import com.example.movieplus.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
private ActivityMainBinding binding;
private FirebaseUser user;
private FirebaseAuth mAuth;
private String movie = "By Movie Title";
private String person  = "by person name";
private RetrofitService retrofitService;
private MovieSearchAdapter movieSearchAdapter;
private PersonSearchAdapter personSearchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding = ActivityMainBinding.inflate(getLayoutInflater());
       View view = binding.getRoot();
       setContentView(view);

       mAuth = FirebaseAuth.getInstance();
       user = mAuth.getCurrentUser();
       if (user == null){
           Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
           startActivity(intent);
           finish();
       }else {
         //  binding.textName.setText(user.getEmail());
       }

       //disable the keyword on start
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

       binding.resultsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

       Paper.init(this);

       retrofitService = RetrofitClient.getClient().create(RetrofitService.class);

       final ArrayList<String> category = new ArrayList<>();
       //Set list for soureceSpiner
        category.add(movie);
        category.add(person);

        //binding.sourceSpinner.attachDataSource(category);


        //Retrive the position at start and the set the spinner
        if (Paper.book().read("position") != null){
           // binding.sourceSpinner.setSelected(Paper.book().read("position"));
            int position = Paper.book().read("position");
            binding.sourceSpinner.setSelection(position);
        }
        //set the text on edit text on create
        int position = binding.sourceSpinner.getSelectedItemPosition();
        if (position == 0){
            binding.queryEditText.setHint("Enter any movie title...");
        }else {
            binding.queryEditText.setHint("Enter any person name...");
        }


        binding.sourceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               // when sourecSepiner in clicked change
                if (position == 0){
                    binding.queryEditText.setHint("Enter any movie title...");
                }else {
                    binding.queryEditText.setHint("Enter any person name...");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Retrieve the results from pape db and start
        if (Paper.book().read("cache") != null){
            String results = Paper.book().read("cache");
            if (Paper.book().read("source")!=null){
                String source = Paper.book().read("source");
                if (source.equals("movie")){
                    //Convert the string cache to model movie response class using gson
                    MovieResponse movieResponse = new Gson().fromJson(results,MovieResponse.class);
                    //
                    if (movieResponse != null){
                        List<MovieResponseResults> movieResponseResults = movieResponse.getResults();
                        //Adapter
                        movieSearchAdapter = new MovieSearchAdapter(MainActivity.this,movieResponseResults);
                        binding.resultsRecyclerView.setAdapter(movieSearchAdapter);
                        //Create some animation to recyclerView item loading
                        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(MainActivity.this, R.anim.layout_slider_right);
                        binding.resultsRecyclerView.setLayoutAnimation(controller);
                        binding.resultsRecyclerView.scheduleLayoutAnimation();
                        //now store the results in paper database to access offins
                        Paper.book().write("cache", new Gson().toJson(movieResponse));
                        //Store also the categoria to set the Spinner at app start
                        Paper.book().write("source","movie");
                    }
                }else {
                    PersonResponse personResponse = new Gson().fromJson(results,PersonResponse.class);
                    //
                    if (personResponse != null){
                        List<PersonResponseResults> personResponseResults = personResponse.getResults();
                        //Adapter
                        personSearchAdapter = new PersonSearchAdapter(MainActivity.this,personResponseResults);
                        binding.resultsRecyclerView.setAdapter(personSearchAdapter);
                        //Create some animation to recyclerView item loading
                        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(MainActivity.this, R.anim.layout_slider_right);
                        binding.resultsRecyclerView.setLayoutAnimation(controller);
                        binding.resultsRecyclerView.scheduleLayoutAnimation();
                        //now store the results in paper database to access offins
                        Paper.book().write("cache", new Gson().toJson(personResponse));
                        //Store also the categoria to set the Spinner at app start
                        Paper.book().write("source","person");
                    }
                }
            }
        }


        //get the query from user
        binding.querySearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.queryEditText.getText() != null){
                    String query = binding.queryEditText.getText().toString();

                    if (query.equals("") || query.equals("")){
                        Toast.makeText(MainActivity.this,"Plase enter any text....", Toast.LENGTH_LONG).show();
                    }else {
                        binding.queryEditText.setText("");
                        //get the caegoria to search the query  more or person
                        String finalQuery = query.replaceAll("","+");

                        if (category.size()>0){
                            String categoryName = category.get(binding.sourceSpinner.getSelectedItemPosition());
                            if (categoryName.equals(movie)){
                                Call<MovieResponse> movieResponseCall = retrofitService.getMoviesByQuery(BuildConfig.THE_MOVIE_DB_API_KEY,finalQuery);
                                movieResponseCall.enqueue(new Callback<MovieResponse>() {
                                    @Override
                                    public void onResponse(@NonNull Call<MovieResponse> call,@NonNull  Response<MovieResponse> response) {
                                        MovieResponse movieResponse = response.body();
                                        if (movieResponse != null){
                                            List<MovieResponseResults> movieResponseResults = movieResponse.getResults();
                                            //Adapter
                                            movieSearchAdapter = new MovieSearchAdapter(MainActivity.this,movieResponseResults);
                                            binding.resultsRecyclerView.setAdapter(movieSearchAdapter);
                                            //Create some animation to recyclerView item loading
                                            LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(MainActivity.this, R.anim.layout_slider_right);
                                            binding.resultsRecyclerView.setLayoutAnimation(controller);
                                            binding.resultsRecyclerView.scheduleLayoutAnimation();
                                            //now store the results in paper database to access offins
                                            Paper.book().write("cache", new Gson().toJson(movieResponse));
                                            //Store also the categoria to set the Spinner at app start
                                            Paper.book().write("source","movie");



                                        }
                                    }

                                    @Override
                                    public void onFailure(@NonNull Call<MovieResponse> call,@NonNull Throwable t) {

                                    }
                                });
                            }else {
                                Call<PersonResponse> personResponseCall = retrofitService.getPersonsByQuery(BuildConfig.THE_MOVIE_DB_API_KEY,finalQuery);
                                personResponseCall.enqueue(new Callback<PersonResponse>() {
                                    @Override
                                    public void onResponse(@NonNull Call<PersonResponse> call, @NonNull Response<PersonResponse> response) {
                                       PersonResponse personResponse = response.body();

                                        if (personResponse != null){
                                            List<PersonResponseResults> personResponseResults = personResponse.getResults();
                                            //Adapter
                                            personSearchAdapter = new PersonSearchAdapter(MainActivity.this,personResponseResults);
                                            binding.resultsRecyclerView.setAdapter(personSearchAdapter);
                                            //Create some animation to recyclerView item loading
                                            LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(MainActivity.this, R.anim.layout_slider_right);
                                            binding.resultsRecyclerView.setLayoutAnimation(controller);
                                            binding.resultsRecyclerView.scheduleLayoutAnimation();
                                            //now store the results in paper database to access offins
                                            Paper.book().write("cache", new Gson().toJson(personResponse));
                                            //Store also the categoria to set the Spinner at app start
                                            Paper.book().write("source","person");



                                        }
                                    }

                                    @Override
                                    public void onFailure(@NonNull Call<PersonResponse> call, @NonNull Throwable t) {

                                    }
                                });
                            }
                        }
                    }
                }


            }
        });

    }


    @Override
    protected void onStop() {
        super.onStop();
        //Set the position of spinner in offline to retrieve at start

        Paper.book().write("position", binding.sourceSpinner.getSelectedItemPosition());
    }
}