package br.com.next.test.investments.tests;



import com.cgalves.mystorie.feature.androidmvpgoogle.InvestTutorialContract;
import com.cgalves.mystorie.feature.androidmvpgoogle.InvestTutorialPresenter;
import com.cgalves.mystorie.feature.androidmvpgoogle.InvestTutorialRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;


/**
 * Created by scopus on 27/12/18.
 */

public class ExampleTutorialPresenterTest {

    @Mock
    private InvestTutorialRepository mInvestRepo;

    @Mock
    private InvestTutorialContract.View mView;

    private InvestTutorialPresenter presenter;

    @Captor
    private ArgumentCaptor<InvestTutorialRepository.CheckInvestCallback> mCheckCaptor;

    @Captor
    private ArgumentCaptor<InvestTutorialRepository.LoadProfileCallback> mLoader;

    @Before
    public void beforeTest() {
        MockitoAnnotations.initMocks(this);
        presenter =  new InvestTutorialPresenter(mInvestRepo, mView);
    }

    /**
     * - Metodos para testar no presenter
     * void checkInvestmentsSummary();
     * void getProfileInvestor();
     * cleanData()
     */

    @Test
    public void test_cleanData() {
        presenter.cleanData(true);
        verify(mView).showProgress();
        verify(mInvestRepo).refreshData();
        verify(mView).hideLoading();
    }

    @Test
    public void test_checkInvestmentsSummary_show_Progress() {
        presenter.checkInvestmentsSummary();
        verify(mView).showProgress();
    }

    @Test
    public void test_checkInvestmentsSummary_return_callback() {
        presenter.checkInvestmentsSummary();
        verify(mInvestRepo).getCheckInvestmentsSummary(mCheckCaptor.capture());
    }

    @Test
    public void test_getProfileInvestor_show_Progress(){
        presenter.getProfileInvestor();
        verify(mView).showProgress();
    }

    @Test
    public void test_getProfile(){
        presenter.getProfileInvestor();
        verify(mInvestRepo).getProfile(mLoader.capture());
    }
}
