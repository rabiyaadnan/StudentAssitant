package com.example.studentassitant;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class viewHolder2 extends RecyclerView.ViewHolder {
    TextView Title2, Date2, Time2;
    Button edit, delete;

    public viewHolder2(@NonNull View itemView) {
        super(itemView);

        Title2 = itemView.findViewById(R.id.title);
        Date2  =  itemView.findViewById(R.id.date);
        Time2  =  itemView.findViewById(R.id.time);
        edit = itemView.findViewById(R.id.updateRedirectButton);
        delete = itemView.findViewById(R.id.deleteButton);

//        edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(view.getId())
//            }
//        });



    }
}
