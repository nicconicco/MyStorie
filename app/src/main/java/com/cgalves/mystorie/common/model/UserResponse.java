package com.cgalves.mystorie.common.model;

public class UserResponse {
    private User user;
    private Error error;

    public UserResponse(User user) {
        this.user = user;
    }

    public UserResponse(Error error) {
        this.error = error;
    }

    public User getUser() {
        return user;
    }

    public Error getError() {
        return error;
    }

    public boolean isSuccess(){
        return user != null;
    }
}
