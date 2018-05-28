package com.example.andreistasevici.popularmovies;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by andreistasevici on 5/28/18.
 */

public interface TheMovieDBAPI {

    @GET("movie/popular")
    Call<MovieApiResponse> fetchPopularMovies();

    @GET("movie/top_rated")
    Call<MovieApiResponse> fetchTopRatedMovies();
}
