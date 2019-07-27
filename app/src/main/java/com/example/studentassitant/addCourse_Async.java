package com.example.studentassitant;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import db.courses;
import db.database;
import db.details;

public class addCourse_Async extends AsyncTask<Void,Void,Boolean> {

        Context c;
        private courses courseInfo;
        String State = "";
        String Name = "";
        ArrayAdapter<String> adapt;
        Spinner spinner;
    public addCourse_Async(Context c, courses course, String State, String Name, ArrayAdapter<String> adapt, Spinner spinner ) {
        this.c = c;
        this.courseInfo = course;
        this.State = State;
        this.Name = Name;
        this.adapt = adapt;
        this.spinner = spinner;
    }

    @Override
        protected Boolean doInBackground(Void... voids) {

            database mydb = database.getAppDatabase(c);
            if (State.equals("Add")) {


                if (courseInfo.getCourseName() != null) {
                    courses checking = mydb.coursesDao().findByName(courseInfo.getCourseName());
                    if (checking == null) {
                        mydb.coursesDao().insertAll(courseInfo);
                        return true;
                    } else {
                        return false;
                    }

                }

                return false;
            }
        else if (State.equals("Recover")) {
            if (Name != null) {
                courses checking = mydb.coursesDao().findByName(Name);
                if (checking != null) {
                    checking.setCourseDel(false);
                    List<details> deets = mydb.detailsDao().findByCourseName(Name);

                    for(int i =0; i < deets.size(); i++) {
                        deets.get(i).setCourseDel(false);
                        mydb.detailsDao().updateAll(deets.get(i));
                    }
                    mydb.coursesDao().updateAll(checking);
                    return true;
                } else {
                    return false;
                }

            }

            return false;
        }
            else if (State.equals("PermanentDelete")) {
                if (Name != null) {
                    courses checking = mydb.coursesDao().findByName(Name);
                    if (checking != null) {
                        List<details> deets = mydb.detailsDao().findByCourseName(Name);
                        for(int i =0; i < deets.size(); i++)
                            mydb.detailsDao().delete(deets.get(i));
                        mydb.coursesDao().delete(checking);
                        return true;
                    } else {
                        return false;
                    }

                }

                return false;
            }
            else if (State.equals("Update"))
            {
                if (courseInfo.getCourseName() != null) {
                    courses checking = mydb.coursesDao().findByName(courseInfo.getCourseName());
                    if (checking != null) {
                        mydb.coursesDao().updateAll(courseInfo);
                        return true;
                    }
                    else
                    {
                        return false;
                    }

                }

                return false;
            }
            else if (State.equals("Delete"))
            {
                if (Name != null) {
                    courses checking = mydb.coursesDao().findByName(Name);
                    if (checking != null) {
                        checking.setCourseDel(true);
                        List<details> deets = mydb.detailsDao().findByCourseName(Name);
                        for(int i = 0; i<deets.size(); i++) {
                            deets.get(i).setCourseDel(true);
                            mydb.detailsDao().updateAll(deets.get(i));
                        }


                        Date c = Calendar.getInstance().getTime();
                        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                        String formattedDate = df.format(c);

                        checking.setDod(formattedDate);
                        mydb.coursesDao().updateAll(checking);
                        //mydb.coursesDao().delete(checking); //update with deleted boolean
                        return true;
                    }

                    else
                    {
                        return false;
                    }

                }

                return false;
            }
            else if (State.equals("FindByName"))
            {
                courseInfo = mydb.coursesDao().findByName(Name);
                if (courseInfo != null) {
                    return true;
                }
                else{
                    return false;
                }
            }
            else if (State.equals("AddCoursesInSpinner"))
            {
                List<courses> course= mydb.coursesDao().findAll();
                if (course != null) {
                    for(int i = 0; i < course.size(); i++)
                    {
                        adapt.add(course.get(i).getCourseName());
                    }
                    adapt.notifyDataSetChanged();
                    return true;
                }
                else{
                    return false;
                }
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aVoid) {
            super.onPostExecute(aVoid);
            if (State.equals("Add")) {
                if (aVoid == true) {
                    Intent intent = new Intent(c, MainActivity.class);
                    c.startActivity(intent);
                } else {
                    Toast.makeText(c, "Course Name already exists!", Toast.LENGTH_SHORT).show();
                }
            }
            else if (State.equals("Recover")) {
                if (aVoid == true) {
                    Intent intent = new Intent(c, MainActivity.class);
                    c.startActivity(intent);
                    Toast.makeText(c, "Course has been recovered!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(c, "Course Name does not exist!", Toast.LENGTH_SHORT).show();
                }
            }
            else if (State.equals("PermanentDelete")) {
                if (aVoid == true) {
                    Intent intent = new Intent(c, MainActivity.class);
                    c.startActivity(intent);
                    Toast.makeText(c, "Course has been deleted!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(c, "Course Name does not exist!", Toast.LENGTH_SHORT).show();
                }
            }
            else if (State.equals("Update"))
            {
                if (aVoid == true) {
                    Intent intent = new Intent(c, MainActivity.class);
                    Toast.makeText(c, "Updated!", Toast.LENGTH_SHORT).show();
                    c.startActivity(intent);
                }
                else {
                    Toast.makeText(c, "Course Name does not exist", Toast.LENGTH_SHORT).show();
                }

            }
            else if (State.equals("Delete"))
            {
                if (aVoid == true) {
                    Intent intent = new Intent(c, MainActivity.class);
                    Toast.makeText(c, "Course has been deleted!", Toast.LENGTH_SHORT).show();
                    c.startActivity(intent);
                }
                else {
                    Toast.makeText(c, "Bhenchod yahan py tu nahi aana tha", Toast.LENGTH_SHORT).show();
                }

            }
            else if (State.equals("FindByName"))
            {
                if(aVoid == true){
                    Intent intent = new Intent(c, UpdateCourse.class);
                    intent.putExtra("Name",  courseInfo.getCourseName());
                    intent.putExtra("Venue", courseInfo.getClassroom());
                    intent.putExtra("Time", courseInfo.getTime() );
                    intent.putExtra("Days", courseInfo.getDays());
                    c.startActivity(intent);
                }
            }



        }
    }


