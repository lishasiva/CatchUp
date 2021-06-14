package com.example.catchup.activities;

import android.content.Intent;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;

import com.example.catchup.R;
import com.example.catchup.bases.BaseActivity;
import com.example.catchup.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends BaseActivity {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private Toolbar actionbarRegister;
    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();

    public void init() {

        actionbarRegister = (Toolbar) findViewById(R.id.actionbarRegister);
        setSupportActionBar(actionbarRegister);
        getSupportActionBar().setTitle("Üyelik Oluştur");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    EditText txtUsername, txtName, txtSurname, txtEmail, txtPassword;
    RadioButton isCustomerRadioButton, isDiyetisyenRadioButton;
    Button btnFinishAccount;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();

        txtUsername = findViewById(R.id.txtUsername);
        txtName = findViewById(R.id.txtName);
        txtSurname = findViewById(R.id.txtSurname);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);

        isCustomerRadioButton = findViewById(R.id.isCustomerRadioButton);
        isDiyetisyenRadioButton = findViewById(R.id.isDiyetisyenRadioButton);

        btnFinishAccount = findViewById(R.id.btnFinishAccount);
        progressBar = findViewById(R.id.progressBar);

        btnFinishAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        isCustomerRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) isDiyetisyenRadioButton.setChecked(false);
            }
        });

        isDiyetisyenRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) isCustomerRadioButton.setChecked(false);

            }
        });

    }

    public void register() {

        if (txtUsername.getText().toString().equals("") ||
                txtName.getText().toString().equals("") ||
                txtSurname.getText().toString().equals("") ||
                txtEmail.getText().toString().equals("") ||
                txtPassword.getText().toString().equals("")) {
            showAlerDialog("Hata", "Boş bırakılan alanlar var ");
            return;
        }

        txtUsername.setEnabled(false);
        txtName.setEnabled(false);
        txtSurname.setEnabled(false);
        txtEmail.setEnabled(false);
        txtPassword.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
        btnFinishAccount.setEnabled(false);

        mAuth.createUserWithEmailAndPassword(txtEmail.getText().toString(), txtPassword.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            saveUserDataToFirebase();
                        } else {

                            progressBar.setVisibility(View.INVISIBLE);
                            btnFinishAccount.setEnabled(true);
                            txtUsername.setEnabled(true);
                            txtName.setEnabled(true);
                            txtSurname.setEnabled(true);
                            txtEmail.setEnabled(true);
                            txtPassword.setEnabled(true);
                            showAlerDialog("Hata", task.getException().toString());

                        }
                    }
                });

    }

    public void saveUserDataToFirebase(){

        User currentUser = new User(mAuth.getCurrentUser().getUid(),
                txtUsername.getText().toString(),
                txtName.getText().toString(),
                txtSurname.getText().toString(),
                mAuth.getCurrentUser().getEmail(),
                isCustomerRadioButton.isChecked());

        myRef.child("Users").child(currentUser.getUserId()).setValue(currentUser).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {

                if (task.isSuccessful()) {

                    progressBar.setVisibility(View.INVISIBLE);
                    startActivity(new Intent(_activity,HomeActivity.class));
                    _activity.finish();

                } else {

                    progressBar.setVisibility(View.INVISIBLE);
                    btnFinishAccount.setEnabled(true);
                    txtUsername.setEnabled(true);
                    txtName.setEnabled(true);
                    txtSurname.setEnabled(true);
                    txtEmail.setEnabled(true);
                    txtPassword.setEnabled(true);

                    showAlerDialog("Hata","Bilinmeyen bir hata ile karşılaşıldı.");
                }
            }
        });

    }
}