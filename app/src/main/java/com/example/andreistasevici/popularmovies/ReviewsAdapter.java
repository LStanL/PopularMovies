package com.example.andreistasevici.popularmovies;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by andreistasevici on 8/15/18.
 */

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewViewHolder> {

    private List<Review> mReviews;

    class ReviewViewHolder extends RecyclerView.ViewHolder {
        private TextView mAuthorReviewTextView;
        private TextView mReviewTextView;

        public ReviewViewHolder(View reviewView) {
            super(reviewView);
            //TODO: figure out which field do i want to display in this list and find by id
        }
    }

    @Override
    public int getItemCount() {
        return mReviews.size();
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //TODO: add layout for review list item and inflate it here
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        //TODO: set text to text views
    }
}
