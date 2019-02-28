package com.cgalves.mystorie.feature.simulate.view;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.cgalves.mystorie.R;
import com.cgalves.mystorie.common.activity.BaseActivity;
import com.cgalves.mystorie.common.utils.ViewUtils;
import com.cgalves.mystorie.feature.simulate.model.CreditSimulations;
import com.cgalves.mystorie.feature.simulate.presenter.CreditsSimulateContract;
import com.cgalves.mystorie.feature.simulate.presenter.CreditsSimulatePresenter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.util.Date;


/**
 * Created by Scopus
 */

@SuppressLint("Registered")
@EActivity(R.layout.activity_credits_simulate)
@OptionsMenu(R.menu.menu_add)
public class CreditsSimulateActivity extends BaseActivity implements CreditsSimulateContract.View, CreditsSimulateListener {

    @ViewById
    RelativeLayout llCalculateButton;

    private static final String TAG = CreditsSimulateActivity.class.getSimpleName();

    private CreditsSimulateContract.Presenter<CreditsSimulateContract.View> presenter;

    @CreditsSimulateContract.CreditSimulationMode
    private int selectedSimulationMode = CreditsSimulateContract.View.SIMULATION_BY_AMOUNT_NEEDED;

    @Override
    public void showWaiting() {
        this.showLoading(false);
    }

    @Override
    public void hideWaiting() {
        this.hidenLoading();
    }

    @Override
    public void showError(String error) {
        ViewUtils.showAlert(this, error);
    }

    @AfterViews
    void init() {
        initToolbar();
    }

    @Override
    protected void onResume() {
        super.onResume();

        initPresenter();
    }

    @Override
    protected void onPause() {
        super.onPause();

        finishPresenter();
    }

    @OptionsItem(android.R.id.home)
    void homeMenuItemClick() {
        finish();
    }

    @Click(R.id.bt_calculate)
    void onCliBtCalculate() {
        float amount = 0.0f;
        int installmentCount = 0;
        Date firstInstallmentDate = new Date();

        presenter.calculateSimulation(selectedSimulationMode, amount, installmentCount, firstInstallmentDate);
    }

    @Override
    public void showSimulationByAmountNeededResult(@NonNull CreditSimulations creditSimulations) {
        showCalculateButton(false);
    }

    @Override
    public void showSimulationByAmountCanPayResult(@NonNull CreditSimulations creditSimulations) {
        showCalculateButton(false);
    }

    private void initToolbar() {
        setupToolbar(false);
        toolbar.setTitle("");
        toolbar.setBackgroundColor(getColor(R.color.transparent));
    }

    private void initPresenter() {
        presenter = new CreditsSimulatePresenter<>(this);
        ((CreditsSimulatePresenter<CreditsSimulateContract.View>) presenter).attachView(this);
    }

    private void finishPresenter() {
        ((CreditsSimulatePresenter<CreditsSimulateContract.View>) presenter).detachView();
        presenter = null;
    }

    private void showCalculateButton(boolean show) {
        llCalculateButton.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void dateSelect(String data) {
        Log.i("Teste", data);
    }

}
