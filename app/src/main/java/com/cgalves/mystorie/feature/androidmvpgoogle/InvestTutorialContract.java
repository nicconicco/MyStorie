package com.cgalves.mystorie.feature.androidmvpgoogle;


public interface InvestTutorialContract {

    interface View {
        void onCheckInvestmentsSummary(int number);
        void onGetProfileInvestor(Status status);
        void showProgress();
        void hideLoading();
        void onError(String error);
    }

    interface UserActionListener {
        void cleanData(boolean value);
        void checkInvestmentsSummary();
        void getProfileInvestor();
    }
}
