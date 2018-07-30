package com.cgalves.mystorie.feature.novidades;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cgalves.mystorie.R;

/**
 * Created by scopus on 27/07/18.
 */

public class NovidadesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_novidades, container, false);
        return rootView;
    }
}
