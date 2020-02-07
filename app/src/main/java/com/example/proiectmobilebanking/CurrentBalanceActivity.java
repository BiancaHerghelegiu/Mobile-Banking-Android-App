package com.example.proiectmobilebanking;

import android.content.Intent;
import android.os.Bundle;

import com.example.proiectmobilebanking.database.DatabaseManager;
import com.example.proiectmobilebanking.database.models.Tranzaction;
import com.example.proiectmobilebanking.database.service.TranzactionService;
import com.example.proiectmobilebanking.firebase.FirebaseController;
import com.example.proiectmobilebanking.json.HttpManager;
import com.example.proiectmobilebanking.json.HttpResponse;
import com.example.proiectmobilebanking.json.JsonParser;
import com.example.proiectmobilebanking.util.AdapterTranzactie;
import com.example.proiectmobilebanking.util.AdapterTranzactieJson;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CurrentBalanceActivity extends AppCompatActivity {
long idUser;
Integer sum=0;
TextView currentBalance;
SharedPreferencesUser preferences;
Integer sold;
Integer received;
Button btnRefresh;
    private ArrayList<TranzactionJson> transactions=new ArrayList<TranzactionJson>();
    private static final String URL = "https://api.myjson.com/bins/wivsu";
    private HttpResponse httpResponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_balance);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        currentBalance=findViewById(R.id.sold2);
        preferences=new SharedPreferencesUser(getApplicationContext());
        idUser=preferences.getUser();
        new TranzactionService.GetAllTransactions(getApplicationContext()) {
            @Override
            protected void onPostExecute(
                    List<Tranzaction> results) {
                if (results != null) {
                    for(Tranzaction tranzaction:results){
                        if(tranzaction.getIdUser()==idUser&&tranzaction.getStatus().equals("Send")){
                            sum=sum+tranzaction.getAmount();

                        }

                    }
                    sold=Integer.parseInt(currentBalance.getText().toString());
                    if(sum!=null)
                    {sold=sold-sum;
                        if(sold>0){
                            currentBalance.setText(sold.toString());
                        }
                        else {Toast.makeText(getApplicationContext(),R.string.enough_money,Toast.LENGTH_LONG).show();}}
                    else sold=Integer.parseInt(currentBalance.getText().toString());
            }}
        }.execute();



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
        btnRefresh=findViewById(R.id.btnRefresh);

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addResponse(httpResponse.getTransactionsBRD(),httpResponse.getTransactionsBT(),httpResponse.getTransactionsBCR());
                sold=sold+calculateSum(transactions);
                if(sold>0){
                    currentBalance.setText(sold.toString());
                }
                else {Toast.makeText(getApplicationContext(),R.string.enough_money,Toast.LENGTH_LONG).show();}
            }
        });


    }
    private void addResponse(List<TranzactionJson> list1, List<TranzactionJson> list2, List<TranzactionJson> list3){
        for(int i=0;i<list1.size();i++)
        { if(!transactions.contains(list1.get(i)))
            transactions.add(list1.get(i));}
        for(int i=0;i<list2.size();i++)
        { if(!transactions.contains(list2.get(i)))
            transactions.add(list2.get(i));}
        for(int i=0;i<list3.size();i++)
        { if(!transactions.contains(list3.get(i)))
            transactions.add(list3.get(i));}
    }

    private Integer calculateSum(ArrayList<TranzactionJson> transactions){
        Integer sum=0;
        for(TranzactionJson tranzactionJson :transactions){
            sum=sum+tranzactionJson.getAmount();
        }
        return sum;
    }

}
