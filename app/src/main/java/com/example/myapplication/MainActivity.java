package com.example.myapplication;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public EditText theDate;
    public Button btnGoCalendar;
    public Context mContext;
    public Spinner freq1;
    public Spinner freq2;
    public EditText amount;
    public EditText period;
    public Button submitButton;

    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Spinner spinner = (Spinner) findViewById(R.id.spinn);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Monthly, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        theDate = (EditText) findViewById(R.id.date);
        btnGoCalendar = (Button) findViewById(R.id.btnGoCalendar);
        submitButton = (Button) findViewById(R.id.submit);

        freq1 = (Spinner) findViewById(R.id.spinn);
        freq2 = (Spinner) findViewById(R.id.spinn2);
        amount = (EditText) findViewById(R.id.tex1);
        period = (EditText) findViewById(R.id.tex2);
        Log.d(TAG, "onCreate: View Initialization done");
        realm = Realm.getDefaultInstance();

        realm.beginTransaction();



        Intent incomingIntent = getIntent();
        String date = incomingIntent.getStringExtra("date");
        theDate.setText(date);

        btnGoCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

        mContext = this;


        Spinner spinner2 = (Spinner) findViewById(R.id.spinn2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.Monthly, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String it = (String) parent.getItemAtPosition(position);
                Toast t = Toast.makeText(getApplicationContext(), it + "Selected", Toast.LENGTH_LONG);
                //t.setGravity(Gravity.BOTTOM|Gravity.RIGHT,0,0);
                t.show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "No Selection", Toast.LENGTH_LONG).show();
            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String it = (String) parent.getItemAtPosition(position);
                Toast t = Toast.makeText(getApplicationContext(), it + "Selected", Toast.LENGTH_LONG);
                //t.setGravity(Gravity.BOTTOM|Gravity.RIGHT,0,0);
                t.show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "No Selection", Toast.LENGTH_LONG).show();
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveData();
                Toast.makeText(mContext, "Success", Toast.LENGTH_SHORT).show();


            }


        });


    }

    public void saveData() {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm Realm) {
                Detail person = realm.createObject(Detail.class);
                person.setFreq1(freq1.getSelectedItem().toString());
                person.setFreq2(freq2.getSelectedItem().toString());
                person.setAmount(Integer.parseInt(amount.getText().toString()));
                person.setPeriod(Integer.parseInt(period.getText().toString()));
                person.setDate(theDate.getText().toString());

            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "onSuccess: Data Written Successfully");

            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.d(TAG, "onError: Error Occured");
            }


        });

    }
}


