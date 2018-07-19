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

        final EditText etEmail = findViewById(R.id.etEmail);
        final EditText etPassword = findViewById(R.id.etPassword);
        final EditText etPasswordConfirmation = findViewById(R.id.etPasswordConfirm);
        final Button bRegister = findViewById(R.id.bRegister);

        bRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String passwordConfirmation = etPasswordConfirmation.getText().toString();

                if (!email.isEmpty() && !password.isEmpty() && password.equals(passwordConfirmation)) {
                    // save user to database & move to user activity
                    db.insertUser(email, password);

                    Intent registerIntent = new Intent(RegisterActivity.this, UserAreaActivity.class);
                    registerIntent.putExtra("USER_EMAIL", email);

                    RegisterActivity.this.startActivity(registerIntent);
                } else {
                    // failed registration
                    Intent registerIntent = new Intent(RegisterActivity.this, RegisterActivity.class);
                    RegisterActivity.this.startActivity(registerIntent);
                }
            }
        });
    }
}
