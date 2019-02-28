package com.cgalves.mystorie.feature.simulate.model.repository;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Scopus
 */

public class CreditApplication implements Serializable {

    public double creditsValueDescription;
    public String creditsDescription;
    public String productType;
    public double requestedValue;
    public double iofValue;
    public double protectedInsuranceValue;
    public double creditsValue;
    public int parcelCount;
    public double parcelValue;
    public Date firstInstallmentDate;
    public Date lastInstallmentDate;
    public double effectiveInterestRateAM;
    public double effectiveInterestRateAA;
    public double iofTributesValue;
    public double iofTributesRate;
    public double totalPaymentToAuthorizeValue;
    public double totalPaymentToAuthorizeRate;
    public double totalEffectiveCostAM;
    public double totalEffectiveCostAA;

    public CreditApplication(double creditsValueDescription,
                             String creditsDescription,
                             String productType,
                             double requestedValue,
                             double iofValue,
                             double protectedInsuranceValue,
                             double creditsValue,
                             int parcelCount,
                             double parcelValue,
                             Date firstInstallmentDate,
                             Date lastInstallmentDate,
                             double effectiveInsterestRateAM,
                             double effectiveInsterestRateAA,
                             double iofTributesValue,
                             double iofTributesRete,
                             double totalPaymentToAuthorizeValue,
                             double totalPaymentToAuthorizeRate,
                             double totalEffectiveCostAM,
                             double totalEffectiveCostAA) {

        this.creditsDescription = creditsDescription;
        this.creditsValueDescription = creditsValueDescription;
        this.productType = productType;
        this.requestedValue = requestedValue;
        this.iofValue = iofValue;
        this.protectedInsuranceValue = protectedInsuranceValue;
        this.creditsValue = creditsValue;
        this.parcelCount = parcelCount;
        this.parcelValue = parcelValue;
        this.firstInstallmentDate = firstInstallmentDate;
        this.lastInstallmentDate = lastInstallmentDate;
        this.effectiveInterestRateAM = effectiveInsterestRateAM;
        this.effectiveInterestRateAA = effectiveInsterestRateAA;
        this.iofTributesValue = iofTributesValue;
        this.iofTributesRate = iofTributesRete;
        this.totalPaymentToAuthorizeValue = totalPaymentToAuthorizeValue;
        this.totalPaymentToAuthorizeRate = totalPaymentToAuthorizeRate;
        this.totalEffectiveCostAA = totalEffectiveCostAA;
        this.totalEffectiveCostAM = totalEffectiveCostAM;
    }
}
