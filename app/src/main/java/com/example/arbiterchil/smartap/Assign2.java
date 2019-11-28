package com.example.arbiterchil.smartap;

import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Assign2 extends AppCompatActivity {
    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign2);

        tabLayout= findViewById(R.id.atabs);
        viewPager = findViewById(R.id.viewpage);

        viewpage viewpage= new viewpage(getSupportFragmentManager());
        viewpage.AddFrag(new aquiz(),"Quiz");
        viewpage.AddFrag(new aasign(),"Assignment");
        viewpage.AddFrag(new arecit(),"Recitation");
        viewpage.AddFrag(new aactive(),"Research/Projects");
        viewpage.AddFrag(new aexam(),"Exam");
        viewpage.AddFrag(new exalab(),"Com Lab Exam");
        viewpage.AddFrag(new acomlab(),"Com Lab");
        viewpage.AddFrag(new comexa(),"Com Quizzes");

        viewPager.setAdapter(viewpage);
        tabLayout.setupWithViewPager(viewPager);
    }


}
