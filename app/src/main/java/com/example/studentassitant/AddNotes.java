package com.example.studentassitant;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import db.additionalNotes;

public class AddNotes extends AppCompatActivity {

    String ampm = " am";
    TimePickerDialog picker2;
    EditText eText2;



    String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.additionalnotespage);

        final TextView Title = findViewById(R.id.titleText);
        final TextView Date = findViewById(R.id.dateText);
        final TextView Description = findViewById(R.id.descriptionText);

        Button save = (Button) findViewById(R.id.saveButton);
        eText2 = findViewById(R.id.timeText);
        eText2.setInputType(InputType.TYPE_NULL);

        eText2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {//b is focus
                if (b) {
                    final Calendar cldr = Calendar.getInstance();
                    int hour = cldr.get(Calendar.HOUR_OF_DAY);
                    int minutes = cldr.get(Calendar.MINUTE);
                    // time picker2 dialog
                    picker2 = new TimePickerDialog(AddNotes.this,
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
                picker2 = new TimePickerDialog(AddNotes.this,
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

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                additionalNotes Notes = new additionalNotes(Title.getText().toString(), Description.getText().toString(), Date.getText().toString(), time);
                //AddNotesAsync async = new AddNotesAsync(AddNotes.this, Title.getText().toString(), Description.getText().toString(), Date.getText().toString(), time);

                AddNotesAsync async = new AddNotesAsync(AddNotes.this, Notes,"Add", Notes.getId());
                async.execute();
            }
        });
    }
}