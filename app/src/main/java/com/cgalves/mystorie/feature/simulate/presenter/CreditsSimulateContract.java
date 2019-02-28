package com.cgalves.mystorie.feature.simulate.presenter;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;

import com.cgalves.mystorie.common.presenter.MvpView;
import com.cgalves.mystorie.feature.simulate.model.CreditSimulations;

import java.util.Date;



/**
 * Created by Scopus
 */

public interface CreditsSimulateContract {

    interface Presenter<V extends CreditsSimulateContract.View> extends MvpView<V> {

        void calculateSimulation(final @CreditSimulationMode int simulationMode, float amount, int installmentCount, @NonNull Date firstInstallmentDate);
    }

    interface View extends MvpView {

        int SIMULATION_BY_AMOUNT_NEEDED = 0;
        int SIMULATION_BY_AMOUNT_CAN_PAY = 1;
        int SIMULATION_MODE_COUNT = 2;

        void showWaiting();
        void hideWaiting();
        void showError(String error);

        void showSimulationByAmountNeededResult(@NonNull CreditSimulations creditSimulation);
        void showSimulationByAmountCanPayResult(@NonNull CreditSimulations creditSimulation);
    }

    @IntDef({ CreditsSimulateContract.View.SIMULATION_BY_AMOUNT_NEEDED, CreditsSimulateContract.View.SIMULATION_BY_AMOUNT_CAN_PAY})
    @interface CreditSimulationMode {};
}
