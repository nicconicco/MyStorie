package com.cgalves.mystorie.feature.simulate.model;

import java.io.Serializable;

/**
 * Created by Scopus
 */

public class CreditSimulation implements Serializable {

    public boolean isInsuranceSimulation;
    public double amount;
    public double taxes;
    public double installmentAmount; // value is assigned only when 'Quanto você pode pagar por mês' is selected

    public CreditSimulation() {

    }
}
