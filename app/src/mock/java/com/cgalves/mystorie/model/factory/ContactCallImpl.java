package com.cgalves.mystorie.model.factory;

import android.content.Context;

import com.cgalves.mystorie.common.abstractcalls.ContactAbstractCall;
import com.cgalves.mystorie.common.model.User;

import org.greenrobot.eventbus.EventBus;

public class ContactCallImpl extends ContactAbstractCall {
    public ContactCallImpl(EventBus bus, Context context) {
        super(bus, context);
    }

    @Override
    public void findInformationUser() {
        User u = new User();
        u.setImage("https://images.pexels.com/photos/937465/pexels-photo-937465.jpeg?auto=compress&cs=tinysrgb&h=350");
        u.setCellphone("+1 55 99 2934 213");
        u.setTwitter("twitter.com/budda");
        u.setFacebook("facebook.com/budda");
        u.setEmail("budda@budda.com");
        post(u, bus);
    }
}
