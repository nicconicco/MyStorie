package com.cgalves.mystorie.feature.home.view.activity;

import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.cgalves.mystorie.R;
import com.cgalves.mystorie.common.activity.BaseActivity;
import com.cgalves.mystorie.common.model.User;
import com.cgalves.mystorie.feature.home.model.Image;
import com.cgalves.mystorie.feature.home.model.Section;
import com.cgalves.mystorie.feature.home.presenter.HomeContract;
import com.cgalves.mystorie.feature.home.presenter.HomePresenterImpl;
import com.cgalves.mystorie.feature.home.view.adapter.DrawerMenuLeftSideAdapter;
import com.cgalves.mystorie.feature.home.view.adapter.HomeFragmentPagerAdapter;
import com.cgalves.mystorie.feature.section.SectionActivity_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EActivity(R.layout.activity_home)
public class HomeActivity extends BaseActivity implements HomeContract.HomePresenterView {

    @ViewById
    TextView tvUserName;

    @ViewById
    public ViewPager viewpager;

    @ViewById
    TabLayout tabLayout;

    @Bean
    HomePresenterImpl<HomeActivity> presenter;

    @ViewById
    ImageView ivSection, ivCenter;

    @ViewById
    DrawerLayout drawerLayout;

    // Unnecessary put an recycler when the menu have too mutch 20 elements

    @ViewById
    LinearLayout leftDrawer;

    @ViewById
    ListView listview;

    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;

    @AfterViews
    void init() {
        setupToolbar(false);
        toolbar.setTitle("");
        toolbar.setBackgroundColor(getColor(R.color.transparent));

        presenter.attachView(this);
        presenter.register();
        presenter.findImagesTopHeader();
        presenter.findSectionInBody();

        setupTabLayout();
    }

    private void setupTabLayout() {
        tabLayout.setupWithViewPager(viewpager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
                setImageHome(tab.getPosition());
            }

            private void setImageHome(int position) {
                switch (position) {
                    case 0: {
                        setImage(ContextCompat.getDrawable(HomeActivity.this, R.drawable.seneca1), ContextCompat.getDrawable(HomeActivity.this, R.drawable.novidade));
                        break;
                    }
                    case 1: {
                        setImage(ContextCompat.getDrawable(HomeActivity.this, R.drawable.seneca2), ContextCompat.getDrawable(HomeActivity.this, R.drawable.noticia));
                        break;
                    }
                    case 2: {
                        setImage(ContextCompat.getDrawable(HomeActivity.this, R.drawable.aristoteles), ContextCompat.getDrawable(HomeActivity.this, R.drawable.contact));
                        break;
                    }
                    default: {
                        setImage(ContextCompat.getDrawable(HomeActivity.this, R.drawable.aristoteles), ContextCompat.getDrawable(HomeActivity.this, R.drawable.contact));
                        break;
                    }
                }
            }

            private void setImage(Drawable drawable, Drawable drawable2) {
                ivSection.setImageDrawable(drawable);
                ivCenter.setImageDrawable(drawable2);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                /**
                 * Para usar este componenten é preciso sobreescrever todos os estados obrigatorios da interface
                 */
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                /**
                 * Para usar este componenten é preciso sobreescrever todos os estados obrigatorios da interface
                 */
            }
        });
    }

    private void setupMenuLeftSide(List<Section> result, User user) {

        tvUserName.setText("Bem vindo! " + user.getName());
        DrawerMenuLeftSideAdapter adapter = new DrawerMenuLeftSideAdapter(this, result);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new DrawerItemClickListener());
        drawerLayout.addDrawerListener(mDrawerToggle);

        setupDrawerToggle();
    }

    protected void setupDrawerToggle() {
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        mDrawerToggle.syncState();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        presenter.unregister();
    }

    @Override
    public void onResultImages(List<Image> result) {

        ViewPager viewPager = findViewById(R.id.viewpager);

        HomeFragmentPagerAdapter adapter = new HomeFragmentPagerAdapter(this, getSupportFragmentManager());

        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
    }


    @Override
    public void onResultSectionBody(List<Section> result, User user) {
        setupMenuLeftSide(result, user);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView(this);
        presenter.register();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Click(R.id.btn_exit)
    void onClickLogout() {
        finish();
    }

    private class DrawerItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            selectItem(position);
        }

        private void selectItem(int position) {
            switch (position) {
                case 0:
                    SectionActivity_.intent(HomeActivity.this).sectionName(getString(R.string.noticias)).start();
                    break;
                case 1:
                    SectionActivity_.intent(HomeActivity.this).sectionName(getString(R.string.novidades)).start();
                    break;
                case 2:
                    SectionActivity_.intent(HomeActivity.this).sectionName(getString(R.string.contato)).start();
                    break;
                default:
                    break;
            }

            setAndCloseMenu(position);
        }

        public void setAndCloseMenu(int andCloseMenu) {
//            setTitle(mNavigationDrawerItemTitles[andCloseMenu]);
            drawerLayout.closeDrawer(leftDrawer);
        }
    }
}