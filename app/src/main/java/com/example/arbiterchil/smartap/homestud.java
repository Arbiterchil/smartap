package com.example.arbiterchil.smartap;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class homestud extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    Toolbar toolbar;
    private DrawerLayout drawer;
    NavigationView navigationView;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    DatabaseReference rfe;
    TextView notifs;
    String curredntid;
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homestud);




        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        rfe = FirebaseDatabase.getInstance().getReference().child("User");
        mDatabase = FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawerstudent_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        notifs = (TextView) MenuItemCompat.getActionView(navigationView.getMenu().findItem(R.id.nav_notif));

        UpdateNav();
        initial();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new dfrag()).commit();
            navigationView.setCheckedItem(R.id.nav_profile );
        }



    }
    private void initial() {

        notifs.setGravity(Gravity.CENTER_VERTICAL);
        notifs.setTypeface(null, Typeface.BOLD);
        notifs.setTextColor(getResources().getColor(R.color.red));
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        final String RegisteredUserID = currentUser.getUid();
        final Query querys = FirebaseDatabase.getInstance().getReference().child("Notifications")
                .child(RegisteredUserID)
                .orderByChild("uid").equalTo(RegisteredUserID);
        querys.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){
                    int count = (int) dataSnapshot.getChildrenCount();


                    notifs.setText(Integer.toString(count)+" + ");


                }else{

                    notifs.setText("");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void UpdateStatus(String state) {


        HashMap<String,Object> online = new HashMap<>();
        online.put("state",state);

        curredntid = firebaseAuth.getCurrentUser().getUid();
        mDatabase.updateChildren(online);



    }

    private  void logout(){
        UpdateStatus("offline");
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getApplicationContext(),login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

        finish();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new dfrag()).commit();
                break;
            case R.id.nav_notif:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new notification()).commit();
                break;
            case R.id.joingroup:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new joinsubject()).commit();
                break;
            case R.id.posting:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new posting()).commit();
                break;
            case R.id.nav_sig:
                logout();
                finish();
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }
    public void UpdateNav() {
        navigationView = findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        final TextView txt = header.findViewById(R.id.prof);
        final TextView txtss = header.findViewById(R.id.emails);
        txtss.setText(user.getEmail());
        String haha = user.getEmail();
        Query query = rfe.orderByChild("email").equalTo(haha);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    String hehe = ds.child("fullname").getValue(String.class);
                    txt.setText(hehe);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



}

