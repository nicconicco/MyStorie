package com.cgalves.mystorie.feature.simulate.model.response;

import com.cgalves.mystorie.common.base.BaseCallback;
import com.cgalves.mystorie.feature.simulate.presenter.CreditsSimulateContract;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Scopus
 */

public class CreditSimulationsResponse extends BaseCallback {

    @CreditsSimulateContract.CreditSimulationMode
    public int simulationMode;

    public boolean isUserEligibleForInsurance;

    public List<CreditSimulationResponse> simulations;

    public CreditSimulationsResponse() {
        simulations = new ArrayList<>();
    }
}
