package com.example.zhenmin.justplay;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    private  Spinner s;
    private Button datePick;
    private Button timePick;
    private Button newPage;
    private Button newPage2;
    private Button submit;
    private Button btnShowContacts;
    private Button btnTextToSpeech;
    private RadioButton rbB;
    private String[] data = new String[]{"UML","BYYL","SF"};
    private EditText etSpeech;
    private TextToSpeech tts;
    private CheckBox cb1,cb2,cb3,cb4;
    private TextView selectResult;
    String textToSpeech;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
        initData();

    }

    private void initListener() {
        DatePicker();
        TimePicker();
        SingleSelect();

        cb1.setOnCheckedChangeListener(this);
        cb2.setOnCheckedChangeListener(this);
        cb3.setOnCheckedChangeListener(this);
        cb4.setOnCheckedChangeListener(this);

        btnShowContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ShowContactsActivity.class));
            }
        });


        newPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TestActivityForRecycleView.class));
            }
        });
        newPage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Tab.class));
            }
        });

        btnTextToSpeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SurfaceViewTest.class));
            }
        });

    }

    private void SingleSelect() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbB.isChecked()){
                    Toast.makeText(MainActivity.this, "所选是正确的！", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"所选是错误的！",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void TimePicker() {
        timePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String time = String.format("%d:%d",hourOfDay,minute);
                        System.out.println("时间是："+time);
                        timePick.setText(time);
                    }
                },0,0,true).show();
            }
        });
    }

    private void DatePicker() {
        datePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        String theDate = String.format("%d-%d-%d", year, monthOfYear + 1, dayOfMonth);
                        System.out.println(theDate);
                        datePick.setText(theDate);
                    }
                },2015,11,01).show();
            }
        });
    }

    private void initData() {
        s.setAdapter(new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,data));
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("选择的数据为"+data[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        textToSpeech = etSpeech.getText().toString();
        tts = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS){
                    int result = tts.setLanguage(Locale.US);
                    if (result!=TextToSpeech.LANG_COUNTRY_AVAILABLE&&result!=TextToSpeech.LANG_AVAILABLE){
                        Toast.makeText(MainActivity.this,"TTS暂时不支持这种语言的朗读",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }

    private void initView() {
        s = (Spinner) findViewById(R.id.spinner);
        datePick = (Button) findViewById(R.id.dataPick);
        timePick = (Button) findViewById(R.id.timePick);
        newPage = (Button) findViewById(R.id.newTextPage);
        newPage2 = (Button) findViewById(R.id.newTextPage2);
        btnShowContacts = (Button) findViewById(R.id.showContacts);
        btnTextToSpeech = (Button) findViewById(R.id.btn_textToSpeech);
        submit = (Button) findViewById(R.id.submit);
        etSpeech = (EditText) findViewById(R.id.et_speech);
        rbB = (RadioButton) findViewById(R.id.rb2);
        cb1 = (CheckBox) findViewById(R.id.cb1);
        cb2 = (CheckBox) findViewById(R.id.cb2);
        cb3 = (CheckBox) findViewById(R.id.cb3);
        cb4 = (CheckBox) findViewById(R.id.cb4);
        selectResult = (TextView) findViewById(R.id.selection);


    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        String str = "我喜欢：";
        if (cb1.isChecked()){
            str+=cb1.getText().toString()+",";
        }
        if (cb2.isChecked()){
            str+=cb2.getText().toString()+",";
        }
        if (cb3.isChecked()){
            str+=cb3.getText().toString()+",";
        }
        if (cb4.isChecked()){
            str+=cb4.getText().toString();
        }
        selectResult.setText(str);

    }
    @Override
    public void onDestroy() {
        if (tts!=null){
            tts.shutdown();
        }
        super.onDestroy();
    }
}
