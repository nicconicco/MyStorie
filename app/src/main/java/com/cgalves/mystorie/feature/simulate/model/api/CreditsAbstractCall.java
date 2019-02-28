package com.cgalves.mystorie.feature.simulate.model.api;

import android.content.Context;
import android.support.annotation.NonNull;

import com.cgalves.mystorie.common.abstractcalls.AbstractCall;
import com.cgalves.mystorie.feature.simulate.presenter.CreditsSimulateContract;

import org.greenrobot.eventbus.EventBus;

import java.util.Date;


/**
 * Created by Scopus
 */

public abstract class CreditsAbstractCall extends AbstractCall {

    public CreditsAbstractCall(EventBus bus, Context context) {
        super(bus, context);
    }

    public abstract void getAvailableCredits();

    public abstract void getCreditsContractedSummary();

    public abstract void getLimits();

    public abstract void getCreditContractedData();

    public abstract void getPlotHistory();

    public abstract void getContractInfo();

    public abstract void getCreditApplicationData();

    public abstract void getInsuranceCoverage();

    public abstract void getAgreementInfo();

    public abstract void getCreditsReceipt();

    public abstract void sendEmailReceiptPDF(String email, String cpf);

    public abstract void getPdfReceipt();

    public abstract void calculateSimulation(@CreditsSimulateContract.CreditSimulationMode int simulationMode, float amount, int installmentCount, @NonNull Date firstInstallmentDate);
}
