package com.example.studentassitant;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.example.studentassitant.QAP;
import com.example.studentassitant.R;

public class FragmentSection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_section);
        Fragment r = new QAP();
        FragmentManager fmr = getFragmentManager();
        FragmentTransaction ftr = fmr.beginTransaction();
        ftr.replace(R.id.fragment,r);
        ftr.commit();
    }
}
