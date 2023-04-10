package com.example.movieplus.Interfaces;

import com.example.movieplus.Model.MovieResponse;
import com.example.movieplus.Model.PersonResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {

    //Api
    @GET("search/movie")
    Call<MovieResponse> getMoviesByQuery(@Query("api_key") String api_key, @Query("query") String query) ;


    @GET("search/person")
    Call<PersonResponse> getPersonsByQuery(@Query("api_key") String api_key, @Query("query") String query) ;

}
