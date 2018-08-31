package com.example.andreistasevici.popularmovies.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.andreistasevici.popularmovies.R;
import com.example.andreistasevici.popularmovies.models.Review;

import java.util.List;

/**
 * Created by andreistasevici on 8/15/18.
 */

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewViewHolder> {

    private List<Review> mReviews;

    public ReviewsAdapter(List<Review> reviews) {
        mReviews = reviews;
    }

    class ReviewViewHolder extends RecyclerView.ViewHolder {
        private TextView mAuthorReviewTextView;
        private TextView mReviewTextView;

        public ReviewViewHolder(View reviewView) {
            super(reviewView);
            mAuthorReviewTextView = reviewView.findViewById(R.id.tv_review_author);
            mReviewTextView = reviewView.findViewById(R.id.tv_review);
        }
    }

    @Override
    public int getItemCount() {
        return mReviews.size();
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View reviewView = layoutInflater.inflate(R.layout.reviews_list_item, parent, false);
        ReviewViewHolder reviewViewHolder = new ReviewViewHolder(reviewView);
        return reviewViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Review review = mReviews.get(position);

        TextView authorReviewTextView = holder.mAuthorReviewTextView;
        TextView reviewTextView = holder.mReviewTextView;

        //define strings and set the value in the text views
        String authorReviewText = review.getAuthor() + ":";
        authorReviewTextView.setText(authorReviewText);
        reviewTextView.setText(review.getContent());

    }
}
