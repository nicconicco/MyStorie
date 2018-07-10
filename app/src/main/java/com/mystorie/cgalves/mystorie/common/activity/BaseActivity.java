package com.mystorie.cgalves.mystorie.common.activity;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mystorie.cgalves.mystorie.Constants;
import com.mystorie.cgalves.mystorie.R;

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
