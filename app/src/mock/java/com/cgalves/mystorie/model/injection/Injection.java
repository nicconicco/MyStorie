package com.cgalves.mystorie.model.injection;


import com.cgalves.mystorie.feature.androidmvpgoogle.InvestTutorialRepositories;
import com.cgalves.mystorie.feature.androidmvpgoogle.InvestTutorialRepository;

public class Injection {
    public static InvestTutorialRepository provideInvestTutorialRepository() {
        return InvestTutorialRepositories.getInMemoryRepoInstance(new FakeInvestTutorialServiceApiImpl());
    }
}
