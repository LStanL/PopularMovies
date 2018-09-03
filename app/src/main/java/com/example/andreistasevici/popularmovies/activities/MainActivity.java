package com.example.andreistasevici.popularmovies.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.andreistasevici.popularmovies.models.MainViewModel;
import com.example.andreistasevici.popularmovies.models.Movie;
import com.example.andreistasevici.popularmovies.network.MovieApiResponse;
import com.example.andreistasevici.popularmovies.adapters.MoviesAdapter;
import com.example.andreistasevici.popularmovies.R;
import com.example.andreistasevici.popularmovies.network.RetrofitClientInstance;
import com.example.andreistasevici.popularmovies.network.TheMovieDBAPI;
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

    private RecyclerView moviesRecyclerView;
    private MoviesAdapter moviesAdapter;
    private static final String TAG = MainActivity.class.getSimpleName();
    private int itemSelected;

    private AppDatabase mDb;

    private SharedPreferences sharedPreferences;

    private GridLayoutManager mLayoutManager;
    Parcelable mListState;

    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TheMovieDBAPI movieDBAPI;
        Call<MovieApiResponse> call;

        //creating DB instance
        mDb = AppDatabase.getInstance(getApplicationContext());

        //getting viewModel
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        //setting layout manager
        mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);

        sharedPreferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        itemSelected = sharedPreferences.getInt("menu_item_selected", -1);

        if (itemSelected == -1) {
            Log.d(TAG, "onCreate: mydebuglog itemSelected is " + itemSelected + " so loading popular movies");
            //Making request to fetch popular movies on new activity creation
            movieDBAPI = RetrofitClientInstance.getRetrofitInstance().create(TheMovieDBAPI.class);
            call = movieDBAPI.fetchPopularMovies(getResources().getString(R.string.api_key));
            getMovies(call);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("menu_item_selected", itemSelected);
            editor.commit();
        } else {
            //populating adapter depending on which menu item was selected
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
                    viewModel.getMoviesLiveData().observe(this, new Observer<List<Movie>>() {
                        @Override
                        public void onChanged(@Nullable List<Movie> movies) {
                            moviesAdapter = new MoviesAdapter(MainActivity.this, movies,
                                    MainActivity.this);
                            moviesRecyclerView.setAdapter(moviesAdapter);
                            moviesRecyclerView.setLayoutManager(mLayoutManager);
                        }
                    });
                    break;
            }
        }
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
        itemSelected = item.getItemId();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("menu_item_selected", itemSelected);
        editor.commit();

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
                viewModel.getMoviesLiveData().observe(this, new Observer<List<Movie>>() {
                    @Override
                    public void onChanged(@Nullable List<Movie> movies) {
                        moviesAdapter = new MoviesAdapter(MainActivity.this, movies,
                                MainActivity.this);
                        moviesRecyclerView.setAdapter(moviesAdapter);
                        moviesRecyclerView.setLayoutManager(mLayoutManager);
                    }
                });
                break;
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
                moviesRecyclerView.setLayoutManager(mLayoutManager);
            }

            @Override
            public void onFailure(Call<MovieApiResponse> call, Throwable t) {
                Log.d(TAG, "failure while fetching movies");
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        mListState = mLayoutManager.onSaveInstanceState();
        outState.putParcelable("LIST_STATE_KEY", mListState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null) {
            mListState = savedInstanceState.getParcelable("LIST_STATE_KEY");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mListState != null) {
            mLayoutManager.onRestoreInstanceState(mListState);
        }
    }
}
