package com.cgalves.mystorie.feature.simulate.model.repository;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.cgalves.mystorie.common.base.ReturnSystemVO;
import com.cgalves.mystorie.common.factory.APIAbstractFactory;
import com.cgalves.mystorie.common.presenter.BaseErrorCallback;
import com.cgalves.mystorie.common.presenter.BaseRepository;
import com.cgalves.mystorie.feature.simulate.model.Credit;
import com.cgalves.mystorie.feature.simulate.model.CreditSimulation;
import com.cgalves.mystorie.feature.simulate.model.CreditSimulations;
import com.cgalves.mystorie.feature.simulate.model.Credits;
import com.cgalves.mystorie.feature.simulate.model.CreditsReceiptVO;
import com.cgalves.mystorie.feature.simulate.model.api.CreditsAbstractCall;
import com.cgalves.mystorie.feature.simulate.model.response.AgreementInfoResponse;
import com.cgalves.mystorie.feature.simulate.model.response.AvailableCreditResponse;
import com.cgalves.mystorie.feature.simulate.model.response.ContractInfoResponse;
import com.cgalves.mystorie.feature.simulate.model.response.CreditApplicationResponse;
import com.cgalves.mystorie.feature.simulate.model.response.CreditContractedResponse;
import com.cgalves.mystorie.feature.simulate.model.response.CreditContractedSummaryResponse;
import com.cgalves.mystorie.feature.simulate.model.response.CreditSimulationResponse;
import com.cgalves.mystorie.feature.simulate.model.response.CreditSimulationsResponse;
import com.cgalves.mystorie.feature.simulate.model.response.CreditsPlotHistoryListResponse;
import com.cgalves.mystorie.feature.simulate.model.response.CreditsPlotHistoryResponse;
import com.cgalves.mystorie.feature.simulate.model.response.EmailSentResponse;
import com.cgalves.mystorie.feature.simulate.model.response.LimitsResponse;
import com.cgalves.mystorie.feature.simulate.model.response.PdfReceiptResponse;
import com.cgalves.mystorie.feature.simulate.presenter.CreditsSimulateContract;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created Scopus
 */

public class CreditsRepository extends BaseRepository {

    private static final String TAG = CreditsRepository.class.getSimpleName();

    private CreditsAbstractCall creditsAbstractCall;

    public interface CreditsCallback<T> extends BaseErrorCallback {
        void onSuccess(T creditsVO);
    }

    public CreditsRepository(Context context) {
        super(context);

        creditsAbstractCall = APIAbstractFactory
                .getFactory(context)
                .getCreditsCall(EventBus.getDefault(), context);
    }

    // -------------------------------------- //

    @Subscribe(sticky = false, threadMode = ThreadMode.MAIN)
    public void onError(String error) {
        if (getCreditsCallback != null) {
            getCreditsCallback.onError(error);
        }
    }

    @Subscribe(sticky = false, threadMode = ThreadMode.MAIN)
    public void onError(ReturnSystemVO returnSystemVO) {
        if (getCreditsCallback != null) {
            getCreditsCallback.onError(returnSystemVO);
        }
    }

    // -------------------------------------- //


    // 1 -

    private CreditsCallback<Credits> getCreditsCallback;
    private Credits credits = new Credits();

    public void getCredits(CreditsCallback<Credits> callback) {
        this.getCreditsCallback = callback;

        creditsAbstractCall.getAvailableCredits();
    }

    @Subscribe(sticky = false, threadMode = ThreadMode.MAIN)
    public void onAvailableCreditsLoaded(AvailableCreditResponse credits) {
        Log.d(TAG, "onAvailableCreditsLoaded");

        this.credits.parcel = credits.creditsAvailable;

        creditsAbstractCall.getCreditsContractedSummary();
    }

    @Subscribe(sticky = false, threadMode = ThreadMode.MAIN)
    public void onCreditsContractedSummaryLoaded(List<CreditContractedSummaryResponse> creditsContracted) {
        Log.d(TAG, "onCreditsContractedSummaryLoaded");

        credits.creditsContracted = new ArrayList<>();

        for (CreditContractedSummaryResponse creditContracted : creditsContracted) {
            Credit credit = new Credit();

            credit.creditDescription = creditContracted.contracted.description;
            credit.amountPaid = creditContracted.contracted.payOut;
            credit.contracted = creditContracted.contracted.contractedValue;

            credits.creditsContracted.add(credit);
        }

        creditsAbstractCall.getLimits();
    }

    @Subscribe(sticky = false, threadMode = ThreadMode.MAIN)
    public void onLimitsLoaded(LimitsResponse limits) {
        Log.d(TAG, "onLimitsLoaded");

        credits.limitUsed = limits.used.amountUsed;
        credits.limitReleased = limits.released.amountReleased;
        credits.limitTotal = limits.total.amountTotal;

        if (getCreditsCallback != null) {
            getCreditsCallback.onSuccess(credits);
        }
    }

    // 1 -  End

    // 2

    private CreditsCallback<CreditContracted> getCreditContractedDataCallback;
    private CreditsCallback<CreditsPlotHistoryList> getPlotHistoryCallback;

