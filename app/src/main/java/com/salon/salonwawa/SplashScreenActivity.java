package com.salon.salonwawa;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseUser;

public class SplashScreenActivity extends Activity {

    int secondsDelay = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        startApp();
    }

    public void startApp() {
        Backend backend=new Backend(SplashScreenActivity.this);
        FirebaseUser user=backend.cekUserAktif();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                if(user==null||!user.isEmailVerified()) {
                    startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                    finish();
                }
                else{
                    backend.retroGet();
                }
            }
        }, secondsDelay * 1000);
    }
}
