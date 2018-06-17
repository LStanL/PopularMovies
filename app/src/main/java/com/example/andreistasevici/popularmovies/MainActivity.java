package com.example.andreistasevici.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.ListItemClickListener {

    /*
    Resource used to help with RecyclerView:
    https://guides.codepath.com/android/using-the-recyclerview
    */

    private RecyclerView recyclerView;
    private MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Making request to fetch popular movies on activity creation
        TheMovieDBAPI movieDBAPI = RetrofitClientInstance.getRetrofitInstance().create(TheMovieDBAPI.class);
        Call<MovieApiResponse> call = movieDBAPI.fetchPopularMovies(getResources().getString(R.string.api_key));
        call.enqueue(new Callback<MovieApiResponse>() {
            @Override
            public void onResponse(Call<MovieApiResponse> call,
                                   Response<MovieApiResponse> response) {
                recyclerView = findViewById(R.id.rv_movies_list);
                moviesAdapter = new MoviesAdapter(MainActivity.this, response.body().getMovies(), MainActivity.this);
                recyclerView.setAdapter(moviesAdapter);
                recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

            }

            @Override
            public void onFailure(Call<MovieApiResponse> call, Throwable t) {

            }
        });

    }

    /*
    Implementing onListItemClick, open a new activity and pass all the details
    */
    @Override
    public void onListItemClick(Movie movie) {
        Intent intent = new Intent(MainActivity.this, MovieDetailsActivity.class);
        intent.putExtra("MOVIE_POSTER_PATH", movie.getmMoviePosterPath());
        intent.putExtra("MOVIE_NAME", movie.getMovieName());
        intent.putExtra("MOVIE_RELEASE_DATE", movie.getReleaseDate());
        intent.putExtra("MOVIE_VOTE_AVERAGE", movie.getVoteAverage());
        intent.putExtra("MOVIE_PLOT_SYNOPSIS", movie.getPlotSynposis());
        startActivity(intent);
    }

    /*
    Override onCreateOptionsMenu to create menu
    */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sort, menu);
        return true;
    }

    /*
    Override onOptionsItemSelected to take proper action when menu item selected
    */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemSelected = item.getItemId();

        TheMovieDBAPI movieDBAPI = null;
        Call<MovieApiResponse> call = null;

        //Check which menu item was selected
        switch (itemSelected) {
            case R.id.search_popular:
                movieDBAPI = RetrofitClientInstance.getRetrofitInstance().create(TheMovieDBAPI.class);
                call = movieDBAPI.fetchPopularMovies(getResources().getString(R.string.api_key));
                call.enqueue(new Callback<MovieApiResponse>() {
                    @Override
                    public void onResponse(Call<MovieApiResponse> call,
                                           Response<MovieApiResponse> response) {
                        recyclerView = findViewById(R.id.rv_movies_list);
                        moviesAdapter = new MoviesAdapter(MainActivity.this, response.body().getMovies(), MainActivity.this);
                        recyclerView.setAdapter(moviesAdapter);
                        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

                    }

                    @Override
                    public void onFailure(Call<MovieApiResponse> call, Throwable t) {

                    }
                });
            case R.id.search_top_rated:
                movieDBAPI = RetrofitClientInstance.getRetrofitInstance().create(TheMovieDBAPI.class);
                call = movieDBAPI.fetchTopRatedMovies(getResources().getString(R.string.api_key));
                call.enqueue(new Callback<MovieApiResponse>() {
                    @Override
                    public void onResponse(Call<MovieApiResponse> call,
                                           Response<MovieApiResponse> response) {
                        recyclerView = findViewById(R.id.rv_movies_list);
                        moviesAdapter = new MoviesAdapter(MainActivity.this, response.body().getMovies(), MainActivity.this);
                        recyclerView.setAdapter(moviesAdapter);
                        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

                    }

                    @Override
                    public void onFailure(Call<MovieApiResponse> call, Throwable t) {

                    }
                });

        }
        return super.onOptionsItemSelected(item);
    }
}
