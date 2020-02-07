package com.example.proiectmobilebanking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proiectmobilebanking.database.DatabaseManager;
import com.example.proiectmobilebanking.database.dao.UserDao;
import com.example.proiectmobilebanking.database.models.User;
import com.example.proiectmobilebanking.database.service.UserService;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
Button btnregister;
Button btnlogin;
EditText etEmail;
EditText etPassword;
   private UserDao userDao;
   private DatabaseManager manager;
   SharedPreferencesUser preferences;
   private String email;
   private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmail=findViewById(R.id.tv_username);
        etPassword=findViewById(R.id.tv_password);
        btnregister=findViewById(R.id.register_button);
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });
        preferences=new SharedPreferencesUser(getApplicationContext());
        btnlogin=findViewById(R.id.login_button);
        email=etEmail.getText().toString();
        password=etPassword.getText().toString();
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new UserService.GetAll(getApplicationContext()) {
                    @Override
                    protected void onPostExecute(
                            List<User> results) {
                        if (results != null) {
                           int exista=0;
                            for(User user:results){
                                if(user.getEmail().equals(etEmail.getText().toString())&& user.getPassword().equals(etPassword.getText().toString())){
                                    preferences.setUser(user.getId());
                                    preferences.isLogged(true);
                                    exista=1;
                                    Intent intent=new Intent(getApplicationContext(),SelectActivity.class);
                                    startActivity(intent);

                                }

                            }
                            if(exista==0){
                                Toast.makeText(getApplicationContext(),R.string.incorrect_user,Toast.LENGTH_LONG).show();
                            }

                        }
                    }
                }.execute();


            }
        });
    }

}

