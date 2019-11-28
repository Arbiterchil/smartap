package com.example.arbiterchil.smartap;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class computes extends AppCompatActivity {

    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter;
    TextView ohshit;
    Spinner homan;
    DatabaseReference reference;
    Spinner spin,spins,spinss,spinses;
    FirebaseDatabase firebaseDatabase;
    ListView listView;

    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;
    Button passit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computes);



        tabLayout= findViewById(R.id.atabs);
        viewPager = findViewById(R.id.viewpage);
//        passit = findViewById(R.id.pass);


        viewpage viewpage= new viewpage(getSupportFragmentManager());
        viewpage.AddFrag(new quizrec(),"Compute");
        viewpage.AddFrag(new assrec(),"Save Record");
        viewPager.setAdapter(viewpage);
        tabLayout.setupWithViewPager(viewPager);

//        spin = findViewById(R.id.groups);
//        spins = findViewById(R.id.memname);
//        spinss = findViewById(R.id.grads);
//        ohshit = findViewById(R.id.uidsel);
//        ohshit.setVisibility(View.GONE);


    }


}
