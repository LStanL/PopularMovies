package com.example.andreistasevici.popularmovies.models;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.andreistasevici.popularmovies.database.AppDatabase;

import java.util.List;

/**
 * Created by andreistasevici on 8/29/18.
 */

public class MainViewModel extends AndroidViewModel {

    private LiveData<List<Movie>> moviedLiveData;

    private static final String TAG = MainViewModel.class.getSimpleName();

    //constructor
    public MainViewModel(@NonNull Application application) {
        super(application);
        AppDatabase database = AppDatabase.getInstance(this.getApplication());
        moviedLiveData = database.movieDao().loadFavoriteMovies();
        Log.d(TAG, "MainViewModel: fetchingallmovies");
    }

    public LiveData<List<Movie>> getMoviesLiveData() {
        return moviedLiveData;
    }
}
