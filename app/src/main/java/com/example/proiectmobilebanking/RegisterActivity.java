package com.example.proiectmobilebanking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.proiectmobilebanking.database.DatabaseManager;
import com.example.proiectmobilebanking.database.models.User;
import com.example.proiectmobilebanking.database.service.UserService;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {
    Button btnlogin;
    Button btnRegister;
    EditText firstName;
    EditText lastName;
    EditText email;
    EditText iban;
    EditText password;
    EditText confirmPassword;
    RadioGroup rgGender;
    DatabaseManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initComponents();
    }

    private void initComponents() {
        //initilizare controale
        btnlogin = findViewById(R.id.bt_goLogin);
        btnRegister=findViewById(R.id.bt_register);
        firstName = findViewById(R.id.et_firstName);
        lastName=findViewById(R.id.et_lastName);
        email=findViewById(R.id.et_email);
        iban=findViewById(R.id.et_iban);
        password=findViewById(R.id.et_password);
        rgGender=findViewById(R.id.radioGroup);
        confirmPassword=findViewById(R.id.et_confirmPassword);

        manager= Room.databaseBuilder(getApplicationContext(),DatabaseManager.class,"pocketBank").fallbackToDestructiveMigration().build();
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(valid())
                {
                    User user=createUser();
                    new UserService.Insert(getApplicationContext()) {
                        @Override
                        protected void onPostExecute(User result) {
                            if (result != null) {
                               Toast.makeText(getApplicationContext(),R.string.register_succesful,Toast.LENGTH_LONG).show();
                               Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                               startActivity(intent);
                            }
                        }
                    }.execute(user);



                }
            }
        });
    }

    private boolean valid() {
        if (firstName.getText() == null || firstName.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.invalid_firstName, Toast.LENGTH_LONG).show();
            return false;
        }
        if (lastName.getText() == null || lastName.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.invalid_lastName, Toast.LENGTH_LONG).show();
            return false;
        }
        if (email.getText() == null || email.getText().toString().trim().isEmpty()||!email.getText().toString().contains("@")) {
            Toast.makeText(getApplicationContext(), R.string.invalid_email, Toast.LENGTH_LONG).show();
            return false;
        }
        if (iban.getText() == null || iban.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.invalid_iban, Toast.LENGTH_LONG).show();
            return false;
        }
        if (password.getText() == null || password.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.invalid_password, Toast.LENGTH_LONG).show();
            return false;
        }
        if(((password.getText().toString().compareTo(confirmPassword.getText().toString()))!=0) || (confirmPassword.getText() == null || confirmPassword.getText().toString().trim().isEmpty()))
        {
            Toast.makeText(getApplicationContext(), R.string.invalid_password2, Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
    private User createUser(){
        String firstNameS=firstName.getText().toString();
        String lastNameS=lastName.getText().toString();
        String emailS=email.getText().toString();
        String ibans=iban.getText().toString();
        String passwordS=password.getText().toString();
        String confirmPasswordS=confirmPassword.getText().toString();
        RadioButton selectedGender=findViewById(rgGender.getCheckedRadioButtonId());
        String genderS=selectedGender.getText().toString();
        return new User(firstNameS,lastNameS,emailS,ibans,passwordS,confirmPasswordS,genderS);
    }


}
