package com.cgalves.mystorie.common.model;


import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;
@ParseClassName("Novidade")
public class Novidade extends ParseObject {

    public String name;
    public String txt;
    public String img;
    public static final String FIELD_NAME = "name";
    public static final String FIELD_TEXT = "txt";
    public static final String FIELD_IMAGE = "image";
    public static final String FIELD_OWNER = "owner";

    public Novidade() {
        super();
    }

    public Novidade(String name, String txt, String image) {
        super();
        setNameInBank(name);
        setTxtInBank(txt);
        setImageInBank(image);
    }

    public static String getFieldName() {
        return FIELD_NAME;
    }

    public static String getFieldText() {
        return FIELD_TEXT;
    }

    public static String getFieldImage() {
        return FIELD_IMAGE;
    }

    public static String getFieldOwner() {
        return FIELD_OWNER;
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