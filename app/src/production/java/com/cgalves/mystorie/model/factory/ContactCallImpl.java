package com.cgalves.mystorie.model.factory;

import android.content.Context;
import android.util.Log;

import com.cgalves.mystorie.R;
import com.cgalves.mystorie.common.abstractcalls.ContactAbstractCall;
import com.cgalves.mystorie.common.model.Contact;
import com.cgalves.mystorie.common.model.User;
import com.cgalves.mystorie.common.utils.GsonUtility;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class ContactCallImpl extends ContactAbstractCall {
    public ContactCallImpl(EventBus bus, Context context) {
        super(bus, context);
    }

    @Override
    public void findInformationUser() {
        User user = new User();

        ParseQuery<Contact> query = ParseQuery.getQuery(Contact.class);
        query.whereEqualTo("owner", ParseUser.getCurrentUser());
        query.findInBackground((objects, e) -> {
            if (e == null) {
                if (objects.size() == 0) {
                    configFirstContactInformation();
                    Log.d(context.getString(R.string.tag_next_flow), "Object Return:\n" + GsonUtils.objectToJson(objects));
                } else {
                    ArrayList<Contact> list = (ArrayList<Contact>) objects;
                    Contact contact = objects.get(0);
                    String json = GsonUtils.objectToJson(objects);

                    user.setEmail(contact.getEmail());
                    user.setFacebook(contact.getFacebook());
                    user.setTwitter(contact.getTwitter());
                    user.setCellphone(contact.getCellphone());
                    user.setImage(contact.getImage());
                }
                Log.d(context.getString(R.string.tag_next_flow), "ACHOU" + GsonUtils.objectToJson(objects));
            } else {
                Log.d(context.getString(R.string.tag_next_flow), "Error: " + e.getMessage());
            }
        });
        post(user, bus);
    }

    private void configFirstContactInformation() {
        Contact contact = new Contact(context.getString(R.string.facebook_miss_information), context.getString(R.string.twitter_miss_information),
                context.getString(R.string.cellphone_miss_information), context.getString(R.string.email_miss_information), "https://cdn1.iconfinder.com/data/icons/social-messaging-productivity-1-1/128/gender-male2-512.png", ParseUser.getCurrentUser());
        contact.setOwner(ParseUser.getCurrentUser());
        contact.saveInBackground();
    }
}
