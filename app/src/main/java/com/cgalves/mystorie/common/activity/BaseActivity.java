package com.cgalves.mystorie.common.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.cgalves.mystorie.BuildConfig;
import com.cgalves.mystorie.R;
import com.cgalves.mystorie.common.dialog.CustomProgressDialog;



/**
 * Created by Scopus on 10/07/18.
 */

public class BaseActivity extends AppCompatActivity {

    protected static final CustomProgressDialog progressBar = new CustomProgressDialog();
    protected Toolbar toolbar;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void setupToolbar(boolean backFlag) {
        toolbar = findViewById(R.id.toolbar);

        if(toolbar != null) {
            toolbar.setTitle(getString(R.string.app_name));
            setSupportActionBar(toolbar);
            if(backFlag) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(getString(R.string.tag_next_flow),"Flavor: " + BuildConfig.FLAVOR + " : Im in Activity: " + this.getClass().getSimpleName());
    }
}
