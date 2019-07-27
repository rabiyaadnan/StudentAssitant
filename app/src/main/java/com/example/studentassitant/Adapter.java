package com.example.studentassitant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import db.courses;
import db.details;

public class Adapter extends RecyclerView.Adapter<viewHolder>
{
    List<details> Deets;
    public int ItemLayout;

    public Adapter(List<details> Deets, int itemLayout) {
        this.Deets = Deets;
        ItemLayout = itemLayout;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context c = parent.getContext();
        LayoutInflater li = LayoutInflater.from(c);
        View view = li.inflate(ItemLayout, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        if (holder != null && Deets != null)
        {
                holder.Sub.setText(Deets.get(position).getCourseName());
                holder.Time.setText(Deets.get(position).getTime());
                holder.Title.setText(Deets.get(position).getTitle());
                holder.Date.setText(Deets.get(position).getDate());
        }

    }

    @Override
    public int getItemCount() {
        if (Deets != null)
        {
            return Deets.size();
        }
        else return 0;
    }
    public void setItems(List<details> alc){
        Deets = alc;
    }
}
