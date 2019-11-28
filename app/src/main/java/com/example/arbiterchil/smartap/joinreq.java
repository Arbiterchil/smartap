package com.example.arbiterchil.smartap;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class joinreq extends AppCompatActivity {

    private String gc;

    TextView gcido,gcname,total,remark,fullname;
    CircleImageView circleImageView;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference reference;
    FirebaseDatabase firebaseDatabase;
    Button surpriseBay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joinreq);
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        gcido= findViewById(R.id.gcid);
        gcname = findViewById(R.id.mercury);
        total = findViewById(R.id.total);
        remark = findViewById(R.id.remark);
        fullname = findViewById(R.id.fulls);
        surpriseBay = findViewById(R.id.surprise);

        final String key = getIntent().getExtras().get("gcid").toString();
        circleImageView  = findViewById(R.id.profile);
        gcname.setText(getIntent().getExtras().get("groupname").toString());
        gcido.setText(getIntent().getExtras().get("gcid").toString());
        Toast.makeText(this, "You the Number Room "+key, Toast.LENGTH_SHORT).show();



        surpriseBay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showtheGrade();


            }
        });




    }

    private void showtheGrade() {
        String names = fullname.getText().toString();
        final String key = getIntent().getExtras().get("gcid").toString();
        Query feargrade= FirebaseDatabase.getInstance().getReference()
                .child("PostRecord").child(key).orderByChild("fullname").equalTo(names);
        feargrade.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){

                    String totalis= ds.child("total").getValue(String.class);
                    String remarkis = ds.child("remark").getValue(String.class);

                    total.setText(totalis);
                    remark.setText(remarkis);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        final String RegisteredUserID = currentUser.getUid();
        reference = FirebaseDatabase.getInstance()
                .getReference()
                .child("User")
                .child(RegisteredUserID);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String urls = dataSnapshot.child("url").getValue(String.class);
                String name = dataSnapshot.child("fullname").getValue(String.class);
                fullname.setText(name);
                Picasso.get().load(urls).into(circleImageView);
                Toast.makeText(joinreq.this, name, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
