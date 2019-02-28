package com.cgalves.mystorie.model.factory;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.cgalves.mystorie.R;
import com.cgalves.mystorie.common.utils.DateDeserializer;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreditCardCallImpl extends CreditsAbstractCall {

    private Gson gson;

    public CreditCardCallImpl(EventBus bus, Context context) {
        super(bus, context);

        gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializer()).create();
    }

    @Override
    public void getAvailableCredits() {

        AvailableCreditResponse creditsResponse = new AvailableCreditResponse(200.0);

        post(creditsResponse, bus);
    }

    @Override
    public void getCreditsContractedSummary() {

        List<CreditContractedSummaryResponse> creditsContractedSummaryResponse = new ArrayList<>();

        creditsContractedSummaryResponse.add(
                new CreditContractedSummaryResponse("Casamento", 200.0, 5000.0, "Next"));

        creditsContractedSummaryResponse.add(
                new CreditContractedSummaryResponse("MacBook", 0.0, 16000.0, "Bradesco"));

        creditsContractedSummaryResponse.add(
                new CreditContractedSummaryResponse("Cell", 900.35, 1000.20, "Next"));

        post(creditsContractedSummaryResponse, bus);

    }

    @Override
    public void getLimits() {
        LimitsResponse limitsResponse = new LimitsResponse(300.0, 10000.55, 20000.45);

        post(limitsResponse, bus);
    }

    @Override
    public void getCreditContractedData() {

        CreditContractedResponse creditContractedResponse = new CreditContractedResponse(
                10000.0, "Casamento", "Crédito parcelado", 5000.0,
                57.80, 120.0, 11930.40, 48, 237.98,
                new Date(), new Date(), 1.66, 1.44,
                57.80, 1.44,
                57.80, 1.14,
                1.66, 1.14);

        post(creditContractedResponse, bus);
    }

    @Override
    public void getPlotHistory() {
        // TODO ainda não tem o swagger correspondente a CreditsPlotHistoryListResponse e CreditsPlotHistoryResponse

        CreditsPlotHistoryListResponse plotHistoryList = new CreditsPlotHistoryListResponse();
        plotHistoryList.plotsHistory = new ArrayList<>();

        plotHistoryList.plotsHistory.add(new CreditsPlotHistoryResponse("01", new Date(), 100.0));
        plotHistoryList.plotsHistory.add(new CreditsPlotHistoryResponse("02", new Date(), 140.0));
        plotHistoryList.plotsHistory.add(new CreditsPlotHistoryResponse("03", new Date(), 100.05));
        plotHistoryList.plotsHistory.add(new CreditsPlotHistoryResponse("04", new Date(), 100.0));
        plotHistoryList.plotsHistory.add(new CreditsPlotHistoryResponse("05", new Date(), 1060.6));
        plotHistoryList.plotsHistory.add(new CreditsPlotHistoryResponse("06", new Date(), 170.0));

        post(plotHistoryList, bus);
    }

    @Override
    public void getCreditApplicationData() {

        CreditApplicationResponse creditApplicationResponse = new CreditApplicationResponse(
                10000.0, "Casamento", "Crédito parcelado", 5000.0,
                57.80, 120.0, 11930.40, 48, 237.98,
                new Date(), new Date(), 1.66, 1.44,
                57.80, 1.44,
                57.80, 1.14,
                1.66, 1.14);

        post(creditApplicationResponse, bus);
    }

    @Override
    public void getInsuranceCoverage() {

        final String[] coverage = context.getResources().getStringArray(R.array.credit_installment_insurance_info_coverage);

        Map<String, String> result = new HashMap<>();

        boolean isKey = true;
        String key = "";

        for (String item : coverage) {
            if (isKey) {
                key = item;
            } else {
                result.put(key, item);
                key = "";
            }

            isKey = !isKey;
        }

        post(result, bus);
    }

    @Override
    public void getContractInfo() {

        ContractInfoResponse contractInfoResponse = new ContractInfoResponse();
        contractInfoResponse.contractInfo = context.getString(R.string.credit_installment_confirmation_contract_info);

        post(contractInfoResponse, bus);
    }

    @Override
    public void getAgreementInfo() {

        AgreementInfoResponse agreementInfoResponse = new AgreementInfoResponse();
        agreementInfoResponse.agreementInfo = context.getString(R.string.credit_installment_confirmation_agreement_info);

        post(agreementInfoResponse, bus);
    }

    @Override
    public void getCreditsReceipt() {

        Date date;

        try {
            date = new SimpleDateFormat("YYYY-MM-dd").parse("2018-04-11");
        } catch (Exception e) {
            date = new Date();
            e.printStackTrace();
        }


        CreditsReceiptVO creditsReceiptVO = new CreditsReceiptVO(1000.0,
                "Casamento",
                1000.0, 500.0, "Débito",
                48, 237.45, date,
                "rhHT@wNp Bn57OFp6 Hp9usN#l 8vrYENDF *7xTA#nS x7#GNJEI gvp4UHwo 6wdLy6O3 uykC*VOv eeDJAptr rf2@SNˆG *VIhqwRP uva2nnLg RltMxUqd uWVIqzQG RyfWixFW mny5vRsc MYfG*b#M YH8h*fFz T?mLBb4T q5zqBSz* O3?SMwCa 38072196 28643030",
                "660.730.212.728.50", "045.098.327", "", new Date());

        post(creditsReceiptVO, bus);
    }

    @Override
    public void sendEmailReceiptPDF(String email, String cpf) {
        EmailSentResponse emailSentResponse = new EmailSentResponse();
        emailSentResponse.message = "teste@teste.com.br";
        post(emailSentResponse, bus);
    }

    @Override
    public void getPdfReceipt() {
        InputStream inputStream = context.getResources().openRawResource(R.raw.pdf_mock_base64);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String jsonString = writer.toString();
        PdfReceiptResponse pdfReceiptResponse = gson.fromJson(jsonString, PdfReceiptResponse.class);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        post(pdfReceiptResponse, bus);
    }

    private boolean isUserEligibleForInsurance = false;

    @Override
    public void calculateSimulation(int simulationMode, float amount, int installmentCount, @NonNull Date firstInstallmentDate) {
        // TODO ainda não tem o swagger com o endpoint que retorna simulações de empréstimo para popular o CreditSimulationsResponse

        this.isUserEligibleForInsurance = (!this.isUserEligibleForInsurance);

        CreditSimulationsResponse creditSimulationResponse = new CreditSimulationsResponse();

        creditSimulationResponse.simulationMode = simulationMode;
        creditSimulationResponse.isUserEligibleForInsurance = isUserEligibleForInsurance;

        // simulation 1
        creditSimulationResponse.simulations.add(getSimulation(simulationMode, creditSimulationResponse.isUserEligibleForInsurance));

        // simulation 2, if applicable
        if (creditSimulationResponse.isUserEligibleForInsurance) {
            creditSimulationResponse.simulations.add(getSimulation(simulationMode, false));
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        post(creditSimulationResponse, bus);
    }

    private CreditSimulationResponse getSimulation(@CreditsSimulateContract.CreditSimulationMode int simulationMode, boolean isInsuranceSimulation) {

        CreditSimulationResponse simulation = new CreditSimulationResponse();

        simulation.isInsuranceSimulation = isInsuranceSimulation;

        if (simulationMode == CreditsSimulateContract.View.SIMULATION_BY_AMOUNT_NEEDED) {
            simulation.amount = 1800.0;
        } else {
            simulation.amount = 500.0;
        }

        simulation.taxes = 2.5;

        if (simulationMode == CreditsSimulateContract.View.SIMULATION_BY_AMOUNT_CAN_PAY) {
            simulation.installmentAmount = 1500.0;
        }

        return simulation;
    }

    @Override
    protected void post(final Object t, final EventBus bus) {
        new Handler().postDelayed(() -> CreditCardCallImpl.super.post(t, bus), 800);
    }
}
