package com.salon.salonwawa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class PesanActivity extends Activity {

    Spinner spinnerTukangCukur;
    EditText editTextHari,editTextTanggal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan);

        editTextHari=findViewById(R.id.editTextHari);
        editTextTanggal=findViewById(R.id.editTextTanggal);

        spinnerTukangCukur = findViewById(R.id.spinnerTukangCukur);
        ArrayAdapter<String> adapterTukangCukur = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.tukang_cukur));
        adapterTukangCukur.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTukangCukur.setAdapter(adapterTukangCukur);
        spinnerTukangCukur.setSelection(0);

        Button cancel = findViewById(R.id.buttonCancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button lanjut = findViewById(R.id.buttonLanjut);
        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data.invoice=new Invoice(editTextHari.getText().toString(),
                        editTextTanggal.getText().toString(),spinnerTukangCukur.getSelectedItem().toString(),"");
                startActivity(new Intent(PesanActivity.this, BayarActivity.class));
                finish();
            }
        });

    }

}
