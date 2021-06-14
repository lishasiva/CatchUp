package com.example.catchup.activities;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.catchup.R;
import com.example.catchup.bases.BaseActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends BaseActivity {


    private Toolbar actionbarLogin;

    public void init() {


        actionbarLogin = (Toolbar) findViewById(R.id.actionbarLogin);
        setSupportActionBar(actionbarLogin);
        getSupportActionBar().setTitle(" Giriş Yap");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
    }


    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    EditText txtUsername, txtPassword;
    Button btnEnterAccount;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        btnEnterAccount = findViewById(R.id.btnEnterAccount);
        progressBar = findViewById(R.id.progressBar);

        btnEnterAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login();

            }
        });


    }

    public void login() {

        if (txtUsername.getText().toString().equals("") ||
                txtPassword.getText().toString().equals("")) {
            showAlerDialog("Hata", "Boş bırakılan alanlar var ");
            return;
        }

        txtUsername.setEnabled(false);
        txtPassword.setEnabled(false);
        btnEnterAccount.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(txtUsername.getText().toString(),txtPassword.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    startActivity(new Intent(_activity,HomeActivity.class));
                    _activity.finish();

                } else {
                    txtUsername.setEnabled(true);
                    txtPassword.setEnabled(true);
                    btnEnterAccount.setEnabled(true);
                    progressBar.setVisibility(View.INVISIBLE);
                    showAlerDialog("Hata","lütfen mail adresi ve şifrenizi kontrol ediniz");
                }
            }
        });

    }
}