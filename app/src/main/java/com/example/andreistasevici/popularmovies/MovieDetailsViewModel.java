package com.example.andreistasevici.popularmovies;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.andreistasevici.popularmovies.database.AppDatabase;

/**
 * Created by andreistasevici on 8/29/18.
 */

public class MovieDetailsViewModel extends ViewModel {

    private LiveData<Movie> movieLiveData;

    public MovieDetailsViewModel(AppDatabase database, int movieId) {
        movieLiveData = database.movieDao().getMovieById(movieId);
    }

    public LiveData<Movie> getMovieLiveData() {
        return movieLiveData;
    }
}
