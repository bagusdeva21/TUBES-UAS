package com.salon.salonwawa;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashSet;
import java.util.Set;

public class RegisterActivity extends Activity {

    private EditText editTextUsername, editTextEmail, editTextUmur, editTextNoTelp,
            editTextNama, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextUmur = findViewById(R.id.editTextUmur);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextNama = findViewById(R.id.editTextNama);
        editTextNoTelp = findViewById(R.id.editTextNoTelp);

        Button clear = findViewById(R.id.buttonClear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextPassword.setText("");
                editTextUsername.setText("");
                editTextUmur.setText("");
                editTextNama.setText("");
                editTextNoTelp.setText("");
                editTextEmail.setText("");
            }
        });

        Button register = findViewById(R.id.buttonRegister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user=new User(editTextNama.getText().toString(), editTextUmur.getText().toString(),
                        editTextNoTelp.getText().toString(), editTextEmail.getText().toString(),
                        editTextUsername.getText().toString(), Data.MD5(editTextPassword.getText().toString()));
                Backend backend=new Backend(RegisterActivity.this);
                backend.register(user);
            }
        });

    }

}
