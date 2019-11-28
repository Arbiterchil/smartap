package com.example.arbiterchil.smartap;

import android.content.DialogInterface;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.CountDownTimer;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.sql.Time;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;

public class Poplist extends AppCompatActivity {
    CountDownTimer  countErchil;
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
    ArrayAdapter<String>arrayAdapter;
    ArrayList<String>arrayList =  new ArrayList<>();
    TextView underthepressure;
    private boolean Ascending = true;
    RecyclerView recyclerView;
    List<memberlist> list;
    FirebaseUser curentUser;
    FirebaseAuth mauth;
    ImageView starting,savings,backing,backus;
    FloatingActionButton fab_plus,fab_join,fab_recrd,fab_delete,fab_seat,fab_list,fab_sutlist;
    Animation Fopen,Fclose,Fclock,Fanticlock;
    boolean isOp = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poplist);

        mauth = FirebaseAuth.getInstance();
        curentUser = mauth.getCurrentUser();


        fab_plus = findViewById(R.id.fab_plus);
        fab_join = findViewById(R.id.fab_join);
        fab_recrd = findViewById(R.id.fab_list);
        fab_delete = findViewById(R.id.fab_delete);
        fab_list = findViewById(R.id.fab_record);
        fab_sutlist = findViewById(R.id.fab_stuflist);
        fab_seat = findViewById(R.id.fab_seat);
       Fopen = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_open);
       Fclose =  AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        Fclock = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_clockwise);
        Fanticlock =  AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_anticlockwise);

        fab_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isOp){
                    fab_plus.startAnimation(Fanticlock);
                    fab_recrd.startAnimation(Fclose);
                    fab_delete.startAnimation(Fclose);
                    fab_list.startAnimation(Fclose);
                    fab_sutlist.startAnimation(Fclose);
                    fab_seat.startAnimation(Fclose);
                    fab_delete.setClickable(false);
                    fab_seat.setClickable(false);
                    fab_list.setClickable(false);
                    fab_recrd.setClickable(false);
                    fab_sutlist.setClickable(false);
                    isOp = false;
                }else{
                    fab_plus.startAnimation(Fclock);
                    fab_recrd.startAnimation(Fopen);
                    fab_delete.startAnimation(Fopen);
                    fab_list.startAnimation(Fopen);
                    fab_sutlist.startAnimation(Fopen);
                    fab_seat.startAnimation(Fopen);
                    fab_seat.setClickable(true);
                    fab_sutlist.setClickable(true);
                    fab_delete.setClickable(true);
                    fab_list.setClickable(true);
                    fab_recrd.setClickable(true);
                    isOp = true;

                }
            }
        });

        fab_seat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String key = getIntent().getExtras().get("gcid").toString();
                Intent hehe = new Intent(Poplist.this,seatplanauto.class);
                hehe.putExtra("gcid",key);
                startActivity(hehe);


            }
        });
        fab_sutlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String key = getIntent().getExtras().get("gcid").toString();
                Intent hehe = new Intent(Poplist.this,comfirmjoin.class);
                hehe.putExtra("gcid",key);
                startActivity(hehe);
            }
        });

        fab_recrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String key = getIntent().getExtras().get("gcid").toString();
                Intent hehe = new Intent(Poplist.this,checkyou.class);
                hehe.putExtra("key",key);
                startActivity(hehe);
            }
        });
        underthepressure = findViewById(R.id.Datenow);
        Calendar calendar = Calendar.getInstance();
        String getCurrrentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        underthepressure.setText(getCurrrentDate);
        mauth = FirebaseAuth.getInstance();
        curentUser = mauth.getCurrentUser();
        refpri = FirebaseDatabase.getInstance();
        gcname= findViewById(R.id.gcname);
        idgroup = findViewById(R.id.idgroup);
        starting = findViewById(R.id.startthings);
        savings = findViewById(R.id.savethings);
        gcname.setText(getIntent().getStringExtra("groupname"));
        idgroup.setText(getIntent().getStringExtra("gcid"));

        mDatabase = FirebaseDatabase.getInstance().getReference();


        fab_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Delete();
            }
        });
        fab_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String key= getIntent().getExtras().get("gcid").toString();
                Intent gcroom = new Intent(Poplist.this,Attendance.class);
                gcroom.putExtra("gcid",key);
                startActivity(gcroom);
            }
        });

        savings.setVisibility(View.GONE);
        Timewise = findViewById(R.id.timecounter);
        Teachsave();



    }
    private void Teachsave() {
        starting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                backus.setVisibility(View.GONE);

                final String key = getIntent().getExtras().get("gcid").toString();
                AlertDialog.Builder dial = new AlertDialog.Builder(Poplist.this);
                dial.setTitle("Want to use Standard Time?");
                dial.setMessage("Standard Time is 15 if  not limit to 1 min");
                dial.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        StandardTime();
                        StrikeZone();
                        starting.setVisibility(View.INVISIBLE);
                        savings.setVisibility(View.VISIBLE);

                    }
                })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                TimeTicking();

                                StrikeZone();
                                starting.setVisibility(View.INVISIBLE);
                                savings.setVisibility(View.VISIBLE);
                            }
                        });

                dial.show();

            }

        });

        savings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { starting.setVisibility(View.VISIBLE);
                sofort();
                deleteNotifs();
                    SaveIntance();
                    StudlistDekete();
                    backtoreality();
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
                                    String stam = no.child("status").getValue(String.class);
                                    String own = no.child("type").getValue(String.class);

                                    switch (stam){
                                        case "Late or Absent":
                                            String wew = "Normal";
                                            reybo.child(aids).child("status").setValue(wew);
                                            break;

                                        case "Present":
                                            String wewo = "Normal";
                                            reybo.child(aids).child("status").setValue(wewo);
                                            break;
                                        case "Late":
                                            String wewi = "Normal";
                                            reybo.child(aids).child("status").setValue(wewi);
                                            break;

                                    }
                                    switch (own){

                                        case "Ready":
                                            String yoko = "student";
                                            reybo.child(aids).child("type").setValue(yoko);
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

                countErchil.cancel();

                savings.setVisibility(View.GONE);

            }});
