package com.example.andreistasevici.popularmovies;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.andreistasevici.popularmovies.database.AppDatabase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.ListItemClickListener {

    /*
    * Resource used to understand RecyclerView:
    * https://guides.codepath.com/android/using-the-recyclerview
    * */

    LiveData<List<Movie>> movies;
    private RecyclerView moviesRecyclerView;
    private MoviesAdapter moviesAdapter;
    private static final String TAG = MainActivity.class.getSimpleName();

    private AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //creating DB instance and fetching all movies
        mDb = AppDatabase.getInstance(getApplicationContext());
        movies = mDb.movieDao().loadFavoriteMovies();
        Log.d(TAG, "onCreate: fetched all movies");

        //Making request to fetch popular movies on activity creation
        TheMovieDBAPI movieDBAPI = RetrofitClientInstance.getRetrofitInstance().create(TheMovieDBAPI.class);
        Call<MovieApiResponse> call = movieDBAPI.fetchPopularMovies(getResources().getString(R.string.api_key));
        getMovies(call);

    }

    /*
    Implementing onListItemClick, open a new activity and pass all the details
    */
    @Override
    public void onListItemClick(Movie movie) {
        Intent intent = new Intent(MainActivity.this, MovieDetailsActivity.class);
        intent.putExtra("movie", movie);
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
                getMovies(call);
                break;
            case R.id.search_top_rated:
                movieDBAPI = RetrofitClientInstance.getRetrofitInstance().create(TheMovieDBAPI.class);
                call = movieDBAPI.fetchTopRatedMovies(getResources().getString(R.string.api_key));
                getMovies(call);
                break;
            case R.id.display_favorites:
                moviesRecyclerView = findViewById(R.id.rv_movies_list);
                movies.observe(this, new Observer<List<Movie>>() {
                    @Override
                    public void onChanged(@Nullable List<Movie> movies) {
                        moviesAdapter = new MoviesAdapter(MainActivity.this, movies,
                                MainActivity.this);
                        moviesRecyclerView.setAdapter(moviesAdapter);
                        moviesRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                    }
                });
        }
        return super.onOptionsItemSelected(item);
    }

    private void getMovies(Call<MovieApiResponse> call) {
        call.enqueue(new Callback<MovieApiResponse>() {
            @Override
            public void onResponse(Call<MovieApiResponse> call,
                                   Response<MovieApiResponse> response) {
                moviesRecyclerView = findViewById(R.id.rv_movies_list);
                moviesAdapter = new MoviesAdapter(MainActivity.this, response.body().getMovies(),
                        MainActivity.this);
                moviesRecyclerView.setAdapter(moviesAdapter);
                moviesRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
            }

            @Override
            public void onFailure(Call<MovieApiResponse> call, Throwable t) {
                Log.d(TAG, "failure while fetching movies");
            }
        });
    }
}
