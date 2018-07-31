package com.cgalves.mystorie.common.model;

/**
 * Created by scopus on 31/07/18.
 */

public class User {

    private String name;

    public User(String name) {
        this.name = name;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
