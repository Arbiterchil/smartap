package com.example.arbiterchil.smartap;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class StudeAtten extends AppCompatActivity {
    CountDownTimer countErchil;
    CircleImageView taplod;
    Button checkme;
    DatabaseReference mDatabase;
    FirebaseDatabase refpri;
    private DatabaseReference refl;
    FirebaseAuth firebaseAuth;
    TextView gcname,passgen,idgroup,mems,Timewise;
    FirebaseDatabase firebaseDatabase;
    private  String rec;
    Button sendreq,delete,leave,Attendance;
    FirebaseListAdapter listAdapter;
    ListView listv;
    FirebaseUser user;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> arrayList =  new ArrayList<>();
    TextView underthepressur,tename;
    private boolean Ascending = true;
    RecyclerView recyclerView;
    List<memberlist> list;
    FirebaseUser curentUser;
    FirebaseAuth mauth;
    Button starting,savings,backing,backus;
    FloatingActionButton fab_plus,fab_join,fab_recrd,fab_delete,fab_leave,fab_list;
    Animation Fopen,Fclose,Fclock,Fanticlock;
    CircleImageView seats;
    boolean isOp = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stude_atten);
        taplod = findViewById(R.id.tap);
        fab_plus = findViewById(R.id.fab_plus);
        fab_join = findViewById(R.id.fab_join);
        fab_recrd = findViewById(R.id.fab_list);
        fab_leave = findViewById(R.id.fab_leave);
        Fopen = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_open);
        Fclose =  AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        Fclock = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_clockwise);
        Fanticlock =  AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_anticlockwise);
        refpri = FirebaseDatabase.getInstance();
        gcname= findViewById(R.id.gcname);
        idgroup = findViewById(R.id.idgroup);
        mauth = FirebaseAuth.getInstance();
        curentUser = mauth.getCurrentUser();
        taplod = findViewById(R.id.tap);
        tename = findViewById(R.id.studentname);

        gcname.setText(getIntent().getStringExtra("groupname"));
        idgroup.setText(getIntent().getStringExtra("gcid"));

        seats = findViewById(R.id.seatplan);
        mDatabase = FirebaseDatabase.getInstance().
                getReference().child("User").
                child(mauth.getCurrentUser().getUid());

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String keyran = dataSnapshot.getKey();

                try {
                    smartdab smartdab = dataSnapshot.getValue(smartdab.class);
                    tename.setText(smartdab.getFullname());
                }catch(Exception e){
                    Log.d("dfrag", e.getMessage());
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        mDatabase = FirebaseDatabase.getInstance().getReference();

        fab_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOp){
                    fab_plus.startAnimation(Fanticlock);
                    fab_join.startAnimation(Fclose);
                    fab_recrd.startAnimation(Fclose);
                    fab_leave.startAnimation(Fclose);
                    fab_leave.setClickable(false);
                    fab_join.setClickable(false);
                    fab_recrd.setClickable(false);
                    isOp = false;
                }else{
                    fab_plus.startAnimation(Fclock);
                    fab_join.startAnimation(Fopen);
                    fab_recrd.startAnimation(Fopen);
                    fab_leave.startAnimation(Fopen);
                    fab_leave.setClickable(true);
                    fab_join.setClickable(true);
                    fab_recrd.setClickable(true);
                    isOp = true;

                }
            }
        });


        taplod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JoinMember();
                imhere();
                seatplanpre();
            }
        });
        Conception();

        seats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String key = getIntent().getExtras().get("gcid").toString();
                Intent hehe = new Intent(StudeAtten.this,seatplanauto.class);
                hehe.putExtra("gcid",key);
                startActivity(hehe);
            }
        });

        fab_leave.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
          Leave();

        }
        });
        fab_recrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String key = getIntent().getExtras().get("gcid").toString();
                Intent hehe = new Intent(StudeAtten.this,checkyou.class);
                hehe.putExtra("key",key);
                startActivity(hehe);
            }
        });
        fab_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String key= getIntent().getExtras().get("gcid").toString();
                Intent gcroom = new Intent(StudeAtten.this,jpass.class);
                gcroom.putExtra("gcid",key);
                startActivity(gcroom);
            }
        });

        taplod.setVisibility(View.GONE);
        waduchek();


    cantpress();
    Joinem();

    }



    private void seatplanpre() {
        final String namingme = tename.getText().toString();
        final String key = getIntent().getExtras().get("gcid").toString();

       DatabaseReference liststud = FirebaseDatabase.getInstance().getReference().child("StudentList")
                .child(key);
        liststud.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot ds: dataSnapshot.getChildren()){
                        String name = ds.child("fullname").getValue(String.class);

                        Query query1 = FirebaseDatabase.getInstance().getReference().child("StudentList")
                                .child(key).orderByChild("fullname").equalTo(namingme);

                        query1.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot ds : dataSnapshot.getChildren()){
                                    String fuck = ds.getKey();
                                    String stats = ds.child("status").getValue(String.class);

                                    DatabaseReference dearfred = FirebaseDatabase.getInstance().
                                            getReference().child("StudentList")
                                            .child(key).child(fuck);
                                    dearfred.child("arc").setValue("here");

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
    private void imhere() {
        final String teach = getIntent().getExtras().get("ted").toString();

        final DatabaseReference references = FirebaseDatabase.getInstance()
                .getReference()
                .child("StunAttend");
        references.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String wew = tename.getText().toString();
                String teah = "im here Sir/Mam";
                Map<String, Object> hyaki = new HashMap<>();
                hyaki.put("name", wew);
                hyaki.put("note", teah);
                hyaki.put("uid",mauth.getCurrentUser().getUid());
                references.child(teach).child(references.push().getKey()).setValue(hyaki);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    private void Joinem() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        final String RegisteredUserID = currentUser.getUid();
        final String key = getIntent().getExtras().get("gcid").toString();
        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Groups").child(key);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final String data = dataSnapshot.child("Member").getKey();
                ref.child(data).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {

                            Query q = FirebaseDatabase.getInstance().getReference().child("Groups").child(key).child(data);
                            q.orderByChild("uid").equalTo(RegisteredUserID).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()) {

                                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                            String uid = ds.child("uid").getValue(String.class);
                                            if (RegisteredUserID.equals(uid)) {
                                                Toast.makeText(StudeAtten.this, "You Already joined the Class", Toast.LENGTH_SHORT).show();
                                                fab_leave.setEnabled(true);
                                                fab_join.setEnabled(false);
                                            } else {
                                                fab_join.setEnabled(true);
                                                Toast.makeText(StudeAtten.this, "Click Join Buuton in the Rigth Side", Toast.LENGTH_SHORT).show();
                                            }


                                        }


                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void cantpress() {

        fab_leave.setEnabled(false);

    }
    private void StrikeZone() {
        final String key = getIntent().getExtras().get("gcid").toString();
        final DatabaseReference Lates = FirebaseDatabase.getInstance().getReference()
                .child("GroupMember").child(key);
        Lates.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String datamen = dataSnapshot.child("Member").getKey();
                final DatabaseReference reybo = FirebaseDatabase.getInstance().getReference()
                        .child("Groups").child(key).child(datamen);

                reybo.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot no : dataSnapshot.getChildren()){

                            String aids = no.getKey();
                            String stam = no.child("type").getValue(String.class);

                            switch (stam){
                                case "student":
                                    String wew = "Ready";
                                    reybo.child(aids).child("type").setValue(wew);
                                    break;


                            } }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }
    private void Leave() {
        final String wawa = tename.getText().toString();
        final String key = getIntent().getExtras().get("gcid").toString();
        AlertDialog.Builder dial = new AlertDialog.Builder(StudeAtten.this);
        dial.setTitle("Leave Room.");
        dial.setIcon(R.drawable.flower);
        dial.setMessage("Are You Sure want to Leave?");
        dial.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                final String RegisteredUserID = currentUser.getUid();
                DatabaseReference leaveme = FirebaseDatabase.getInstance().getReference()
                        .child("Groups").child(key);

                leaveme.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String datamem = dataSnapshot.child("Member").getKey();

                        dataSnapshot.child(datamem).child(RegisteredUserID).getRef().removeValue();
                        Toast.makeText(StudeAtten.this, "You Leave The Subject", Toast.LENGTH_SHORT).show();
                        finish();

                        DatabaseReference liststud = FirebaseDatabase.getInstance().getReference().child("StudentList")
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
                                                    DatabaseReference dearfred = FirebaseDatabase.getInstance().getReference().child("StudentList")
                                                            .child(key).child(fuck);
                                                    dearfred.child("status").setValue("not Entered");

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

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });




            }
        })
                .setNegativeButton("No",
                        new DialogInterface.OnClickListener()
                        {@Override public void onClick(DialogInterface dialog, int which){dialog.dismiss();}
                        });dial.show();

    }
    private void Conception() {
        final String key = getIntent().getExtras().get("gcid").toString();

        final DatabaseReference vive = FirebaseDatabase.getInstance().getReference()
                .child("Groups");

        vive.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String data = dataSnapshot.child("Member").getKey();

                DatabaseReference mob = FirebaseDatabase.getInstance().getReference()
                        .child("Groups").child(key).child(data);
                mob.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot ds: dataSnapshot.getChildren()){
                            String status = ds.child("type").getValue(String.class);

                            switch (status){
                                case "student":
                                    taplod.setVisibility(View.INVISIBLE);
                                    Toast.makeText(StudeAtten.this,
                                            "Tap Button Will not Show if the Teacher did not Start the Activity yet.",
                                            Toast.LENGTH_SHORT).show();
                                    break;
                                case "Ready":
                                    taplod.setVisibility(View.VISIBLE);
                                    break;
                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    private void JoinMember() {
        taplod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                final String RegisteredUserID = currentUser.getUid();
                final Bundle b = new Bundle();
                b.putString("userId", RegisteredUserID);
                final String key = getIntent().getExtras().get("gcid").toString();
                final DatabaseReference joinmem = FirebaseDatabase.getInstance().getReference()
                        .child("Groups").child(key);
                joinmem.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String datamem = dataSnapshot.child("Member").getKey();

                        final DatabaseReference fer = FirebaseDatabase.getInstance().getReference()
                                .child("Groups").child(key).child(datamem).child(RegisteredUserID);

                        fer.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                String stats = dataSnapshot.child("status").getValue(String.class);

                                switch (stats) {

                                    case "Normal":

                                        String Present = "Present";
                                        fer.child("status").setValue(Present);

                                        break;
                                    case "Late or Absent":

                                        String Lte = "Late";
                                        fer.child("status").setValue(Lte);

                                        break;
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });


                        taplod.setVisibility(View.GONE);
//                        backing.setVisibility(View.GONE);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);


        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){


        }else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){

        }

    }
        private void waduchek() {

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        final String RegisteredUserID = currentUser.getUid();

        final Bundle b = new Bundle();
        b.putString("userId", RegisteredUserID);

        DatabaseReference jLoginDatabase = FirebaseDatabase.getInstance().getReference().child("User").child(RegisteredUserID);

        jLoginDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String userType = dataSnapshot.child("status").getValue().toString();
                switch (userType) {
                    case "Teacher":



                            break;
                    case "Student":
                        taplod.setVisibility(View.GONE);

//                        backus.setVisibility(View.GONE);



                        break;


                    default:
                        Toast.makeText(StudeAtten.this, "Can't Identify User", Toast.LENGTH_SHORT).show();
                        return;
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
