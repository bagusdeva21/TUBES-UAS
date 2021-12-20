package com.salon.salonwawa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateProfileActivity extends Activity {

    private EditText editTextUsername, editTextUmur, editTextNoTelp,
            editTextNama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateprofile);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextUmur = findViewById(R.id.editTextUmur);
        editTextNama = findViewById(R.id.editTextNama);
        editTextNoTelp = findViewById(R.id.editTextNoTelp);
        editTextUsername.setText(Data.user.username);
        editTextUmur.setText(Data.user.umur);
        editTextNama.setText(Data.user.nama);
        editTextNoTelp.setText(Data.user.noTelp);

        Button clear = findViewById(R.id.buttonClear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextUsername.setText("");
                editTextUmur.setText("");
                editTextNama.setText("");
                editTextNoTelp.setText("");
            }
        });

        Button register = findViewById(R.id.buttonRegister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data.user.nama=editTextNama.getText().toString();
                Data.user.umur=editTextUmur.getText().toString();
                Data.user.noTelp=editTextNoTelp.getText().toString();
                Data.user.username=editTextUsername.getText().toString();
                Backend backend=new Backend(UpdateProfileActivity.this);
                backend.update(Data.user.username);
                backend.retroPutUser(Data.user);
                Intent i = new Intent(UpdateProfileActivity.this, HomeActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("EXIT", true);
                startActivity(i);
            }
        });

    }

}
