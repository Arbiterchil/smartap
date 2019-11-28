package com.example.arbiterchil.smartap;


import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;


/**
 * A simple {@link Fragment} subclass.
 */
public class logins extends Fragment {

    View ew;
    CardView cca;
    EditText emailed;
    EditText passwords;
    EditText pen;
    EditText flname;
    EditText idnumer,lastname;
    Button logs;
    ImageView userimage;
    DatabaseReference reference;
    FirebaseAuth firebaseAuth;
    RadioGroup rge;
    RadioButton Teacher, Student;
    FirebaseUser user;
    String uid;
    static int Preqcode = 1;
    static int REQUESCODE = 1;
    Uri pickedImage;
    private FirebaseStorage mStorage;
    ProgressBar progslog;
    FirebaseUser curus;
    public logins() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ew = inflater.inflate(R.layout.fragment_logins, container, false);

        cca= ew.findViewById(R.id.linen);
        flname = ew.findViewById(R.id.flname);
        idnumer = ew.findViewById(R.id.idnumer);
        pen = ew.findViewById(R.id.pen);
        emailed = ew.findViewById(R.id.emailed);
        passwords = ew.findViewById(R.id.passwords);
        rge = ew.findViewById(R.id.rge);
        firebaseAuth = FirebaseAuth.getInstance();
        logs = ew.findViewById(R.id.logs);
        progslog = ew.findViewById(R.id.progslog);
        lastname = ew.findViewById(R.id.lname);
        progslog.setVisibility(View.INVISIBLE);
        Teacher =ew.findViewById(R.id.teacher);
        Student = ew.findViewById(R.id.student);


        logs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progslog.setVisibility(View.VISIBLE);
                cca.setVisibility(View.INVISIBLE);
                logs.setVisibility(View.INVISIBLE);
                final String conpass = pen.getText().toString();
                final String email = emailed.getText().toString().trim();
                final String password = passwords.getText().toString().trim();
                final String fn = flname.getText().toString().trim();
                final String ln = lastname.getText().toString().trim();
                final String fullname = lastname.getText().toString().trim()+", "+flname.getText().toString().trim();
                final  String idnumber = idnumer.getText().toString().trim();
                final int selectedId = rge.getCheckedRadioButtonId();
                final RadioButton radioButton = ew.findViewById(selectedId);
                if(selectedId  == -1) {
                    Toast.makeText(getActivity(), "Please Choose Status", Toast.LENGTH_SHORT).show();
                    rge.requestFocus();
                    progslog.setVisibility(View.INVISIBLE);
                    cca.setVisibility(View.VISIBLE);
                    logs.setVisibility(View.VISIBLE);
                }else if(fn.isEmpty()){
                    flname.requestFocus();
                    Toast.makeText(getActivity(), "Please put First Name", Toast.LENGTH_SHORT).show();
                    progslog.setVisibility(View.INVISIBLE);
                    cca.setVisibility(View.VISIBLE);
                    logs.setVisibility(View.VISIBLE);
                }else if(ln.isEmpty()){
                    lastname.requestFocus();
                    Toast.makeText(getActivity(), "Put Last Name", Toast.LENGTH_SHORT).show();
                    progslog.setVisibility(View.INVISIBLE);
                    cca.setVisibility(View.VISIBLE);
                    logs.setVisibility(View.VISIBLE);
                }else if(!password.equals(conpass)){
                    pen.requestFocus();
                    Toast.makeText(getActivity(), "Confirm Password Error ", Toast.LENGTH_SHORT).show();
                    progslog.setVisibility(View.INVISIBLE);
                    cca.setVisibility(View.VISIBLE);
                    logs.setVisibility(View.VISIBLE);
                }else if(email.isEmpty()){
                    emailed.requestFocus();
                    Toast.makeText(getActivity(), "Gmail or Email Required", Toast.LENGTH_SHORT).show();
                    progslog.setVisibility(View.INVISIBLE);
                    cca.setVisibility(View.VISIBLE);
                    logs.setVisibility(View.VISIBLE);
                }else if( password.isEmpty()){
                    passwords.requestFocus();
                    Toast.makeText(getActivity(), "Password Required", Toast.LENGTH_SHORT).show();
                    progslog.setVisibility(View.INVISIBLE);
                    cca.setVisibility(View.VISIBLE);
                    logs.setVisibility(View.VISIBLE);
                }else if( idnumber.isEmpty()){
                    idnumer.requestFocus();
                    Toast.makeText(getActivity(), "Please Put an ID", Toast.LENGTH_SHORT).show();
                    progslog.setVisibility(View.INVISIBLE);
                    cca.setVisibility(View.VISIBLE);
                    logs.setVisibility(View.VISIBLE);
                }else {

                    final String status = radioButton.getText().toString().trim();
                    firebaseAuth.createUserWithEmailAndPassword
                            (email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                smartdab smartasslove = new smartdab(email, password, fullname, idnumber, status,null);
                                FirebaseDatabase.getInstance().getReference("SmartDab").child("User").
                                        child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(smartasslove).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){


                                            Toast.makeText(getActivity(), "Save Successfully", Toast.LENGTH_SHORT).show();
                                            progslog.setVisibility(View.INVISIBLE);
                                            cca.setVisibility(View.VISIBLE);
                                            logs.setVisibility(View.VISIBLE);
                                            pen.setText("");
                                            emailed.setText("");
                                            passwords.setText("");
                                            flname.setText("");
                                            lastname.setText("");
                                            lastname.setText("");
                                            flname.setText("");
                                            idnumer.setText("");
                                        } else {
                                            Toast.makeText(getActivity(), "Please try again!.", Toast.LENGTH_SHORT).show();
                                            logs.setVisibility(View.VISIBLE);
                                        }
                                    }

                                });

                            } else {

                                Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                                logs.setVisibility(View.VISIBLE);
//                                loadProg.setVisibility(View.INVISIBLE);
                            }
                        }
                    });

                }





            }
        });


        Nani();


        return ew;
    }

    private void Nani() {


        idnumer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                final String idnums = idnumer.getText().toString();
//                Query query =  FirebaseDatabase.getInstance().getReference("SmartDab").child("User").
//                        orderByChild("idnumber").equalTo(idnums);
//                query.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                        Toast.makeText(getActivity(), "Already Exists ID Number", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {





            }

            @Override
            public void afterTextChanged(Editable s) {
                final String idnums = idnumer.getText().toString();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("SmartDab").child("User");

                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){

                           for (DataSnapshot ds : dataSnapshot.getChildren()){

                               String same = ds.child("idnumber").getValue(String.class);

                               if (same.equals(idnums)){
                                   Toast.makeText(getActivity(), "ID Number Already Exists", Toast.LENGTH_SHORT).show();
                                   idnumer.setText("");

                               }


                           }


                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });






            }
        });



    }


    @Override
    public void onStart() {
        super.onStart();
        if(firebaseAuth.getCurrentUser() != null){

        }
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){


        }else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){

        }
    }
}
