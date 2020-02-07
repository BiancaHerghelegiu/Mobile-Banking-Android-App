package com.example.proiectmobilebanking;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.proiectmobilebanking.database.DatabaseManager;
import com.example.proiectmobilebanking.database.models.Tranzaction;
import com.example.proiectmobilebanking.database.models.User;
import com.example.proiectmobilebanking.database.service.TranzactionService;
import com.example.proiectmobilebanking.database.service.UserService;
import com.example.proiectmobilebanking.firebase.FirebaseController;
import com.example.proiectmobilebanking.firebase.FirebaseNotifier;
import com.example.proiectmobilebanking.util.Suggestion;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FeedbackFragment extends Fragment implements FirebaseNotifier {
Button btnFeedback;
EditText textFb;
RatingBar rating;
ListView lvSuggestions;
//private static final String SHARED_NAME = "sharedName";
//private static final String RATING_BAR_KEY = "ratingBarKey";
private SharedPreferences preferences;
long idUser;
SharedPreferencesUser preferencesUser;
String email;
String sugestText;
int selected=-1;
private FirebaseController firebaseController;
private List<Suggestion> suggestions=new ArrayList<>();
    public FeedbackFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_feedback, container, false);
        preferencesUser=new SharedPreferencesUser(getContext());
        idUser=preferencesUser.getUser();
        new UserService.GetAll(getContext()) {
            @Override
            protected void onPostExecute(
                    List<User> results) {
                if (results != null) {
                    for(User user:results){
                        if(user.getId()==idUser){
                        email=user.getEmail();
                        }

                    }
                }
            }
        }.execute();
        initComponents(view);
        firebaseController=FirebaseController.getInstance(this);
        findAllByUser();
        return view;
    }
    private void initComponents(View view){
        btnFeedback=view.findViewById(R.id.btnSendFeedback);
        textFb=view.findViewById(R.id.etNumeFeedback);
        rating=view.findViewById(R.id.ratingBar);
        lvSuggestions=view.findViewById(R.id.lvSuggestion);
        ArrayAdapter<Suggestion> adapter=new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,suggestions);
        lvSuggestions.setAdapter(adapter);

        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(valid()) {
                    sugestText=String.valueOf(textFb.getText());
                    Toast.makeText(getContext(),R.string.feedback_send,Toast.LENGTH_LONG).show();
                    String id=null;
                    Suggestion suggestion = new Suggestion(id, idUser, email, sugestText);

                    firebaseController.insert(suggestion);

                }
            }
        });
            if(getActivity()!=null){
                preferences=getActivity().getSharedPreferences("preferencesRating", Context.MODE_PRIVATE); //initializez
                float value=preferences.getFloat("rbKey",-1);
                if(value!=-1){
                    rating.setRating(value);
                }
                rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        SharedPreferences.Editor edit=preferences.edit();
                        edit.putFloat("rbKey",rating);
                        edit.apply();
                    }
                });

            }

           lvSuggestions.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
               @Override
               public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                   firebaseController.delete(suggestions.get(position));
                   Toast.makeText(getContext(),getString(R.string.suggestion_deleted),Toast.LENGTH_LONG).show();
                   return true;
               }
           });
    }

    @Override
    public void suggestionsChanges(List<Suggestion> suggestion) {
        textFb.setText("");
        suggestions.clear();
        suggestions.addAll(suggestion);
        ArrayAdapter adapter = (ArrayAdapter) lvSuggestions.getAdapter();
        adapter.notifyDataSetChanged();

    }

    public boolean valid(){
        if (textFb.getText() == null || textFb.getText().toString().trim().isEmpty()) {
            Toast.makeText(getContext(), R.string.invalid_email, Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public void findAllByUser(){
        FirebaseController.openDb();
        FirebaseController.databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                List<Suggestion> suggestions=new ArrayList<>();
                for(DataSnapshot data:dataSnapshot.getChildren()){
                    Suggestion suggestion=data.getValue(Suggestion.class);
                    if(suggestion!=null){
                        if(suggestion.getUserEmail().equals(email))
                        {suggestions.add(suggestion);}
                    }
                }
                FirebaseController.firebaseNotifier.suggestionsChanges(suggestions);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Report","Not available");
            }
        });}
}
