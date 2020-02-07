package com.example.proiectmobilebanking;

import android.os.Bundle;

import com.example.proiectmobilebanking.util.Contact;
import com.example.proiectmobilebanking.util.CustomAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {
private List<Contact> contacts=new ArrayList<Contact>();
private ListView lvContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initComponents();


    }
    private void initComponents(){
        initContacts();
        lvContacts=findViewById(R.id.listViewContacts);
        CustomAdapter adapter = new CustomAdapter(getApplicationContext(),
                R.layout.contact_row, contacts, getLayoutInflater());
        lvContacts.setAdapter(adapter);
    }
    private void initContacts(){
        Contact c1=new Contact("Bucharest","0721117890","0234223390","pocketbank.bucharest@poketbank.com");
        Contact c2=new Contact("Cluj-Napoca","0711223344","02354455671","pocketbank.cluj-napoca@poketbank.com");
        Contact c3=new Contact("Iasi","0733456009","0267890098","pocketbank.iasi@poketbank.com");
        Contact c4=new Contact("Barlad","0772097562","0221777456","pocketbank.barlad@poketbank.com");
        contacts.add(c1);
        contacts.add(c2);
        contacts.add(c3);
        contacts.add(c4);
    }
}
