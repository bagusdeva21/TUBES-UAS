package com.salon.salonwawa;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;

import com.salon.salonwawa.databinding.ActivityInvoiceBinding;
import com.salon.salonwawa.databinding.ActivityProfilBinding;

public class InvoiceActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityInvoiceBinding binding= DataBindingUtil.setContentView(this,R.layout.activity_invoice);
        binding.setInvoice(Data.invoice);

        Button back = binding.buttonBack;
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
