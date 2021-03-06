package com.example.andreistasevici.popularmovies.network;

import com.example.andreistasevici.popularmovies.models.Review;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by andreistasevici on 8/14/18.
 */

public class ReviewsApiResponse {

    @SerializedName("results")
    public List<Review> reviews;

    public List<Review> getReviews() {return reviews;}
}
