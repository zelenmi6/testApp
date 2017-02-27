package com.example.milan.testapp.json;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Milan on 2/22/2017.
 */

public class ShowEmbedded implements Parcelable{

    @SerializedName("show")
    ShowDetails show;

    public ShowDetails getShowDetails() {
        return show;
    }

    @Override
    public String toString() {
        return "Embedded " + show.getName();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.show, flags);
    }

    public ShowEmbedded() {
    }

    protected ShowEmbedded(Parcel in) {
        this.show = in.readParcelable(ShowDetails.class.getClassLoader());
    }

    public static final Creator<ShowEmbedded> CREATOR = new Creator<ShowEmbedded>() {
        @Override
        public ShowEmbedded createFromParcel(Parcel source) {
            return new ShowEmbedded(source);
        }

        @Override
        public ShowEmbedded[] newArray(int size) {
            return new ShowEmbedded[size];
        }
    };
}
