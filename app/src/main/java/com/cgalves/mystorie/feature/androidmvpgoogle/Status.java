package com.cgalves.mystorie.feature.androidmvpgoogle;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


/**
 * Created by scopus on 26/12/18.
 */

public class Status implements Serializable {

    @SerializedName("description")
    private String description;

    @SerializedName("statusCode")
    private int statusCode;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public boolean isExpired() {
        return statusCode == 3;
    }

    public boolean isUndefined(){
        return statusCode == 2 || statusCode == 4;
    }

    public boolean isExistProfile(){
        return statusCode == 1 || statusCode == 5;
    }

    public boolean isUndefinedWithTerms(){
        return statusCode == 5;
    }
}
