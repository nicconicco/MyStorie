package com.cgalves.mystorie.feature.androidmvpgoogle;


import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

public final class InvestTutorialRepositories {

    private static InvestTutorialRepository repository = null;

    private InvestTutorialRepositories() {
        // no instance
    }

    public synchronized static InvestTutorialRepository getInMemoryRepoInstance(@NonNull InvestTutorialServiceApi api) {
        checkNotNull(api);
        if (null == repository) {
            repository = new InMemoryInvestTutorialRepository(api);
        }
        return repository;
    }
}
