package com.example.studentassitant;

import android.content.Context;
import android.os.AsyncTask;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import db.additionalNotes;
import db.database;

public class getAddNotesAsync extends AsyncTask<Void,Void,Boolean> {

    Context c;
    private List<additionalNotes> addNotesInfo;
    RecyclerView rv;
    RecyclerViewAdapter rva;

    public getAddNotesAsync(Context c, RecyclerView rv, RecyclerViewAdapter rva) {
        this.c = c;
        this.rv = rv;
        this.rva = rva;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {

        database mydb = database.getAppDatabase(c);
        addNotesInfo = mydb.additionalNotesDao().findAll();
        return true;
    }

    @Override
    protected void onPostExecute(Boolean aVoid) {
        super.onPostExecute(aVoid);
        if(addNotesInfo!= null)
        {
            ArrayList<additionalNotes> alc = new ArrayList<>();
            for(int i = 0; i < addNotesInfo.size(); i++)
            {
                if(addNotesInfo.get(i).getDel() == false)
                    alc.add(addNotesInfo.get(i));
            }
            rva.setItems(alc);
            rva.notifyDataSetChanged();
        }
    }
}
