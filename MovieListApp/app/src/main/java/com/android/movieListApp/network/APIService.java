package com.android.movieListApp.network;


import com.android.movieListApp.model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    @GET("volley_array.json")
    Call<List<Movie>> moviesList();
}
