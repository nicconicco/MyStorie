package com.mystorie.cgalves.mystorie;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by Scopus on 10/07/18.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Going to: ","Flavor: " + Constants.type + " : " + this.getClass().getSimpleName());
    }
}
