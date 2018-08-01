package com.example.joelercoaster.droidlett;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import database.model.DatabaseHelper;
import database.model.User;
import io.intercom.android.sdk.Intercom;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etEmail = findViewById(R.id.etEmail);
        final EditText etPassword = findViewById(R.id.etPassword);
        final Button bLogin = findViewById(R.id.bLogin);
        final TextView registerLink = findViewById(R.id.tvRegister);

        final DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        Intercom.client().setLauncherVisibility(Intercom.Visibility.GONE);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                User user = db.getUser(email);

                if ( (user != null) && (user.getPassword().equalsIgnoreCase(password)) ) {

                    Intent userAreaIntent = new Intent(LoginActivity.this, UserAreaActivity.class);
                    userAreaIntent.putExtra("USER_EMAIL", email);
                    finish();
                    LoginActivity.this.startActivity(userAreaIntent);
                } else {

                    Intent userAreaIntent = new Intent(LoginActivity.this, UserAreaActivity.class);
                    finish();
                    LoginActivity.this.startActivity(userAreaIntent);
                }
            }
        });

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });
    }
}
