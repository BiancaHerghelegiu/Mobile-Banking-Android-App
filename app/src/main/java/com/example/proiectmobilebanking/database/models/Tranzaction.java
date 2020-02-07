package com.example.proiectmobilebanking.database.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.example.proiectmobilebanking.json.InfoExtraTransaction;

import java.io.Serializable;
@Entity(tableName="tranzactions",foreignKeys = @ForeignKey(entity = User.class,parentColumns = "id",childColumns = "idUser",onDelete = ForeignKey.CASCADE),indices=@Index("idUser"))
public class Tranzaction implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="idTranzactie")
    private long idTranzactie;
    @ColumnInfo(name="beneficiaryName")
    private String beneficiaryName;
    @ColumnInfo(name="accountNumber")
    private String accountNumber;
    @ColumnInfo(name="status")
    private String status;
    @ColumnInfo(name="amount")
    private Integer amount;
    @ColumnInfo(name="idUser")
    private long idUser;

    public Tranzaction( String beneficiaryName, String accountNumber, String status, Integer amount, long idUser) {
        this.beneficiaryName = beneficiaryName;
        this.accountNumber = accountNumber;
        this.status = status;
        this.amount = amount;
        this.idUser = idUser;
    }
    @Ignore
    public Tranzaction(String beneficiaryName, String accountNumber, Integer amount, String status) {
        this.beneficiaryName = beneficiaryName;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.status=status;
    }

    public long getIdTranzactie() {
        return idTranzactie;
    }

    public void setIdTranzactie(long idTranzactie) {
        this.idTranzactie = idTranzactie;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
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

    protected Tranzaction(Parcel in)
    {   this.idTranzactie=in.readLong();
        this.beneficiaryName=in.readString();
        this.accountNumber=in.readString();
        this.amount=in.readInt();
        this.status=in.readString();
        this.idUser=in.readLong();
    }

    @Override
    public String toString() {
        return "Tranzaction{" +
                "beneficiaryName='" + beneficiaryName + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", amount=" + amount +
                ", id=" + idTranzactie +
                ", idUser=" + idUser +
                ", status=" + status +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(idTranzactie);
        dest.writeString(beneficiaryName);
        dest.writeString(accountNumber);
        dest.writeInt(amount);
        dest.writeString(status);
        dest.writeLong(idUser);
    }

    public static final Creator<Tranzaction> CREATOR =
            new Creator<Tranzaction>() {
                @Override
                public Tranzaction createFromParcel(Parcel source) {
                    return new Tranzaction(source);
                }

                @Override
                public Tranzaction[] newArray(int size) {
                    return new Tranzaction[size];
                }
            };
}
