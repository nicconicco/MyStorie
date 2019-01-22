package com.cgalves.mystorie.feature.list.view.activity;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cgalves.mystorie.R;
import com.cgalves.mystorie.common.activity.BaseActivity;
import com.cgalves.mystorie.common.model.DetailSection;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_detail)
public class DetailActivity extends BaseActivity {

    @ViewById(R.id.iv_detail)
    ImageView img;

    @ViewById
    TextView tvTxt;

    @Extra
    DetailSection detailSection;

    @AfterViews
    void init() {
        setupToolbar(true);
        toolbar.setTitle(detailSection.getName());
        tvTxt.setText(detailSection.getTxt());
//        Glide.with(this).load(detailSection.getImage().in
    }
}
