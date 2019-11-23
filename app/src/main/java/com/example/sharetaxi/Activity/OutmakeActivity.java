package com.example.sharetaxi.Activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.sharetaxi.R;
import com.example.sharetaxi.others.DatePickerFragment;
import com.example.sharetaxi.others.TimePickerFragment;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

public class OutmakeActivity extends AppCompatActivity  {

        static String placeName = null;
        static int placeNum = 0;

        static String comKor = null;
        static int comNum = 0;

        static String message = null;

        static Time time = null;



        private TextView textView_Date;
        private DatePickerDialog.OnDateSetListener callbackMethod;






    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_outmake);

        ///
        final TextView test = (TextView)findViewById(R. id. title);
        ///

        String[] des_List = {"선택", "가경터미널", "조치원역", "오송역", "미호삼거리"};

        final Spinner des_Spinner = (Spinner)findViewById(R. id. des_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.custom_simple_dropdown_item_1line,
                des_List);
        des_Spinner.setAdapter(adapter);
        //des_Spinner.setSelection(0);


        des_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String str = (String) des_Spinner.getSelectedItem();
                placeName=str;
                placeNum=position;
                des_Spinner.setSelection(position);
                //test.setText(Integer.toString(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



        String[] com_List = {"선택", "1명", "2명", "3명"};

        final Spinner com_Spinner = (Spinner)findViewById(R. id. com_spinner);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
                this,
                R.layout.custom_simple_dropdown_item_1line,
                com_List);
        com_Spinner.setAdapter(adapter1);
        com_Spinner.setSelection(0);

        com_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String str = (String) com_Spinner.getSelectedItem();
                comKor = str;
                comNum = position;
                //test.setText(comNum);
                com_Spinner.setSelection(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
                                                                                                    //서버로 보내는 데이터2 시간2 날짜/시간
        final Button dateButton = (Button)findViewById(R.id.dateButton);
        final Button timeButton = (Button)findViewById(R. id.timeButton);


        this.InitializeView();
        this.InitializeListener();


        /*timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                                                      //시간 프래그먼트 소환
            //    OnClickHandler(v);
        }
        });*/







        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                                                      //날짜 프래그먼트 소환
                DatePickerFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(),"Datepicker");
            }
        });
        



        final Button make_Button = (Button)findViewById(R. id. makeButton);

        make_Button.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                AlertDialog.Builder alert_confirm = new AlertDialog.Builder(OutmakeActivity.this);
                alert_confirm.setMessage("아래 정보로 새로운 방을 만드시겠습니까?" + "\n" +
                        "\n"+"목적지 : " + placeName +
                        "\n"+"출발날짜 : " + time +
                        "\n"+"메시지 :" + message).setCancelable(false).setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EditText msg_Text = (EditText)findViewById(R. id. message);                                 //onButton 이벤트 동작으로
                                Intent resultIntent = new Intent(OutmakeActivity.this, ResultActivity.class);
                                final String message = msg_Text.getText().toString();
                                //resultIntent.putExtra("변수들");
                                OutmakeActivity.this.startActivity(resultIntent);
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

                                              //서버로 보내는 데이터3 메시지

        }

    public void InitializeView()
    {
        textView_Date = (TextView)findViewById(R.id.textView_date);
    }

    public void InitializeListener()
    {
        callbackMethod = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                textView_Date.setText(year + "년" + monthOfYear + "월" + dayOfMonth + "일");
            }
        };
    }

    public void OnClickHandler(View view)
    {
        DatePickerDialog dialog = new DatePickerDialog(this, callbackMethod, 2019, 5, 24);

        dialog.show();
    }

}
