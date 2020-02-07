package com.example.proiectmobilebanking;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUser {
    private final static String SHARED_PREF_NAME = "sharedPrefName";
    private final static String IS_LOGGED = "is_logged";
    private final static String SET_USER= "user";
    SharedPreferences preferences;
    public SharedPreferencesUser(Context context){
        preferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);

    }
    public void isLogged(boolean status){
        SharedPreferences.Editor editor=preferences.edit();
        editor.putBoolean(IS_LOGGED,status);
    }
    public void setUser(long id){
        SharedPreferences.Editor editor=preferences.edit();
        editor.putLong(SET_USER,id);
        editor.apply();
    }
    public long getUser(){
        long id=preferences.getLong(SET_USER,0);
        return id;
    }
}
