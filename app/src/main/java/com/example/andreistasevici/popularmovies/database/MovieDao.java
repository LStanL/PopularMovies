package com.example.andreistasevici.popularmovies.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by andreistasevici on 8/26/18.
 */

@Dao
public interface MovieDao {

    @Query("SELECT * FROM movie")
    List<MovieEntry> loadFavoriteMovies();

    @Insert
    void insertMovie(MovieEntry movieEntry);

    @Delete
    void deleteMovie(MovieEntry movieEntry);
}
