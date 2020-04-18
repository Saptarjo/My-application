package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner = (Spinner) findViewById(R.id.spinn);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Monthly,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Spinner spinner2 = (Spinner) findViewById(R.id.spinn2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.Monthly,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String it=(String)parent.getItemAtPosition(position);
                Toast t = Toast.makeText(getApplicationContext(),it + "Selected",Toast.LENGTH_LONG);
                //t.setGravity(Gravity.BOTTOM|Gravity.RIGHT,0,0);
                t.show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(),"No Selection",Toast.LENGTH_LONG).show();
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
                Toast.makeText(getApplicationContext(),"No Selection",Toast.LENGTH_LONG).show();
            }
        });
    }
}