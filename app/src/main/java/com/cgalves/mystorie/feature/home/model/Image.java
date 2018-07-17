package com.cgalves.mystorie.feature.home.model;

import android.graphics.drawable.Drawable;

/**
 * Created by Scopus on 17/07/18.
 */

public class Image {

    private Drawable image;
    private String title;

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
