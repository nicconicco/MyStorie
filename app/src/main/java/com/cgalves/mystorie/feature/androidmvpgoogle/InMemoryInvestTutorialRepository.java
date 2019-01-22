package com.cgalves.mystorie.feature.androidmvpgoogle;



import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import static com.google.common.base.Preconditions.checkNotNull;

public class InMemoryInvestTutorialRepository implements InvestTutorialRepository {

    @VisibleForTesting
    Status mCacheStatus;

    @VisibleForTesting
    Integer codeReceived = 0;

    private final InvestTutorialServiceApi api;

    public Status getmCacheStatus() {
        return mCacheStatus;
    }

    public Integer getCodeReceived() {
        return codeReceived;
    }

    public InMemoryInvestTutorialRepository(@NonNull InvestTutorialServiceApi api) {
        this.api = checkNotNull(api);
    }

    @Override
    public void getProfile(@NonNull LoadProfileCallback callback) {
        checkNotNull(callback);

        if(mCacheStatus == null) {
            api.getProfile(new InvestTutorialServiceApi.InvestTutorialServiceCallback<Status>() {
                @Override
                public void onLoaded(Status status) {
                    mCacheStatus = status;
                    callback.onLoadProfile(status);
                }

                @Override
                public void onError(String error) {
                    callback.onError(error);
                }
            });
        } else {
            callback.onLoadProfile(mCacheStatus);
        }
    }

    @Override
    public void getCheckInvestmentsSummary(@NonNull CheckInvestCallback callback) {
        checkNotNull(callback);
        if(codeReceived == 0) {
            api.getCheckInvestSummary(new InvestTutorialServiceApi.InvestTutorialServiceCallback<Integer>() {
                @Override
                public void onLoaded(Integer number) {
                    codeReceived = number;
                    callback.onCheckInvestmentsSummary(number);
                }

                @Override
                public void onError(String error) {
                    callback.onError(error);
                }
            });
        } else {
            callback.onCheckInvestmentsSummary(codeReceived);
        }
    }

    @Override
    public void refreshData() {
        mCacheStatus = null;
        codeReceived = 0;
    }
}