    public void getCreditContractedData(CreditsCallback<CreditContracted> getCreditContractedDataCallback) {
        this.getCreditContractedDataCallback = getCreditContractedDataCallback;

        creditsAbstractCall.getCreditContractedData();
    }

    @Subscribe(sticky = false, threadMode = ThreadMode.MAIN)
    public void onGetCreditContractedDataLoaded(CreditContractedResponse creditContractedResponse) {
        Log.d(TAG, "repository - onGetCreditContractedDataLoaded");

        CreditContracted creditContracted = new CreditContracted(
                creditContractedResponse.creditValueDescription,
                creditContractedResponse.creditDescription,
                creditContractedResponse.productType,
                creditContractedResponse.requestedValue,
                creditContractedResponse.iofValue,
                creditContractedResponse.protectedInsuranceValue,
                creditContractedResponse.creditValue,
                creditContractedResponse.parcelCount,
                creditContractedResponse.parcelValue,
                creditContractedResponse.firstInstallmentDate,
                creditContractedResponse.lastInstallmentDate,
                creditContractedResponse.effectiveInsterestRateAM,
                creditContractedResponse.effectiveInsterestRateAA,
                creditContractedResponse.iofTributesValue,
                creditContractedResponse.iofTributesRate,
                creditContractedResponse.totalPaymentToAuthorizeValue,
                creditContractedResponse.totalPaymentsToAuthorizeRate,
                creditContractedResponse.totalEffectiveCostAM,
                creditContractedResponse.totalEffectiveCostAA
        );

        if (getCreditContractedDataCallback != null) {
            getCreditContractedDataCallback.onSuccess(creditContracted);
        }
    }

    public void getPlotHistory(CreditsCallback<CreditsPlotHistoryList> callback) {
        this.getPlotHistoryCallback = callback;

        creditsAbstractCall.getPlotHistory();
    }

    @Subscribe(sticky = false, threadMode = ThreadMode.MAIN)
    public void onCreditsPlotHistory(CreditsPlotHistoryListResponse creditsPlotHistoryListResponse) {
        Log.d(TAG, "onCreditsPlotHistory");

        CreditsPlotHistoryList creditsPlotHistoryList = new CreditsPlotHistoryList();

        creditsPlotHistoryList.plotsHistory = new ArrayList<>();

        for (CreditsPlotHistoryResponse creditsPlotHistoryResponse : creditsPlotHistoryListResponse.plotsHistory) {
            CreditsPlotHistory creditsPlotHistory = new CreditsPlotHistory(
                    creditsPlotHistoryResponse.parcel,
                    creditsPlotHistoryResponse.dataPacel,
                    creditsPlotHistoryResponse.valuePacel
            );

            creditsPlotHistoryList.plotsHistory.add(creditsPlotHistory);
        }

        if (getPlotHistoryCallback != null) {
            getPlotHistoryCallback.onSuccess(creditsPlotHistoryList);
        }
    }

    // 2 - End

    // 3

    private CreditsCallback<ContractInfo> getContractInfoCallback;
    private CreditsCallback<CreditApplication> getCreditApplicationDataCallback;
    private CreditsCallback<Map<String, String>> getInsuranceCoverageCallback;
    private CreditsCallback<AgreementInfo> getAgreementInfoCallback;

    public void getContractInfo(CreditsCallback<ContractInfo> getContractInfoCallback) {
        this.getContractInfoCallback = getContractInfoCallback;

        creditsAbstractCall.getContractInfo();
    }

    @Subscribe(sticky = false, threadMode = ThreadMode.MAIN)
    public void onContractInfoLoaded(ContractInfoResponse contractInfoResponse) {
        if (this.getContractInfoCallback != null) {

            ContractInfo contractInfo = new ContractInfo();
            contractInfo.contractInfo = contractInfoResponse.contractInfo;

            this.getContractInfoCallback.onSuccess(contractInfo);
        }
    }

    public void getCreditApplicationData(CreditsCallback<CreditApplication> getCreditApplicationDataCallback) {
        this.getCreditApplicationDataCallback = getCreditApplicationDataCallback;

        creditsAbstractCall.getCreditApplicationData();
    }

    @Subscribe(sticky = false, threadMode = ThreadMode.MAIN)
    public void onGetCreditApplicationDataLoaded(CreditApplicationResponse creditApplicationResponse) {
        Log.d(TAG, "repository - onCreditApplicationDataLoaded");

        CreditApplication creditApplication = new CreditApplication(
                creditApplicationResponse.creditValueDescription,
                creditApplicationResponse.creditDescription,
                creditApplicationResponse.productType,
                creditApplicationResponse.requestedValue,
                creditApplicationResponse.iofValue,
                creditApplicationResponse.protectedInsuranceValue,
                creditApplicationResponse.creditValue,
                creditApplicationResponse.parcelCount,
                creditApplicationResponse.parcelValue,
                creditApplicationResponse.firstInstallmentDate,
                creditApplicationResponse.lastInstallmentDate,
                creditApplicationResponse.effectiveInsterestRateAM,
                creditApplicationResponse.effectiveInsterestRateAA,
                creditApplicationResponse.iofTributesValue,
                creditApplicationResponse.iofTributesRate,
                creditApplicationResponse.totalPaymentToAuthorizeValue,
                creditApplicationResponse.totalPaymentsToAuthorizeRate,
                creditApplicationResponse.totalEffectiveCostAM,
                creditApplicationResponse.totalEffectiveCostAA
        );

        if (getCreditApplicationDataCallback != null) {
            getCreditApplicationDataCallback.onSuccess(creditApplication);
        }
    }

