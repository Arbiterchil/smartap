package com.example.arbiterchil.smartap;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.euicc.DownloadableSubscription;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.florent37.materialtextfield.MaterialTextField;
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
import com.google.firebase.iid.FirebaseInstanceId;


import java.util.HashMap;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class login extends AppCompatActivity {
        Button l;
        Button reg;
        FirebaseAuth firebaseAuth;
        EditText us;
        EditText pw;
        TextView forget;
        ProgressDialog prog;

        private CheckBox rem;
        private SharedPreferences mprefs;
        private static  final String PREFS_NAME = "PrefsFile";
        ProgressBar proglog;
        DatabaseReference fearToken;
        private SharedPreferences mpreferences;
        private SharedPreferences.Editor editor;
private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;
    String curredntid;
    DatabaseReference strange;
    FirebaseUser firebaseUser;
    String cuurentid;
        @Override
        protected void onCreate (Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            prog = new ProgressDialog(this);
            firebaseAuth = FirebaseAuth.getInstance();
            firebaseUser = firebaseAuth.getCurrentUser();
//            tabLayout= findViewById(R.id.atabs);
//            viewPager = findViewById(R.id.viewpage);
//
//            getWindow().setBackgroundDrawableResource(R.drawable.calming);
//
//            viewpage viewpage= new viewpage(getSupportFragmentManager());
//            viewpage.AddFrag(new logind(),"SIGN IN");
//            viewpage.AddFrag(new logins(),"SIGN UP");
//            viewPager.setAdapter(viewpage);
//            tabLayout.setupWithViewPager(viewPager);


            fearToken = FirebaseDatabase.getInstance().getReference().child("User");




        mpreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mpreferences = getSharedPreferences("com.example.arbiterchil.smartap",MODE_PRIVATE);
        editor = mpreferences.edit();


         mprefs  = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        us =  findViewById(R.id.us);
        pw = findViewById(R.id.pw);
        rem =  findViewById(R.id.rem);
        l = findViewById(R.id.l);
        reg = findViewById(R.id.reg);


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { Intent intent = new Intent(login.this, Register.class);
                startActivity(intent);}});



        forget = findViewById(R.id.forget);
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, forget.class);
                startActivity(intent);
            }
        });

        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = us.getText().toString();
                String atay = "@gmail.com";
                final String email =username+atay ;
                String password = pw.getText().toString().replace(" ","");


                remembermefucker();

                prog.setTitle("Please Wait.......");
                prog.setMessage("Signing In Account.");
                prog.setCanceledOnTouchOutside(false);
                prog.show();

                l.setVisibility(View.INVISIBLE);
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(login.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    prog.dismiss();
                    us.requestFocus();
                    l.setVisibility(View.VISIBLE);
                return;
                }
                if(TextUtils.isEmpty(password)){

                    Toast.makeText(login.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    prog.dismiss();
                    pw.requestFocus();
                   l.setVisibility(View.VISIBLE);
                return;
                }

                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                                    String RegisteredUserID = currentUser.getUid();
                                    final Bundle b = new Bundle();
                                    b.putString("userId", RegisteredUserID);

                                    DatabaseReference jLoginDatabase = FirebaseDatabase.getInstance().getReference()
                                            .child("User").child(RegisteredUserID);
                                    jLoginDatabase.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            String userType = dataSnapshot.child("status").getValue(String.class);
                                            assert userType != null;
                                            switch (userType) {
                                                case "Teacher":


                                                    String userOn = firebaseAuth.getCurrentUser().getUid();
                                                    String Token = FirebaseInstanceId.getInstance().getToken();
                                                    fearToken.child(userOn).child("device_token").setValue(Token);


                                                    fearToken.addListenerForSingleValueEvent(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                            Toast.makeText(login.this, "Successfully Log In as Teacher", Toast.LENGTH_SHORT).show();
                                                            startActivity(new Intent(login.this, home.class).putExtras(b));
                                                            prog.dismiss();
                                                            finish();

                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                                        }
                                                    });


                                                    break;
                                                case "Student":

                                                    String Tokens = FirebaseInstanceId.getInstance().getToken();
                                                    fearToken.child(firebaseAuth.getCurrentUser().getUid())
                                                            .child("device_token").setValue(Tokens);

                                                    fearToken.addListenerForSingleValueEvent(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                            Toast.makeText(login.this, "Successfully Log In as Student", Toast.LENGTH_SHORT).show();
                                                            Intent intent = new Intent(login.this, homestud.class);
                                                            startActivity(intent);
                                                            prog.dismiss();
                                                            finish();
                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                                        }
                                                    });


                                                    break;
                                                default:
                                                    Toast.makeText(login.this, "Failed Login. Please Try Again", Toast.LENGTH_SHORT).show();

                                                    break;

                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                } else {
                                    Toast.makeText(login.this, "SIGNING IN FAILED", Toast.LENGTH_SHORT).show();
//                                    us.setText("");
//                                    pw.setText("");
                                    prog.dismiss();
                                    l.setVisibility(View.VISIBLE);

                                }

                            }
                        });





            }

        });

        sharedawohh();
