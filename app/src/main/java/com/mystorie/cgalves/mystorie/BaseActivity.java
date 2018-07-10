package com.mystorie.cgalves.mystorie;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by Scopus on 10/07/18.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(getString(R.string.tag_next_flow),"Flavor: " + Constants.type + " : Im in Activity: " + this.getClass().getSimpleName());
    }
}
