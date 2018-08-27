package com.example.andreistasevici.popularmovies.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by andreistasevici on 8/26/18.
 */

@Entity(tableName = "movie")
public class MovieEntry {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private boolean isFavorite;

    //creating 2 constructors
    //first one can be used by us,
    //but has to be ignored, since room doesn't support multiple constructors
    @Ignore
    public MovieEntry(String name, boolean isFavorite) {
        this.name = name;
        this.isFavorite = isFavorite;
    }

    //second constructor is used by room
    public MovieEntry(int id, String name, boolean isFavorite) {
        this.id = id;
        this.name = name;
        this.isFavorite = isFavorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
