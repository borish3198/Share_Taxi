package com.example.sharetaxi;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GagyungActivity extends AppCompatActivity {

    static final String[] LIST_MENU = /*{"taxi1", "taxi2", "taxi3", "taxi4", "taxi5",};*/new String[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gagyung);

        final List<String> list = new ArrayList<String>();


        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        ListView listview = (ListView) findViewById(R.id.taxilist);
        listview.setAdapter(adapter);

        final Intent intent = getIntent();
        final String userID = intent.getStringExtra("userID");

        TextView title = (TextView) findViewById(R.id.title);
        Button outmake = (Button)findViewById(R.id.makenewButton);
        final TextView testView = (TextView) findViewById(R.id.testview);

        outmake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(GagyungActivity.this, OutmakeActivity.class);
                GagyungActivity.this.startActivity(loginIntent);
            }
        });

        Response.Listener<JSONArray> responseListener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject item = response.getJSONObject(i);
                        list.add(item.toString());
                        adapter.notifyDataSetChanged();
                        testView.setText(item.getString("messageText"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
        };
        GagyungRequest gagyungRequest = new GagyungRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(GagyungActivity.this);
        queue.add(gagyungRequest);


        };


    private long time= 0;
    @Override
    public void onBackPressed(){
        if(System.currentTimeMillis()-time>=2000){
            time=System.currentTimeMillis();
            Toast.makeText(getApplicationContext(),"뒤로 버튼을 한번 더 누르면 이전화면으로 돌아갑니다.",Toast.LENGTH_SHORT).show();
        }else if(System.currentTimeMillis()-time<2000){
            finish();
        }
    }

}

