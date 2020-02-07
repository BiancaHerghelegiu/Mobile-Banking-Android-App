package com.example.proiectmobilebanking.firebase;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.proiectmobilebanking.SharedPreferencesUser;
import com.example.proiectmobilebanking.util.Suggestion;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseController {
    public static DatabaseReference databaseReference;
    public static FirebaseDatabase controller;
    private static FirebaseController firebaseController;
    public static FirebaseNotifier firebaseNotifier;
    SharedPreferencesUser preferences;

    public FirebaseController(FirebaseNotifier firebaseNotifier){
        controller=FirebaseDatabase.getInstance();
        this.firebaseNotifier=firebaseNotifier;
    }

    public static void openDb(){databaseReference=controller.getReference("suggestions");}

    public static FirebaseController getInstance(FirebaseNotifier firebaseNotifier){
        if(firebaseController==null){
            synchronized (FirebaseController.class){
                if(firebaseController==null){
                    firebaseController=new FirebaseController(firebaseNotifier);
                }
            }
        }
        return firebaseController;
    }

    public String insert(Suggestion suggestion){
        if(suggestion==null){
            return null;
        }
        openDb();
        if(suggestion.getId()==null ||suggestion.getId().trim().isEmpty())
        {
            suggestion.setId(databaseReference.push().getKey());
        }
        databaseReference.child(suggestion.getId()).setValue(suggestion);
        databaseReference.child(suggestion.getId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Suggestion temp=dataSnapshot.getValue(Suggestion.class);
                if(temp!=null){
                    Log.i("FireController","Suggestion is updated");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("FirebaseController", "Suggestion is not saved");
            }
        });
        return suggestion.getId();
    }

    public void delete(Suggestion suggestion){
        if(suggestion==null ||suggestion.getId().trim().isEmpty()){
            return;
        }
       openDb();
        databaseReference.child(suggestion.getId()).removeValue();
        databaseReference.child(suggestion.getId()).removeEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.i("FirebaseControl","Suggestion was removed");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("FirebaseControl","Can not delete suggestion");
            }
        });
    }


}
