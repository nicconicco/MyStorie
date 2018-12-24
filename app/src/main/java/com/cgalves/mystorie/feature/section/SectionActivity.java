package com.cgalves.mystorie.feature.section;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.cgalves.mystorie.R;
import com.cgalves.mystorie.common.activity.BaseActivity;
import com.cgalves.mystorie.feature.contact.view.ContactFragment_;
import com.cgalves.mystorie.feature.noticias.view.fragment.NoticiasFragment_;
import com.cgalves.mystorie.feature.novidades.view.fragment.NovidadesFragment_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;

@EActivity(R.layout.activity_section)
public class SectionActivity extends BaseActivity {

    @Extra
    String sectionName;

    @AfterViews
    void init() {
        Fragment fragment = null;
        switch (sectionName) {
            case "Notícias": {
                fragment = getNoticiasFragment();
                break;
            }
            case "Novidades!": {
                fragment = NovidadesFragment_.builder()
                        .showToolBarBack(true)
                        .build();
                break;
            }
            case "Contato": {
                fragment = ContactFragment_.builder()
                        .showToolBarBack(true)
                        .build();
                break;
            }
            default: {
                break;
            }
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        } else {
            Log.e(SectionActivity.class.getName(), "Error in creating fragment");
        }
    }

    private Fragment getNoticiasFragment() {
        Fragment fragment;
        fragment = NoticiasFragment_.builder()
                .showToolBarBack(true)
                .build();
        return fragment;
    }
}
