package com.example.studentassitant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import db.courses;
import db.details;

public class Popup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*.8),(int)(height*.6));

        TextView SubName = findViewById(R.id.textView9);
        SubName.setText(getIntent().getStringExtra("Name"));
        Button yes = findViewById(R.id.button8);
        Button no = findViewById(R.id.button9);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getIntent().getStringExtra("State").equals("UpdateCourse")) {
                    addCourse_Async acs = new addCourse_Async(Popup.this, new courses(), "Delete", getIntent().getStringExtra("Name"), null, null);
                    acs.execute();
                }
                else if(getIntent().getStringExtra("State").equals("UpdateDetails")) {
                    AddDetailAsync ada = new AddDetailAsync(Popup.this,new details(), "Delete", Integer.parseInt(getIntent().getStringExtra("Name")));
                    ada.execute();
                }

            }
        });
    }
}
