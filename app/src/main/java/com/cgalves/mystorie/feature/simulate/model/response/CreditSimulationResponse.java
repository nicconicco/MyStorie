package com.cgalves.mystorie.feature.simulate.model.response;

import java.io.Serializable;

/**
 * Created by Scopus
 */

public class CreditSimulationResponse implements Serializable {

    public boolean isInsuranceSimulation;
    public double amount;
    public double taxes;
    public double installmentAmount; // value is assigned only when 'Quanto você pode pagar por mês' is selected

    public CreditSimulationResponse() {

    }
}
