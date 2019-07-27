package com.example.studentassitant;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class binHolder extends RecyclerView.ViewHolder {
    TextView name, type, date;

    public binHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        type = itemView.findViewById(R.id.type);
        date = itemView.findViewById(R.id.date);
    }
}
