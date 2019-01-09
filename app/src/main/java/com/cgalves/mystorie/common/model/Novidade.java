package com.cgalves.mystorie.common.model;


import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

import static com.cgalves.mystorie.common.model.Constants.FIELD_IMAGE;
import static com.cgalves.mystorie.common.model.Constants.FIELD_NAME;
import static com.cgalves.mystorie.common.model.Constants.FIELD_OWNER;
import static com.cgalves.mystorie.common.model.Constants.FIELD_TEXT;

@ParseClassName("Novidade")
public class Novidade extends ParseObject {

    private String name;
    private String txt;
    private String img;

    public Novidade() {
        // this is proposital
    }

    public void NovidadeConstruct(String name, String txt, String image) {
        setNameInBank(name);
        setTxtInBank(txt);
        setImageInBank(image);
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setNameInBank(String value) {
        put(FIELD_NAME, value);
    }

    public String getNameInBank() {
        return getString(FIELD_NAME);
    }

    public void setTxtInBank(String value) {
        put(FIELD_TEXT, value);
    }

    public String getTxtInBank() {
        return getString(FIELD_TEXT);
    }

    public void setImageInBank(String value) {
        put(FIELD_IMAGE, value);
    }

    public String getImageInBank() {
        return getString(FIELD_IMAGE);
    }

    // Get the user for this item
    public ParseUser getUser()  {
        return getParseUser(FIELD_OWNER);
    }

    // Associate each item with a user
    public void setOwner(ParseUser user) {
        put(FIELD_OWNER, user);
    }
}