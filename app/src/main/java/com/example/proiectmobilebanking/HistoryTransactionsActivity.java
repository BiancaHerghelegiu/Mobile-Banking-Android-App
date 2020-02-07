package com.example.proiectmobilebanking;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.proiectmobilebanking.database.DatabaseManager;
import com.example.proiectmobilebanking.database.models.User;
import com.example.proiectmobilebanking.database.service.TranzactionService;
import com.example.proiectmobilebanking.database.service.UserService;
import com.example.proiectmobilebanking.json.HttpManager;
import com.example.proiectmobilebanking.json.HttpResponse;
import com.example.proiectmobilebanking.json.JsonParser;
import com.example.proiectmobilebanking.util.AdapterTranzactie;
import com.example.proiectmobilebanking.database.models.Tranzaction;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.proiectmobilebanking.MoneyActivity.ADD_TRANZACTION_HISTORY;

public class HistoryTransactionsActivity extends AppCompatActivity {
private ListView lvHistoryTranz;
private List<Tranzaction> transactions=new ArrayList<Tranzaction>();
    public static final int UPDATE_TRANZ=300;
    public static final int code=230;
    FloatingActionButton fabAdd;
    Button btnSend;
    SharedPreferencesUser preferences;
    int selectTranz;

    long userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_transactions);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lvHistoryTranz=findViewById(R.id.lvHistory);
        addInLvFromDb();
        preferences=new SharedPreferencesUser(getApplicationContext());
        userId=preferences.getUser();
        fabAdd=findViewById(R.id.fabAdd);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(getApplicationContext(),MoneyActivity.class);
                startActivityForResult(it,code);
            }
        });

        lvHistoryTranz.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                createAlertDialog(position);
                return true;
            }
        });


    }


    private void createAlertDialog(final int position){
        final Tranzaction transaction=transactions.get(position);
        AlertDialog dialog=new AlertDialog.Builder(this)
                .setTitle(R.string.delete_dialog_title)
                .setMessage(getString(R.string.delete_dialog_message))
                .setPositiveButton(getString(R.string.yes_alert_dialog),
                        new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                       deleteFromDb(position);
                        lvHistoryTranz.invalidate();
                        addInLvFromDb();
                        Toast.makeText(getApplicationContext(),getString(R.string.transaction_removed),Toast.LENGTH_LONG).show();




            }
        })
                .setNegativeButton(getString(R.string.no_alert_dialog), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),getString(R.string.deletion_canceled),Toast.LENGTH_LONG).show();
            }
        })
                .create();
        dialog.show();
    }

    private void deleteFromDb(final int index){
        new TranzactionService.Delete(getApplicationContext()) {
            @Override
            protected void onPostExecute(Integer result) {
                if (result == 1) {
                    transactions.remove(index);

                } else {
                    Toast.makeText(getApplicationContext(),
                            R.string.cannot_delete_tranzaction,
                            Toast.LENGTH_LONG).show();
                }
            }
        }.execute(transactions.get(index));
    }


    private void addInListView(){
        lvHistoryTranz=findViewById(R.id.lvHistory);
       //lvHistory.invalidate();
    AdapterTranzactie adapter=new AdapterTranzactie(getApplicationContext(),
              R.layout.tranzaction_row,transactions,getLayoutInflater());
        lvHistoryTranz.setAdapter(adapter);
      adapter.notifyDataSetChanged();

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if ((this.code == requestCode) && (resultCode == RESULT_OK) && (data != null)) {
            final Tranzaction transaction = data.getParcelableExtra(ADD_TRANZACTION_HISTORY);

            new TranzactionService.Insert(getApplicationContext()) {
                @Override
                protected void onPostExecute(Tranzaction result) {
                    if (result != null) {
                       //Toast.makeText(getApplicationContext(),getString(R.string.transaction_done_toast),Toast.LENGTH_LONG).show();
                        transaction.setIdUser(userId);
                        lvHistoryTranz.invalidate();
                        addInLvFromDb();

                    }
                }
            }.execute(transaction);

        }}


        private void addInLvFromDb () {
            lvHistoryTranz = findViewById(R.id.lvHistory);
            new TranzactionService.GetAllTransactions(getApplicationContext()) {
                @Override
                protected void onPostExecute(
                        List<Tranzaction> results) {
                    if (results != null) {
                        int exista=0;
                        transactions.clear();
                        for(Tranzaction tranzaction:results){
                            if(tranzaction.getIdUser()==userId){
                                transactions.add(tranzaction);

                            }

                        }
                        if(transactions!=null)
                        {AdapterTranzactie adapter = new AdapterTranzactie(getApplicationContext(),
                                R.layout.tranzaction_row, transactions, getLayoutInflater());
                        lvHistoryTranz.setAdapter(adapter);
                        adapter.notifyDataSetChanged();}
                    }


                    }
            }.execute();
        }
    }


