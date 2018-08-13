package com.example.andreistasevici.popularmovies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by andreistasevici on 5/28/18.
 * Reference: http://www.vogella.com/tutorials/Retrofit/article.html
 */

public interface TheMovieDBAPI {

    @GET("movie/popular")
    Call<MovieApiResponse> fetchPopularMovies(@Query("api_key") String api_key);

    @GET("movie/top_rated")
    Call<MovieApiResponse> fetchTopRatedMovies(@Query("api_key") String api_key);

    @GET("movie/{movieId}/videos")
    Call<TrailersApiResponse> fetchTrailers(@Path("movieId") String movieId,
                                            @Query("api_key") String api_key);

}
