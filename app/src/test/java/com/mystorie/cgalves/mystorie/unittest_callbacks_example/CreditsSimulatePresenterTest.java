package com.mystorie.cgalves.mystorie.unittest_callbacks_example;

import android.content.Context;

import com.cgalves.mystorie.common.base.ReturnSystemVO;
import com.cgalves.mystorie.common.base.SystemVO;
import com.cgalves.mystorie.feature.simulate.model.CreditSimulations;
import com.cgalves.mystorie.feature.simulate.model.repository.CreditsRepository;
import com.cgalves.mystorie.feature.simulate.presenter.CreditsSimulateContract;
import com.cgalves.mystorie.feature.simulate.presenter.CreditsSimulatePresenter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;



import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CreditsSimulatePresenterTest {

    @Mock
    Context context;
    @Mock
    CreditsSimulateContract.View view;
    @Mock
    CreditsRepository creditsRepository;
    @Captor
    ArgumentCaptor<CreditsRepository.CreditsCallback<CreditSimulations>> creditsCallbackCaptor;

    private CreditsSimulatePresenter<CreditsSimulateContract.View> presenter;

    @Before
    public void setUp() {
        presenter = new CreditsSimulatePresenter<>(context);
        presenter.setCreditsRepository(creditsRepository);
        presenter.setView(view);
    }

    @After
    public void tearDown() {
        presenter = null;
    }

    @Test
    public void calculateSimulation_shouldShowSimulationByAmountNeededResult() {

        // setup request
        int selectedSimulationMode = CreditsSimulateContract.View.SIMULATION_BY_AMOUNT_NEEDED;
        float amount = 0.0f;
        int installmentCount = 0;
        Date firstInstallmentDate = new Date();

        // setup response
        CreditSimulations creditSimulation = new CreditSimulations();
        creditSimulation.simulationMode = selectedSimulationMode;

        // test business logic
        presenter.calculateSimulation(selectedSimulationMode, amount, installmentCount, firstInstallmentDate);

        // verifications
        verify(creditsRepository).calculateSimulation(anyInt(), anyFloat(), anyInt(), any(Date.class), creditsCallbackCaptor.capture());
        creditsCallbackCaptor.getValue().onSuccess(creditSimulation);

        InOrder inOrder = Mockito.inOrder(presenter.getMvpView());
        inOrder.verify(presenter.getMvpView()).showWaiting();
        inOrder.verify(presenter.getMvpView()).showSimulationByAmountNeededResult(any(CreditSimulations.class));
        inOrder.verify(presenter.getMvpView()).hideWaiting();
    }

    @Test
    public void calculateSimulation_shouldShowSimulationByAmountCanPayResult() {

        // setup request
        int selectedSimulationMode = CreditsSimulateContract.View.SIMULATION_BY_AMOUNT_CAN_PAY;
        float amount = 0.0f;
        int installmentCount = 0;
        Date firstInstallmentDate = new Date();

        // setup response
        CreditSimulations creditSimulation = new CreditSimulations();
        creditSimulation.simulationMode = selectedSimulationMode;

        // test business logic
        presenter.calculateSimulation(selectedSimulationMode, amount, installmentCount, firstInstallmentDate);

        // verifications
        verify(creditsRepository).calculateSimulation(anyInt(), anyFloat(), anyInt(), any(Date.class), creditsCallbackCaptor.capture());
        creditsCallbackCaptor.getValue().onSuccess(creditSimulation);

        InOrder inOrder = Mockito.inOrder(presenter.getMvpView());
        inOrder.verify(presenter.getMvpView()).showWaiting();
        inOrder.verify(presenter.getMvpView()).showSimulationByAmountCanPayResult(any(CreditSimulations.class));
        inOrder.verify(presenter.getMvpView()).hideWaiting();
    }

    @Test
    public void calculateSimulation_shouldShowBusinessError() {

        // setup request
        int selectedSimulationMode = CreditsSimulateContract.View.SIMULATION_BY_AMOUNT_CAN_PAY;
        float amount = 0.0f;
        int installmentCount = 0;
        Date firstInstallmentDate = new Date();

        // setup response
        ReturnSystemVO returnSystemVO = new ReturnSystemVO(new SystemVO());
        returnSystemVO.mensagem = "business error";

        // test business logic
        presenter.calculateSimulation(selectedSimulationMode, amount, installmentCount, firstInstallmentDate);

        // verifications
        verify(creditsRepository).calculateSimulation(anyInt(), anyFloat(), anyInt(), any(Date.class), creditsCallbackCaptor.capture());
        creditsCallbackCaptor.getValue().onError(returnSystemVO);

        InOrder inOrder = Mockito.inOrder(presenter.getMvpView());
        inOrder.verify(presenter.getMvpView()).showWaiting();
        inOrder.verify(presenter.getMvpView()).showError(returnSystemVO.mensagem);
        inOrder.verify(presenter.getMvpView()).hideWaiting();
    }

    @Test
    public void calculateSimulation_shouldShowSystemError() {

        // setup request
        int selectedSimulationMode = CreditsSimulateContract.View.SIMULATION_BY_AMOUNT_CAN_PAY;
        float amount = 0.0f;
        int installmentCount = 0;
        Date firstInstallmentDate = new Date();

        // setup response
        String systemError = "system error";

        // test business logic
        presenter.calculateSimulation(selectedSimulationMode, amount, installmentCount, firstInstallmentDate);

        // verifications
        verify(creditsRepository).calculateSimulation(anyInt(), anyFloat(), anyInt(), any(Date.class), creditsCallbackCaptor.capture());
        creditsCallbackCaptor.getValue().onError(systemError);

        InOrder inOrder = Mockito.inOrder(presenter.getMvpView());
        inOrder.verify(presenter.getMvpView()).showWaiting();
        inOrder.verify(presenter.getMvpView()).showError(systemError);
        inOrder.verify(presenter.getMvpView()).hideWaiting();
    }
}