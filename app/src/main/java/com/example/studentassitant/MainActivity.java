package com.example.studentassitant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private TabLayout tablayout;
    private ViewPager viewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        tablayout = (TabLayout) findViewById(R.id.tablayout_id);
        viewPager = (ViewPager) findViewById(R.id.viewpager_id);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment (new fragment_quiz(),"Quiz");
        adapter.addFragment (new fragment_assignment(),"assignment");
        adapter.addFragment (new fragment_project(),"project");
        adapter.addFragment (new fragment_additional(),"courses");
        adapter.addFragment (new fragment_additional(),"notes");

        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);
        Button btn = findViewById(R.id.saikal);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, recycleBin.class));
            }
        });

    }
}
