package com.example.proiectmobilebanking;

import android.content.Intent;
import android.os.Bundle;

import com.example.proiectmobilebanking.Chart.ChartActivity;
import com.example.proiectmobilebanking.Raports.RaportsActivity;
import com.example.proiectmobilebanking.database.DatabaseManager;
import com.example.proiectmobilebanking.database.models.Tranzaction;
import com.example.proiectmobilebanking.database.models.User;
import com.example.proiectmobilebanking.database.service.TranzactionService;
import com.example.proiectmobilebanking.database.service.UserService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SelectActivity extends AppCompatActivity {
    Button btnInfo;
    Button btnCurrentBal;
//    Button btnTranzactions;
    Button btnHistory;
    Button btnReceivedTransactions;
    FloatingActionButton fabChart;
    public static final int code = 230;
    public static final String ADD_TRANZACTION_HISTORY = "addTranzactionH";
    ArrayList<Tranzaction> transactions = new ArrayList<>();
    User userCuID;
    //DatabaseManager manager;
    long idUser;
    SharedPreferencesUser preferences;
    Button btnDeleteAccount;
    Button btnRaport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        preferences=new SharedPreferencesUser(getApplicationContext());
        idUser=preferences.getUser();
        new UserService.GetAll(getApplicationContext()) {
            @Override
            protected void onPostExecute(
                    List<User> results) {
                if (results != null) {

                    for(User user:results){
                        if(user.getId()==idUser){
                           userCuID=user;

                        }

                    }
                }
            }
        }.execute();
        initComponents();
    }

    public void initComponents() {
        btnInfo = findViewById(R.id.btnInfos);
        btnCurrentBal = findViewById(R.id.btnCurentBal);
        //btnTranzactions = findViewById(R.id.btnTransactions);
        btnHistory = findViewById(R.id.btnHistory);
        btnDeleteAccount=findViewById(R.id.btnDeleteAccount);
        btnRaport=findViewById(R.id.btnRaport);
        fabChart=findViewById(R.id.fabChart);
        btnReceivedTransactions=findViewById(R.id.btnReceivedTransactions);

        btnReceivedTransactions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ReceivedTransactionActivity.class);
                startActivity(intent);
            }
        });
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });

        btnCurrentBal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(getApplicationContext(), CurrentBalanceActivity.class);
                startActivity(intent3);
            }
        });
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(getApplicationContext(), HistoryTransactionsActivity.class);
                startActivity(intent4);
            }
        });
        btnDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new UserService.Delete(getApplicationContext()) {
                    @Override
                    protected void onPostExecute(Integer result) {
                        if (result == 1) {
                            Toast.makeText(getApplicationContext(),getString(R.string.account_erased),Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(getApplicationContext(),
                                    R.string.cannot_delete_user,
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }.execute(userCuID);
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
        btnRaport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), RaportsActivity.class);
                startActivity(intent);
            }
        });
        fabChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), ChartActivity.class);
                startActivity(intent);
            }
        });
    }

    }

