package com.example.andreistasevici.popularmovies.layoutmanager;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

/**
 * Created by andreistasevici on 8/18/18.
 * Class to remove scrolling from Reviews RecyclerView
 * Source: https://stackoverflow.com/questions/30531091/how-to-disable-recyclerview-scrolling
 */

public class NoScrollLinearLayoutManager extends LinearLayoutManager {

    public NoScrollLinearLayoutManager(Context context) {
        super(context);
    }

    @Override
    public boolean canScrollVertically() {
        return false;
    }
}
