package com.cgalves.mystorie.feature.simulate.model.repository;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Scopus
 */

public class CreditsPlotHistory implements Serializable {

    public String parcel;
    public Date dataPacel;
    public Double valuePacel;

    public CreditsPlotHistory(String parcel, Date dataPacel, Double valuePacel) {
        this.parcel = parcel;
        this.dataPacel = dataPacel;
        this.valuePacel = valuePacel;
    }
}
