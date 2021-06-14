package com.example.catchup.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.catchup.R;
import com.example.catchup.bases.BaseActivity;
import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends BaseActivity {

    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mAuth.getCurrentUser() == null) {

                    startActivity(new Intent(_activity,WelcomeActivity.class));

                } else {

                    startActivity(new Intent(_activity,HomeActivity.class));

                }
                _activity.finish();

            }
        },1000);
    }
}