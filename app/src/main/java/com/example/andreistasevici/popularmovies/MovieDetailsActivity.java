package com.example.andreistasevici.popularmovies;

import android.content.Intent;
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

    //Base URI used for images
    private final static String IMAGE_BASE_URI = "http://image.tmdb.org/t/p/w185";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        mMovieDetailsPoster = findViewById(R.id.iv_movie_details_poster);
        mMovieTitle = findViewById(R.id.tv_movie_title);
        mReleaseDate = findViewById(R.id.tv_release_date);
        mVoteAverage = findViewById(R.id.tv_vote_average);
        mPlotSynopsis = findViewById(R.id.tv_plot_synopsis);

        //Get the intent that started this activity
        Intent intentThatStartedThisActivity = getIntent();

        //Construct URI, fetch the image and display using Picasso
        String imageUri = IMAGE_BASE_URI +
                intentThatStartedThisActivity.getStringExtra("MOVIE_POSTER_PATH");
        Picasso
                .with(this)
                .load(imageUri)
                .into(mMovieDetailsPoster);

        //Assign movie details passed in the intent
        mMovieTitle.setText(intentThatStartedThisActivity.getStringExtra("MOVIE_NAME"));
        mReleaseDate.setText(intentThatStartedThisActivity.getStringExtra("MOVIE_RELEASE_DATE"));
        mVoteAverage.setText(intentThatStartedThisActivity.getStringExtra("MOVIE_VOTE_AVERAGE"));
        mPlotSynopsis.setText(intentThatStartedThisActivity.getStringExtra("MOVIE_PLOT_SYNOPSIS"));
    }
}
