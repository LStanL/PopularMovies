package com.example.andreistasevici.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.ListItemClickListener {

    /* resource used to help with RecyclerView:
     https://guides.codepath.com/android/using-the-recyclerview*/

    private RecyclerView recyclerView;
    private Toast mToast;
    private MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    /* implementing onListItemClick , for now let's just show a Toast message when clicked*/
    @Override
    public void onListItemClick(int clickedItemIndex) {
        if (mToast != null) {
            mToast.cancel();
        }

        String toastMessage = "Item # " + String.valueOf(clickedItemIndex + 1) + " clicked";
        mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);
        mToast.show();
    }

    /*Override onCreateOptionsMenu to create menu*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sort, menu);
        return true;
    }

    /*Override onOptionsItemSelected to take proper action when menu item selected*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemSelected = item.getItemId();

        TheMovieDBAPI movieDBAPI = null;
        Call<MovieApiResponse> call = null;

        /*Check which menu item was selected*/
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
