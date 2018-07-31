package com.cgalves.mystorie.common.fragment;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toolbar;

import com.cgalves.mystorie.BuildConfig;
import com.cgalves.mystorie.R;

import org.androidannotations.annotations.Click;

/**
 * Created by scopus on 27/07/18.
 */

public class BaseFragment extends Fragment {

    public void showToolbarOrNot(boolean showToolBarBack, LinearLayout toolbarFragment) {
        if(showToolBarBack) {
            toolbarFragment.setVisibility(View.VISIBLE);
        } else {
            toolbarFragment.setVisibility(View.GONE);
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.d(getString(R.string.tag_next_flow),"Flavor: " + BuildConfig.FLAVOR + " : Im in Activity: " + this.getClass().getSimpleName());
    }
}