//getPreferencesData();
        }

//    private void setLogOnline() {
//        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
//        String RegisteredUserID = currentUser.getUid();
//        DatabaseReference itsuka = FirebaseDatabase.getInstance().getReference().child(RegisteredUserID);
//        itsuka.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot ds:dataSnapshot.getChildren()){
//
//                    final String name = ds.child("fullname").getValue(String.class);
//
//
//                    DatabaseReference fenris = FirebaseDatabase.getInstance().getReference().child("StudentList");
//
//                    fenris.addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                            final String keys = dataSnapshot.getKey();
//
//                            Query fingral = FirebaseDatabase.getInstance().getReference().child("StudentList")
//                                    .child(keys).orderByChild("fullname").equalTo(name);
//
//                            fingral.addListenerForSingleValueEvent(new ValueEventListener() {
//                                @Override
//                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().
//                                            child("StudentList").child(keys);
//                                            databaseReference.child("state").setValue("online");
//
//
//                                }
//
//                                @Override
//                                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                                }
//                            });
//
//
//
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                        }
//                    });
//
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//
//
//        }


    private void UpdateStatus(String state) {

        strange = FirebaseDatabase.getInstance().getReference().child("User").child(firebaseAuth.getCurrentUser()
                .getUid());
        HashMap<String,Object> online = new HashMap<>();
        online.put("state",state);

        curredntid = firebaseAuth.getCurrentUser().getUid();
        strange.updateChildren(online);



    }

    private void remembermefucker() {

        if (rem.isChecked()){
            editor.putString(getString(R.string.checkbox),"True");
            editor.commit();

            String username = us.getText().toString();
            editor.putString(getString(R.string.user),username);
            editor.commit();
            String passwords = pw.getText().toString();
            editor.putString(getString(R.string.pass),passwords);
            editor.commit();
        }else{
            editor.putString(getString(R.string.checkbox),"False");
            editor.commit();

            String username = us.getText().toString();
            editor.putString(getString(R.string.user),"");
            editor.commit();
            String passwords = pw.getText().toString();
            editor.putString(getString(R.string.pass),"");
            editor.commit();
        }


        }
    private void sharedawohh() {

            String checkbox = mpreferences.getString(getString(R.string.checkbox),"False");
            String user = mpreferences.getString(getString(R.string.user),"");
            String pass = mpreferences.getString(getString(R.string.pass),"");



            us.setText(user);
            pw.setText(pass);


            if (checkbox.equals("True")){
                    rem.setChecked(true);
            }else{
                rem.setChecked(false);
            }

        }
    private void Loading(){


        final Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();

                for (int p = 0 ; p < 100; p++){
                    proglog.setProgress(p);
                    try {
                        sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        };
        thread.start();
    }
    private void getPreferencesData() {

        if(rem.isChecked()){
            Boolean boolisCheck  = rem.isChecked();
            SharedPreferences.Editor editor =mprefs.edit();
            editor.putString("pref_name", us.getText().toString());
            editor.putString("pref_pass", pw.getText().toString());
            editor.putBoolean("pref_check", boolisCheck);
            editor.apply();
            Toast.makeText(login.this, "This is remember", Toast.LENGTH_SHORT).show();
        }else{
            mprefs.edit().clear().apply();
        }
        us.getText().clear();
        pw.getText().clear();

        SharedPreferences sp = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        if (sp.contains("pref_name")) {
            String u = sp.getString("pref_name", "");
            us.setText(u);
        }
        if (sp.contains("pref_pass")) {
            String p = sp.getString("pref_pass", "");
            pw.setText(p);
        }
        if (sp.contains("pref_check")) {
            Boolean b = sp.getBoolean("pref_check", false);
            rem.setChecked(b);
        }

    }

}


