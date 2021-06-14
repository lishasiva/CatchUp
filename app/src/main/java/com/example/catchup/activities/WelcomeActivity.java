package com.example.catchup.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.example.catchup.R;
import com.example.catchup.bases.BaseActivity;

public class WelcomeActivity extends BaseActivity {

    private Button btnWelcomeRegister, btnWelcomeLogin;

    public void init() {
        btnWelcomeRegister = (Button) findViewById(R.id.btnWelcomeRegister);
        btnWelcomeLogin = (Button) findViewById(R.id.btnWelcomeLogin);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        init();


        btnWelcomeLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentLogin = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intentLogin);

            }
        });

        btnWelcomeRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentRegister = new Intent(WelcomeActivity.this, RegisterActivity.class);
                startActivity(intentRegister);
            }
        });




    }
}