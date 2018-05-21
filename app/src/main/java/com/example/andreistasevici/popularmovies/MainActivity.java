package com.example.andreistasevici.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.ListItemClickListener {

    /* resource used to help with RecyclerView:
     https://guides.codepath.com/android/using-the-recyclerview*/

    ArrayList<Movie> movies;
    private RecyclerView recyclerView;
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.rv_movies_list);
        movies = Movie.createMoviesList(20);
        MoviesAdapter moviesAdapter = new MoviesAdapter(movies, this);

        recyclerView.setAdapter(moviesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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
