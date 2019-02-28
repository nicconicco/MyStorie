package com.cgalves.mystorie.feature.simulate.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.cgalves.mystorie.common.base.ReturnSystemVO;
import com.cgalves.mystorie.common.presenter.BasePresenter;
import com.cgalves.mystorie.common.presenter.MvpView;
import com.cgalves.mystorie.feature.simulate.model.CreditSimulations;
import com.cgalves.mystorie.feature.simulate.model.repository.CreditsRepository;

import java.util.Date;


/**
 * Created by Scopus
 */

public class CreditsSimulatePresenter<V extends CreditsSimulateContract.View & MvpView>
        extends BasePresenter<V>
        implements CreditsSimulateContract.Presenter<V> {

    private CreditsRepository creditsRepository;

    public CreditsSimulatePresenter(Context context) {
        super(context);
    }

    @Override
    public void calculateSimulation(final int simulationMode, float amount, int installmentCount, @NonNull Date firstInstallmentDate) {
        getMvpView().showWaiting();

        getCreditsRepository().calculateSimulation(simulationMode, amount, installmentCount, firstInstallmentDate, new CreditsRepository.CreditsCallback<CreditSimulations>() {

            @Override
            public void onSuccess(CreditSimulations creditSimulation) {
                switch (creditSimulation.simulationMode) {
                    case CreditsSimulateContract.View.SIMULATION_BY_AMOUNT_NEEDED:
                        getMvpView().showSimulationByAmountNeededResult(creditSimulation);
                        break;

                    case CreditsSimulateContract.View.SIMULATION_BY_AMOUNT_CAN_PAY:
                        getMvpView().showSimulationByAmountCanPayResult(creditSimulation);
                        break;
                }

                getMvpView().hideWaiting();
            }

            @Override
            public void onError(String error) {
                getMvpView().showError(error);
                getMvpView().hideWaiting();
            }

            @Override
            public void onError(ReturnSystemVO returnSystemVO) {
                getMvpView().showError(returnSystemVO.mensagem);
                getMvpView().hideWaiting();
            }
        });
    }

    @Override
    protected void attachRepositories() {
        this.creditsRepository = new CreditsRepository(context);
    }

    @Override
    protected void detachRepositories() {
        this.creditsRepository.unregister();
        this.creditsRepository = null;
    }

    private CreditsRepository getCreditsRepository() {
        return this.creditsRepository;
    }

    public void setCreditsRepository(CreditsRepository repository) {
        this.creditsRepository = repository;
    }
}
