package com.mystorie.cgalves.mystorie.testsgoogle;

import com.cgalves.mystorie.feature.androidmvpgoogle.InvestTutorialServiceApi;
import com.cgalves.mystorie.feature.androidmvpgoogle.Status;

import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;

public class ExampleServiceApiTest {

    @Mock
    private InvestTutorialServiceApi.InvestTutorialServiceCallback<Status> callback;

    @Test
    public void apiGetProfileReturnCallback(){
        InvestTutorialServiceApi api = mock(InvestTutorialServiceApi.class);
        api.getProfile(callback);
    }

    @Test
    public void statusReturn() {
        InvestTutorialServiceApi.InvestTutorialServiceCallback api = mock(InvestTutorialServiceApi.InvestTutorialServiceCallback.class);
        Status status = new Status();
        status.setStatusCode(3);
        api.onLoaded(status);
    }

    @Test
    public void intReturn() {
        InvestTutorialServiceApi.InvestTutorialServiceCallback api = mock(InvestTutorialServiceApi.InvestTutorialServiceCallback.class);
        api.onLoaded(1);
    }

    @Test
    public void onError() {
        InvestTutorialServiceApi.InvestTutorialServiceCallback api = mock(InvestTutorialServiceApi.InvestTutorialServiceCallback.class);
        api.onError("Erro");
    }
}
