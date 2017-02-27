package com.example.milan.testapp.json;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Milan on 2/20/2017.
 */

public class TvShow implements Parcelable {
    @SerializedName("id")
    private int id;
    @SerializedName("url")
    private String url;
    @SerializedName("name")
    private String name;
    @SerializedName("season")
    private int season;
    @SerializedName("number")
    private int number;
    @SerializedName("_embedded")
    private ShowEmbedded _embedded;

    public TvShow() {

    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public int getSeason() {
        return season;
    }

    public int getNumber() {
        return number;
    }

    public ShowEmbedded get_embedded() {
        return _embedded;
    }

    @Override
    public String toString() {
        return _embedded.getShowDetails().getName();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.url);
        dest.writeString(this.name);
        dest.writeInt(this.season);
        dest.writeInt(this.number);
        dest.writeParcelable(this._embedded, flags);
    }

    protected TvShow(Parcel in) {
        this.id = in.readInt();
        this.url = in.readString();
        this.name = in.readString();
        this.season = in.readInt();
        this.number = in.readInt();
        this._embedded = in.readParcelable(ShowEmbedded.class.getClassLoader());
    }

    public static final Creator<TvShow> CREATOR = new Creator<TvShow>() {
        @Override
        public TvShow createFromParcel(Parcel source) {
            return new TvShow(source);
        }

        @Override
        public TvShow[] newArray(int size) {
            return new TvShow[size];
        }
    };
}
