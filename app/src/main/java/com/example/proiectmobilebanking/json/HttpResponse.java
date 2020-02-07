package com.example.proiectmobilebanking.json;

import androidx.annotation.NonNull;

import com.example.proiectmobilebanking.TranzactionJson;
import com.example.proiectmobilebanking.database.models.Tranzaction;

import java.util.List;

public class HttpResponse {
    public List<TranzactionJson> transactionsBRD;
    public List<TranzactionJson> transactionsBT;
    public List<TranzactionJson> transactionsBCR;

    public HttpResponse(List<TranzactionJson> transactionsBRD, List<TranzactionJson> transactionsBT, List<TranzactionJson> transactionsBCR) {
        this.transactionsBRD = transactionsBRD;
        this.transactionsBT = transactionsBT;
        this.transactionsBCR = transactionsBCR;
    }

    public List<TranzactionJson> getTransactionsBRD() {
        return transactionsBRD;
    }

    public void setTransactionsBRD(List<TranzactionJson> transactionsBRD) {
        this.transactionsBRD = transactionsBRD;
    }

    public List<TranzactionJson> getTransactionsBT() {
        return transactionsBT;
    }

    public void setTransactionsBT(List<TranzactionJson> transactionsBT) {
        this.transactionsBT = transactionsBT;
    }

    public List<TranzactionJson> getTransactionsBCR() {
        return transactionsBCR;
    }

    public void setTransactionsBCR(List<TranzactionJson> transactionsBCR) {
        this.transactionsBCR = transactionsBCR;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
