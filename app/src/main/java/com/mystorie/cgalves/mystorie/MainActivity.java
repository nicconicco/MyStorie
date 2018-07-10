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
        Log.d(getString(R.string.tag_next_flow), this.getString(R.string.state_after_view));
    }

    @Click(R.id.btn_one)
    void onClickBtnOne(){
        Log.d(getString(R.string.tag_next_flow), this.getString(R.string.click) + " : onClickBtnOne");
    }

    @Click(R.id.btn_two)
    void onClickBtnTwo(){
        Log.d(getString(R.string.tag_next_flow), this.getString(R.string.click) + " : onClickBtnTwo");
    }
}
