package com.mystorie.cgalves.mystorie;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;


/**
 * Created by Scopus on 09/07/18.
 */

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @AfterViews
    void init() {
        Log.d("\n\n\n\n\n\n\n\n"+TAG, this.getString(R.string.state_after_view)+"\n\n\n\n\n");
    }

    @Click(R.id.btn_two)
    void opa(){
        Log.d("\n\n\n\n\n\n\n\n\n"+TAG, this.getString(R.string.click)+"\n\n\n\n\n");
        Toast.makeText(this, "opa", Toast.LENGTH_LONG).show();
    }
}
