package com.example.proiectmobilebanking.firebase;

import com.example.proiectmobilebanking.util.Suggestion;

import java.util.List;

public interface FirebaseNotifier {
    void suggestionsChanges(List<Suggestion> suggestions);
}
