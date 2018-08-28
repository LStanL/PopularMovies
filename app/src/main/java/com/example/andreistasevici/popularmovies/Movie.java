package com.example.andreistasevici.popularmovies;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by andreistasevici on 5/17/18.
 * References:
 * http://www.vogella.com/tutorials/Retrofit/article.html
 * http://www.vogella.com/tutorials/AndroidParcelable/article.html
 */

@Entity(tableName = "movie")
public class Movie implements Parcelable{

    /*
    * Data class for retrofit
    * */

    @NonNull
    @PrimaryKey
    @SerializedName("id")
    private int id;

    @SerializedName("poster_path")
    private String moviePosterPath;

    @SerializedName("title")
    private String movieName;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("vote_average")
    private String voteAverage;

    @SerializedName("overview")
    private String plotSynopsis;

    //Contructor


    public Movie(int id, String moviePosterPath, String movieName, String releaseDate,
                 String voteAverage, String plotSynopsis) {
        this.id = id;
        this.moviePosterPath = moviePosterPath;
        this.movieName = movieName;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
        this.plotSynopsis = plotSynopsis;
    }

    //Getters for all the fields
    public String getMoviePosterPath() {
        return moviePosterPath;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public String getPlotSynopsis() {
        return plotSynopsis;
    }

    public int getId() { return id; }


    //Parcelling part
    public Movie(Parcel in) {
        this.moviePosterPath = in.readString();
        this.movieName = in.readString();
        this.releaseDate = in.readString();
        this.voteAverage = in.readString();
        this.plotSynopsis = in.readString();
        this.id = in.readInt();
    }

    /*overriding describeContents and writeToParcel which is required when
    * implementing Parcelable*/
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.moviePosterPath);
        dest.writeString(this.movieName);
        dest.writeString(this.releaseDate);
        dest.writeString(this.voteAverage);
        dest.writeString(this.plotSynopsis);
        dest.writeInt(this.id);
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
