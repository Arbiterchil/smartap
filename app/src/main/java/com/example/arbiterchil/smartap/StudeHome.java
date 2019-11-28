package com.example.arbiterchil.smartap;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class StudeHome extends AppCompatActivity {
    CircleImageView setimage;
    TextView fullnames,stats,emails,udis,idnum;
    ImageView reseta,update;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser firebaseUser;
    DatabaseReference mDatabase;
    Button proceed,outmena;
    TextView fullshit;
    private FirebaseUser curredntid;
    private  String currentMe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stude_home);


        fullshit= findViewById(R.id.nameexist);
        proceed = findViewById(R.id.proc);
        outmena = findViewById(R.id.logoutshit);
        firebaseAuth = FirebaseAuth.getInstance();
        curredntid = firebaseAuth.getCurrentUser();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();

        mDatabase = FirebaseDatabase.getInstance().
                getReference().child("User").
                child(firebaseAuth.getCurrentUser().getUid());

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    smartdab smartdab = dataSnapshot.getValue(smartdab.class);
                    fullshit.setText(smartdab.getFullname());



                }catch(Exception e){
                    Log.d("dfrag", e.getMessage());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudeHome.this,homestud.class));
                finish();
            }
        });

        outmena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Offline();
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(),login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });



    }

    private void Offline() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        final String RegisteredUserID = currentUser.getUid();

        final DatabaseReference muscle = FirebaseDatabase.getInstance().getReference().child("User")
                .child(RegisteredUserID);
        muscle.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                muscle.child("state").setValue("offline");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void UpdateStatus(String state) {


        HashMap<String,Object> online = new HashMap<>();
        online.put("state",state);

        currentMe = firebaseAuth.getCurrentUser().getUid();
        mDatabase.updateChildren(online);



    }

    @Override
    protected void onStart() {
        super.onStart();
        UpdateStatus("online");
    }
}
