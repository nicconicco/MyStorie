package com.cgalves.mystorie.common.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

import static com.cgalves.mystorie.common.model.Novidade.FIELD_OWNER;

@ParseClassName("Contact")
public class Contact extends ParseObject {

    private static final String FIELD_FACEBOOK = "facebookUrl";
    private static final String FIELD_TWITTER = "twitterUrl";
    private static final String FIELD_CELLPHONE = "cellphone";

    public Contact() {
        super();
    }

    public Contact(String facebook, String twitter, String cellphone, ParseUser owner) {
        super();
        setFacebook(facebook);
        setTwitter(twitter);
        setCellphone(cellphone);
        setOwner(owner);
    }

    public void setFacebook(String value) {
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
    public ParseUser getUser()  {
        return getParseUser(FIELD_OWNER);
    }

    // Associate each item with a user
    public void setOwner(ParseUser user) {
        put(FIELD_OWNER, user);
    }
}
