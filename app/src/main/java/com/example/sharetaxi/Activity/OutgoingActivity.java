package com.example.sharetaxi.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sharetaxi.R;

public class OutgoingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outgoing);

        Button terminalButton = (Button) findViewById(R. id. terminalButton);
        Button jochiwonButton = (Button) findViewById(R. id. jochiwonButton);
        Button osongButton = (Button) findViewById(R. id. osongButton);
        Button mihoButton = (Button) findViewById(R. id. mihoButton);


        final Intent intent = getIntent();
        final String userID = intent.getStringExtra("userID");
        final String placeA = "1";
        final String placeB = "2";
        final String placeC = "3";
        final String placeD = "4";

        terminalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gagyungIntent = new Intent(OutgoingActivity.this, GagyungActivity.class);
                gagyungIntent.putExtra("userID", userID);
                gagyungIntent.putExtra("placeNum", 1);
                OutgoingActivity.this.startActivity(gagyungIntent);
            }
        });



/*        jochiwonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gagyungIntent = new Intent(OutgoingActivity.this, GagyungActivity.class);
                gagyungIntent.putExtra("userID", userID);
                gagyungIntent.putExtra("placeNum",2);
                OutgoingActivity.this.startActivity(gagyungIntent);
            }
        });



        osongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gagyungIntent = new Intent(OutgoingActivity.this, GagyungActivity.class);
                gagyungIntent.putExtra("userID", userID);
                gagyungIntent.putExtra("placeNum",3);
                OutgoingActivity.this.startActivity(gagyungIntent);
            }
        });


        mihoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gagyungIntent = new Intent(OutgoingActivity.this, GagyungActivity.class);
                gagyungIntent.putExtra("userID", userID);
                gagyungIntent.putExtra("placeNum",4);
                OutgoingActivity.this.startActivity(gagyungIntent);
            }
        });*/
    }
}
