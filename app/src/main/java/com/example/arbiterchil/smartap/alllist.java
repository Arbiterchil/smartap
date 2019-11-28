package com.example.arbiterchil.smartap;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class alllist extends AppCompatActivity {

    EditText id,fn,ln;
    Button save;
    DatabaseReference ref;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    ProgressDialog prog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alllist);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        id = findViewById(R.id.idput);
        fn = findViewById(R.id.fnput);
        ln = findViewById(R.id.lnput);
        save = findViewById(R.id.savethelist);
        ref = FirebaseDatabase.getInstance().getReference();
        DisplayMetrics dm =  new DisplayMetrics();
        prog = new ProgressDialog(this);
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int hiegth = dm.heightPixels;

        getWindow().setLayout((int)(width*.9),(int)(hiegth*.6));



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prog.setTitle("Please Wait.......");
                prog.setMessage("Saving Student In the List.");
                prog.setCanceledOnTouchOutside(false);
                prog.show();
                final String idnum = id.getText().toString();
                String first = fn.getText().toString();
                String last = ln.getText().toString();
                final String full = last+", "+first;
                if (TextUtils.isEmpty(idnum)){
                    id.requestFocus();
                    Toast.makeText(alllist.this, "Put Id Number.", Toast.LENGTH_SHORT).show();
                    prog.dismiss();
                }else if (TextUtils.isEmpty(first)){
                    fn.requestFocus();
                    Toast.makeText(alllist.this, "Put First Name.", Toast.LENGTH_SHORT).show();
                    prog.dismiss();
                }else if(TextUtils.isEmpty(last)){
                    ln.requestFocus();
                    prog.dismiss();
                    Toast.makeText(alllist.this, "Put last Name", Toast.LENGTH_SHORT).show();
                }else{

                    final String key = getIntent().getExtras().get("gcid").toString();
                    ref = FirebaseDatabase.getInstance().getReference().child("StudentList").child(key);
                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String uid = ref.push().getKey();
                            String trick = "not Entered";
                            String state = "offline";
                            Map<String, Object> liststudents = new HashMap<>();
                            liststudents.put("id",idnum);
                            liststudents.put("fullname",full);
                            liststudents.put("status",trick);
                            liststudents.put("arc","not here");
                            liststudents.put("state",state);
                            ref.child(uid).setValue(liststudents);
                            Toast.makeText(alllist.this, "Save Successfully.", Toast.LENGTH_SHORT).show();
                            id.setText("");
                            fn.setText("");
                            ln.setText("");
                            prog.dismiss();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
            }
        });



    }
}
