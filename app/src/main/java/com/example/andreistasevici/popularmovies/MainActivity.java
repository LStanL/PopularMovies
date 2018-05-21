package com.example.andreistasevici.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /* resource used to help with RecyclerView:
     https://guides.codepath.com/android/using-the-recyclerview*/

    ArrayList<Movie> movies;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.rv_movies_list);
        movies = Movie.createMoviesList(20);
        MoviesAdapter moviesAdapter = new MoviesAdapter(movies);

        recyclerView.setAdapter(moviesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
