package com.cgalves.mystorie;

import android.app.Application;

/**
 * Created by Scopus on 17/07/18.
 */

public class MyStorieApplication extends Application {

    private static MyStorieApplication isntance = null;
    private String token;

    @Override
    public void onCreate() {
        super.onCreate();
        isntance = this;
    }

    public static MyStorieApplication getsInstance() {
        if (isntance == null)
            isntance = new MyStorieApplication();
        return isntance;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
