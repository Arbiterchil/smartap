package com.example.arbiterchil.smartap;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class giftclass extends AppCompatActivity {

    TextView groupid,groupnames,groupcreator,groupuid,selectedCreate;
    EditText seagive;
    FirebaseAuth fireball;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference feru;
    private DatabaseReference fenrir;
    Button giftgroup;
    RecyclerView recyclerView;
    Spinner spin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_giftclass);
        selectedCreate = findViewById(R.id.uidcreta);
        groupid = findViewById(R.id.gid);
        groupnames = findViewById(R.id.gnamedaw);
        groupcreator = findViewById(R.id.groupcreatorId);
        groupuid = findViewById(R.id.gpassdaw);
        giftgroup = findViewById(R.id.giftHappy);
        spin = findViewById(R.id.spinthatshit);
        selectedCreate.setVisibility(View.GONE);
        fireball = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        feru = firebaseDatabase.getReference().child("User");

        final String key= getIntent().getExtras().get("gcid").toString();
        Toast.makeText(this, "ID"+key, Toast.LENGTH_SHORT).show();

        GroupData();
        SpinTheShit();

        giftgroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HappyGift();
                finish();
            }
        });

    }

    private void HappyGift() {
        final String key= getIntent().getExtras().get("gcid").toString();
        Toast.makeText(this, "ID"+key, Toast.LENGTH_SHORT).show();
        fenrir = FirebaseDatabase.getInstance().getReference().child("Groups").child(key);

        fenrir.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String GetUid = selectedCreate.getText().toString();
                fenrir.child("uid").setValue(GetUid);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    private void SpinTheShit() {

       DatabaseReference databaseReference =
               FirebaseDatabase.getInstance().getReference().child("User");

       databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               List<String > are = new ArrayList<>();

            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                String userType = ds.child("status").getValue().toString();
                switch (userType) {
                    case "Teacher":

                        String Name = ds.child("fullname").getValue().toString();
                        are.add(Name);
                        Collections.sort(are);
                        break;

                }
            }
               ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>
                       (giftclass.this, android.R.layout.simple_spinner_item,
                       are);
               areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
               spin.setAdapter(areasAdapter);
               spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                   @Override
                   public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                       String selectedItem = spin.getSelectedItem().toString();

                       Toast.makeText(giftclass.this, "You Select Prof: "+selectedItem, Toast.LENGTH_SHORT).show();

                    Query query = FirebaseDatabase.getInstance().getReference().child("User")
                            .orderByChild("fullname").equalTo(selectedItem);

                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                         for (DataSnapshot  sd : dataSnapshot.getChildren()){
                             String uids = sd.getKey();

                             selectedCreate.setText(uids);

                         }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                   }

                   @Override
                   public void onNothingSelected(AdapterView<?> parent) {

                   }
               });
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });
    }
    private void GroupData() {
        final String key= getIntent().getExtras().get("gcid").toString();
        Toast.makeText(this, "ID"+key, Toast.LENGTH_SHORT).show();
        fenrir = FirebaseDatabase.getInstance().getReference().child("Groups").child(key);
        fenrir.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String gpn = dataSnapshot.child("groupname").getValue(String.class);
                    String gcid = dataSnapshot.child("gcid").getValue(String.class);
                    String gcreator = dataSnapshot.child("uid").getValue(String.class);
                    String gcpass = dataSnapshot.child("groupass").getValue(String.class);
                    groupid.setText(gcreator);
                    groupnames.setText(gpn);
                    groupcreator.setText(gcid);
                    groupuid.setText(gcpass);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


}
