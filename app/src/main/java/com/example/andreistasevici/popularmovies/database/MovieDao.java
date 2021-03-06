package com.example.andreistasevici.popularmovies.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.andreistasevici.popularmovies.models.Movie;

import java.util.List;

/**
 * Created by andreistasevici on 8/26/18.
 */

@Dao
public interface MovieDao {

    @Query("SELECT * FROM movie")
    LiveData<List<Movie>> loadFavoriteMovies();

    @Query("SELECT * FROM movie WHERE id = :id")
    LiveData<Movie> getMovieById(int id);

    @Insert
    void insertMovie(Movie movie);

    @Delete
    void deleteMovie(Movie movie);
}
