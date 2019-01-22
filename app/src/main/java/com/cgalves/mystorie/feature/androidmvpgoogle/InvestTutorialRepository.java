package com.cgalves.mystorie.feature.androidmvpgoogle;


import android.support.annotation.NonNull;

public interface InvestTutorialRepository {

    interface LoadProfileCallback {

        void onLoadProfile(Status status);

        void onError(String error);
    }

    interface CheckInvestCallback {

        void onCheckInvestmentsSummary(int number);

        void onError(String error);
    }

    void getProfile(@NonNull LoadProfileCallback callback);

    void getCheckInvestmentsSummary(@NonNull CheckInvestCallback callback);

    void refreshData();
}
