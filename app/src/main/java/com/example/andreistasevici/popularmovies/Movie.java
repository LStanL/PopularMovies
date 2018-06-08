package com.example.andreistasevici.popularmovies;

import com.google.gson.annotations.SerializedName;

/**
 * Created by andreistasevici on 5/17/18.
 */

public class Movie {

    @SerializedName("title")
    private String mMovieName;

    @SerializedName("poster_path")
    private String mMoviePosterPath;

    /* contructor */
    public Movie(String movieName, String posterPath) {
        this.mMovieName = movieName;
        this.mMoviePosterPath = posterPath;
    }

    public String getMovieName() {
        return mMovieName;
    }

    public void setMovieName(String movieName) {
        this.mMovieName = movieName;
    }

    public String getmMoviePosterPath() {
        return mMoviePosterPath;
    }

    public void setmMoviePosterPath(String mMoviePosterPath) {
        this.mMoviePosterPath = mMoviePosterPath;
    }
}
