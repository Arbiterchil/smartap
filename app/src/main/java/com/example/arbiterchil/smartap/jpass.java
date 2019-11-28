package com.example.arbiterchil.smartap;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class jpass extends AppCompatActivity {

    Button joins;
    EditText joinpass;
    DatabaseReference mref,mref1;
    FirebaseAuth firebaseAuth;
    private DatabaseReference refer;
    FirebaseUser user;
    String currentuser;
    TextView stud,sting;
    ProgressDialog prog;
    DatabaseReference liststud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jpass);
        prog = new ProgressDialog(this);

        stud = findViewById(R.id.studentsname);
        liststud = FirebaseDatabase.getInstance().getReference().child("StudentList");
        mref = FirebaseDatabase.getInstance().getReference().child("Groups");
        refer = FirebaseDatabase.getInstance().getReference().child("Users");
        mref = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        currentuser = firebaseAuth.getCurrentUser().getUid();
        sting  = findViewById(R.id.stringIma);

        DisplayMetrics dm =  new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int hiegth = dm.heightPixels;

        getWindow().setLayout((int)(width*.7),(int)(hiegth*.3));


        joins = findViewById(R.id.joins);
        joinpass= findViewById(R.id.joinpass);
        refer = FirebaseDatabase.getInstance().
                getReference().child("User").
                child(firebaseAuth.getCurrentUser().getUid());

        refer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                smartdab smartdab = dataSnapshot.getValue(smartdab.class);
                stud.setText(smartdab.getFullname());
                sting.setText(smartdab.getUrl());
                stud.setVisibility(View.GONE);
                sting.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        Query query = FirebaseDatabase.getInstance().getReference().child("User")
                .orderByChild("url").equalTo(stud.getText().toString());

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds: dataSnapshot.getChildren()){

                    String haha = ds.child("url").getValue(String.class);

                    sting.setText(haha);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        Menyueh();

        joins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prog.setTitle("Please Wait.......");
                prog.setMessage("Saving Student In the List.");
                prog.setCanceledOnTouchOutside(false);
                prog.show();


                String passwo = joinpass.getText().toString();
                if(passwo.isEmpty()) {
                    prog.dismiss();
                    Toast.makeText(jpass.this, "Type the Password to join.", Toast.LENGTH_SHORT).show();
                }
                    Join();


            }
        });






    }

    private void Menyueh() {

        final String key= getIntent().getExtras().get("gcid").toString();
        DatabaseReference fears = FirebaseDatabase.getInstance().getReference().child("StudentList")
                .child(key);
        fears.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot ds: dataSnapshot.getChildren()){
                        String name = stud.getText().toString();
                        Query query1 = FirebaseDatabase.getInstance().getReference().child("StudentList")
                                .child(key).orderByChild("fullname").equalTo(name);
                        query1.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot ds : dataSnapshot.getChildren()){
                                    String fuck = ds.getKey();
                                    String stats = ds.child("status").getValue(String.class);
                                    if ("Drop".equals(stats)){
                                        final AlertDialog.Builder builder = new AlertDialog.Builder(jpass.this);
                                        builder.setMessage("You are Drop from the Subject Please Approach your Teacher. If you have A " +
                                                "Valid Reason to join again.")
                                                .setIcon(R.drawable.flower)
                                                .setCancelable(false)
                                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        finish();
                                                    }
                                                });
                                        AlertDialog alertDialog = builder.create();
                                        alertDialog.show();
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });





                    }


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    private void Join() {

                final String passwo = joinpass.getText().toString();
        final String key= getIntent().getExtras().get("gcid").toString();
        Query query = FirebaseDatabase.getInstance().getReference().child("Groups")
                .orderByChild("groupass").equalTo(passwo);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                    {

                        final String wowo = dataSnapshot1.getKey();
                        final String teach = dataSnapshot1.child("uid").getValue(String.class);
                        if(dataSnapshot1.getChildrenCount() >0)
                        {
                String passwordreal = dataSnapshot1.child("groupass").getValue(String.class);
                if (passwo.equals(passwordreal)){



                                final DatabaseReference refers = FirebaseDatabase.getInstance().
                                        getReference("SmartDab");
                                refers.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        String absents = "0";
                                        String lates = "0";
                                        String type = "student";
                                        String stats;
                                        stats = "Normal";
                                        String stature = "Joined";
                                        String uid = firebaseAuth.getCurrentUser().getUid();
                                        final String wawa = stud.getText().toString().trim();
                                        String trick = "not Called";
                                        Map<String, Object> users = new HashMap<>();
                                        users.put("fullname",wawa);
                                        users.put("absents", absents);
                                        users.put("lates", lates);
                                        users.put("status",stats);
                                        users.put("type",type);
                                        users.put("uid",uid);
                                        users.put("trickart",trick);
                                        users.put("stature",stature);
                                        users.put("url",sting.getText().toString());
                                        mref.child("Groups").child(wowo).child("Member").child(uid).setValue(users);
                                        Toast.makeText(jpass.this, "You Join the Class List", Toast.LENGTH_SHORT).show();

                                        final String key= getIntent().getExtras().get("gcid").toString();

                                        liststud = FirebaseDatabase.getInstance().getReference().child("StudentList")
                                        .child(key);
                                        liststud.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.exists()){

                                                    for (DataSnapshot ds: dataSnapshot.getChildren()){

                                                        String name = ds.child("fullname").getValue(String.class);

                                                 Query query1 = FirebaseDatabase.getInstance().getReference().child("StudentList")
                                                         .child(key).orderByChild("fullname").equalTo(wawa);

                                                 query1.addListenerForSingleValueEvent(new ValueEventListener() {
                                                     @Override
                                                     public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                         for (DataSnapshot ds : dataSnapshot.getChildren()){
                                                             String fuck = ds.getKey();
                                                             String stats = ds.child("status").getValue(String.class);

                                                             DatabaseReference dearfred = FirebaseDatabase.getInstance().
                                                                     getReference().child("StudentList")
                                                                     .child(key).child(fuck);
                                                             dearfred.child("status").setValue("Entered");

                                                         }
                                                     }

                                                     @Override
                                                     public void onCancelled(@NonNull DatabaseError databaseError) {

                                                     }
                                                 });


                                                    }
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });

                                   prog.dismiss();
                                        finish();
                                        Intent gcroom = new Intent(jpass.this,checkyou.class);
                                        gcroom.putExtra("key",key);
                                        startActivity(gcroom);

                                    }
                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {
                                    }
                                });
                            }
                        }
                    }
                }else{
                    Toast.makeText(jpass.this, "Wrong Input Password Credentials", Toast.LENGTH_SHORT).show();
                }}
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

//    ValueEventListener valueEventListener = new ValueEventListener() {
//     @Override
//     public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//
//     }
//
//     @Override
//     public void onCancelled(@NonNull DatabaseError databaseError) {
//
//     }
// };




}
