package com.cgalves.mystorie.feature.simulate.model.response;

import java.io.Serializable;

/**
 * Created by Scopus
 */

public class CreditContractedSummaryResponse implements Serializable {

    public String bank;
    public Credit contracted;

    public CreditContractedSummaryResponse(String description, Double payOut, Double contracted, String bank) {
        this.bank = bank;
        this.contracted = new Credit();

        this.contracted.description = description;
        this.contracted.payOut = payOut;
        this.contracted.contractedValue = contracted;
    }

    public class Credit {

        public String description;
        public Double payOut;
        public Double contractedValue;

    }
}
