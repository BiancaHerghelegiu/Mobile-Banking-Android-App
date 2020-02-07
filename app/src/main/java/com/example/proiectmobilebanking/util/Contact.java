package com.example.proiectmobilebanking.util;

public class Contact {
    private String location;
    private String mobileTh;
    private String fixTh;
    private String email;

    public Contact(String location, String mobileTh, String fixTh, String email) {
        this.location = location;
        this.mobileTh = mobileTh;
        this.fixTh = fixTh;
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMobileTh() {
        return mobileTh;
    }

    public void setMobileTh(String mobileTh) {
        this.mobileTh = mobileTh;
    }

    public String getFixTh() {
        return fixTh;
    }

    public void setFixTh(String fixTh) {
        this.fixTh = fixTh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
