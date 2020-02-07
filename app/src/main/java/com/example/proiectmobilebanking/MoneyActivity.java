package com.example.proiectmobilebanking;

import android.content.Intent;
import android.os.Bundle;

import com.example.proiectmobilebanking.database.DatabaseManager;
import com.example.proiectmobilebanking.database.models.Tranzaction;
import com.example.proiectmobilebanking.database.models.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MoneyActivity extends AppCompatActivity {
    public static final String ADD_TRANZACTION_HISTORY = "addTranzactionH";

    EditText etBeneficiar;
    EditText etAccount;
    EditText etAmount;
    Button btnSend;
    Spinner spnStatus;
    long idUserTransaction;
    SharedPreferencesUser preferences;
    Intent intent;
    Tranzaction edit;
    String tip;
    public static final int code=230;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        preferences=new SharedPreferencesUser(getApplicationContext());
        idUserTransaction=preferences.getUser();
        intent=getIntent();

        initComponents();
  }

    public void initComponents(){
        etBeneficiar=findViewById(R.id.et_beneficiary2);
        etAccount=findViewById(R.id.et_accountNumber2);
        etAmount=findViewById(R.id.et_amount2);
        spnStatus=findViewById(R.id.spinner_status);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(getApplicationContext(),R.array.spinner_status,R.layout.support_simple_spinner_dropdown_item);
        spnStatus.setAdapter(adapter);
        btnSend=findViewById(R.id.btn_send2);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(valid())
                {
                    final Tranzaction tranzaction=createTranzaction();

                intent.putExtra(ADD_TRANZACTION_HISTORY,tranzaction);
                    setResult(RESULT_OK, intent);
                    Toast.makeText(getApplicationContext(),R.string.money_send,Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

    }
    public void editTranzaction(View view){

        String benef=etBeneficiar.getText().toString();
        edit.setBeneficiaryName(benef);
        String account=etAccount.getText().toString();
        edit.setAccountNumber(account);
        Integer amount=Integer.parseInt(etAmount.getText().toString());
        edit.setAmount(amount);
        String status=spnStatus.getSelectedItem().toString();
        edit.setStatus(status);
        intent.putExtra("editat",edit);
        setResult(RESULT_OK,intent);
        finish();

    }
    private boolean valid()
    {
        if(etBeneficiar.getText().toString()==null||etBeneficiar.getText().toString().trim().isEmpty())
        {
            Toast.makeText(getApplicationContext(),R.string.error_beneficiar,Toast.LENGTH_LONG).show();
            return false;
        }
        if(etAccount.getText().toString()==null||etAccount.getText().toString().trim().isEmpty())
        {
            Toast.makeText(getApplicationContext(),R.string.error_account,Toast.LENGTH_LONG).show();
            return false;
        }
        if(etAmount.getText().toString()==null||etAmount.getText().toString().trim().isEmpty())
        {
            Toast.makeText(getApplicationContext(),R.string.error_amount,Toast.LENGTH_LONG).show();
            return false;
        }


            return true;
    }
    private Tranzaction createTranzaction(){
        String benef=etBeneficiar.getText().toString();
        String account=etAccount.getText().toString();
        Integer amount=Integer.parseInt(etAmount.getText().toString());
        String status=spnStatus.getSelectedItem().toString();
        return new Tranzaction(benef,account,status,amount,idUserTransaction);
    }

    private void onUpdate(Tranzaction tranzaction){
        etBeneficiar.setText(tranzaction.getBeneficiaryName());
            etAccount.setText(tranzaction.getAccountNumber());
            etAmount.setText(tranzaction.getAmount());
        if(tranzaction.getStatus()!=null){
            ArrayAdapter<String> adapter = (ArrayAdapter<String>) spnStatus.getAdapter();
            for (int i = 0; i < adapter.getCount(); i++) {
                if (adapter.getItem(i).equals(tranzaction.getStatus())) {
                    spnStatus.setSelection(i);
                    break;
                }
            }
        }

    }

}
