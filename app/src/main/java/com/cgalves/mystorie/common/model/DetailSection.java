package com.cgalves.mystorie.common.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by scopus on 25/07/18.
 */

public class DetailSection implements Parcelable {

    public String name;
    private String txt;
    public String image;

    public DetailSection(String name, String txt, String image) {
        setDetail(name, txt, image);
    }

    private void setDetail(String name, String txt, String image) {
        setName(name);
        setTxt(txt);
        setImage(image);
    }

    protected DetailSection(Parcel in) {
        this.name = in.readString();
        this.txt = in.readString();
        this.image = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.txt);
        dest.writeString(this.image);
    }

    public static final Creator<DetailSection> CREATOR = new Creator<DetailSection>() {
        @Override
        public DetailSection createFromParcel(Parcel source) {
            return new DetailSection(source);
        }

        @Override
        public DetailSection[] newArray(int size) {
            return new DetailSection[size];
        }
    };
}
