package com.example.catchup.bases;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    public Activity _activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _activity = this;
    }

    public void showAlerDialog(String tittle, String message){

        AlertDialog.Builder alerDialog = new AlertDialog.Builder(this);
        alerDialog.setTitle(tittle)
                .setMessage(message)
                .setPositiveButton("Tamam",null)
                .setCancelable(false)
                .show();
    }
}
