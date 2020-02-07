package com.example.proiectmobilebanking.database.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName="users")
public class User {
    @PrimaryKey(autoGenerate =true)
    @ColumnInfo(name="id")
    private long id;
    @ColumnInfo(name="firstName")
    private String firstName;
    @ColumnInfo(name="lastName")
    private String lastName;
    @ColumnInfo(name="email")
    private String email;
    @ColumnInfo(name="iban")
    private String iban;
    @ColumnInfo(name="password")
    private String password;
    @ColumnInfo(name="confirmPassword")
    private String confirmPassword;
    @ColumnInfo(name="gender")
    private String gender;

    public User(long id, String firstName, String lastName, String email, String iban, String password, String confirmPassword, String gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.iban = iban;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.gender = gender;
    }
    @Ignore
    public User(String firstName, String lastName, String email, String iban, String password, String confirmPassword, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.iban = iban;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.gender = gender;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @NonNull
    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName+ '\'' +
                ", lastName=" +lastName +
                ", email=" + email +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
