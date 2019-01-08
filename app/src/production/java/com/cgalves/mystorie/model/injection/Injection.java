package br.com.next.api.call.investments;

import br.com.next.ui.activity.investments.tutorial.androidtutorial.InvestTutorialRepositories;
import br.com.next.ui.activity.investments.tutorial.androidtutorial.InvestTutorialRepository;

public class Injection {
    public static InvestTutorialRepository provideInvestTutorialRepository() {
        return InvestTutorialRepositories.getInMemoryRepoInstance(new FakeInvestTutorialServiceApiImpl());
    }
}
