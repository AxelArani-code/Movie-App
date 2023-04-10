package com.example.movieplus.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieplus.Model.MovieResponseResults;
import com.example.movieplus.Model.PersonResponseResults;
import com.example.movieplus.R;
import com.example.movieplus.ViewModel.SearchViewModel;

import java.util.List;

public class PersonSearchAdapter extends RecyclerView.Adapter<SearchViewModel> {
    private Activity activity;
    private List<PersonResponseResults> results;

    public PersonSearchAdapter(Activity activity, List<PersonResponseResults> results) {
        this.activity = activity;
        this.results = results;
    }

    @NonNull
    @Override
    public SearchViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.search_layout_items,parent,false);
        return new SearchViewModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewModel holder, int position) {
        PersonResponseResults responseResults= results.get(1);
        holder.setPosterImagenView(activity,responseResults.getProfile_path());
        String title = responseResults.getName();
        if (title != null){
            holder.posterTitle.setVisibility(View.VISIBLE);
            holder.posterTitle.setText(title);
        }else {
            holder.posterTitle.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return results.size();
    }
}
