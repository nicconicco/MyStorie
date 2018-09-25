package com.cgalves.mystorie.common.activity;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.cgalves.mystorie.BuildConfig;
import com.cgalves.mystorie.R;
import com.cgalves.mystorie.common.presenter.BaseContract;
import com.cgalves.mystorie.common.presenter.BaseViewModel;


/**
 * Created by Carlos Nicolau Galves on 10/07/18.
 */

public abstract class BaseActivity <V extends BaseContract.View, P extends BaseContract.Presenter<V>> extends AppCompatActivity implements BaseContract.View, LifecycleRegistryOwner {


    private final LifecycleRegistry lifecycleRegistry =
            new LifecycleRegistry(this);
    protected P presenter;

    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseViewModel<V, P> viewModel =
                ViewModelProviders.of(this).get(BaseViewModel.class);
        if (viewModel.getPresenter() == null) {
            viewModel.setPresenter(initPresenter());
        }
        presenter = viewModel.getPresenter();
        presenter.attachLifecycle(getLifecycle());
        presenter.attachView((V) this);
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }

    @CallSuper
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachLifecycle(getLifecycle());
        presenter.detachView();
    }

    protected abstract P initPresenter();


    public Toolbar toolbar;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void setupToolbar() {
        toolbar = findViewById(R.id.toolbar);

        if(toolbar != null) {
            toolbar.setTitle(getString(R.string.app_name));
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(getString(R.string.tag_next_flow),"Flavor: " + BuildConfig.FLAVOR + " : Im in Activity: " + this.getClass().getSimpleName());
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
