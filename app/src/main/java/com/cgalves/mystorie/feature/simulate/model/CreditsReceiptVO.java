package com.cgalves.mystorie.feature.simulate.model;


import java.util.Date;

/**
 * Created by Scopus
 */
public class CreditsReceiptVO {

    // TODO: Verficar se deve utlizar MobileTopUpPostResponseVO

    public Double creditsValueDescription;
    public String creditsDescription;
    public Double requestedValue;
    public Double creditsValue;
    public String formOfPayment;
    public Integer parcelAmount;
    public Double parcelValue;
    public Date dueDate;
    public String authentication;
    public String controlNumber;
    public String bankAuthentication;
    public String receiptPdf;
    public Date date;


    public CreditsReceiptVO(Double creditsValueDescription, String creditsDescription,
                            Double requestedValue, Double creditsValue, String formOfPayment,
                            Integer parcelAmount, Double parcelValue, Date dueDate,
                            String authentication, String controlNumber, String bankAuthentication,
                            String receiptPdf, Date date) {
        this.creditsValueDescription = creditsValueDescription;
        this.creditsDescription = creditsDescription;
        this.requestedValue = requestedValue;
        this.creditsValue = creditsValue;
        this.formOfPayment = formOfPayment;
        this.parcelAmount = parcelAmount;
        this.parcelValue = parcelValue;
        this.dueDate = dueDate;
        this.authentication = authentication;
        this.controlNumber = controlNumber;
        this.bankAuthentication = bankAuthentication;
        this.receiptPdf = receiptPdf;
        this.date = date;
    }
}
