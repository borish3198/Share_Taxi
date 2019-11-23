package com.example.sharetaxi.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.sharetaxi.Request.GagyungRequest;
import com.example.sharetaxi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class GagyungActivity extends AppCompatActivity {

    static int univ_postion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gagyung);

        final List<String> list = new ArrayList<String>();
        final List<String> room_list = new ArrayList<String>();



        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        ListView listview = (ListView) findViewById(R.id.taxilist);
        listview.setAdapter(adapter);                                                               //리스트 뷰 항목 끌어오기 위한 요소들

        final Intent intent = getIntent();
        final String userID = intent.getStringExtra("userID");
//        final int placeNum = intent.getStringExtra("placeNum");                          //OutgoingActivity에서 넘긴 데이터를 수거


        TextView title = (TextView) findViewById(R.id.title);                                       //레이아웃의 각 항목들 변수로 지정
        Button outmake = (Button)findViewById(R.id.makenewButton);
        final TextView testView = (TextView) findViewById(R.id.testview);

        //testView.setText(userID);

        outmake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                                                         //새로 만들기 버튼 클릭 이벤트

                AlertDialog.Builder alert_confirm = new AlertDialog.Builder(GagyungActivity.this);
                alert_confirm.setMessage("새로운 방을 만드시겠습니까?").setCancelable(false).setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent loginIntent = new Intent(GagyungActivity.this, OutmakeActivity.class);
                                GagyungActivity.this.startActivity(loginIntent);
                            }
                        }).setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 'No'
                                return;
                            }
                        });
                AlertDialog alert = alert_confirm.create();
                alert.show();
            }
        });

//        save=new JSONArray();

        Response.Listener<JSONArray> responseListener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {                            //리스너
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject item = response.getJSONObject(i);
                        room_list.add(item.getString("roomNum"));
                        String temp = item.getString("departTime").substring(0, 16) + "\n" + item.getString("messageText");
                        list.add(temp);
                        adapter.notifyDataSetChanged();
                        //testView.setText(item.getString("messageText"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
    };



        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String roomNum = room_list.get(position);
                testView.setText(Integer.toString(position));
                Intent resultIntent = new Intent(GagyungActivity.this, ResultActivity.class);
                resultIntent.putExtra("roomNum", roomNum);
                resultIntent.putExtra("userID", userID);
                GagyungActivity.this.startActivity(resultIntent);

                //방 조인하기 전 재확인하는 용도, position 변수문제로 보류

                 /*AlertDialog.Builder alert_confirm = new AlertDialog.Builder(GagyungActivity.this);
                alert_confirm.setMessage("선택한 택시에 동승하시겠습니까?").setCancelable(false).setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent resultIntent = new Intent(GagyungActivity.this, ResultActivity.class);
                                resultIntent.putExtra("userID", userID);
                                GagyungActivity.this.startActivity(resultIntent);
                            }
                        }).setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 'No'
                                return;
                            }
                        });
                AlertDialog alert = alert_confirm.create();
                alert.show();*/
                                                                                                        //출처: https://shstarkr.tistory.com/144 [아마그래머]
            }
        });


        GagyungRequest gagyungRequest = new GagyungRequest(responseListener);             //통신
        RequestQueue queue = Volley.newRequestQueue(GagyungActivity.this);
        queue.add(gagyungRequest);

    };

/*    private long time= 0;                                                                          //뒤로가기 두번 눌러야 뒤로 가도록
    @Override
    public void onBackPressed(){
        if(System.currentTimeMillis()-time>=2000){
            time=System.currentTimeMillis();
            Toast.makeText(getApplicationContext(),"뒤로 버튼을 한번 더 누르면 이전화면으로 돌아갑니다.",Toast.LENGTH_SHORT).show();
        }else if(System.currentTimeMillis()-time<2000){
            finish();
        }
    }*/

}

