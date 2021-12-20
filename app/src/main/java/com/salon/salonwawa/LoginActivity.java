package com.salon.salonwawa;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ua.com.crosp.solutions.library.prettytoast.PrettyToast;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText editTextUsername=findViewById(R.id.editTextUsername);
        final EditText editTextPassword=findViewById(R.id.editTextPassword);

        Button cancel = findViewById(R.id.buttonCancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextPassword.setText("");
                editTextUsername.setText("");
            }
        });

        Button login = findViewById(R.id.buttonLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password=editTextPassword.getText().toString();
                String username=editTextUsername.getText().toString();

                if(!username.equals("")||!password.equals("")){
                    Backend backend=new Backend(LoginActivity.this);
                    backend.login(username,Data.MD5(password));
                }
                else{
                    PrettyToast.showError(getApplicationContext(),"Login gagal");
                }
            }
        });

        Button register = findViewById(R.id.buttonRegister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

    }


}
