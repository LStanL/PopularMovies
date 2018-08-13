package com.example.andreistasevici.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsActivity extends AppCompatActivity {

    //Fields for all the UI elements present in the activity
    private ImageView mMovieDetailsPoster;
    private TextView mMovieTitle;
    private TextView mReleaseDate;
    private TextView mVoteAverage;
    private TextView mPlotSynopsis;

    private RecyclerView trailersRecyclerView;
    private TrailersAdapter trailersAdapter;

    public static final String TAG = MovieDetailsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        //getting object that was passed in the intent
        Bundle data = getIntent().getExtras();
        Movie movie = data.getParcelable("movie");

        //get id for the movie that was selected and pass it in the API call to fetch trailers
        String movieId = movie.getId();

        //network call to fetch trailers
        TheMovieDBAPI movieDBAPI = RetrofitClientInstance.getRetrofitInstance().create(TheMovieDBAPI.class);
        Call<TrailersApiResponse> call = movieDBAPI.fetchTrailers(movieId,
                getResources().getString(R.string.api_key));
        call.enqueue(new Callback<TrailersApiResponse>() {
            @Override
            public void onResponse(Call<TrailersApiResponse> call, Response<TrailersApiResponse> response) {
                trailersRecyclerView = findViewById(R.id.rv_trailers_list);
                trailersAdapter = new TrailersAdapter(response.body().getTrailers());
                trailersRecyclerView.setAdapter(trailersAdapter);
                trailersRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }

            @Override
            public void onFailure(Call<TrailersApiResponse> call, Throwable t) {
                Log.d(TAG, "failure in fetching movie trailers");
            }
        });

        //set all the UI string for movie details
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
