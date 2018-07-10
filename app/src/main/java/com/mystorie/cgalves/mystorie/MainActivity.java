package com.mystorie.cgalves.mystorie;


import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;


/**
 * Created by Scopus on 09/07/18.
 */

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//
//    }

    @Click(R.id.btn_two)
    void opa(){
        Toast.makeText(this, "opa", Toast.LENGTH_LONG).show();
    }
}
