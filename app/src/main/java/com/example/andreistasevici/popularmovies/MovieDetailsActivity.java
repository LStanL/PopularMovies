package com.example.andreistasevici.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {

    //Fields for all the UI elements present in the activity
    private ImageView mMovieDetailsPoster;
    private TextView mMovieTitle;
    private TextView mReleaseDate;
    private TextView mVoteAverage;
    private TextView mPlotSynopsis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        //getting object that was passed in the intent
        Bundle data = getIntent().getExtras();
        Movie movie = data.getParcelable("movie");
        setUpUI(movie);
    }

    private void setUpUI(Movie movie) {
        mMovieDetailsPoster = findViewById(R.id.iv_movie_details_poster);
        mMovieTitle = findViewById(R.id.tv_movie_title);
        mReleaseDate = findViewById(R.id.tv_release_date);
        mVoteAverage = findViewById(R.id.tv_vote_average);
        mPlotSynopsis = findViewById(R.id.tv_plot_synopsis);

        //Construct URI, fetch the image and display using Picasso
        String imageUri = Constants.IMAGE_BASE_URI + movie.getmMoviePosterPath();
        Picasso
                .with(this)
                .load(imageUri)
                .into(mMovieDetailsPoster);

        //Assign movie details passed in the intent
        mMovieTitle.setText(movie.getMovieName());
        mReleaseDate.setText(movie.getReleaseDate());
        mVoteAverage.setText(movie.getVoteAverage());
        mPlotSynopsis.setText(movie.getPlotSynposis());

    }
}
