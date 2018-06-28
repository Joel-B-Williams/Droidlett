package com.example.joelercoaster.droidlett;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import database.model.DatabaseHelper;
import io.intercom.android.sdk.Intercom;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Intercom.client().setLauncherVisibility(Intercom.Visibility.GONE);
        final DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final EditText etPasswordConfirmation = (EditText) findViewById(R.id.etPasswordConfirm);
        final Button bRegister = (Button) findViewById(R.id.bRegister);

        bRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // save user to database
                if (etPassword.getText().toString().equals(etPasswordConfirmation.getText().toString())) {

                    db.insertUser(etEmail.getText().toString(), etPassword.getText().toString());

                    Intent registerIntent = new Intent(RegisterActivity.this, UserAreaActivity.class);
                    registerIntent.putExtra("USER_EMAIL", etEmail.getText().toString());

                    RegisterActivity.this.startActivity(registerIntent);
                } else {

                    Intent registerIntent = new Intent(RegisterActivity.this, RegisterActivity.class);
                    RegisterActivity.this.startActivity(registerIntent);
                }
            }
        });
    }
}
