package com.example.studentassitant;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import db.courses;
import db.database;
import db.details;

public class AddDetailAsync extends AsyncTask<Void,Void,Boolean> {

    Context c;
    private details courseInfo;
    String State;
    int ID;
    public AddDetailAsync(Context c, details detail, String State, int ID) {
        this.c = c;
        this.courseInfo = detail;
        this.State = State;
        this.ID = ID;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {

        database mydb = database.getAppDatabase(c);
        if(State.equals("Add")) {
            if (courseInfo.getCourseName() != null) {
                    mydb.detailsDao().insertAll(courseInfo);
                    return true;
                } else {
                    return false;
                }

            }
        else if(State.equals("Recover"))
        {
            details checking = mydb.detailsDao().findByID(ID);
            if (checking != null) {
                checking.setDetailDel(false);
                mydb.detailsDao().updateAll(checking);
                return true;
            } else {
                return false;
            }
        }
        else if(State.equals("PermanentDelete"))
        {
            details checking = mydb.detailsDao().findByID(ID);
            if (checking != null) {
                mydb.detailsDao().delete(checking);
                return true;
            } else {
                return false;
            }
        }
        else if(State.equals("Update"))
        {
            details checking = mydb.detailsDao().findByID(courseInfo.getId());
            if (checking != null) {
                mydb.detailsDao().updateAll(courseInfo);
                return true;
            } else {
                return false;
            }
        }

        else if(State.equals("Delete"))
        {
            details checking = mydb.detailsDao().findByID(ID);
            if (checking != null) {
                checking.setDetailDel(true);

                Date c = Calendar.getInstance().getTime();

                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String formattedDate = df.format(c);


                checking.setDod(formattedDate);

                mydb.detailsDao().updateAll(checking);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean aVoid) {
        super.onPostExecute(aVoid);
        if(State.equals("Add")) {
            if (aVoid == true) {
                Intent intent = new Intent(c, MainActivity.class);
                Toast.makeText(c, "Course detail has been added", Toast.LENGTH_SHORT).show();
                c.startActivity(intent);
            }
        }
        else if(State.equals("Update")){
            if (aVoid == true) {
                Intent intent = new Intent(c, MainActivity.class);
                Toast.makeText(c, "Course detail has been updated", Toast.LENGTH_SHORT).show();
                c.startActivity(intent);
            }
        }
        else if(State.equals("Delete")){
            if (aVoid == true) {
                Intent intent = new Intent(c, MainActivity.class);
                Toast.makeText(c, "Course detail has been deleted", Toast.LENGTH_SHORT).show();
                c.startActivity(intent);
            }
        }
        else if(State.equals("Recover")){
            if (aVoid == true) {
                Intent intent = new Intent(c, MainActivity.class);
                Toast.makeText(c, "Course detail has been recovered", Toast.LENGTH_SHORT).show();
                c.startActivity(intent);
            }
        }
        else if(State.equals("PermanentDelete")){
            if (aVoid == true) {
                Intent intent = new Intent(c, MainActivity.class);
                Toast.makeText(c, "Course detail has been deleted", Toast.LENGTH_SHORT).show();
                c.startActivity(intent);
            }
        }



    }
}


