package com.cgalves.mystorie.common.fragment;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toolbar;

import com.cgalves.mystorie.BuildConfig;
import com.cgalves.mystorie.R;

/**
 * Created by scopus on 27/07/18.
 */

public class BaseFragment extends Fragment {

    @Override
    public void onStart() {
        super.onStart();
        Log.d(getString(R.string.tag_next_flow),"Flavor: " + BuildConfig.FLAVOR + " : Im in Activity: " + this.getClass().getSimpleName());
    }
}
