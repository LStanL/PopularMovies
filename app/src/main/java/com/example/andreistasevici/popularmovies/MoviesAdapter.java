package com.example.andreistasevici.popularmovies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by andreistasevici on 5/13/18.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private final ListItemClickListener mOnClickListener;
    private int movieCount;
    private List<Movie> mMovies;
    private Context mContext;
    private final static String TAG = MoviesAdapter.class.getSimpleName();
    private final static String IMAGE_BASE_URI = "http://image.tmdb.org/t/p/w185";

    /* adding interface to implement "click on item" functionality */
    public interface ListItemClickListener {
        void onListItemClick(Movie movie);
    }

    /* constructor for MoviesAdapter */
    public MoviesAdapter(Context context, List<Movie> movies, ListItemClickListener onClickListener) {
        this.mContext = context;
        mMovies = movies;
        mOnClickListener = onClickListener;
    }

    /* creating an adapter inner class for ViewHolder object */
    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView movieItemView;
        ImageView movieImageView;

        /* creating default constructor */
        public MovieViewHolder(View movieView) {
            super(movieView);
            movieImageView = movieView.findViewById(R.id.iv_movie_poster);
            movieView.setOnClickListener(this);
        }

        /* override onClick method since we are implementing
        * OnClickListener for this class */
        @Override
        public void onClick(View v) {
            int clickedPostition = getAdapterPosition();
            Movie movie = mMovies.get(clickedPostition);

            mOnClickListener.onListItemClick(movie);
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

        ImageView imageView = movieViewHolder.movieImageView;

        // image uri
        String imageUri = IMAGE_BASE_URI + movie.getmMoviePosterPath();

        Log.d(TAG, "onBindViewHolder: the url for the image will be: " + imageUri);
        Picasso
                .with(mContext)
                .load(imageUri)
                .into(imageView);


    }
}
