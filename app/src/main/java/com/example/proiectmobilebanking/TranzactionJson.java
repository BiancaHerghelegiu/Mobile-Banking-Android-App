package com.example.proiectmobilebanking;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.proiectmobilebanking.database.models.Tranzaction;
import com.example.proiectmobilebanking.json.InfoExtraTransaction;

public class TranzactionJson implements Parcelable{
    private String beneficiaryName;
    private String accountNumber;
    private String status;
    private Integer amount;
    private InfoExtraTransaction info;

    public TranzactionJson(String beneficiaryName, String accountNumber, String status, Integer amount, InfoExtraTransaction info) {
        this.beneficiaryName = beneficiaryName;
        this.accountNumber = accountNumber;
        this.status = status;
        this.amount = amount;
        this.info = info;
    }

    public TranzactionJson(String beneficiaryName, String accountNumber, Integer amount, String status) {
        this.beneficiaryName = beneficiaryName;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.status=status;
    }

    public InfoExtraTransaction getInfo() {
        return info;
    }

    public void setInfo(InfoExtraTransaction info) {
        this.info = info;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    private TranzactionJson(Parcel in)
    {
        this.beneficiaryName=in.readString();
        this.accountNumber=in.readString();
        this.amount=in.readInt();
        this.status=in.readString();
    }

    @Override
    public String toString() {
        return "Tranzaction{" +
                "beneficiaryName='" + beneficiaryName + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", amount=" + amount +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(beneficiaryName);
        dest.writeString(accountNumber);
        dest.writeInt(amount);
        dest.writeString(status);
    }

    public static Parcelable.Creator<TranzactionJson> CREATOR =
            new Parcelable.Creator<TranzactionJson>() {
                @Override
                public TranzactionJson createFromParcel(Parcel source) {
                    return new TranzactionJson(source);
                }

                @Override
                public TranzactionJson[] newArray(int size) {
                    return new TranzactionJson[size];
                }
            };
}
