package com.example.andreistasevici.popularmovies;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by andreistasevici on 5/17/18.
 * References:
 * http://www.vogella.com/tutorials/Retrofit/article.html
 * http://www.vogella.com/tutorials/AndroidParcelable/article.html
 */

public class Movie implements Parcelable{

    /*
    * Data class for retrofit
    * */

    @SerializedName("poster_path")
    private String mMoviePosterPath;

    @SerializedName("title")
    private String mMovieName;

    @SerializedName("release_date")
    private String mReleaseDate;

    @SerializedName("vote_average")
    private String mVoteAverage;

    @SerializedName("overview")
    private String mPlotSynposis;

    //Contructor
    public Movie(String posterPath, String movieName, String releaseDate,
                 String voteAverage, String plotSynopsis) {
        this.mMoviePosterPath = posterPath;
        this.mMovieName = movieName;
        this.mReleaseDate = releaseDate;
        this.mVoteAverage = voteAverage;
        this.mPlotSynposis = plotSynopsis;

    }

    //Getters for all the fields
    public String getmMoviePosterPath() {
        return mMoviePosterPath;
    }

    public String getMovieName() {
        return mMovieName;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public String getVoteAverage() {
        return mVoteAverage;
    }

    public String getPlotSynposis() {
        return mPlotSynposis;
    }


    //Parcelling part
    public Movie(Parcel in) {
        this.mMoviePosterPath = in.readString();
        this.mMovieName = in.readString();
        this.mReleaseDate = in.readString();
        this.mVoteAverage = in.readString();
        this.mPlotSynposis = in.readString();
    }

    /*overriding describeContents and writeToParcel which is required when
    * implementing Parcelable*/
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mMoviePosterPath);
        dest.writeString(this.mMovieName);
        dest.writeString(this.mReleaseDate);
        dest.writeString(this.mVoteAverage);
        dest.writeString(this.mPlotSynposis);
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