//        backus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//
//
//            }});
    }

    private void backtoreality() {

        final String key = getIntent().getExtras().get("gcid").toString();
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

    private void StudlistDekete() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        final String RegisteredUserID = currentUser.getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("StunAttend");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                DatabaseReference uidssame = FirebaseDatabase.getInstance().getReference().child("StunAttend")
                        .child(RegisteredUserID);
                uidssame.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()){

                            Toast.makeText(Poplist.this, "And Have a Great Day.", Toast.LENGTH_SHORT).show();

                        }

                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void deleteNotifs() {
        final String key = getIntent().getExtras().get("gcid").toString();
        DatabaseReference refuge = FirebaseDatabase.getInstance().getReference();


        refuge.child("StunAttend").child(key).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){
                    Toast.makeText(Poplist.this, "Have a Nice Day.", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
    private void StandardTime() {
                     countErchil = new CountDownTimer(900000,1000){
//        countErchil = new CountDownTimer(50000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                String Timetext = String.format(Locale.getDefault(),"%02d : %02d "
                        ,TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)% 60,
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)% 60);
                Timewise.setText(Timetext);
            }
            @Override
            public void onFinish() {
                Timewise.setText("DONE!");
                final String key = getIntent().getExtras().get("gcid").toString();
                final DatabaseReference Lates = FirebaseDatabase.getInstance().getReference()
                        .child("GroupMember").child(key);
                Lates.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String datamem = dataSnapshot.child("Member").getKey();


                        final DatabaseReference deck = FirebaseDatabase.getInstance().getReference()
                                .child("Groups").child(key).child(datamem);
                        deck.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                for (DataSnapshot no : dataSnapshot.getChildren()){

                                    String aids = no.getKey();
                                    String sta = no.child("status").getValue(String.class);

                                    switch (sta){
                                        case "Normal":
                                            String wew = "Late or Absent";
                                            deck.child(aids).child("status").setValue(wew);
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
        }.start();


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
    private void backtoNormal() {

        final String key = getIntent().getExtras().get("gcid").toString();

        final DatabaseReference vovo = FirebaseDatabase.getInstance().getReference()
                .child("Groups");
        vovo.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                String gege = dataSnapshot.getKey();
                String star = "Started";
                vovo.child(gege).child("typestatus").setValue("Not Started");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void Shockwave() {
        final String key = getIntent().getExtras().get("gcid").toString();

         final DatabaseReference vivi = FirebaseDatabase.getInstance().getReference()
                 .child("Groups");
        vivi.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String heha= dataSnapshot.child("Member").getKey();

                        DatabaseReference creed = vivi.child(heha);
                        creed.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                String wew = dataSnapshot.getKey();
                                for (DataSnapshot ds: dataSnapshot.getChildren()){
                                    String one = ds.child("status").getValue(String.class);
                                    String two = ds.child("absents").getValue(String.class);
                                    switch (one){

                                        case "Present":

                                            Toast.makeText(Poplist.this, "Your Present on this day.", Toast.LENGTH_SHORT).show();

                                            break;

                                    }
                                    switch (two){

                                        case "3":

                                            Intent serviceIntent = new Intent(Poplist.this, ServicesUid.class);
                                            serviceIntent.putExtra("Uid", wew);
                                            serviceIntent.putExtra("extra", "You have A Notification Please Check it.");
                                            ContextCompat.startForegroundService(Poplist.this, serviceIntent);



                                            break;
                                        case "6":

                                            Intent serviceIntents = new Intent(Poplist.this, ServicesUid.class);
                                            serviceIntents.putExtra("Uid", wew);
                                            serviceIntents.putExtra("extra", "You have A Notification Please Check it.");
                                            ContextCompat.startForegroundService(Poplist.this, serviceIntents);

                                            break;
                                        case "8":

                                            Intent serviceIntentss = new Intent(Poplist.this, ServicesUid.class);
                                            serviceIntentss.putExtra("Uid", wew);
                                            serviceIntentss.putExtra("extra", "You have A Notification Please Check it.");
                                            ContextCompat.startForegroundService(Poplist.this, serviceIntentss);

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
    private void TimeTicking() {
        //             countErchil = new CountDownTimer(900000,1000){
        countErchil = new CountDownTimer(60000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                String Timetext = String.format(Locale.getDefault(),"%02d : %02d "
                        ,TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)% 60,
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)% 60);
                Timewise.setText(Timetext);
            }
            @Override
            public void onFinish() {
                Timewise.setText("DONE!");
                final String key = getIntent().getExtras().get("gcid").toString();
                final DatabaseReference Lates = FirebaseDatabase.getInstance().getReference()
                        .child("GroupMember").child(key);
                Lates.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String datamem = dataSnapshot.child("Member").getKey();


                final DatabaseReference deck = FirebaseDatabase.getInstance().getReference()
                        .child("Groups").child(key).child(datamem);
                deck.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot no : dataSnapshot.getChildren()){

                            String aids = no.getKey();
                            String sta = no.child("status").getValue(String.class);

                            switch (sta){
                                case "Normal":
                                    String wew = "Late or Absent";
                                    deck.child(aids).child("status").setValue(wew);
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
        }.start();

    }
    private void SaveIntance() {

        final String key = getIntent().getExtras().get("gcid").toString();
        final DatabaseReference freshstart = FirebaseDatabase.getInstance().getReference()
                .child("GroupMember").child(key);
        freshstart.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                final String data = dataSnapshot.child("Member").getKey();

                final DatabaseReference fenrir = FirebaseDatabase.getInstance().getReference()
                        .child("Groups").child(key).child(data);
                fenrir.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot sd : dataSnapshot.getChildren()){
                            String uid = sd.getKey();
                            String fulls = sd.child("fullname").getValue(String.class);

                            String ds = sd.child("status").getValue(String.class);
                            String abs = sd.child("absents").getValue(String.class);
                            String lts = sd.child("lates").getValue(String.class);
                            String url = sd.child("url").getValue(String.class);
                            switch (ds){

                                case "Present":

                                    String full = sd.child("fullname").getValue(String.class);
                                    Toast.makeText(Poplist.this, full+" is Present", Toast.LENGTH_SHORT).show();

                                    break;
                                case "Late or Absent":

                                    int numer = Integer.parseInt(abs);
                                    numer++;
                                    String addcounts = Integer.toString(numer);
                                    fenrir.child(uid).child("absents").setValue(addcounts);

                                    Map<String, Object> us = new HashMap<>();
                                    us.put("fullname",fulls);
                                    us.put("absenttimes",addcounts);
                                    us.put("url",url);
                                    us.put("date",underthepressure.getText().toString());
                                            String heavy = mDatabase.push().getKey();
                                            mDatabase.child("Groups")
                                            .child(key).child("Records").child("AbsentRecord").child(heavy).setValue(us);

                                    break;
                                case "Late":

                                    int pussy = Integer.parseInt(lts);
                                    pussy++;
                                    String dodo = Integer.toString(pussy);

                                    fenrir.child(uid).child("lates").setValue(dodo);
                                    Map<String, Object> use = new HashMap<>();
                                    use.put("fullname",fulls);
                                    use.put("latetimes",dodo);
                                    use.put("url",url);
                                    use.put("date",underthepressure.getText().toString());
                                    String heavys = mDatabase.push().getKey();
                                    mDatabase.child("Groups")
                                            .child(key).child("Records").child("LateRecord").child(heavys).setValue(use);

                                    break;

                                case "Normal":
                                    int numers = Integer.parseInt(abs);
                                    numers++;
                                    String addcountss = Integer.toString(numers);
                                    fenrir.child(uid).child("absents").setValue(addcountss);

                                    Map<String, Object> usr = new HashMap<>();
                                    usr.put("fullname",fulls);
                                    usr.put("url",url);
                                    usr.put("absenttimes",addcountss);
                                    usr.put("date",underthepressure.getText().toString());
                                    String metal = mDatabase.push().getKey();
                                    mDatabase.child("Groups")
                                            .child(key).child("Records").child("AbsentRecord").child(metal).setValue(usr);



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
    private void sofort() {
        final String key = getIntent().getExtras().get("gcid").toString();
        final DatabaseReference Freshmeat = FirebaseDatabase.getInstance().getReference()
                .child("Groups");
            Freshmeat.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    final String gcname = dataSnapshot.child("groupname").getValue(String.class);
                    final String teachid = dataSnapshot.child("uid").getValue(String.class);
                    final String mems = dataSnapshot.child("Member").getKey();
                    final DatabaseReference alienz = FirebaseDatabase.getInstance()
                            .getReference().child("Groups")
                            .child(key);
                    alienz.child(mems).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                if (dataSnapshot.getChildrenCount() > 0) {
                                    final String uidmam = ds.getKey();
                                    final String fulls = ds.child("fullname").getValue(String.class);
                                    final String abs = ds.child("absents").getValue(String.class);

                                    switch (abs) {
                                        case "3":
                                            final DatabaseReference fear = FirebaseDatabase.getInstance().
                                                    getReference()
                                                    .child("Notifications");
                                            fear.addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                                                    String notes = "Please be mindful for your Absents you have already [4] in the Subject of " +
                                                            gcname + " please be careful in near future. That's All ,"+fulls;
                                                    String ggjud = "Student " + fulls + " Drop cause of Absents reach counts to " + abs + " From Subject"
                                                            + gcname;
                                                    String ggcka = "You've been drop from the Subject " + gcname + " exceed of " +
                                                            abs + " Absents.If you have valid reason Approach your Subject Teacher.";
                                                    Map<String, Object> hyaki = new HashMap<>();
                                                    hyaki.put("name", fulls);
                                                    hyaki.put("note", notes);
                                                    hyaki.put("uid",uidmam);

                                                    fear.child(uidmam).child(fear.push().getKey()).setValue(hyaki);
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }

                                            });
                                                final DatabaseReference reference = FirebaseDatabase.getInstance()
                                                        .getReference()
                                                        .child("Notifications");
                                                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                        String teah = "Student " + fulls + " reach absents count to [4] from Subject " + gcname;
                                                        Map<String, Object> hyaki = new HashMap<>();
                                                        hyaki.put("name", fulls);
                                                        hyaki.put("note", teah);
                                                        hyaki.put("uid",teachid);
                                                        reference.child(teachid).child(reference.push().getKey()).setValue(hyaki);



                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                                    }
                                                });
                                            break;
                                        case "6":
                                            final DatabaseReference fears= FirebaseDatabase.getInstance().
                                                    getReference()
                                                    .child("Notifications");
                                            fears.addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    String notes = "Please be mindful for your Absents you have already [7] in the Subject of " +
                                                            gcname + " please be careful in near future. That's All ,"+fulls;



                                                    String ggjud = "Student " + fulls + " Drop cause of Absents reach counts to " + abs + " From Subject"
                                                            + gcname;
                                                    String ggcka = "You've been drop from the Subject " + gcname + " exceed of " +
                                                            abs + " Absents.If you have valid reason Approach your Subject Teacher.";
                                                    Map<String, Object> hyaki = new HashMap<>();
                                                    hyaki.put("name", fulls);
                                                    hyaki.put("note", notes);
                                                    hyaki.put("uid",uidmam);
                                                    fears.child(uidmam).child(fears.push().getKey()).setValue(hyaki);
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                            final DatabaseReference references = FirebaseDatabase.getInstance()
                                                    .getReference()
                                                    .child("Notifications");
                                            references.addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                    String teah = "Student " + fulls + " reach absents count to [7] from Subject " + gcname;
                                                    Map<String, Object> hyaki = new HashMap<>();
                                                    hyaki.put("name", fulls);
                                                    hyaki.put("note", teah);
                                                    hyaki.put("uid",teachid);
                                                    references.child(teachid).child(references.push().getKey()).setValue(hyaki);
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });


                                            break;
                                        case "8":

                                            dataSnapshot.child(uidmam).getRef().removeValue();

                                            final String key = getIntent().getExtras().get("gcid").toString();
                                            DatabaseReference fera = FirebaseDatabase.getInstance()
                                                    .getReference()
                                                    .child("StudentList")
                                                    .child(key);
                                            fera.addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    if (dataSnapshot.exists()){
                                                        for (DataSnapshot ds:dataSnapshot.getChildren()){
                                                            Query query1 = FirebaseDatabase.getInstance()
                                                                    .getReference().child("StudentList")
                                                                    .child(key).orderByChild("fullname").equalTo(fulls);
                                                            query1.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                @Override
                                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                    for (DataSnapshot ds : dataSnapshot.getChildren()){
                                                                        String fuck = ds.getKey();
                                                                        String stats = ds.child("status").getValue(String.class);

                                                                        DatabaseReference dearfred =
                                                                                FirebaseDatabase.getInstance().
                                                                                getReference()
                                                                                        .child("StudentList")
                                                                                .child(key)
                                                                                        .child(fuck);
                                                                        dearfred.child("status").setValue("Drop");
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



                                            final DatabaseReference fearsome= FirebaseDatabase.getInstance().
                                                    getReference()
                                                    .child("Notifications");
                                            fearsome.addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    String notes = "Please be mindful for your Absents you have already [7] in the Subject of " +
                                                            gcname + " please be careful in near future. That's All ,"+fulls;
                                                    String ggcka = "You've been drop from the Subject " + gcname + " exceed of [9] Absents.If you have valid reason Approach your Subject Teacher.";
                                                    Map<String, Object> hyaki = new HashMap<>();
                                                    hyaki.put("name", fulls);
                                                    hyaki.put("note", ggcka);
                                                    hyaki.put("uid",uidmam);
                                                    fearsome.child(uidmam).child(fearsome.push().getKey()).setValue(hyaki);
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                            final DatabaseReference referencesome = FirebaseDatabase.getInstance()
                                                    .getReference()
                                                    .child("Notifications");
                                            referencesome.addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                    String teah = "Student " + fulls + " reach absents count to [7] from Subject " + gcname;
                                                    String ggjud = "Student " + fulls + " Drop cause of Absents reach counts to [9] From Subject" + gcname;
                                                    Map<String, Object> hyaki = new HashMap<>();
                                                    hyaki.put("name", fulls);
                                                    hyaki.put("note", ggjud);
                                                    hyaki.put("uid",teachid);
                                                    referencesome.child(teachid).child(fearsome.push().getKey()).setValue(hyaki);
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });



                                            break;
                                    }
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
    private void Delete() {
        final String key = getIntent().getExtras().get("gcid").toString();
        AlertDialog.Builder dial = new AlertDialog.Builder(Poplist.this);
        dial.setTitle("Delete Room.");
        dial.setIcon(R.drawable.flower);
        dial.setMessage("Are You Sure want to Delete it?");
        dial.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                mDatabase.child("Groups").child(key).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Poplist.this, "Deleted", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });


            }
        })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                });

        dial.show();
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

