package com.example.studentassitant;

import android.content.Context;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import db.courses;
import db.details;

public class Adapter extends RecyclerView.Adapter<Adapter.viewHolder>
{
    private static final int USER_TYPE = 1;
    private static final int HEADER_TYPE = 2;
    private ItemTouchHelper touchHelper;
    List<details> Deets;
    public int ItemLayout;
    private Adapter.ClickAdapterListener listener;
    private SparseBooleanArray selectedItems;

    private static int currentSelectedIndex = -1;

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        TextView QAP, Sub, Title, Time, Date;
        LinearLayout Layout;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            Sub = itemView.findViewById(R.id.Subject);
            Title = itemView.findViewById(R.id.Title);
            Time = itemView.findViewById(R.id.Time);
            Date = itemView.findViewById(R.id.Date);
            Layout = itemView.findViewById(R.id.qap_row);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View view) {
            listener.onRowLongClicked(getAdapterPosition());
            view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
            return true;
        }
    }

    public Adapter(List<details> Deets, int itemLayout, Adapter.ClickAdapterListener listener) {
        this.Deets = Deets;
        ItemLayout = itemLayout;
        this.listener = listener;
        selectedItems = new SparseBooleanArray();
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
    public void onBindViewHolder(@NonNull final viewHolder holder, int position) {

        if (holder != null && Deets != null) {
            holder.Sub.setText(Deets.get(position).getCourseName());
            holder.Time.setText(Deets.get(position).getTime());
            holder.Title.setText(Deets.get(position).getTitle());
            holder.Date.setText(Deets.get(position).getDate());
            holder.itemView.setActivated(selectedItems.get(position, false));
            applyClickEvents(holder, position);
        }

    }

    private void applyClickEvents(Adapter.viewHolder holder, final int position) {
        holder.Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onRowClicked(position);
            }
        });

        holder.Layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onRowLongClicked(position);
                view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
                return true;
            }
        });
    }
    @Override
    public int getItemCount() {
        if (Deets != null)
        {
            return Deets.size();
        }
        else return 0;
    }

    public void toggleSelection(int pos) {
        currentSelectedIndex = pos;
        if (selectedItems.get(pos, false)) {
            selectedItems.delete(pos);
        } else {
            selectedItems.put(pos, true);
        }
        notifyItemChanged(pos);
    }

    public void selectAll() {

        for (int i = 0; i < getItemCount(); i++)
            selectedItems.put(i, true);
        notifyDataSetChanged();

    }


    public void clearSelections() {
        selectedItems.clear();
        notifyDataSetChanged();
    }

    public int getSelectedItemCount() {
        return selectedItems.size();
    }

    public void setItems(List<details> alc){
        Deets = alc;
    }
    public List getSelectedItems() {
        List items =
                new ArrayList(selectedItems.size());
        for (int i = 0; i < selectedItems.size(); i++) {
            items.add(selectedItems.keyAt(i));
        }
        return items;
    }

    public void removeData(int position) {

        Deets.remove(position);

        resetCurrentIndex();
    }



    private void resetCurrentIndex() {
        currentSelectedIndex = -1;
    }

    public interface ClickAdapterListener {

        void onRowClicked(int position);

        void onRowLongClicked(int position);
    }





}
