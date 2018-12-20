package mvvm.feature.home.view.activity;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.cgalves.mystorie.MyStorieApplication;
import com.cgalves.mystorie.R;
import com.cgalves.mystorie.common.activity.BaseActivity;
import com.cgalves.mystorie.common.model.User;
import com.cgalves.mystorie.feature.home.model.Image;
import com.cgalves.mystorie.feature.home.model.ImageResponse;
import com.cgalves.mystorie.feature.home.model.Section;
import com.cgalves.mystorie.feature.home.model.SectionResponse;
import com.cgalves.mystorie.feature.section.SectionActivity_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import mvvm.feature.home.view.adapter.DrawerMenuLeftSideAdapter;
import mvvm.feature.home.view.adapter.HomeFragmentPagerAdapter;
import mvvm.feature.home.vm.HomeViewModel;

@EActivity(R.layout.activity_home)
public class HomeActivity extends BaseActivity {

    @ViewById
    TextView tvUserName;
    @ViewById
    ViewPager viewpager;
    @ViewById
    TabLayout tabLayout;
    @ViewById
    ImageView ivSection, ivCenter;
    @ViewById
    DrawerLayout drawerLayout;
    @ViewById
    LinearLayout leftDrawer;
    @ViewById
    ListView listview;

    ActionBarDrawerToggle mDrawerToggle;
    private String[] mNavigationDrawerItemTitles;
    private HomeViewModel homeViewModel;

    @AfterViews
    void init() {
        setupToolbar(false);
        toolbar.setTitle("");
        toolbar.setBackgroundColor(getColor(R.color.transparent));

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        setupImages();
        setupSection();

        setupTabLayout();
    }

    private void setupSection() {
        MutableLiveData<SectionResponse> sectionResponseMutableLiveData = homeViewModel.sectionResponseMutableLiveData;
        if (sectionResponseMutableLiveData != null) {
            if (sectionResponseMutableLiveData != null && sectionResponseMutableLiveData.getValue().isSuccess()) {
                onResultSectionBody(sectionResponseMutableLiveData.getValue().getList(), new User(((MyStorieApplication) getApplication()).getName()));
            } else {
                loadSection();
            }
        }
    }

    private void setupImages() {
        MutableLiveData<ImageResponse> imageResponseMutableLiveData = homeViewModel.imageResponseMutableLiveData;
        if(imageResponseMutableLiveData!=null) {
            if (imageResponseMutableLiveData.getValue() != null) {
                if (imageResponseMutableLiveData.getValue().isSuccess()) {
                    onResultImages(imageResponseMutableLiveData.getValue().getList());
                } else {
                    loadList();
                }
            }
        }
    }

    private void loadList() {
        homeViewModel.fetchList(((MyStorieApplication) getApplication()).getToken()).observe(this, imageResponse -> {
            if (imageResponse != null) {
                if (imageResponse.isSuccess()) {
                    onResultImages(imageResponse.getList());
                }
            }
        });
    }

    private void loadSection() {
        homeViewModel.fetchSection(((MyStorieApplication) getApplication()).getToken()).observe(this, sectionResponse -> {
            if (sectionResponse != null) {
                if (sectionResponse.isSuccess()) {
                    onResultSectionBody(sectionResponse.getList(), new User(((MyStorieApplication) getApplication()).getName()));
                }
            }
        });
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
        mNavigationDrawerItemTitles = getResources().getStringArray(R.array.navigation_drawer_items_array);

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

    public void onResultImages(List<Image> result) {
        ViewPager viewPager = findViewById(R.id.viewpager);
        HomeFragmentPagerAdapter adapter = new HomeFragmentPagerAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void onResultSectionBody(List<Section> result, User user) {
        setupMenuLeftSide(result, user);
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
                    goToSection(HomeActivity.this, getString(R.string.noticias));
                    break;
                case 1:
                    goToSection(HomeActivity.this, getString(R.string.novidades));
                    break;
                case 2:
                    goToSection(HomeActivity.this, getString(R.string.contato));
                    break;
                default:
                    break;
            }

            setTitle(mNavigationDrawerItemTitles[position]);
            drawerLayout.closeDrawer(leftDrawer);
        }

        private void goToSection(Context context, String title) {
            SectionActivity_.intent(context).sectionName(title).start();
        }
    }
}