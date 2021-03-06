package com.example.andreistasevici.popularmovies.network;

import com.example.andreistasevici.popularmovies.models.Trailer;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by andreistasevici on 8/11/18.
 */

public class TrailersApiResponse {

    @SerializedName("results")
    public List<Trailer> trailers;

    public List<Trailer> getTrailers() {return trailers; }
}