//    @Override
//    protected void onStart() {
//        super.onStart();
//        final String key = getIntent().getExtras().get("gcid").toString();
//       DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("StudentList").child(key);
//
//       FirebaseRecyclerOptions<AttendanceHolder> options =new FirebaseRecyclerOptions.Builder<AttendanceHolder>()
//               .setQuery(ref,AttendanceHolder.class)
//               .build();
//
//     FirebaseRecyclerAdapter<AttendanceHolder,kings>adapter =
//             new FirebaseRecyclerAdapter<AttendanceHolder, kings>(options) {
//         @Override
//         protected void onBindViewHolder(@NonNull kings holder, int position, @NonNull AttendanceHolder model) {
//
//             holder.idnumbers.setText(model.getCreatedid());
//             holder.namesstud.setText(model.getWholename());
//             holder.en.setVisibility(View.INVISIBLE);
//
//             if ("Entered".equals(model.getWhatstatus())){
//                 holder.en.setVisibility(View.VISIBLE);
//                 holder.not.setVisibility(View.INVISIBLE);
//             }
//         }
//
//         @NonNull
//         @Override
//         public kings onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rowblock,viewGroup,false);
//             return new kings(v);
//         }
//     };
//
//        recyclerView.setAdapter(adapter);
//        adapter.startListening();
//
//    }
//    private static class kings extends RecyclerView.ViewHolder{
//        TextView idnumbers,namesstud;
//        ImageView en,not;
//
//        public kings( View itemView) {
//            super(itemView);
//
//            idnumbers = itemView.findViewById(R.id.idning);
//            namesstud = itemView.findViewById(R.id.naming);
//            en = itemView.findViewById(R.id.checkme);
//            not = itemView.findViewById(R.id.checkmenot);
//
//        }
//    }
}


