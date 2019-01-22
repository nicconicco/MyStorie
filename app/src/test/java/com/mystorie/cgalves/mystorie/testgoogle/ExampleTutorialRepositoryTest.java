package br.com.next.test.investments.tests;

import com.cgalves.mystorie.feature.androidmvpgoogle.InMemoryInvestTutorialRepository;
import com.cgalves.mystorie.feature.androidmvpgoogle.InvestTutorialServiceApi;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

public class ExampleTutorialRepositoryTest {

    private InMemoryInvestTutorialRepository repository;

    @Mock
    private InvestTutorialServiceApi mServiceApi;

    @Mock
    public InMemoryInvestTutorialRepository.CheckInvestCallback mCheckCallback;

    @Mock
    private InMemoryInvestTutorialRepository.LoadProfileCallback mLoadCallback;

    /**
     * {@link ArgumentCaptor} is a powerful Mockito API to capture argument values and use them to
     * perform further actions or assertions on them.
     */
    @Captor
    private ArgumentCaptor<InvestTutorialServiceApi.InvestTutorialServiceCallback> mLoadCaptor;


    @Before
    public void setupNotesRepository() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        repository = new InMemoryInvestTutorialRepository(mServiceApi);
    }

    @Test
    public void test_repository_getCheckInvestmentsSummary_onLoad(){
        repository.getCheckInvestmentsSummary(mCheckCallback);
        // Confere se chamou o onLoad da funcao
        verify(mServiceApi).getCheckInvestSummary(mLoadCaptor.capture());
    }

    @Test
    public void test_respository_cache(){
        repository.getCheckInvestmentsSummary(mCheckCallback);
        verify(mServiceApi).getCheckInvestSummary(mLoadCaptor.capture());
        mLoadCaptor.getValue().onLoaded(1);
    }


    @Test
    public void test_repository_getProfile_onLoad(){
        repository.getProfile(mLoadCallback);
        // Confere se chamou o onLoad da funcao
        verify(mServiceApi).getProfile(mLoadCaptor.capture());
    }

    @Test
    public void test_getCheckInvestmentsSummary_() {
        repository.getCheckInvestmentsSummary(mCheckCallback);
        verify(mServiceApi).getCheckInvestSummary(mLoadCaptor.capture());
    }

    @Test
    public void test_defaultValue() {
        repository.getProfile(mLoadCallback);
        assertEquals(repository.getCodeReceived(), (Integer.valueOf(0)));
    }
}
