package com.example.sharetaxi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OutgoingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outgoing);

        Button terminalButton = (Button) findViewById(R. id. terminalButton);
        final Intent intent = getIntent();
        final String userID = intent.getStringExtra("userID");

        terminalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gagyungIntent = new Intent(OutgoingActivity.this, GagyungActivity.class);
                gagyungIntent.putExtra("userID", userID);
                OutgoingActivity.this.startActivity(gagyungIntent);
            }
        });
    }
}
