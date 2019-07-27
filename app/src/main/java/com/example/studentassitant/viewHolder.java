package com.example.studentassitant;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentassitant.R;

public class viewHolder extends RecyclerView.ViewHolder {
    TextView QAP, Sub, Title, Time, Date;

    public viewHolder(@NonNull View itemView) {
        super(itemView);
        Sub = itemView.findViewById(R.id.Subject);
        Title = itemView.findViewById(R.id.Title);
        Time = itemView.findViewById(R.id.Time);
        Date = itemView.findViewById(R.id.Date);
    }
}
