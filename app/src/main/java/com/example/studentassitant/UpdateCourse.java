package com.example.studentassitant;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.io.Serializable;
import java.util.Calendar;

import db.courses;

public class UpdateCourse extends AppCompatActivity {

    String ampm = " am";
    TimePickerDialog picker2;
    EditText eText2;

    String time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_course);
        TextView Name = findViewById(R.id.editText4);
        TextView Venue = findViewById(R.id.editText5);
        TextView Time = findViewById(R.id.editText7);
        Name.setText(getIntent().getStringExtra("Name"));
        Venue.setText(getIntent().getStringExtra("Venue"));
        Time.setText(getIntent().getStringExtra("Time"));
        String Days = getIntent().getStringExtra("Days");
        CheckBox check = findViewById(R.id.checkBox8);
        if (Days.contains("Monday"))
        {
            check.setChecked(true);
        }
        if (Days.contains("Tuesday"))
        {
            check = findViewById(R.id.checkBox9);
            check.setChecked(true);
        }
        if (Days.contains("Wednesday"))
        {
            check = findViewById(R.id.checkBox10);
            check.setChecked(true);
        }
        if (Days.contains("Thursday"))
        {
            check = findViewById(R.id.checkBox11);
            check.setChecked(true);
        }
        if (Days.contains("Friday"))
        {
            check = findViewById(R.id.checkBox12);
            check.setChecked(true);
        }
        if (Days.contains("Saturday"))
        {
            check = findViewById(R.id.checkBox13);
            check.setChecked(true);
        }
        if (Days.contains("Sunday"))
        {
            check = findViewById(R.id.checkBox14);
            check.setChecked(true);
        }
        Button btn = findViewById(R.id.button2);
        eText2= findViewById(R.id.editText7);
        eText2.setInputType(InputType.TYPE_NULL);

        eText2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {//b is focus
                if (b) {
                    final Calendar cldr = Calendar.getInstance();
                    int hour = cldr.get(Calendar.HOUR_OF_DAY);
                    int minutes = cldr.get(Calendar.MINUTE);
                    // time picker2 dialog
                    picker2 = new TimePickerDialog(UpdateCourse.this,
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
                                    time = sHour + ":" + Mins + ampm;
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
                picker2 = new TimePickerDialog(UpdateCourse.this,
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
                                time = sHour + ":" + Mins + ampm;
                            }
                        }, hour, minutes, false);
                picker2.show();
            }
        });

        Button delete = findViewById(R.id.button7);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView Name = findViewById(R.id.editText4);
                Intent intent = new Intent(UpdateCourse.this, Popup.class);
                intent.putExtra("Name", Name.getText().toString());
                intent.putExtra("State", "UpdateCourse");
                startActivity(intent);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView Name = findViewById(R.id.editText4);
                TextView Venue = findViewById(R.id.editText5);





                String Days = "";
                CheckBox Mon = (CheckBox) findViewById(R.id.checkBox8);
                CheckBox Tue = (CheckBox) findViewById(R.id.checkBox9);
                CheckBox Wed = (CheckBox) findViewById(R.id.checkBox10);
                CheckBox Thur = (CheckBox) findViewById(R.id.checkBox11);
                CheckBox Fri = (CheckBox) findViewById(R.id.checkBox12);
                CheckBox Sat = (CheckBox) findViewById(R.id.checkBox13);
                CheckBox Sun = (CheckBox) findViewById(R.id.checkBox14);
                if(Mon.isChecked())
                    Days+="Monday,";
                if(Tue.isChecked())
                    Days+="Tuesday,";
                if(Wed.isChecked())
                    Days+="Wednesday,";
                if(Thur.isChecked())
                    Days+="Thursday,";
                if(Fri.isChecked())
                    Days+="Friday,";
                if(Sat.isChecked())
                    Days+="Saturday,";
                if(Sun.isChecked())
                    Days+="Sunday,";

                TextView Times = findViewById(R.id.editText7);
                courses course = new courses(Name.getText().toString(), Days,Venue.getText().toString(),Times.getText().toString());
                addCourse_Async acs = new addCourse_Async(UpdateCourse.this, course,"Update", " ", null, null);
                acs.execute();
            }
        });
    }
}
