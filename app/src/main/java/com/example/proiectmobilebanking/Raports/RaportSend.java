package com.example.proiectmobilebanking.Raports;

import android.content.Context;
import android.os.Bundle;

import com.example.proiectmobilebanking.SharedPreferencesUser;
import com.example.proiectmobilebanking.database.DatabaseManager;
import com.example.proiectmobilebanking.database.models.Tranzaction;
import com.example.proiectmobilebanking.database.service.TranzactionService;
import com.example.proiectmobilebanking.util.AdapterTranzactie;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.proiectmobilebanking.R;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RaportSend extends AppCompatActivity {
ListView lvSend;
List<String> beneficiars=new ArrayList<>();
long idUser;
SharedPreferencesUser preferences;
Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raport_send);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lvSend=findViewById(R.id.lvRaportSend);
        btnSave=findViewById(R.id.btnSaveRaportSendToTxt);
        preferences=new SharedPreferencesUser(getApplicationContext());
        idUser=preferences.getUser();
        new TranzactionService.GetAllTransactions(getApplicationContext()) {
            @Override
            protected void onPostExecute(
                    List<Tranzaction> results) {
                if (results != null) {
                    for(Tranzaction tranzaction:results){
                        if(tranzaction.getIdUser()==idUser&&tranzaction.getStatus().equals("Send")){
                            beneficiars.add(tranzaction.getBeneficiaryName());
                            ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,beneficiars);
                            lvSend.setAdapter(adapter);

                        }

                    }
                }
            }
        }.execute();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToTxt(beneficiars);
                Toast.makeText(getApplicationContext(),R.string.raport_saved_successfully,Toast.LENGTH_LONG).show();
            }
        });
    }
    private void saveToTxt(List<String> beneficiars){
        try {
            FileOutputStream fileOutputStream=openFileOutput("raportSend.txt", Context.MODE_PRIVATE);
            DataOutputStream out=new DataOutputStream(fileOutputStream);
            for(String beneficiar:beneficiars){
                out.write("Beneficiar: ".getBytes());
                out.write(beneficiar.getBytes());
                out.write("\n".getBytes());
            }
            out.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
