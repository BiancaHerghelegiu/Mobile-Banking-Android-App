package com.example.proiectmobilebanking.Raports;

import android.content.Intent;
import android.os.Bundle;

import com.example.proiectmobilebanking.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

public class RaportsActivity extends AppCompatActivity {
    Button btnRaportAmount;
    Button btnRaportSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raports);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    btnRaportAmount=findViewById(R.id.btnRaportAmount);
    btnRaportSend=findViewById(R.id.btnRaportSend);
    btnRaportAmount.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(),RaportAmount.class);
            startActivity(intent);
        }
    });
    btnRaportSend.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(getApplicationContext(),RaportSend.class);
            startActivity(intent);
        }
    });
    }

}
