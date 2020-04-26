package debug;
import io.realm.Realm;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public TextView thedate;
    public Button btngocalendar;
    public Context mContext;
    public Spinner freq1;
    public Spinner freq2;
    public TextView amount;
    public TextView period;

    public void onSubmitPressed(View view){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        try {
            detail person = realm.createObject(detail.class, System.currentTimeMillis() / 1000);
            person.setFreq1(freq1.toString());
            person.setFreq2(freq2.toString());
            person.setAmount(Integer.parseInt(amount.getText().toString()));
            person.setPeriod(Integer.parseInt(period.getText().toString()));
            person.setDate(thedate.toString());
            realm.commitTransaction();
            Toast.makeText(mContext, "Success", Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex){
            realm.cancelTransaction();
            Toast.makeText(mContext, "Failure" + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        thedate = (TextView) findViewById(R.id.date);
        Intent incoming = getIntent();
        String date = incoming.getStringExtra("date");
        thedate.setText(date);
        btngocalendar = (Button) findViewById(R.id.btn);
        mContext = this;

        freq1 = findViewById(R.id.spinn);
        freq2 = findViewById(R.id.spinn2);
        amount = findViewById(R.id.tex1);
        period=findViewById(R.id.tex2);





        btngocalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CalendarActivity.class);
                startActivity(intent);
            }
        });


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