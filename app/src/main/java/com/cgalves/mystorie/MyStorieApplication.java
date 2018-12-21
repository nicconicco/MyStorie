package com.cgalves.mystorie;

import android.app.Application;

import com.cgalves.mystorie.common.model.Contact;
import com.cgalves.mystorie.common.model.Noticia;
import com.cgalves.mystorie.common.model.Novidade;
import com.parse.Parse;
import com.parse.ParseObject;

import org.androidannotations.annotations.EApplication;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@EApplication
public class MyStorieApplication extends Application{

    private String token;
    private String name;

    @Override
    public void onCreate() {
        super.onCreate();
        configParseServer();
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

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
