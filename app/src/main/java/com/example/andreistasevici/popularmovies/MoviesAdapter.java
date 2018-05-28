package com.example.andreistasevici.popularmovies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by andreistasevici on 5/13/18.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private final ListItemClickListener mOnClickListener;
    private int movieCount;
    private List<Movie> mMovies;

    /* adding interface to implement "click on item" functionality */
    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    /* constructor for MoviesAdapter */
    public MoviesAdapter(List<Movie> movies, ListItemClickListener onClickListener) {
        mMovies = movies;
        mOnClickListener = onClickListener;
    }

    /* creating an adapter inner class for ViewHolder object */
    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView movieItemView;

        /* creating default constructor */
        public MovieViewHolder(View movieView) {
            super(movieView);
            movieItemView = (TextView) movieView.findViewById(R.id.tv_movie_name);
            movieView.setOnClickListener(this);
        }

        /* override onClick method since we are implementing
        * OnClickListener for this class */
        @Override
        public void onClick(View v) {
            int clickedPostition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPostition);
        }
    }

    /* overriding all the required methods when extending RecyclerView.Adapter */
    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    @NonNull
    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View movieView = layoutInflater.inflate(R.layout.movies_list_item, parent, false);
        MovieViewHolder movieViewHolder = new MovieViewHolder(movieView);
        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int position) {
        Movie movie = mMovies.get(position);

        TextView textView = movieViewHolder.movieItemView;
        textView.setText(movie.getMovieName());
    }
}
