package com.salon.salonwawa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView textViewUser=findViewById(R.id.textViewUser);
        textViewUser.setText(Data.user.username);

        Button profil = findViewById(R.id.buttonProfilUser);
        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ProfilActivity.class));
            }
        });

        Button pesan = findViewById(R.id.buttonPesanSalon);
        pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, PesanActivity.class));

            }
        });

        Button cekInvoice = findViewById(R.id.buttonCekInvoice);
        cekInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Backend backend=new Backend(HomeActivity.this);
                backend.retroGetInvoice();
            }
        });


        Button lokasi = findViewById(R.id.buttonLokasiSalon);
        lokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, LokasiActivity.class));
            }
        });

        Button logout = findViewById(R.id.buttonLogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Backend backend=new Backend(HomeActivity.this);
                backend.logout();
            }
        });
    }

}
