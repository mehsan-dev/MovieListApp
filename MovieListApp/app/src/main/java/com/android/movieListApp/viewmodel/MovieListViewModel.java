package com.android.movieListApp.viewmodel;


import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.movieListApp.model.Movie;
import com.android.movieListApp.network.APIService;
import com.android.movieListApp.network.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListViewModel extends ViewModel {

    private final MutableLiveData<List<Movie>> moviesList;

    public MovieListViewModel() {
        moviesList = new MutableLiveData<>();
    }

    public MutableLiveData<List<Movie>> getMoviesListObserver() {
        return moviesList;

    }

    public void getMoviesList() {
        APIService apiService = RetrofitInstance.getRetroClient().create(APIService.class);
        Call<List<Movie>> call = apiService.moviesList();
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(@NonNull Call<List<Movie>> call, @NonNull Response<List<Movie>> response) {
                moviesList.postValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Movie>> call, @NonNull Throwable t) {
                moviesList.postValue(null);
            }
        });
    }
}
