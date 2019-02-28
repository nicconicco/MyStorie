package com.cgalves.mystorie.feature.simulate.model;

import java.io.Serializable;
import java.util.List;

public class Credits implements Serializable {

    public Double parcel;
    public List<Credit> creditsContracted;
    public Double limitUsed;
    public Double limitReleased;
    public Double limitTotal;

    public Credits() {

    }

    public boolean hasCredits() {
        return !(this.parcel == null);
    }

    public boolean hasCreditsContracted() {
        return !((creditsContracted == null || creditsContracted.isEmpty()));
    }

    public boolean hasLimitFlex() {
        return !((limitUsed == null && limitTotal == null));
    }
}
