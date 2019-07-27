package com.example.studentassitant;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import db.additionalNotes;
import db.courses;
import db.database;
import db.details;

public class GetDetails extends AsyncTask<Void,Void,Boolean> {

    Context c;
    private List<details> deets;
    private ArrayList<recycle> recycle = new ArrayList<recycle>();
    binAdapter ba;
    RecyclerView rv;
    Adapter rva;
    String state = "";
    public GetDetails(Context c, RecyclerView rv, Adapter rva, String state, binAdapter ba) {
        this.c = c;
        this.rv = rv;
        this.rva = rva;
        this.state = state;
        this.ba = ba;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {


        database mydb = database.getAppDatabase(c);
        deets = mydb.detailsDao().findAll();

        if(state.equals("fillRecycle"))
        {
            List<courses> coursesList = mydb.coursesDao().findAll();
            List<additionalNotes> additionalNotes = mydb.additionalNotesDao().findAll();

            for(int i = 0; i < coursesList.size(); i++) {
                if(coursesList.get(i).isCourseDel())
                {
                    recycle.add(new recycle(-1, coursesList.get(i).getCourseName(),"Courses", coursesList.get(i).getDod()));
                }
            }

            for(int i = 0; i < deets.size(); i++) {
                if(!deets.get(i).getCourseDel() && deets.get(i).getDetailDel())
                {
                    recycle.add(new recycle(deets.get(i).getId(),deets.get(i).getTitle(), deets.get(i).getPQA(), deets.get(i).getDod()));
                }
            }

            for(int i = 0; i < additionalNotes.size(); i++) {
                if(additionalNotes.get(i).getDel())
                {
                    recycle.add(new recycle(additionalNotes.get(i).getId(),additionalNotes.get(i).getTitle(),"Additional Notes", additionalNotes.get(i).getDod()));
                }
            }

        }

        return true;

    }


    @Override
    protected void onPostExecute(Boolean aVoid) {
        super.onPostExecute(aVoid);
        if(!state.equals("fillRecycle")) {
            if (deets != null) {
                ArrayList<details> Detailing = new ArrayList<>();
                for (int i = 0; i < deets.size(); i++) {
                    if (deets.get(i).getPQA().equals(state) && !deets.get(i).isCompleted())
                        if (deets.get(i).getCourseDel() == false && deets.get(i).getDetailDel() == false) {
                            Detailing.add(deets.get(i));
                        }
                }
                rva.setItems(Detailing);
                rva.notifyDataSetChanged();
            }
        }
        else{
            ba.setItems(recycle);
            ba.notifyDataSetChanged();
        }


    }
}


