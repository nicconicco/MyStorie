package com.cgalves.mystorie.common.model;


import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Novidade")
public class Novidade extends ParseObject {

    public static final String FIELD_NAME = "name";
    public static final String FIELD_TEXT = "txt";
    public static final String FIELD_IMAGE = "image";
    public static final String FIELD_OWNER = "owner";
    // Ensure that your subclass has a public default constructor

    public Novidade() {
        super();
    }

    public Novidade(String name, String txt, String image) {
        super();
        setName(name);
        setTxt(txt);
        setName(image);
    }

    public void setName(String value) {
        put(FIELD_NAME, value);
    }

    public String getName() {
        return getString(FIELD_NAME);
    }

    public void setTxt(String value) {
        put(FIELD_TEXT, value);
    }

    public String getTxt() {
        return getString(FIELD_TEXT);
    }

    public void setImage(String value) {
        put(FIELD_IMAGE, value);
    }

    public String getImage() {
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