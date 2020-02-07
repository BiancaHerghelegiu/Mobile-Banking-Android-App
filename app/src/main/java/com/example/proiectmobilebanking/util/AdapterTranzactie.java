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
import com.example.proiectmobilebanking.database.models.Tranzaction;

import java.util.List;

public class AdapterTranzactie extends ArrayAdapter<Tranzaction> {
    private Context context;
    private int resource;
    private List<Tranzaction> tranzactions;
    private LayoutInflater layout;


    public AdapterTranzactie(@NonNull Context context, int resource, @NonNull List<Tranzaction> tranzactions,LayoutInflater layout) {
        super(context, resource, tranzactions);
        this.context=context;
        this.resource=resource;
        this.tranzactions=tranzactions;
        this.layout=layout;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view=layout.inflate(resource,parent,false);
        Tranzaction tranzaction =tranzactions.get(position);
        if(view==null)
        {
            view = layout.inflate(R.layout.tranzaction_row, null, true);
        }


        TextView tvBenef=view.findViewById(R.id.tv_benef);
        TextView tvAccount=view.findViewById(R.id.tv_account);
        TextView tvAmount=view.findViewById(R.id.tv_amount);
        TextView tvStatus=view.findViewById(R.id.tv_status);

        tranzaction=tranzactions.get(position);
        tvBenef.setText(tranzaction.getBeneficiaryName());
        tvAccount.setText(tranzaction.getAccountNumber());
        tvAmount.setText(""+tranzaction.getAmount());
        tvStatus.setText(tranzaction.getStatus());
        return view;
    }
}
