package com.example.andreistasevici.popularmovies;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by andreistasevici on 8/11/18.
 */

public class Trailer implements Parcelable{

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

   //Parcelling part
    public Trailer(Parcel in) {
        this.mId = in.readString();
        this.mKey = in.readString();
        this.mName = in.readString();
        this.mType = in.readString();
    }

    /*overriding describeContents and writeToParcel which is required when
    * implementing Parcelable*/
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mId);
        dest.writeString(this.mKey);
        dest.writeString(this.mName);
        dest.writeString(this.mType);
    }

    public static final Parcelable.Creator<Trailer> CREATOR = new Parcelable.Creator<Trailer>() {
        public Trailer createFromParcel(Parcel in) {
            return new Trailer(in);
        }

        public Trailer[] newArray(int size) {
            return new Trailer[size];
        }
    };
}
