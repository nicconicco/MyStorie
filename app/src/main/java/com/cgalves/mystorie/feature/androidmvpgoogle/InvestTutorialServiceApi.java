package com.cgalves.mystorie.feature.androidmvpgoogle;


public interface InvestTutorialServiceApi {

    interface InvestTutorialServiceCallback<T> {
        void onLoaded(T status);
        void onError(String error);
    }

    void getProfile(InvestTutorialServiceCallback<Status> callback);

    void getCheckInvestSummary(InvestTutorialServiceCallback<Integer> callback);

}
