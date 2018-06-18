package com.example.andreistasevici.popularmovies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by andreistasevici on 5/13/18.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private final ListItemClickListener mOnClickListener;
    private List<Movie> mMovies;
    private Context mContext;

    /*
    * Adding interface to implement "click on item" functionality
    * */
    public interface ListItemClickListener {
        void onListItemClick(Movie movie);
    }

    /*
    * Constructor for MoviesAdapter
    * */
    public MoviesAdapter(Context context, List<Movie> movies, ListItemClickListener onClickListener) {
        this.mContext = context;
        mMovies = movies;
        mOnClickListener = onClickListener;
    }

    /*
    * Creating an adapter inner class for ViewHolder object
    * */
    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView movieImageView;

        //Creating default constructor
        public MovieViewHolder(View movieView) {
            super(movieView);
            movieImageView = movieView.findViewById(R.id.iv_movie_poster);
            movieView.setOnClickListener(this);
        }

        /*
        * Override onClick method since we are implementing OnClickListener for this class
        * */
        @Override
        public void onClick(View v) {
            int clickedPostition = getAdapterPosition();
            Movie movie = mMovies.get(clickedPostition);

            mOnClickListener.onListItemClick(movie);
        }
    }

    /*
    * Overriding all the required methods when extending RecyclerView.Adapter
    * */
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
        ImageView imageView = movieViewHolder.movieImageView;

        //construct image uri and load fetch it using picasso
        String imageUri = Constants.IMAGE_BASE_URI + movie.getmMoviePosterPath();
        Picasso
                .with(mContext)
                .load(imageUri)
                .into(imageView);
    }
}
