package com.example.studentassitant;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import db.details;

public class fragment_assignment extends Fragment implements Adapter.ClickAdapterListener{
    View view;

    RecyclerView object;
    Adapter adapt;
    GestureDetector gesture;
    private ActionModeCallback actionModeCallback;
    private ActionMode actionMode;
    public fragment_assignment() {
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View c =  inflater.inflate(R.layout.fragment_qa, container, false);
        object = c.findViewById(R.id.recycler);
        gesture = new GestureDetector(c.getContext(), new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent v) {
                View view = object.findChildViewUnder(v.getX(), v.getY());
                if (view != null)
                {
                    int index = object.getChildAdapterPosition(view);
                    details Selected_Details = adapt.Deets.get(index);
                    Intent intent = new Intent(c.getContext(), UpdateDetails.class);
                    intent.putExtra("Title", Selected_Details.getTitle());
                    intent.putExtra("CourseName", Selected_Details.getCourseName());
                    intent.putExtra("Time", Selected_Details.getTime());
                    intent.putExtra("PQA", Selected_Details.getPQA());
                    intent.putExtra("Date", Selected_Details.getDate());
                    intent.putExtra("Description", Selected_Details.getDescription());
                    intent.putExtra("ID", Integer.toString(Selected_Details.getId()));
                    intent.putExtra("Completed", Selected_Details.isCompleted());
                    startActivity(intent);
                }
                else
                    Toast.makeText(c.getContext(), "Sorry, no view has been selected", Toast.LENGTH_SHORT);
                return true;
            }
        });

        adapt = new Adapter(new ArrayList<details>(), R.layout.qap_row,this);


        object.setAdapter(adapt);

        actionModeCallback = new ActionModeCallback();
        object.setLayoutManager(new LinearLayoutManager(c.getContext()));

        object.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                gesture.onTouchEvent(e);
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        GetDetails gc = new GetDetails(c.getContext(), object, adapt, "Assignment",null);
        gc.execute();
        Button DataEntry = (Button) c.findViewById(R.id.button3);
        DataEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(c.getContext(), DetailsEntry.class);
                intent.putExtra("PQA", "Assignment");
                startActivity(intent);
            }
        });
        return c;
    }


    private class ActionModeCallback implements ActionMode.Callback {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.menu_action_mode, menu);

            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            Log.d("API123", "here");
            switch (item.getItemId()) {


                case R.id.action_delete:
                    // delete all the selected rows
                    deleteRows();
                    mode.finish();
                    return true;

                case R.id.action_select_all:
                    selectAll();
                    return true;

                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            adapt.clearSelections();
            actionMode = null;
        }
    }
    private void enableActionMode(int position) {
        if (actionMode == null) {
            actionMode = ((AppCompatActivity) getActivity()).startSupportActionMode(actionModeCallback);
        }
        toggleSelection(position);
    }

    private void toggleSelection(int position) {
        adapt.toggleSelection(position);
        int count = adapt.getSelectedItemCount();

        if (count == 0) {
            actionMode.finish();
            actionMode = null;
        } else {
            actionMode.setTitle(String.valueOf(count));
            actionMode.invalidate();
        }
    }

    private void selectAll() {
        adapt.selectAll();
        int count = adapt.getSelectedItemCount();

        if (count == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(String.valueOf(count));
            actionMode.invalidate();
        }

        actionMode = null;
    }

    private void deleteRows() {
        List selectedItemPositions =
                adapt.getSelectedItems();
        for (int i = selectedItemPositions.size() - 1; i >= 0; i--) {
            adapt.removeData((Integer) selectedItemPositions.get(i));
        }
        adapt.notifyDataSetChanged();
        actionMode = null;

    }

    @Override
    public void onRowClicked(int position) {
        enableActionMode(position);
    }

    @Override public void onRowLongClicked(int position) {
        enableActionMode(position);
    }
}
