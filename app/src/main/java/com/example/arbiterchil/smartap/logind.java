package com.example.arbiterchil.smartap;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
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
import com.google.firebase.iid.FirebaseInstanceId;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class logind extends Fragment {
    CircleImageView l;
    Button reg;
    FirebaseAuth firebaseAuth;
    EditText us;
    EditText pw;
    TextView forget;

    private CheckBox rem;
    private SharedPreferences mprefs;
    private static final String PREFS_NAME = "PrefsFile";
    ProgressBar proglog;

    private SharedPreferences mpreferences;
    private SharedPreferences.Editor editor;
    View vio;
    CardView lin;
    ImageView im;

    public logind() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vio = inflater.inflate(R.layout.fragment_logind, container, false);
        lin = vio.findViewById(R.id.linen);
        im = vio.findViewById(R.id.ima);
        mpreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
//        mpreferences = getSharedPreferences("com.example.arbiterchil.smartap",MODE_PRIVATE);
        editor = mpreferences.edit();

        firebaseAuth = FirebaseAuth.getInstance();
        proglog = vio.findViewById(R.id.proglog);
        us = vio.findViewById(R.id.us);
        pw = vio.findViewById(R.id.pw);
        rem = vio.findViewById(R.id.rem);
        l = vio.findViewById(R.id.l);
        proglog.setVisibility(View.INVISIBLE);
        im.setVisibility(View.INVISIBLE);
        forget = vio.findViewById(R.id.forget);
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), forget.class);
                startActivity(intent);
            }
        });

        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = us.getText().toString();
                String atay = "@gmail.com";
                final String email =username+atay ;
                final String password = pw.getText().toString();
                remembermefucker();
                proglog.setVisibility(View.VISIBLE);
                l.setVisibility(View.INVISIBLE);
                im.setVisibility(View.VISIBLE);
                lin.setVisibility(View.INVISIBLE);
                forget.setVisibility(View.INVISIBLE);
                rem.setVisibility(View.INVISIBLE);

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getActivity(), "Please Enter Email", Toast.LENGTH_SHORT).show();

                    us.requestFocus();
                    proglog.setVisibility(View.INVISIBLE);
                    im.setVisibility(View.INVISIBLE);
                    lin.setVisibility(View.VISIBLE);
                    forget.setVisibility(View.VISIBLE);
                    rem.setVisibility(View.VISIBLE);
                    l.setVisibility(View.VISIBLE);

                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getActivity(), "Please Enter Password", Toast.LENGTH_SHORT).show();

                    proglog.setVisibility(View.INVISIBLE);
                    pw.requestFocus();
                    im.setVisibility(View.INVISIBLE);
                    lin.setVisibility(View.VISIBLE);
                    forget.setVisibility(View.VISIBLE);
                    rem.setVisibility(View.VISIBLE);
                    l.setVisibility(View.VISIBLE);
                    return;

                }

                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                                    String RegisteredUserID = currentUser.getUid();
                                    final Bundle b = new Bundle();
                                    b.putString("userId", RegisteredUserID);
                                    final DatabaseReference jLoginDatabase = FirebaseDatabase.getInstance().getReference()
                                            .child("SmartDab").child("User").child(RegisteredUserID);
                                    jLoginDatabase.addValueEventListener(new ValueEventListener(){
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            String userType = dataSnapshot.child("status").getValue(String.class);
                                            String key = dataSnapshot.getKey();
                                            assert userType != null;
                                            switch (userType) {
                                                case "Teacher":
                                                    Loading();
                                                    Toast.makeText(getActivity(), "Successfully Log In as Teacher", Toast.LENGTH_SHORT).show();
                                                    startActivity(new Intent(getActivity(), home.class).putExtras(b));
                                                    getActivity().finish();
                                                    break;
                                                case "Student":
                                                    Loading();
                                                    Toast.makeText(getActivity(), "Successfully Log In as Student", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(getActivity(), homestud.class);
                                                    startActivity(intent);
                                                    getActivity().finish();

                                                    break;
                                                default:
                                                    Toast.makeText(getActivity(), "Failed Login. Please Try Again", Toast.LENGTH_SHORT).show();
                                                    proglog.setVisibility(View.INVISIBLE);
                                                    l.setVisibility(View.VISIBLE);
                                                    break;

                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                } else {
                                    Toast.makeText(getActivity(), "Mali Choi! Tarunga", Toast.LENGTH_SHORT).show();
//                                    us.setText("");
//                                    pw.setText("");
                                    proglog.setVisibility(View.INVISIBLE);
                                    l.setVisibility(View.VISIBLE);
                                    lin.setVisibility(View.VISIBLE);
                                    forget.setVisibility(View.VISIBLE);
                                    rem.setVisibility(View.VISIBLE);
                                    im.setVisibility(View.INVISIBLE);

                                }

                            }
                        });


            }

        });

        sharedawohh();
        return vio;
    }

    private void remembermefucker() {

        if (rem.isChecked()) {
            editor.putString(getString(R.string.checkbox), "True");
            editor.commit();

            String username = us.getText().toString();
            editor.putString(getString(R.string.user), username);
            editor.commit();
            String passwords = pw.getText().toString();
            editor.putString(getString(R.string.pass), passwords);
            editor.commit();
        } else {
            editor.putString(getString(R.string.checkbox), "False");
            editor.commit();

            String username = us.getText().toString();
            editor.putString(getString(R.string.user), "");
            editor.commit();
            String passwords = pw.getText().toString();
            editor.putString(getString(R.string.pass), "");
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
    private void Loading() {

        proglog.setVisibility(View.VISIBLE);

        final Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();

                for (int p = 0; p < 100; p++) {
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

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);


        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){


        }else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){

        }


    }
}
