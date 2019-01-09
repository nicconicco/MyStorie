package com.cgalves.mystorie.common.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

import static com.cgalves.mystorie.common.model.Constants.FIELD_CELLPHONE;
import static com.cgalves.mystorie.common.model.Constants.FIELD_EMAIL;
import static com.cgalves.mystorie.common.model.Constants.FIELD_FACEBOOK;
import static com.cgalves.mystorie.common.model.Constants.FIELD_IMAGE_FACEBOOK;
import static com.cgalves.mystorie.common.model.Constants.FIELD_OWNER;
import static com.cgalves.mystorie.common.model.Constants.FIELD_TWITTER;

@ParseClassName("Contact")
public final class Contact extends ParseObject {

    public Contact() {
        // This constructor is intentionally empty. Nothing special is needed here.
    }

    public Contact(String facebook, String twitter, String cellphone, String email, String image, ParseUser owner) {
        setContactFacebook(facebook);
        setTwitter(twitter);
        setEmail(email);
        setImage(image);
        setCellphone(cellphone);
        setOwner(owner);
    }

    public void setImage(String value) {
        put(FIELD_IMAGE_FACEBOOK, value);
    }

    public String getImage() {
        return getString(FIELD_IMAGE_FACEBOOK);
    }

    public void setEmail(String value) {
        put(FIELD_EMAIL, value);
    }

    public String getEmail() {
        return getString(FIELD_EMAIL);
    }

    public void setContactFacebook(String value) {
        put(FIELD_FACEBOOK, value);
    }

    public String getFacebook() {
        return getString(FIELD_FACEBOOK);
    }

    public void setTwitter(String value) {
        put(FIELD_TWITTER, value);
    }

    public String getTwitter() {
        return getString(FIELD_TWITTER);
    }

    public void setCellphone(String value) {
        put(FIELD_CELLPHONE, value);
    }

    public String getCellphone() {
        return getString(FIELD_CELLPHONE);
    }

    // Get the user for this item
    public ParseUser getUser() {
        return getParseUser(FIELD_OWNER);
    }

    // Associate each item with a user
    public void setOwner(ParseUser user) {
        put(FIELD_OWNER, user);
    }
}
