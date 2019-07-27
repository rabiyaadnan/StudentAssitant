package com.example.studentassitant;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import db.additionalNotes;
import db.courses;
import db.details;

public class binAdapter extends RecyclerView.Adapter<binHolder>
{
    ArrayList<recycle> recycle;
    public int ItemLayout;

    public binAdapter( ArrayList<recycle> recycle, int itemLayout) {
        this.recycle = recycle;
        ItemLayout = itemLayout;

    }

    @NonNull
    @Override
    public binHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context c = parent.getContext();
        LayoutInflater li = LayoutInflater.from(c);
        View view = li.inflate(ItemLayout, parent, false);
        return new binHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull binHolder holder, int position) {

        if (holder != null && recycle != null)
        {
            holder.name.setText(recycle.get(position).getName());
            holder.type.setText(recycle.get(position).getType());
            holder.date.setText(recycle.get(position).getDate());

        }
    }

    @Override
    public int getItemCount() {
        if (recycle != null)
        {
            return recycle.size();
        }
        else return 0;
    }
    public void setItems(ArrayList<recycle> alc){
        recycle = alc;
    }




    }


