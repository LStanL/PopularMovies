package com.example.andreistasevici.popularmovies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by andreistasevici on 8/11/18.
 */

public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.TrailerViewHolder> {

    private List<Trailer> mTrailers;

    //constructor for TrailersAdapter
    public TrailersAdapter(List<Trailer> trailers) {
        this.mTrailers = trailers;
    }

    //internal class for Trailer ViewHolder
    class TrailerViewHolder extends RecyclerView.ViewHolder {
        private TextView movieNameTextView;

        public TrailerViewHolder(View trailerView){
            super(trailerView);
            movieNameTextView = trailerView.findViewById(R.id.tv_trailer_name);
        }
    }

    /*
    * Overriding all the required methods when extending RecyclerView.Adapter
    * */

    @Override
    public int getItemCount() {
        return mTrailers.size();
    }

    @NonNull
    @Override
    public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View trailerView = layoutInflater.inflate(R.layout.trailers_list_item, parent, false);
        TrailerViewHolder trailerViewHolder = new TrailerViewHolder(trailerView);
        return trailerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerViewHolder holder, int position) {
        Trailer trailer = mTrailers.get(position);
        TextView textView = holder.movieNameTextView;

        //add text to text view
        textView.setText(trailer.getName());
    }
}
