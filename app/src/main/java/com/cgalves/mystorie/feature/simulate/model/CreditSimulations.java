package com.cgalves.mystorie.feature.simulate.model;

import com.cgalves.mystorie.feature.simulate.presenter.CreditsSimulateContract;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Scopus
 */

public class CreditSimulations implements Serializable {

    @CreditsSimulateContract.CreditSimulationMode
    public int simulationMode;

    public boolean isUserEligibleForInsurance;

    public List<CreditSimulation> simulations;

    public CreditSimulations() {
        simulations = new ArrayList<>();
    }
}
