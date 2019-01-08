package com.cgalves.mystorie.model.injection;


import com.cgalves.mystorie.feature.androidmvpgoogle.InvestTutorialServiceApi;
import com.cgalves.mystorie.feature.androidmvpgoogle.Status;

public class FakeInvestTutorialServiceApiImpl implements InvestTutorialServiceApi {

    @Override
    public void getProfile(InvestTutorialServiceCallback<Status> callback) {
        Status status = new Status();
        status.setStatusCode(3);
        callback.onLoaded(status);
    }

    @Override
    public void getCheckInvestSummary(InvestTutorialServiceCallback<Integer> callback) {
        callback.onLoaded(1);
    }
}
