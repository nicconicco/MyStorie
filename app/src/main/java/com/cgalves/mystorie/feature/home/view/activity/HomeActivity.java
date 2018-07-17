package com.cgalves.mystorie.feature.home.view.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.cgalves.mystorie.R;
import com.cgalves.mystorie.common.activity.BaseActivity;
import com.cgalves.mystorie.feature.home.model.Image;
import com.cgalves.mystorie.feature.home.model.Section;
import com.cgalves.mystorie.feature.home.presenter.HomeContract;
import com.cgalves.mystorie.feature.home.presenter.HomePresenterImpl;
import com.cgalves.mystorie.feature.home.view.adapter.MenuHomeAdapter;
import com.cgalves.mystorie.feature.home.view.adapter.PhotosAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EActivity(R.layout.activity_home)
public class HomeActivity extends BaseActivity implements HomeContract.HomePresenterView {

    @Bean
    HomePresenterImpl<HomeActivity> presenter;

    @ViewById
    RecyclerView recyclerList;

    private MenuHomeAdapter menuHomeAdapter;
    private PhotosAdapter photosAdapter;

    @AfterViews
    void init() {
        presenter.attachView(this);
        presenter.register();
        presenter.findImagesTopHeader();
        presenter.findSectionInBody();

        recyclerList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        presenter.unregister();
    }

    private void makeMarginToTabLayout(TabLayout tabLayout, int marginRight) {
        for(int i=0; i < tabLayout.getTabCount(); i++) {
            View tab = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(i);
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) tab.getLayoutParams();
            p.setMargins(0, 0, marginRight, 0);
            tab.requestLayout();
        }
    }

    @Override
    public void onResultImages(List<Image> result) {
        ViewPager pager = findViewById(R.id.photos_viewpager);
        photosAdapter = new PhotosAdapter(this, result);
        pager.setAdapter(photosAdapter);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(pager, true);

        makeMarginToTabLayout(tabLayout, 10);
    }

    @Override
    public void onResultSectionBody(List<Section> result) {
        menuHomeAdapter =  new MenuHomeAdapter(this, result, onClickListener());
        recyclerList.setAdapter(menuHomeAdapter);
    }

    private MenuHomeAdapter.OnClickListener onClickListener() {
        return section -> {

        };
    }
}