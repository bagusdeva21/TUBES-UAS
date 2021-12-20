package com.salon.salonwawa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;

import com.salon.salonwawa.databinding.ActivityProfilBinding;

public class ProfilActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityProfilBinding binding= DataBindingUtil.setContentView(this,R.layout.activity_profil);
        binding.setUser(Data.user);

        Button back = binding.buttonBack;
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfilActivity.this, UpdateProfileActivity.class));
            }
        });
    }

}
