package com.example.andreistasevici.popularmovies;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by andreistasevici on 5/27/18.
 */

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
