package com.cgalves.mystorie.feature.simulate.model.response;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Scopus
 */

public class CreditsPlotHistoryResponse implements Serializable {

    public String parcel;
    public Date dataPacel;
    public Double valuePacel;

    public CreditsPlotHistoryResponse(String parcel, Date dataPacel, Double valuePacel) {
        this.parcel = parcel;
        this.dataPacel = dataPacel;
        this.valuePacel = valuePacel;
    }
}
