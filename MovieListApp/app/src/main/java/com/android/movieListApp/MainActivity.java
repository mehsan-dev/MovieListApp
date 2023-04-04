package com.android.movieListApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.movieListApp.adapter.MovieListAdapter;
import com.android.movieListApp.model.Movie;
import com.android.movieListApp.viewmodel.MovieListViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieListAdapter.ItemClickListener {

    private List<Movie> moviesList;
    private MovieListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        final TextView noResult = findViewById(R.id.noResultTv);
        adapter = new MovieListAdapter(this, moviesList, this);
        recyclerView.setAdapter(adapter);


        MovieListViewModel viewModel = new ViewModelProvider(this).get(MovieListViewModel.class);
        viewModel.getMoviesListObserver().observe(this, movies -> {
            if (movies != null) {
                moviesList = movies;
                adapter.setMovieList(movies);
                noResult.setVisibility(View.GONE);
            } else {
                noResult.setVisibility(View.VISIBLE);
            }
        });
        viewModel.getMoviesList();
    }

    @Override
    public void onMovieClick(Movie movie) {
        Toast.makeText(this, "Clicked Movie Name is : " + movie.getTitle(), Toast.LENGTH_SHORT).show();
    }
}