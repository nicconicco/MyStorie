package com.cgalves.mystorie.feature.androidmvpgoogle;

import android.widget.Toast;


import com.cgalves.mystorie.R;
import com.cgalves.mystorie.common.activity.BaseActivity;
import com.cgalves.mystorie.model.injection.Injection;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.LongClick;
import org.androidannotations.annotations.OptionsItem;


@EActivity(R.layout.activity_invest_tutorial)
public class InvestTutorialActivity extends BaseActivity implements InvestTutorialContract.View {

    private static final int SOLD_IN_SAVINGS = 1;
    private static final int NOT_SOLD_IN_SAVING = 2;
    private static final int NOT_SOLD_IN_SAVING_NOT_PROFILE_INVESTOR = 3;
    private static final int NOT_SOLD_IN_SAVING_PROFILE_INVESTOR = 4;
    private int investmentsSummary;
    private Status statusProfile;
    private InvestTutorialContract.UserActionListener mActionsListener;

    @AfterViews
    void initView() {
        loadToolbar();
        mActionsListener = new InvestTutorialPresenter(Injection.provideInvestTutorialRepository(), this);
    }

    /**
     * onClickOkGetIt -> onCheckInvestmentsSummary
     */

    @Click(R.id.ok)
    public void onClickOkGetIt() {
        mActionsListener.checkInvestmentsSummary();
    }

    @LongClick(R.id.ok)
    public void onLongClickOkGetIt() {
        mActionsListener.cleanData(true);
    }

    @Override
    public void onCheckInvestmentsSummary(int number) {
        this.investmentsSummary = number;
        setLayout(number);
    }

    @Override
    public void onGetProfileInvestor(Status status) {
        this.statusProfile = status;
        setLayout(investmentsSummary);
    }

    /**
     * Layout
     */

    @OptionsItem(android.R.id.home)
    void btHomeMenuItem() {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        // back pressed event
    }

    private void loadToolbar() {
        // load toolbar
    }

    @Override
    public void showProgress() {
        // example code
    }

    @Override
    public void hideLoading() {
        // example code
    }

    @Override
    public void onError(String error) {
        Toast.makeText(this, "Deu o seguinte erro: " + error, Toast.LENGTH_SHORT).show();
    }

    private void setLayout(int number) {
        switch (number) {
            case SOLD_IN_SAVINGS:
                //todo: Go to dashboard
                break;
            case NOT_SOLD_IN_SAVING:

                /**
                 * getProfileInvestor -> onGetProfileInvestor
                 */

                mActionsListener.getProfileInvestor();
                break;
            case NOT_SOLD_IN_SAVING_NOT_PROFILE_INVESTOR:
                if (statusProfile != null && statusProfile.getStatusCode() == 2 || statusProfile.getStatusCode() == 4) {
                    // do nothing at this moment
                }
                break;
            case NOT_SOLD_IN_SAVING_PROFILE_INVESTOR:
                if (statusProfile != null && statusProfile.getStatusCode() == 1 || statusProfile.getStatusCode() == 3 || statusProfile.getStatusCode() == 5) {
                    // do nothing at this moment
                }
                break;
            default:
                break;
        }
    }
}
