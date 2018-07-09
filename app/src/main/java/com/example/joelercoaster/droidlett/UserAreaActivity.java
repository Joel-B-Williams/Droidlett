package com.example.joelercoaster.droidlett;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import io.intercom.android.sdk.Intercom;
import io.intercom.android.sdk.identity.Registration;

public class UserAreaActivity extends AppCompatActivity {

    String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        //final TextView tvLogged = (TextView) findViewById(R.id.tvLogged);
        final TextView tvUserEmail = (TextView) findViewById(R.id.tvUserEmail);
        //final TextView tvMessage = (TextView) findViewById(R.id.tvMessage);
        Button bLogout = (Button) findViewById(R.id.bLogout);

        final Bundle extras = getIntent().getExtras();

        if (extras != null && extras.getString("USER_EMAIL") != null && !extras.getString("USER_EMAIL").isEmpty()) {
            userEmail = extras.getString("USER_EMAIL");
            Registration registration = Registration.create().withEmail(userEmail);
            Intercom.client().registerIdentifiedUser(registration);
        } else {
            userEmail = "Visitor";
            Intercom.client().registerUnidentifiedUser();
        }

        tvUserEmail.setText(userEmail);

        Intercom.client().setLauncherVisibility(Intercom.Visibility.VISIBLE);

        bLogout.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v) {
               Intercom.client().logout();

               Intent registerIntent = new Intent(UserAreaActivity.this, LoginActivity.class);
               registerIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
               UserAreaActivity.this.startActivity(registerIntent);
           }
        });


    }
}
