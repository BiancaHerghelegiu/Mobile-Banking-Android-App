package com.example.proiectmobilebanking.util;

import androidx.annotation.NonNull;

public class Suggestion {
    private String id;
    private long idUser;
    private String userEmail;
    private String suggestion;

    public Suggestion(){

    }
    public Suggestion(String id, long idUser, String userEmail, String suggestion) {
        this.id = id;
        this.idUser = idUser;
        this.userEmail = userEmail;
        this.suggestion = suggestion;
    }

    public Suggestion(long idUser, String userEmail, String suggestion) {
        this.idUser = idUser;
        this.userEmail = userEmail;
        this.suggestion = suggestion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    @NonNull
    @Override
    public String toString() {
        return this.idUser+", "+this.userEmail+", "+this.suggestion;
    }
}
