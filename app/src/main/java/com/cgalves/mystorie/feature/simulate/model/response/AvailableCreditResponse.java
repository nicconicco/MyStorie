package com.cgalves.mystorie.feature.simulate.model.response;

import com.cgalves.mystorie.common.base.BaseCallback;

import java.io.Serializable;

/**
 * Created by Scopus
 */

public class AvailableCreditResponse extends BaseCallback implements Serializable {

    public double creditsAvailable;

    public AvailableCreditResponse(double creditsAvailable) {
        this.creditsAvailable = creditsAvailable;
    }

}
