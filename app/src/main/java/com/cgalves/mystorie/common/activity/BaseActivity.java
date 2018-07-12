package com.cgalves.mystorie.common.activity;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.cgalves.mystorie.BuildConfig;
import com.cgalves.mystorie.R;


/**
 * Created by Scopus on 10/07/18.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(getString(R.string.tag_next_flow),"Flavor: " + BuildConfig.FLAVOR + " : Im in Activity: " + this.getClass().getSimpleName());
    }
}
