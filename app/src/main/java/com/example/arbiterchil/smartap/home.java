package com.example.arbiterchil.smartap;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

public class home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    Toolbar toolbar;
    private DrawerLayout drawer;
    NavigationView navigationView;

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    DatabaseReference rfe,mDatabase;
    TextView notifs;
    CircleImageView circleImageView;
    FirebaseDatabase firebaseDatabase;
    View Headerviews;
    FirebaseUser firebaseUser;
    DatabaseReference refer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        user = firebaseAuth.getCurrentUser();
         rfe = FirebaseDatabase.getInstance().getReference().child("User");
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        circleImageView = findViewById(R.id.iconic);
        notifs = (TextView) MenuItemCompat.getActionView(navigationView.getMenu().findItem(R.id.nav_notif));

        initial();

        UpdateNav();


        mDatabase = FirebaseDatabase.getInstance().
                getReference().child("User").
                child(firebaseAuth.getCurrentUser().getUid());





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

    private  void logout(){
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
            case R.id.nav_create:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new create()).commit();
                break;
            case R.id.nav_grade:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new records()).commit();
                break;
            case R.id.givegroup:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new givegroup()).commit();
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
    public void UpdateNav(){
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
                String hehey = dataSnapshot.getKey();
                smartdab smartass = dataSnapshot.getValue(smartdab.class);
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                try {
                    String hehe = ds.child("fullname").getValue(String.class);
                    txt.setText(hehe);





                }catch (Exception e){

                }



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void setNavItemCount(@IdRes int itemId, int count) {
        TextView view = (TextView) navigationView.getMenu().findItem(itemId).getActionView();
        view.setText(count > 0 ? String.valueOf(count) : null);
    }

    @Override
    protected void onStart() {
        super.onStart();

       refer = FirebaseDatabase.getInstance().
                getReference().child("User").
                child(firebaseUser.getUid());

        refer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        try {
                            String url = dataSnapshot.child("url").getValue(String.class);
                            Glide.with(getApplicationContext()).load(url).into(circleImageView);
                        }catch (Exception ex)
                        {
                            ex.printStackTrace();
                        }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }




}
