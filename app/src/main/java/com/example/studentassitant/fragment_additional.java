package com.example.studentassitant;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import db.additionalNotes;

public class fragment_additional extends Fragment {

    View view;
    public fragment_additional() {
    }

    RecyclerView object;
    RecyclerViewAdapter adapt;
    GestureDetector gesture;
    Dialog mydialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View c = inflater.inflate(R.layout.fragment_add_notes, container, false);

        object = c.findViewById(R.id.recyclerView);


        gesture = new GestureDetector(c.getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent v) {
                View view = object.findChildViewUnder(v.getX(), v.getY());
                if (view != null) {
                    int index = object.getChildAdapterPosition(view);

                    additionalNotes a = adapt.getItem(index);


                    //showpopup();


                }
                return true;
            }
        });


        mydialog = new Dialog(getActivity());

        adapt = new RecyclerViewAdapter(new ArrayList<additionalNotes>(), R.layout.addnoteslayout, c.getContext());
        object.setAdapter(adapt);
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
        getAddNotesAsync gc = new getAddNotesAsync(c.getContext(), object, adapt);
        gc.execute();
        Button saveForm = c.findViewById(R.id.addButton);
        saveForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(c.getContext(), AddNotes.class);
                startActivity(intent);
            }
        });

        return c;
    }
}
