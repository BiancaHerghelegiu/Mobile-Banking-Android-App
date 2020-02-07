package com.example.proiectmobilebanking.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.proiectmobilebanking.R;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Contact> {

    private Context context;
    private int resource;
    private List<Contact> contacts;
    Contact contact;
    private LayoutInflater layout;
    public CustomAdapter(@NonNull Context context, int resource,List<Contact> contacts, LayoutInflater layout) {
        super(context, resource,contacts);
        this.context=context;
        this.resource=resource;
        this.contacts=contacts;
        this.layout=layout;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       View view=layout.inflate(resource,parent,false);
       Contact contact =contacts.get(position);
       if(view==null)
       {
           view = layout.inflate(R.layout.contact_row, null, true);
       }

           TextView tvlocation=view.findViewById(R.id.tv_location);
           TextView tvtelephone=view.findViewById(R.id.telephone);
           TextView tvfix=view.findViewById(R.id.fix);
           TextView email=view.findViewById(R.id.email);

           contact=contacts.get(position);
           tvlocation.setText(contact.getLocation());
           tvtelephone.setText(contact.getMobileTh());
           tvfix.setText(contact.getFixTh());
           email.setText(contact.getEmail());
       return view;
    }
}
