package com.example.arbiterchil.smartap;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class Pop extends AppCompatActivity {

    Button genetpasss;
    Button savegroup;
    EditText groupcreate;
    EditText passgenets;
    TextView creator,uids,url;
    DatabaseReference mDatabase;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser user;
    private CircleImageView over;
    private static final  int Gallerypick = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popwindows);

        DisplayMetrics dm =  new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);


        final Bundle shit = getIntent().getExtras();
        int width = dm.widthPixels;
        int hiegth = dm.heightPixels;

        getWindow().setLayout((int)(width*.9),(int)(hiegth*.4));


        firebaseAuth = FirebaseAuth.getInstance();
        uids = findViewById(R.id.uids);
        passgenets =findViewById(R.id.passgenets);
        creator = findViewById(R.id.creator);
        savegroup = findViewById(R.id.savegroup);
        groupcreate  = findViewById(R.id.groupcreate);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Groups");
        mDatabase = FirebaseDatabase.getInstance().getReference().child("User")
                .child(firebaseAuth.getCurrentUser().getUid());
        url = findViewById(R.id.urlimage);
        genetpasss = findViewById(R.id.genetpass);


        genetpasss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                passgenets.setText(generateString(6));
            }
        });



        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                smartdab smartdab = dataSnapshot.getValue(smartdab.class);
                Group g = dataSnapshot.getValue(Group.class);
                creator.setText(smartdab.getFullname());
                uids.setText(g.getUid());

                uids.setVisibility(View.GONE);
                creator.setVisibility(View.GONE);
                }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    mDatabase = FirebaseDatabase.getInstance().getReference();



    savegroup.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        final String groupname = groupcreate.getText().toString().trim();

        final String passgenerate = passgenets.getText().toString().trim();

            if(TextUtils.isEmpty(groupname)){
                Toast.makeText(Pop.this, "Please Enter Group Name", Toast.LENGTH_SHORT).show();
                groupcreate.requestFocus();
            }else if(TextUtils.isEmpty(passgenerate)){
                Toast.makeText(Pop.this, "Please Enter Password Group Name", Toast.LENGTH_SHORT).show();
                    passgenets.requestFocus();
            }else{

                svg();
            }

        }

            private void svg() {
            Group group = new Group();
            DatabaseReference ref = mDatabase.push();
            String urlis = url.getText().toString();
           final String idg = ref.getKey();
                final String passgenerate = passgenets.getText().toString().trim();
                final String groupname = groupcreate.getText().toString().trim();
                    String zuid = FirebaseAuth.getInstance().getCurrentUser().getUid();


                Group smartasslove = new Group(groupname,passgenerate,idg,zuid,urlis);
                            FirebaseDatabase.getInstance().getReference().child("Groups")
                                    .child(idg)
                                    .setValue(smartasslove).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Pop.this, "Save Successfully", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }else{
                                        Toast.makeText(Pop.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });





                        }});
    }
        private String generateString(int length)
        {
            char[] value ="ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
            StringBuilder builder = new StringBuilder();
            Random random = new Random();
            for(int i =0;i <length;i++)
            {
                char c = value[random.nextInt(value.length)];
                builder.append(c);
            }
            return builder.toString();
        }

    @Override
    protected void onStart() {
        super.onStart();
       DatabaseReference refer = FirebaseDatabase.getInstance().
                getReference().child("User").
                child(firebaseAuth.getCurrentUser().getUid());

        refer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                smartdab smartdab = dataSnapshot.getValue(smartdab.class);
                url.setText(smartdab.getUrl());
                url.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
