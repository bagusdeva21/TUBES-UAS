package com.salon.salonwawa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BayarActivity extends Activity {

    String pilihanPembayaran="Cash";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bayar);

        final Button cash=findViewById(R.id.buttonCash);
        final Button debit=findViewById(R.id.buttonDebit);
        final Button qris=findViewById(R.id.buttonQris);

        cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilihanPembayaran="Cash";
                cash.setBackgroundColor(getResources().getColor(R.color.colorGray));
                debit.setBackground(getResources().getDrawable(R.drawable.custom_edit_text));
                qris.setBackground(getResources().getDrawable(R.drawable.custom_edit_text));
            }
        });
        debit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilihanPembayaran="Debit";
                cash.setBackground(getResources().getDrawable(R.drawable.custom_edit_text));
                debit.setBackgroundColor(getResources().getColor(R.color.colorGray));
                qris.setBackground(getResources().getDrawable(R.drawable.custom_edit_text));
            }
        });
        qris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilihanPembayaran="Qris";
                cash.setBackground(getResources().getDrawable(R.drawable.custom_edit_text));
                debit.setBackground(getResources().getDrawable(R.drawable.custom_edit_text));
                qris.setBackgroundColor(getResources().getColor(R.color.colorGray));
            }
        });

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
                Data.invoice.pembayaran=pilihanPembayaran;
                Backend backend=new Backend(BayarActivity.this);
                backend.retroPutInvoice(Data.invoice);
            }
        });

    }

}
