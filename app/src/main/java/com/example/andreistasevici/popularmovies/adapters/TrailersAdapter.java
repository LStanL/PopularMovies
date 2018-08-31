package com.example.andreistasevici.popularmovies.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.URLSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.andreistasevici.popularmovies.R;
import com.example.andreistasevici.popularmovies.models.Trailer;

import java.util.List;

/**
 * Created by andreistasevici on 8/11/18.
 */

public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.TrailerViewHolder> {

    private List<Trailer> mTrailers;
    private final ListItemClickListener mOnClickListener;

    /*
    * Adding interface to implement "click on item" functionality
    * */
    public interface ListItemClickListener {
        void onListItemClick(Trailer trailer);
    }

    //constructor for TrailersAdapter
    public TrailersAdapter(List<Trailer> trailers, ListItemClickListener onClickListener) {
        mTrailers = trailers;
        mOnClickListener = onClickListener;
    }

    //internal class for Trailer ViewHolder
    class TrailerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView trailerTextView;

        public TrailerViewHolder(View trailerView){
            super(trailerView);
            trailerTextView = trailerView.findViewById(R.id.tv_trailer_name);
            trailerTextView.setOnClickListener(this);
        }

        /*
        * Override onClick method since we are implementing OnClickListener for this class
        * */
        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            Trailer trailer = mTrailers.get(clickedPosition);

            mOnClickListener.onListItemClick(trailer);
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
        TextView textView = holder.trailerTextView;


        //add text to text view and make it hyperlink
        //https://twigstechtips.blogspot.com/2013/01/android-make-your-textview-look-like.html
        textView.setText(trailer.getName());
        makeTextViewHyperlink(textView);
    }

    private void makeTextViewHyperlink(TextView tv) {
        SpannableStringBuilder ssb = new SpannableStringBuilder();
        ssb.append(tv.getText());
        ssb.setSpan(new URLSpan("#"), 0, ssb.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(ssb, TextView.BufferType.SPANNABLE);
    }
}
