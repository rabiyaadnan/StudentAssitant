package com.example.studentassitant;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import db.additionalNotes;
import db.database;


public class AddNotesAsync extends AsyncTask<Void, Void, Boolean> {

        Context c;
        private additionalNotes additionalNotesInfo;
        int id;
        String title;
        String description;
        String date;
        String time;
        String State = "";

        public AddNotesAsync(Context c, additionalNotes addNotes, String State, int id) {
            this.c = c;
            this.additionalNotesInfo = addNotes;
            this.State = State;
            //this.title = title;
            this.id = id;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {

                database mydb = database.getAppDatabase(c);

                //additionalNotesInfo = mydb.additionalNotesDao().findByName();

                if(State.equals("Add"))
                {
                    if (additionalNotesInfo != null) {
                        //additionalNotesInfo = new additionalNotes(title, description, date, time);
                        mydb.additionalNotesDao().insertAll(additionalNotesInfo);
                        return true;
                    }

                    else {

                        return false;
                    }
                }
                else if (State.equals("Update"))
                {
                    if (additionalNotesInfo != null)
                    {
                        additionalNotes update = mydb.additionalNotesDao().findByID(additionalNotesInfo.getId());
                        if(update!=null) {
                            mydb.additionalNotesDao().updateAll(additionalNotesInfo);
                            return true;
                        }

                    }
                    else {
                        //Toast.makeText(getBaseContext, "The Message", Toast.Length_Short).show();
                        return false;
                    }
                }
                else if (State.equals("PermanentDelete"))
                {
                    additionalNotes update = mydb.additionalNotesDao().findByID(id);
                    if(update!=null) {
                        mydb.additionalNotesDao().delete(update);
                        return true;
                    }
                    return false;
                }
                else if (State.equals("Recover"))
                {
                    additionalNotes update = mydb.additionalNotesDao().findByID(id);
                    if(update!=null) {
                        update.setDel(false);
                        mydb.additionalNotesDao().updateAll(update);
                        return true;
                    }
                    return false;
                }

            else if (State.equals("Delete"))
            {
                //if (Name != null) {
                    additionalNotes checking = mydb.additionalNotesDao().findByID(id);
                    if (checking != null) {
                        checking.setDel(true);

                        Date c = Calendar.getInstance().getTime();
                        //System.out.println("Current time => " + c);

                        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                        String formattedDate = df.format(c);

                        //System.out.println(formattedDate);

                        checking.setDod(formattedDate);

                        mydb.additionalNotesDao().updateAll(checking);
                        return true;
                    }

                    else
                    {
                        return false;
                    }

                //}

                //return false;
            }

                return false;
        }

        @Override
        protected void onPostExecute(Boolean aVoid) {
                super.onPostExecute(aVoid);
                if(State.equals("PermanentDelete")){
                    if(aVoid == true) {
                        c.startActivity(new Intent(c, MainActivity.class));
                    }
                    else
                    {
                        Toast.makeText(c, "Note does not exist", Toast.LENGTH_SHORT);
                    }
                }
            if(State.equals("Recover")){
                if(aVoid == true) {
                    c.startActivity(new Intent(c, MainActivity.class));
                }
                else
                {
                    Toast.makeText(c, "Note doees not exist", Toast.LENGTH_SHORT);
                }
            }
                else{
                    if (aVoid == true) {
                        Intent intent = new Intent(c, MainActivity.class);
                        intent.putExtra("Title", title);
                        intent.putExtra("description", description);
                        intent.putExtra("date", date);
                        intent.putExtra("time", time);
                        c.startActivity(intent);
                    } else {
                        //error.setVisibility(View.VISIBLE);
                    }
                }

        }

}