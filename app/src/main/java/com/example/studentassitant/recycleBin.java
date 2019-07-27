package com.example.studentassitant;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import db.additionalNotes;
import db.details;

public class recycleBin extends AppCompatActivity {

    View view;
    RecyclerView object;
    binAdapter adapt;
    GestureDetector gesture;
    recycle Selected;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_bin);


            object = findViewById(R.id.recyclerview);
            gesture = new GestureDetector(recycleBin.this, new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent v) {
                    View view = object.findChildViewUnder(v.getX(), v.getY());
                    if (view != null)
                    {
                        int index = object.getChildAdapterPosition(view);
                        Selected = adapt.recycle.get(index);
                        //Toast.makeText(recycleBin.this,"You have selected " + Selected_Details.getName(), Toast.LENGTH_SHORT).show();
                        pop();
                    }
                    else
                        Toast.makeText(recycleBin.this, "Sorry, no item has been clicked", Toast.LENGTH_SHORT).show();
                    return true;
                }
            });


            adapt = new binAdapter(new ArrayList<recycle>(), R.layout.singlebin);
            object.setAdapter(adapt);
            object.setLayoutManager(new LinearLayoutManager(recycleBin.this));
            object.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
                @Override
                public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                    gesture.onTouchEvent(e);
                    return false;
                }

                @Override
                public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

                }

                @Override
                public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

                }
            });




        GetDetails gc = new GetDetails(recycleBin.this, object, null, "fillRecycle", adapt);
        gc.execute();



        }

    public void pop() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(recycleBin.this);
        alertDialogBuilder.setMessage("What do you wish to do with " + Selected.getName() + "?\t("+Selected.getType()+")");
        alertDialogBuilder.setPositiveButton("Recover",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        //Update obj's isdeleted to false
                        if(Selected.getType().equals("Course")){
                            addCourse_Async acs = new addCourse_Async(recycleBin.this, null, "Recover",Selected.getName(),null, null);
                            acs.execute();
                        }
                        else if (Selected.getType().equals("Additional Notes")){
                            AddNotesAsync ana = new AddNotesAsync(recycleBin.this, null, "Recover",Selected.getId());
                            ana.execute();
                        }
                        else
                        {
                            AddDetailAsync ada = new AddDetailAsync(recycleBin.this, null, "Recover", Selected.getId());
                            ada.execute();
                        }

                    }
                });

        alertDialogBuilder.setNegativeButton("Delete Permanently", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(Selected.getType().equals("Course")){
                    addCourse_Async acs = new addCourse_Async(recycleBin.this, null, "PermanentDelete",Selected.getName(),null, null);
                    acs.execute();
                }
                else if (Selected.getType().equals("Additional Notes")){
                    AddNotesAsync ana = new AddNotesAsync(recycleBin.this, null, "PermanentDelete",Selected.getId());
                    ana.execute();
                }
                else
                {
                    AddDetailAsync ada = new AddDetailAsync(recycleBin.this, null, "PermanentDelete", Selected.getId());
                    ada.execute();
                }
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
