package com.example.andreistasevici.popularmovies;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by andreistasevici on 5/13/18.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private int movieCount;

    /* constructor for MoviesAdapter */
    public MoviesAdapter(int numberOfMovies) {
        movieCount = numberOfMovies;
    }

    /* creating an adapter inner class for ViewHolder object */
    class MovieViewHolder extends RecyclerView.ViewHolder {

        TextView movieItemView;

        /* creating default constructor */
        public MovieViewHolder(View movieView) {
            super(movieView);
            movieItemView = (TextView) movieView.findViewById(R.id.tv_movie_name);
        }

    }

    /* overriding all the required methods when extending RecyclerView.Adapter */
    @Override
    public int getItemCount() {
        return movieCount;
    }

    //TODO: not completed
    @NonNull
    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    //TODO: not implemeted
    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

    }
}
