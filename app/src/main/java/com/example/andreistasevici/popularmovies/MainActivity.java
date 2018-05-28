package com.example.andreistasevici.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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

        MovieDBAPI movieDBAPI = RetrofitClientInstance.getRetrofitInstance().create(MovieDBAPI.class);
        Call<MovieApiResponse> call = movieDBAPI.fetchPopularMovies();
        call.enqueue(new Callback<MovieApiResponse>() {
            @Override
            public void onResponse(Call<MovieApiResponse> call,
                                   Response<MovieApiResponse> response) {
                recyclerView = findViewById(R.id.rv_movies_list);
                moviesAdapter = new MoviesAdapter(response.body().getMovies(), MainActivity.this);
                recyclerView.setAdapter(moviesAdapter);
                recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));

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
}
