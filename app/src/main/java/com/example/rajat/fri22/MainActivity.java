package com.example.rajat.fri22;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText name,date;
    Spinner qualification;
    Button submit , datepick;
    String[] content = {"10th","12th","Graduate","Post Graduate"};
    String quali;
    TextView aname,q,dob;
    int dd,mm,yyyy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.nameinput);
        date = findViewById(R.id.dateinput);
        qualification = findViewById(R.id.spinner);
        submit = findViewById(R.id.submit);
        datepick  = findViewById(R.id.date_pick);

        ArrayAdapter a = new ArrayAdapter(MainActivity.this,android.R.layout.simple_spinner_item,content);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        qualification.setAdapter(a);

        qualification.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                quali = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final Calendar c = Calendar.getInstance();
        dd = c.get(Calendar.DATE);
        mm = c.get(Calendar.MONTH);
        yyyy = c.get(Calendar.YEAR);


        datepick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        date.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },yyyy,mm,dd);
                datePickerDialog.show();
            }

        });

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String sname = name.getText().toString();
                String dat = date.getText().toString();

                AlertDialog.Builder alertDialog= new AlertDialog.Builder(MainActivity.this);

                LayoutInflater l = getLayoutInflater();
                v= l.inflate(R.layout.alertdialog,null);
                alertDialog.setView(v);
                aname = v.findViewById(R.id.name);
                q = v.findViewById(R.id.qualification);
                dob= v.findViewById(R.id.dob);

                aname.setText(sname);
                q.setText(quali);
                dob.setText(dat);

                alertDialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alertDialog.show();
            }
        });


    }
}