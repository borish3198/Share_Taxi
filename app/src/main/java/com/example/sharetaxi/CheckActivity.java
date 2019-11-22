package com.example.sharetaxi;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class CheckActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        final TextView roomNumText = (TextView) findViewById(R.id. roomNumText);
        final TextView departTimeText = (TextView) findViewById(R.id. departTimeText);
        final TextView messageTextText = (TextView) findViewById(R.id. messageTextText);
        final TextView user1name = (TextView) findViewById(R. id. user1name);
        final TextView user1number = (TextView) findViewById(R. id. user1number);

        Intent intent = getIntent();
        final String userID = intent.getStringExtra("userID");


        Response.Listener<String> responseListener = new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){

                        roomNumText.setText(jsonResponse.getString("roomNum"));
                        departTimeText.setText(jsonResponse.getString("departTime"));
                        messageTextText.setText(jsonResponse.getString("messageText"));

                       /* user1name.setText(jsonResponse.getString("userName"));
                        user1number.setText(jsonResponse.getString("userNumber"));*/

                    }
                    else
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(CheckActivity.this);
                        builder.setMessage("현재 등록된 방이 없습니다.")
                                .setNegativeButton("뒤로 가기", null)
                                .create()
                                .show();
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        };


    CheckRequest checkRequest = new CheckRequest(userID, responseListener);
    RequestQueue queue = Volley.newRequestQueue(CheckActivity.this);
                queue.add(checkRequest);
    }
}
