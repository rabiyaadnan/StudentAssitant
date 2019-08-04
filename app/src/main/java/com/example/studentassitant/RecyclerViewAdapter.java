package com.example.studentassitant;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import db.additionalNotes;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.viewHolder2> {

    List<additionalNotes> addNotes;
    Context ab;
    public int ItemLayout;
    int id;
    String info;


    //private static int currentSelectedIndex = -1;

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

        }
    }

    public RecyclerViewAdapter(List<additionalNotes> addNotes, int itemLayout, Context c) {
        this.addNotes = addNotes;
        ItemLayout = itemLayout;
        ab = c;
    }

    @NonNull
    @Override
    public viewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context c = parent.getContext();
        LayoutInflater li = LayoutInflater.from(c);
        View view = li.inflate(ItemLayout, parent, false);
        return new viewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.viewHolder2 holder,final int position) {
        if (holder != null) {
            if (addNotes != null) {
                holder.Title2.setText(addNotes.get(position).getTitle());
                holder.Date2.setText(addNotes.get(position).getDate());
                holder.Time2.setText(addNotes.get(position).getTime());

                holder.edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ab, UpdateNotes.class);
                        intent.putExtra("title",addNotes.get(position).getTitle());
                        intent.putExtra("date",addNotes.get(position).getDate());
                        intent.putExtra("time",addNotes.get(position).getTime());
                        intent.putExtra("description",addNotes.get(position).getDescription());
                        intent.putExtra("id", Integer.toString( addNotes.get(position).getId()));

                        ab.startActivity(intent);
                    }
                });

                holder.delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        id = addNotes.get(position).getId();
                        info = "DeleteAddNotes";
                        pop();

                    }
                });
            }


        }
    }

    @Override
    public int getItemCount() {
        if (addNotes != null)
            return addNotes.size();
        return 0;
    }



    public void setItems(List<additionalNotes> arrayListc) {
        addNotes = arrayListc;
    }

    public additionalNotes getItem(int index) {
        return addNotes.get(index);
    }


    public void pop()
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ab);
        alertDialogBuilder.setMessage("Are you sure you want to delete? ");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        {
                            AddNotesAsync acs = new AddNotesAsync(ab, new additionalNotes(), "Delete",id);
                            acs.execute();
                        }
                    }
                });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}