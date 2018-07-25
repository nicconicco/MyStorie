package com.cgalves.mystorie.feature.home.view.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cgalves.mystorie.R;
import com.cgalves.mystorie.common.activity.BaseActivity;
import com.cgalves.mystorie.feature.home.model.Image;
import com.cgalves.mystorie.feature.home.model.Section;
import com.cgalves.mystorie.feature.home.presenter.HomeContract;
import com.cgalves.mystorie.feature.home.presenter.HomePresenterImpl;
import com.cgalves.mystorie.feature.home.view.adapter.DrawerMenuLeftSideAdapter;
import com.cgalves.mystorie.feature.home.view.adapter.MenuHomeAdapter;
import com.cgalves.mystorie.feature.home.view.adapter.PhotosAdapter;
import com.cgalves.mystorie.feature.list.view.activity.ListSectionActivity_;

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

    @ViewById
    DrawerLayout drawerLayout;

    // Unnecessary put an recycler when the menu have too mutch 20 elements
    @ViewById
    ListView leftDrawer;


    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;
    private String[] mNavigationDrawerItemTitles;

    private MenuHomeAdapter menuHomeAdapter;
    private PhotosAdapter photosAdapter;
    private String TAG = HomeActivity.class.getSimpleName();

    @AfterViews
    void init() {
        setupToolbar();

        presenter.attachView(this);
        presenter.register();
        presenter.findImagesTopHeader();
        presenter.findSectionInBody();

        recyclerList.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupMenuLeftSide(List<Section> result) {
        mNavigationDrawerItemTitles = getResources().getStringArray(R.array.navigation_drawer_items_array);
        DrawerMenuLeftSideAdapter adapter = new DrawerMenuLeftSideAdapter(this, result);
        leftDrawer.setAdapter(adapter);
        leftDrawer.setOnItemClickListener(new DrawerItemClickListener());
        drawerLayout.addDrawerListener(mDrawerToggle);
        setupDrawerToggle();
    }

    protected void setupDrawerToggle() {
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggle.syncState();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        presenter.unregister();
    }

    private void makeMarginToTabLayout(TabLayout tabLayout, int marginRight) {
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
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
        menuHomeAdapter = new MenuHomeAdapter(this, result, onClickListener());
        recyclerList.setAdapter(menuHomeAdapter);
        setupMenuLeftSide(result);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView(this);
        presenter.register();
    }

    private MenuHomeAdapter.OnClickListener onClickListener() {
        return section -> {
            presenter.unregister();
            presenter.detachView();
            ListSectionActivity_.intent(HomeActivity.this).section(section).start();
        };
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class DrawerItemClickListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            selectItem(position);
        }

        private void selectItem(int position) {
            Fragment fragment = null;

            switch (position) {
                case 0:
                    fragment = new ConnectFragment();
                    break;
                case 1:
                    fragment = new ConnectFragment();
                    break;
                case 2:
                    fragment = new ConnectFragment();
                    break;

                default:
                    break;

            }

            if (fragment != null) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

                leftDrawer.setItemChecked(position, true);
                leftDrawer.setSelection(position);
                setTitle(mNavigationDrawerItemTitles[position]);
                drawerLayout.closeDrawer(leftDrawer);

            } else {
                Log.e(TAG, "Error in creating fragment");
            }
        }
    }
}