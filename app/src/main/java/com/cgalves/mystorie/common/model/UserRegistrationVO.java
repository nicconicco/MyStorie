package com.cgalves.mystorie.common.model;

/**
 * Created by scopus on 07/08/18.
 */

public class UserRegistrationVO  {
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user = new User();
}
