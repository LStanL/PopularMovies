package com.example.andreistasevici.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Making request to fetch popular movies on activity creation
        Log.d(TAG, "onCreate: fetching most popular movies");
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
                Log.d(TAG, "onCreate:onResponse: fetching most popular movies completed");
            }

            @Override
            public void onFailure(Call<MovieApiResponse> call, Throwable t) {
                Log.d(TAG, "onCreate:onFailure: failure in fetching most popular movies");
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
        Log.d(TAG, "onOptionsItemSelected: itemSelected = " + itemSelected);

        TheMovieDBAPI movieDBAPI = null;
        Call<MovieApiResponse> call = null;

        //Check which menu item was selected
        switch (itemSelected) {
            case R.id.search_popular:
                Log.d(TAG, "onOptionsItemSelected: fetching most popular movies since itemSelected is = " + R.id.search_popular);
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
                        Log.d(TAG, "onOptionsItemSelected:onResponse: fetching most popular movies completed");
                    }

                    @Override
                    public void onFailure(Call<MovieApiResponse> call, Throwable t) {
                        Log.d(TAG, "onOptionsItemSelected:onFailure: failure in fetching most popular movies");
                    }
                });
                break;
            case R.id.search_top_rated:
                Log.d(TAG, "onOptionsItemSelected: fetching top rated movies since itemSelected is = " + R.id.search_top_rated);
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
                        Log.d(TAG, "onOptionsItemSelected:onResponse: fetching top rated movies completed");
                    }

                    @Override
                    public void onFailure(Call<MovieApiResponse> call, Throwable t) {
                        Log.d(TAG, "onOptionsItemSelected:onFailure: failure in fetching top rated movies");
                    }
                });
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
