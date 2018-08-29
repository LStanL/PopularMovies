package com.example.andreistasevici.popularmovies;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andreistasevici.popularmovies.database.AppDatabase;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsActivity extends AppCompatActivity implements TrailersAdapter.ListItemClickListener{

    //Fields for all the UI elements present in the activity
    private ImageView mMovieDetailsPoster;
    private TextView mMovieTitle;
    private TextView mReleaseDate;
    private TextView mVoteAverage;
    private TextView mPlotSynopsis;

    private Button mFavoriteButton;

    private RecyclerView trailersRecyclerView;
    private TrailersAdapter trailersAdapter;

    private RecyclerView reviewsRecyclerView;
    private ReviewsAdapter reviewsAdapter;

    public static final String TAG = MovieDetailsActivity.class.getSimpleName();

    //member variable for the Database
    private AppDatabase mDb;

    private boolean isFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        mFavoriteButton = findViewById(R.id.btn_favorite);

        //initialize DB
        mDb = AppDatabase.getInstance(getApplicationContext());

        //getting object that was passed in the intent
        Bundle data = getIntent().getExtras();
        final Movie movie = data.getParcelable("movie");

        //get id for the movie that was selected and pass it in the API call to fetch trailers
        final int movieId = movie.getId();

        //network call to fetch trailers
        TheMovieDBAPI movieDBAPI = RetrofitClientInstance.getRetrofitInstance().create(TheMovieDBAPI.class);
        Call<TrailersApiResponse> callTrailer = movieDBAPI.fetchTrailers(String.valueOf(movieId),
                getResources().getString(R.string.api_key));
        callTrailer.enqueue(new Callback<TrailersApiResponse>() {
            @Override
            public void onResponse(Call<TrailersApiResponse> call, Response<TrailersApiResponse> response) {
                trailersRecyclerView = findViewById(R.id.rv_trailers_list);
                trailersAdapter = new TrailersAdapter(response.body().getTrailers(),
                        MovieDetailsActivity.this);
                trailersRecyclerView.setAdapter(trailersAdapter);
                trailersRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                //add dividers between items in recyclerview
                //https://stackoverflow.com/questions/24618829/how-to-add-dividers-and-spaces-between-items-in-recyclerview
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(trailersRecyclerView.getContext(),
                        new LinearLayoutManager(getApplicationContext()).getOrientation());
                trailersRecyclerView.addItemDecoration(dividerItemDecoration);
            }

            @Override
            public void onFailure(Call<TrailersApiResponse> call, Throwable t) {
                Log.d(TAG, "failure in fetching movie trailers");
            }
        });

        //network call to fetch reviews
        Call<ReviewsApiResponse> callReviews = movieDBAPI.fetchReviews(String.valueOf(movieId), getResources().getString(R.string.api_key));
        callReviews.enqueue(new Callback<ReviewsApiResponse>() {
            @Override
            public void onResponse(Call<ReviewsApiResponse> call, Response<ReviewsApiResponse> response) {
                reviewsRecyclerView = findViewById(R.id.rv_reviews_list);
                reviewsAdapter = new ReviewsAdapter(response.body().getReviews());
                reviewsRecyclerView.setAdapter(reviewsAdapter);
                reviewsRecyclerView.setLayoutManager(new NoScrollLinearLayoutManager(getApplicationContext()));

                //add dividers between items in recyclerview
                //https://stackoverflow.com/questions/24618829/how-to-add-dividers-and-spaces-between-items-in-recyclerview
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(reviewsRecyclerView.getContext(),
                        new LinearLayoutManager(getApplicationContext()).getOrientation());
                reviewsRecyclerView.addItemDecoration(dividerItemDecoration);
            }

            @Override
            public void onFailure(Call<ReviewsApiResponse> call, Throwable t) {
                Log.d(TAG, "failure in fetching movie reviews");
            }
        });

        //set all the UI string for movie details
        setUpUI(movie);

        //query DB by movie ID and see if movie is favorite
        LiveData<Movie> movieLiveData = mDb.movieDao().getMovieById(movieId);
        movieLiveData.observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(@Nullable Movie movie) {
                if (movie == null) {
                    mFavoriteButton.setText(R.string.favorite_button_default_text);
                    mFavoriteButton.setBackgroundColor(Color.parseColor("#d3d3d3"));
                    isFavorite = false;
                } else {
                    mFavoriteButton.setText(R.string.favorite_button_remove_text);
                    mFavoriteButton.setBackgroundColor(Color.parseColor("#f6f05a"));
                    isFavorite = true;
                }
            }
        });


        //set onClickListener for favorites button
        mFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFavorite) {
                    mDb.movieDao().deleteMovie(movie);
                    mFavoriteButton.setText(R.string.favorite_button_default_text);
                    mFavoriteButton.setBackgroundColor(Color.parseColor("#d3d3d3"));
                    Log.d(TAG, "onClick: removed movie from favorites");
                    isFavorite = false;
                } else {
                    mDb.movieDao().insertMovie(movie);
                    mFavoriteButton.setText(R.string.favorite_button_remove_text);
                    mFavoriteButton.setBackgroundColor(Color.parseColor("#f6f05a"));
                    Log.d(TAG, "onClick: added movie to favorites");
                    isFavorite = true;
                }
            }
        });
    }

    /*
    Implementing onListItemClick, open trailer video in youtube app or browser
    https://stackoverflow.com/questions/574195/android-youtube-app-play-video-intent
    */
    @Override
    public void onListItemClick(Trailer trailer) {
        Intent nativeAppIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(Constants.YOUTUBE_APP_URI + trailer.getKey()));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(Constants.YOUTUBE_VIDEO_URL + trailer.getKey()));

        try {
            startActivity(nativeAppIntent);
        } catch (ActivityNotFoundException ex) {
            startActivity(webIntent);
        }
    }

    private void setUpUI(Movie movie) {
        mMovieDetailsPoster = findViewById(R.id.iv_movie_details_poster);
        mMovieTitle = findViewById(R.id.tv_movie_title);
        mReleaseDate = findViewById(R.id.tv_release_date);
        mVoteAverage = findViewById(R.id.tv_vote_average);
        mPlotSynopsis = findViewById(R.id.tv_plot_synopsis);

        //Construct URI, fetch the image and display using Picasso
        String imageUri = Constants.IMAGE_BASE_URI + movie.getMoviePosterPath();
        Picasso
                .with(this)
                .load(imageUri)
                .into(mMovieDetailsPoster);

        //Assign movie details passed in the intent
        mMovieTitle.setText(movie.getMovieName());
        mReleaseDate.setText(movie.getReleaseDate());
        mVoteAverage.setText(movie.getVoteAverage());
        mPlotSynopsis.setText(movie.getPlotSynopsis());

    }
}
