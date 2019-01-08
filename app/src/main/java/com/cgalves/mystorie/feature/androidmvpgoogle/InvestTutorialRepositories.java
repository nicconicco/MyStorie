package com.cgalves.mystorie.feature.androidmvpgoogle;


import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

public class InvestTutorialRepositories {

    private InvestTutorialRepositories() {
        // no instance
    }

    private static InvestTutorialRepository repository = null;

    public synchronized static InvestTutorialRepository getInMemoryRepoInstance(@NonNull InvestTutorialServiceApi api) {
        checkNotNull(api);
        if (null == repository) {
            repository = new InMemoryInvestTutorialRepository(api);
        }
        return repository;
    }
}
