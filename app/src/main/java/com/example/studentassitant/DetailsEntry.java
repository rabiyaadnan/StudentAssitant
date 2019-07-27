package com.example.studentassitant;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.telecom.Call;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import db.details;

public class DetailsEntry extends AppCompatActivity {

    ///////////// for date ////////////
    DatePickerDialog picker;
    EditText eText;
    Button btnGet;
    TextView tvw;
    String daaate;

    //////////// for time /////////////

    String ampm = " am";
    TimePickerDialog picker2;
    EditText eText2;
    Button btnGet2;
    TextView tvw2;
    String Subject = "";
    String tiiime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_entry);

        TextView PQA = findViewById(R.id.textView);
        PQA.setText(getIntent().getStringExtra("PQA"));

        Spinner sp1 = findViewById(R.id.spinner);
        final List<String> list = new ArrayList<String>();
        list.add("SMD");
        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adp1);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                // TODO Auto-generated method stub

                Toast.makeText(getBaseContext(), list.get(position), Toast.LENGTH_SHORT).show();
                Subject += list.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });


        /////// for Date ///////
        // tvw=(TextView)findViewById(R.id.textView1);
        eText=(EditText) findViewById(R.id.editText8);
        //eText.setInputType(InputType.TYPE_NULL);

        eText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {//b is focus
                if (b){
                    final Calendar cldr = Calendar.getInstance();
                    int day = cldr.get(Calendar.DAY_OF_MONTH);
                    int month = cldr.get(Calendar.MONTH);
                    int year = cldr.get(Calendar.YEAR);
                    // date picker dialog
                    picker = new DatePickerDialog(DetailsEntry.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                    eText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                    daaate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                                }
                            }, year, month, day);
                    picker.show();


                }
            }
        });






        eText.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                eText.setShowSoftInputOnFocus(false);
                // date picker dialog
                picker = new DatePickerDialog(DetailsEntry.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                eText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                daaate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        /* to send datedata forward maybe
         btnGet=(Button)findViewById(R.id.button1);
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvw.setText("Selected Date: "+ eText.getText());
            }
        });
        */

//////   FOR TIME //////
        // tvw2=(TextView)findViewById(R.id.textView1);
        eText2=(EditText) findViewById(R.id.editText3);
        eText2.setInputType(InputType.TYPE_NULL);

        eText2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {//b is focus
                if (b) {
                    final Calendar cldr = Calendar.getInstance();
                    int hour = cldr.get(Calendar.HOUR_OF_DAY);
                    int minutes = cldr.get(Calendar.MINUTE);
                    // time picker2 dialog
                    picker2 = new TimePickerDialog(DetailsEntry.this,
                            new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePicker tp, int sHour, int sMinute) {


                                    if (sHour >= 12) {
                                        if (sHour > 12) {
                                            sHour = sHour - 12;
                                        }

                                        ampm = " pm";
                                    } else {
                                        if (sHour == 0)
                                            sHour = 12;
                                        ampm = " am";
                                    }

//                                if(sHour== 0) {
//                                    sHour = 12;
//                                    ampm= " am";
//                                }


                                    String Mins = "";
                                    if (sMinute < 10)
                                        Mins += "0";
                                    Mins += Integer.toString(sMinute);
                                    eText2.setText(sHour + ":" + Mins + ampm);
                                    tiiime = sHour + ":" + Mins + ampm;
                                }
                            }, hour, minutes, false);
                    picker2.show();
                }
            }
        });

        eText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker2 dialog
                picker2 = new TimePickerDialog(DetailsEntry.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {


                                if(sHour >= 12){
                                    if(sHour>12)
                                    {sHour=sHour-12;}

                                    ampm= " pm";}
                                else{
                                    if(sHour == 0)
                                        sHour = 12;
                                    ampm= " am";}

//                                if(sHour== 0) {
//                                    sHour = 12;
//                                    ampm= " am";
//                                }


                                String Mins = "";
                                if(sMinute < 10)
                                    Mins+= "0";
                                Mins+=Integer.toString(sMinute);
                                eText2.setText(sHour + ":" + Mins + ampm);
                                tiiime = sHour + ":" + Mins + ampm;
                            }
                        }, hour, minutes, false);
                picker2.show();
            }
        });
        final TextView Title = findViewById(R.id.editText);
        Button save = findViewById(R.id.button);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView Time = findViewById(R.id.editText3);
                TextView Date = findViewById(R.id.editText8);
                TextView Desc = findViewById(R.id.editText10);
                Switch Completed = findViewById(R.id.switch1);
                details deets = new details(Subject,Title.getText().toString(),Completed.isChecked(),Desc.getText().toString(),Date.getText().toString(),Time.getText().toString(),getIntent().getStringExtra("PQA").toString());
                AddDetailAsync gc = new AddDetailAsync(DetailsEntry.this,deets,"Add",deets.getId());
                gc.execute();
            }
        });



    }

    public void lol(View view)
    {
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        // date picker dialog
        picker = new DatePickerDialog(DetailsEntry.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        eText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        daaate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                    }
                }, year, month, day);
        picker.show();

    }
}

