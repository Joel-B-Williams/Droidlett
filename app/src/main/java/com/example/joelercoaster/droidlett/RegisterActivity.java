package com.example.joelercoaster.droidlett;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import io.intercom.android.sdk.Intercom;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Intercom.client().setLauncherVisibility(Intercom.Visibility.GONE);

        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final EditText etPasswordConfirmation = (EditText) findViewById(R.id.etPasswordConfirm);
        final Button bRegister = (Button) findViewById(R.id.bRegister);

        Intercom.client().logout();
    }
}
