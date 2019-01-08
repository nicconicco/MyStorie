package com.cgalves.mystorie.feature.androidmvpgoogle;

import android.support.annotation.NonNull;


import static com.google.common.base.Preconditions.checkNotNull;


public class InvestTutorialPresenter implements InvestTutorialContract.UserActionListener {

    private final InvestTutorialRepository mInvestTutorialRepository;

    private final InvestTutorialContract.View view;

    public InvestTutorialPresenter(@NonNull InvestTutorialRepository investTutorialRepository,
                                   @NonNull InvestTutorialContract.View view) {
        mInvestTutorialRepository = checkNotNull(investTutorialRepository, "InvestTutorialRepository cannot be null!");
        this.view = checkNotNull(view, "InvestTutorialContract.View cannot be null!");
    }

    @Override
    public void cleanData(boolean value) {
        view.showProgress();
        if (value) {
            mInvestTutorialRepository.refreshData();
        }
        view.hideLoading();
    }

    @Override
    public void checkInvestmentsSummary() {
        view.showProgress();
        mInvestTutorialRepository.getCheckInvestmentsSummary(new InvestTutorialRepository.CheckInvestCallback() {
            @Override
            public void onCheckInvestmentsSummary(int number) {
                setValue(number);
            }

            @Override
            public void onError(String error) {
                view.hideLoading();
                view.onError(error);
            }
        });
    }

    private void setValue(int number) {
        view.hideLoading();
        view.onCheckInvestmentsSummary(number);
    }

    @Override
    public void getProfileInvestor() {
        view.showProgress();
        mInvestTutorialRepository.getProfile(new InvestTutorialRepository.LoadProfileCallback() {
            @Override
            public void onLoadProfile(Status status) {
                view.hideLoading();
                view.onGetProfileInvestor(status);
            }

            @Override
            public void onError(String error) {
                view.onError(error);
                view.hideLoading();
            }
        });
    }
}
