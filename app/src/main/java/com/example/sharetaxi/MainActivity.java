package com.example.sharetaxi;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button backButton = (Button) findViewById(R. id.backButton);
        Button outButton = (Button) findViewById(R. id.outButton);
        Button inButton = (Button) findViewById(R. id.inButton);
        Button checkButton = (Button) findViewById(R. id.checkButton);

        final Intent intent = getIntent();
        final String userID = intent.getStringExtra("userID");
        final String userName = intent.getStringExtra("userName");


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                MainActivity.this.startActivity(loginIntent);
            }
        });


        outButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent outgoingIntent = new Intent(MainActivity.this, OutgoingActivity.class);
                outgoingIntent.putExtra("userID", userID);
                MainActivity.this.startActivity(outgoingIntent);
            }
        });


        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent checkIntent = new Intent(MainActivity.this, CheckActivity.class);
               checkIntent.putExtra("userID", userID);
                MainActivity.this.startActivity(checkIntent);
            }
        });




        TextView welcomeMessage = (TextView) findViewById(R.id.welcomeMessage);


        String message = "환영합니다. " + userName + "님!";

        welcomeMessage.setText(message);


    }
}
