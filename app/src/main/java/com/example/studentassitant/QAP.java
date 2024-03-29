package com.example.studentassitant;

import android.content.Intent;
import android.os.Bundle;

import android.app.Fragment;

import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentassitant.R;

import java.util.ArrayList;

import db.details;

public class QAP extends Fragment {
    RecyclerView object;
    Adapter adapt;
    GestureDetector gesture;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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

        adapt = new Adapter(new ArrayList<details>(), R.layout.qap_row);
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
//        GetDetails gc = new GetDetails(c.getContext(), object, adapt);
//        gc.execute();
        return c;
    }
}
