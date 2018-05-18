package com.example.andreistasevici.popularmovies;

import java.util.ArrayList;

/**
 * Created by andreistasevici on 5/17/18.
 */

public class Movie {

    private String mMovieName;
    private static int lastMovieId;

    /* contructor */
    public Movie(String movieName) {
        this.mMovieName = movieName;
    }

    public String getmMovieName() {
        return mMovieName;
    }

    /* method to generate ArrayList of movies */
    public static ArrayList<Movie> createMoviesList(int movieCount) {
        ArrayList<Movie> movies = new ArrayList<>();

        for (int i = 1; i < movieCount; i++) {
            lastMovieId++;
            movies.add(new Movie("Movie " + String.valueOf(lastMovieId)));
        }

        return movies;
    }
}
