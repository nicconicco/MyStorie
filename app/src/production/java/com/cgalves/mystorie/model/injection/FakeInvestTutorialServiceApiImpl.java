package br.com.next.api.call.investments;

import br.com.next.api.vo.investments.Status;
import br.com.next.ui.activity.investments.tutorial.androidtutorial.InvestTutorialServiceApi;

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
