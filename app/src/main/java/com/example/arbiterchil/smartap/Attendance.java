package com.example.arbiterchil.smartap;


import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class Attendance extends AppCompatActivity {
    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;
    public Attendance(){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        tabLayout= findViewById(R.id.atabs);
        viewPager = findViewById(R.id.viewpage);
        viewpage viewpage= new viewpage(getSupportFragmentManager());
        viewpage.AddFrag(new attenAbs(),"Absents Records");
        viewpage.AddFrag(new attenLts(),"Lates Records");
        viewPager.setAdapter(viewpage);
        tabLayout.setupWithViewPager(viewPager);


    }

}