    public void getInsuranceCoverage(CreditsCallback<Map<String, String>> getInsuranceCoverageCallback) {
        this.getInsuranceCoverageCallback = getInsuranceCoverageCallback;

        creditsAbstractCall.getInsuranceCoverage();
    }

    @Subscribe(sticky = false, threadMode = ThreadMode.MAIN)
    public void onGetInsuranceCoverageLoaded(Map<String, String> result) {
        if (this.getInsuranceCoverageCallback != null) {
            this.getInsuranceCoverageCallback.onSuccess(result);
        }
    }

    public void getAgreementInfo(CreditsCallback<AgreementInfo> getAgreementInfoCallback) {
        this.getAgreementInfoCallback = getAgreementInfoCallback;
        creditsAbstractCall.getAgreementInfo();
    }

    @Subscribe(sticky = false, threadMode = ThreadMode.MAIN)
    public void onAgreementInfoLoaded(AgreementInfoResponse agreementInfoResponse) {
        if (this.getAgreementInfoCallback != null) {

            AgreementInfo agreementInfo = new AgreementInfo();
            agreementInfo.agreementInfo = agreementInfoResponse.agreementInfo;

            this.getAgreementInfoCallback.onSuccess(agreementInfo);
        }
    }

    // 3 - End

    // 4

    private CreditsCallback<CreditsReceiptVO> getReceiptCallback;

    public void getCreditsReceipt(CreditsCallback<CreditsReceiptVO> callback) {
        this.getReceiptCallback = callback;
        creditsAbstractCall.getCreditsReceipt();
    }

    @Subscribe(sticky = false, threadMode = ThreadMode.MAIN)
    public void onCreditsReceipt(CreditsReceiptVO creditsReceiptVO) {
        getReceiptCallback.onSuccess(creditsReceiptVO);
    }

    // 4 - End

    // 5

    private CreditsCallback<CreditSimulations> creditSimulationCallback;

    public void calculateSimulation(@CreditsSimulateContract.CreditSimulationMode int simulationMode, float amount, int installmentCount, @NonNull Date firstInstallmentDate, CreditsCallback<CreditSimulations> creditSimulationCallback) {
        this.creditSimulationCallback = creditSimulationCallback;

        creditsAbstractCall.calculateSimulation(simulationMode, amount, installmentCount, firstInstallmentDate);
    }

    @Subscribe(sticky = false, threadMode = ThreadMode.MAIN)
    public void onCalculateSimulationLoaded(CreditSimulationsResponse creditSimulationsResponse) {
        if (this.creditSimulationCallback != null) {

            CreditSimulations creditSimulations = new CreditSimulations();

            creditSimulations.simulationMode = creditSimulationsResponse.simulationMode;
            creditSimulations.isUserEligibleForInsurance = creditSimulationsResponse.isUserEligibleForInsurance;

            for (CreditSimulationResponse creditSimulationResponse : creditSimulationsResponse.simulations) {
                CreditSimulation simulation = new CreditSimulation();

                simulation.isInsuranceSimulation = creditSimulationResponse.isInsuranceSimulation;
                simulation.amount = creditSimulationResponse.amount;
                simulation.taxes = creditSimulationResponse.taxes;
                simulation.installmentAmount = creditSimulationResponse.installmentAmount;

                creditSimulations.simulations.add(simulation);
            }

            this.creditSimulationCallback.onSuccess(creditSimulations);
        }
    }

    // 5 - End

    private CreditsCallback<EmailSentResponse> emailSentCallback;

    public void sendEmailReceiptPDF(String email, String cpf, CreditsCallback<EmailSentResponse> callback){
        this.emailSentCallback = callback;

        creditsAbstractCall.sendEmailReceiptPDF(email, cpf);
    }

    @Subscribe(sticky = false, threadMode = ThreadMode.MAIN)
    public void onEmailSent(EmailSentResponse object) {
        this.emailSentCallback.onSuccess(object);
    }

    private CreditsCallback<PdfReceiptResponse> pdfReceiptCallback;

    public void getPDFReceipt(CreditsCallback<PdfReceiptResponse> callback){
        this.pdfReceiptCallback = callback;

        creditsAbstractCall.getPdfReceipt();
    }

    @Subscribe(sticky = false, threadMode = ThreadMode.MAIN)
    public void onPdfREceiptReceived(PdfReceiptResponse object) {
        if(this.pdfReceiptCallback!=null)
            this.pdfReceiptCallback.onSuccess(object);
    }




}
