package com.example.arbiterchil.smartap;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class markabsent extends AppCompatActivity {

    ImageView absentbutton;
    CircleImageView image;
    TextView naming,statusing,mama;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_markabsent);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        naming = findViewById(R.id.getname);
        statusing  = findViewById(R.id.getstats);
        absentbutton = findViewById(R.id.makehimpay);
        image = findViewById(R.id.getimage);
        mama = findViewById(R.id.mama);

        naming.setText(getIntent().getStringExtra("iwant"));
        statusing.setText(getIntent().getStringExtra("tobreak"));
        Picasso.get().load(getIntent().getStringExtra("free")).into(image);

        waduchek();
        absentbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makehimabsent();
            }
        });


    }

    private void makehimabsent() {
        final String key = getIntent().getExtras().get("key").toString();
        final String champ = getIntent().getExtras().get("one").toString();
        DatabaseReference refuse = FirebaseDatabase.getInstance().getReference().child("Groups").child(key);
        refuse.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final String momo = dataSnapshot.child("Member").getKey();

                final DatabaseReference momoiyot = FirebaseDatabase.getInstance().getReference().child("Groups").child(key)
                        .child(momo).child(champ);
                momoiyot.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                            momoiyot.child("status").setValue("Normal");
                        Toast.makeText(markabsent.this, "The Student Status back to [NORMAL STATE].", Toast.LENGTH_SHORT).show();
                        finish();

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

        wannawaste();

    }

    private void wannawaste() {

        final String key = getIntent().getExtras().get("key").toString();
        DatabaseReference liststud = FirebaseDatabase.getInstance().getReference().child("StudentList")
                .child(key);
        liststud.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds:dataSnapshot.getChildren()){

                    String arcwarden = ds.child("arc").getValue(String.class);
                    String oor = ds.getKey();
                    if ("here".equals(arcwarden)){

                        DatabaseReference dearfred = FirebaseDatabase.getInstance().
                                getReference().child("StudentList")
                                .child(key).child(oor);
                        dearfred.child("arc").setValue("not here");

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void waduchek() {

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        final String RegisteredUserID = currentUser.getUid();

        final Bundle b = new Bundle();
        b.putString("userId", RegisteredUserID);

        DatabaseReference jLoginDatabase = FirebaseDatabase.getInstance().getReference()
                .child("User").child(firebaseAuth.getCurrentUser().getUid());

        jLoginDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String userType = dataSnapshot.child("status").getValue(String.class);
               if ("Student".equals(userType)){

                   absentbutton.setVisibility(View.INVISIBLE);
                   mama.setVisibility(View.INVISIBLE);

               }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
