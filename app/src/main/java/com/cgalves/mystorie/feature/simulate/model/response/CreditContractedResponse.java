package com.cgalves.mystorie.feature.simulate.model.response;

import com.cgalves.mystorie.common.base.BaseCallback;

import java.util.Date;


/**
 * Created by Scopus
 */

public class CreditContractedResponse extends BaseCallback {

    public double creditValueDescription;
    public String creditDescription;
    public String productType;
    public double requestedValue;
    public double iofValue;
    public double protectedInsuranceValue;
    public double creditValue;
    public int parcelCount;
    public double parcelValue;
    public Date firstInstallmentDate;
    public Date lastInstallmentDate;
    public double effectiveInsterestRateAM;
    public double effectiveInsterestRateAA;
    public double iofTributesValue;
    public double iofTributesRate;
    public double totalPaymentToAuthorizeValue;
    public double totalPaymentsToAuthorizeRate;
    public double totalEffectiveCostAM;
    public double totalEffectiveCostAA;

    public CreditContractedResponse(double creditValueDescription,
                                    String creditDescription,
                                    String productType,
                                    double requestedValue,
                                    double iofValue,
                                    double protectedInsuranceValue,
                                    double creditValue,
                                    int parcelCount,
                                    double parcelValue,
                                    Date firstInstallmentDate,
                                    Date lastInstallmentDate,
                                    double effectiveInsterestRateAM,
                                    double effectiveInsterestRateAA,
                                    double iofTributesValue,
                                    double iofTributesRete,
                                    double totalPaymentToAuthorizeValue,
                                    double totalPaymentsToAuthorizeRate,
                                    double totalEffectiveCostAM,
                                    double totalEffectiveCostAA) {

        this.creditDescription = creditDescription;
        this.creditValueDescription = creditValueDescription;
        this.productType = productType;
        this.requestedValue = requestedValue;
        this.iofValue = iofValue;
        this.protectedInsuranceValue = protectedInsuranceValue;
        this.creditValue = creditValue;
        this.parcelCount = parcelCount;
        this.parcelValue = parcelValue;
        this.firstInstallmentDate = firstInstallmentDate;
        this.lastInstallmentDate = lastInstallmentDate;
        this.effectiveInsterestRateAM = effectiveInsterestRateAM;
        this.effectiveInsterestRateAA = effectiveInsterestRateAA;
        this.iofTributesValue = iofTributesValue;
        this.iofTributesRate = iofTributesRete;
        this.totalPaymentToAuthorizeValue = totalPaymentToAuthorizeValue;
        this.totalPaymentsToAuthorizeRate = totalPaymentsToAuthorizeRate;
        this.totalEffectiveCostAA = totalEffectiveCostAA;
        this.totalEffectiveCostAM = totalEffectiveCostAM;
    }
}
