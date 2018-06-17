package com.example.andreistasevici.popularmovies;

import com.google.gson.annotations.SerializedName;

/**
 * Created by andreistasevici on 5/17/18.
 * Reference: http://www.vogella.com/tutorials/Retrofit/article.html
 */

public class Movie {

    /*
    * Data class for retrofit
    * */

    @SerializedName("poster_path")
    private String mMoviePosterPath;

    @SerializedName("title")
    private String mMovieName;

    @SerializedName("release_date")
    private String mReleaseDate;

    @SerializedName("vote_average")
    private String mVoteAverage;

    @SerializedName("overview")
    private String mPlotSynposis;

    //Contructor
    public Movie(String posterPath, String movieName, String releaseDate,
                 String voteAverage, String plotSynopsis) {
        this.mMoviePosterPath = posterPath;
        this.mMovieName = movieName;
        this.mReleaseDate = releaseDate;
        this.mVoteAverage = voteAverage;
        this.mPlotSynposis = plotSynopsis;

    }

    //Getters for all the fields
    public String getmMoviePosterPath() {
        return mMoviePosterPath;
    }

    public String getMovieName() {
        return mMovieName;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public String getVoteAverage() {
        return mVoteAverage;
    }

    public String getPlotSynposis() {
        return mPlotSynposis;
    }
}
