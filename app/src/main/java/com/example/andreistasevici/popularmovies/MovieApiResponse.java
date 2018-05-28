package com.example.andreistasevici.popularmovies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by andreistasevici on 5/27/18.
 */

public class MovieApiResponse {

    @SerializedName("results")
    public List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

}
