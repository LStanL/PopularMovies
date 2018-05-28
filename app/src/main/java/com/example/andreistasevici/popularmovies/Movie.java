package com.example.andreistasevici.popularmovies;

import com.google.gson.annotations.SerializedName;

/**
 * Created by andreistasevici on 5/17/18.
 */

public class Movie {

    @SerializedName("title")
    private String mMovieName;
    //private static int lastMovieId;

    /* contructor */
    public Movie(String movieName) {
        this.mMovieName = movieName;
    }

    public String getMovieName() {
        return mMovieName;
    }

    public void setMovieName(String movieName) {
        this.mMovieName = movieName;
    }

}
