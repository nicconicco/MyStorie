package com.cgalves.mystorie.feature.simulate.model.response;

import com.cgalves.mystorie.common.base.BaseCallback;

import java.io.Serializable;


/**
 * Created Scopus
 */

public class LimitsResponse extends BaseCallback implements Serializable {

    public LimitTotal total = new LimitTotal();
    public LimitUsed used = new LimitUsed();
    public LimitReleased released = new LimitReleased();

    public LimitsResponse(Double limitUsed, Double limitReleased, Double limitTotal) {
        this.total.amountTotal = limitTotal;
        this.released.amountReleased = limitReleased;
        this.used.amountUsed = limitUsed;
    }

    public class LimitUsed {
        public Double amountUsed;
    }

    public class LimitReleased {
        public Double amountReleased;
    }

    public class LimitTotal {
        public Double amountTotal;
    }

}
