package com.cgalves.mystorie;

import android.app.Application;

import com.cgalves.mystorie.common.model.Contact;
import com.cgalves.mystorie.common.model.Noticia;
import com.cgalves.mystorie.common.model.Novidade;
import com.parse.Parse;
import com.parse.ParseObject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Scopus on 17/07/18.
 */

public class MyStorieApplication extends Application {

    private static MyStorieApplication isntance = null;
    private String token;

    @Override
    public void onCreate() {
        super.onCreate();

        configParseServer();
        isntance = this;
    }

    private void configParseServer() {
        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.networkInterceptors().add(httpLoggingInterceptor);

        configDatabaseParserClass();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                .clientKey(getString(R.string.back4app_client_key))
                .clientBuilder(builder)
                .server(getString(R.string.back4app_server_url)).build());

    }

    private void configDatabaseParserClass() {
        ParseObject.registerSubclass(Noticia.class);
        ParseObject.registerSubclass(Novidade.class);
        ParseObject.registerSubclass(Contact.class);
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
