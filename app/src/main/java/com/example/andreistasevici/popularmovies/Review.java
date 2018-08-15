package com.example.andreistasevici.popularmovies;

import com.google.gson.annotations.SerializedName;

/**
 * Created by andreistasevici on 8/14/18.
 */

public class Review {

    /*
    * Data class for retrofit
    * */

    @SerializedName("author")
    private String mAuthor;

    @SerializedName("content")
    private String mContent;

    @SerializedName("id")
    private String mId;

    @SerializedName("url")
    private String mUrl;

    public Review(String author, String content, String id, String url) {
        this.mAuthor = author;
        this.mContent = content;
        this.mId = id;
        this.mUrl = url;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getContent() {
        return mContent;
    }

    public String getId() {
        return mId;
    }

    public String getUrl() {
        return mUrl;
    }
}
