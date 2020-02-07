package com.example.proiectmobilebanking.json;

import androidx.annotation.NonNull;

public class InfoExtraTransaction {
        private String adressSender;
        private String telephone;

    public InfoExtraTransaction(String adressSender, String telephone) {
        this.adressSender = adressSender;
        this.telephone = telephone;
    }

    public String getAdressSender() {
        return adressSender;
    }

    public void setAdressSender(String adressSender) {
        this.adressSender = adressSender;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "TransactionExtraInfo{" +
                "AdressSender='" + adressSender + '\'' +
                ", telephone=" + telephone + '\'' +
                '}';
    }
}
