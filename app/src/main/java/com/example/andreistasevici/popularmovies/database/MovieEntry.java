package com.example.andreistasevici.popularmovies.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by andreistasevici on 8/26/18.
 */

@Entity(tableName = "movie")
public class MovieEntry {

    @PrimaryKey
    private int id;
    private String name;

    public MovieEntry(int id, String name) {
        this.id = id;
        this.name = name;
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

}
