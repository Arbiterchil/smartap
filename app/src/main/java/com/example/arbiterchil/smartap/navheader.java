package com.example.arbiterchil.smartap;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class navheader extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    private DatabaseReference mDatabase;
    TextView profs,emailsp;
    FirebaseUser user;
    String currentuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_header);

        profs=findViewById(R.id.prof);
        emailsp = findViewById(R.id.emails);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        final String RegisteredUserID = currentUser.getUid();
        user = FirebaseAuth.getInstance().getCurrentUser();
        currentuser = firebaseAuth.getCurrentUser().getUid();

    }
}