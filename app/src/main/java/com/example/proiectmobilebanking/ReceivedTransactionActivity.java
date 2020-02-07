package com.example.proiectmobilebanking;

import android.content.Intent;
import android.os.Bundle;

import com.example.proiectmobilebanking.database.models.Tranzaction;
import com.example.proiectmobilebanking.json.HttpManager;
import com.example.proiectmobilebanking.json.HttpResponse;
import com.example.proiectmobilebanking.json.JsonParser;
import com.example.proiectmobilebanking.util.AdapterTranzactie;
import com.example.proiectmobilebanking.util.AdapterTranzactieJson;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ReceivedTransactionActivity extends AppCompatActivity {
    private static final String URL = "https://api.myjson.com/bins/wivsu";
    private HttpResponse httpResponse;
    Button btnReceivedTranz;
    ListView lvReceived;
    Integer sum;
    Button btnSeeCurrentBalance;
    private ArrayList<TranzactionJson> transactions=new ArrayList<TranzactionJson>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_received_transaction);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        addInListView();
        initComponents();
        new HttpManager(){
            @Override
            protected void onPostExecute(String s) {
                httpResponse= JsonParser.parseJson(s);
                if(httpResponse!=null){
                    Toast.makeText(getApplicationContext(),R.string.moneyReceived,Toast.LENGTH_LONG).show();

                }
            }
        }.execute(URL);

    }
    private void initComponents(){
        btnReceivedTranz=findViewById(R.id.btnReceivedTransactions);

        btnReceivedTranz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addResponse(httpResponse.getTransactionsBRD(),httpResponse.getTransactionsBT(),httpResponse.getTransactionsBCR());
            }
        });


    }
    private void addResponse(List<TranzactionJson> list1, List<TranzactionJson> list2, List<TranzactionJson> list3){
//transactions.clear();
        for(int i=0;i<list1.size();i++)
        { if(!transactions.contains(list1.get(i)))
            transactions.add(list1.get(i));}
        for(int i=0;i<list2.size();i++)
        { if(!transactions.contains(list2.get(i)))
            transactions.add(list2.get(i));}
        for(int i=0;i<list3.size();i++)
        { if(!transactions.contains(list3.get(i)))
            transactions.add(list3.get(i));}
        AdapterTranzactieJson adapter=(AdapterTranzactieJson) lvReceived.getAdapter();
        adapter.notifyDataSetChanged();
    }
    private void addInListView(){
        lvReceived=findViewById(R.id.lv_receivedTranzaction);
        lvReceived.invalidate();
        AdapterTranzactieJson adapter=new AdapterTranzactieJson(getApplicationContext(),
                R.layout.tranzaction_row,transactions,getLayoutInflater());
        lvReceived.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }



}
