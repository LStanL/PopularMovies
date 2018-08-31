package com.example.andreistasevici.popularmovies.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by andreistasevici on 8/11/18.
 */

public class Trailer {

    @SerializedName("id")
    private String mId;

    @SerializedName("key")
    private String mKey;

    @SerializedName("name")
    private String mName;

    @SerializedName("type")
    private String mType;

    public Trailer(String id, String key, String name, String type) {
        this.mId = id;
        this.mKey = key;
        this.mName = name;
        this.mType = type;
    }

    // getters for all the fields
    public String getId() {return mId;}

    public String getKey() {return mKey;}

    public String getName() {return mName;}

    public String getType() {return mType;}

}
