package com.example.milan.testapp.json;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Milan on 2/22/2017.
 */

public class ShowDetails implements Parcelable {
    @SerializedName("name")
    private String name;
    @SerializedName("url")
    private String url;
    @SerializedName("type")
    private String type;
    @SerializedName("language")
    private String language;

    public ShowDetails() {

    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getType() {
        return type;
    }

    public String getLanguage() {
        return language;
    }

    @Override
    public String toString() {
        return name;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.url);
        dest.writeString(this.type);
        dest.writeString(this.language);
    }

    protected ShowDetails(Parcel in) {
        this.name = in.readString();
        this.url = in.readString();
        this.type = in.readString();
        this.language = in.readString();
    }

    public static final Creator<ShowDetails> CREATOR = new Creator<ShowDetails>() {
        @Override
        public ShowDetails createFromParcel(Parcel source) {
            return new ShowDetails(source);
        }

        @Override
        public ShowDetails[] newArray(int size) {
            return new ShowDetails[size];
        }
    };
}
