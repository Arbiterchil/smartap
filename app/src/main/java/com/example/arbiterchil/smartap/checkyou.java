package com.example.arbiterchil.smartap;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class checkyou extends AppCompatActivity {

    CountDownTimer countErchil;
    CircleImageView taplod, checkme;
    DatabaseReference mDatabase;
    FirebaseDatabase refpri;
    private DatabaseReference refl;
    FirebaseAuth firebaseAuth;
    TextView gcname, passgen, idgroup, mems, Timewise;
    FirebaseDatabase firebaseDatabase;
    private String rec;
    Button sendreq, delete, leave, Attendance;
    FirebaseListAdapter listAdapter;
    ListView listv;
    FirebaseUser user;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> arrayList = new ArrayList<>();
    TextView underthepressure;

    private boolean Ascending = true;
    RecyclerView recyclerView;
    List<memberlist> list;

    FirebaseUser curentUser;
    FirebaseAuth mauth;
    Button starting, savings, backing, backus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkyou);

//        DisplayMetrics dm = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(dm);
//        int width = dm.widthPixels;
//        int hiegth = dm.heightPixels;
//        getWindow().setLayout((int) (width * .9), (int) (hiegth * .5));

        recyclerView = findViewById(R.id.recy);
        recyclerView.setNestedScrollingEnabled(true);

        GridLayoutManager layoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);
        int spanCount = 2;
        int spacing = 50;
        boolean includeEdge = true;
        recyclerView.addItemDecoration(new Gridspcae(spanCount, spacing, includeEdge));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        list = new ArrayList<>();


        if(recyclerView.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }
        else if (recyclerView.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        }



    }

    @Override
    protected void onStart() {
        super.onStart();

        final String key = getIntent().getExtras().get("key").toString();

        refl = FirebaseDatabase.getInstance().getReference().child("Groups").child(key);

        refl.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String datamem = dataSnapshot.child("Member").getKey();

                DatabaseReference references = FirebaseDatabase.getInstance().getReference()
                        .child("Groups").child(key)
                        .child(datamem);
                FirebaseRecyclerOptions<memberlist> options = new
                        FirebaseRecyclerOptions.Builder<memberlist>()
                        .setQuery(references, memberlist.class).build();


                FirebaseRecyclerAdapter<memberlist, KingdomHearts> FaceMyFears =
                        new FirebaseRecyclerAdapter<memberlist, KingdomHearts>(options) {
                            @Override
                            protected void onBindViewHolder(@NonNull KingdomHearts holder, int position, @NonNull memberlist model) {

                                holder.fn.setText(model.getFullname());
                                holder.sta.setText(model.getStatus());
                                holder.uid.setText(model.getUid());
                                holder.uid.setVisibility(View.INVISIBLE);
                                holder.keyso.setVisibility(View.INVISIBLE);
                                Picasso.get().load(model.getUrl()).into(holder.circleImageView);

                                final String iwant = model.getFullname();
                                final String  tobreak = model.getStatus();
                                final String  free = model.getUrl();
                                final String onevision = model.getUid();
                                final String key = getIntent().getExtras().get("key").toString();
                                holder.itemView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Intent queen = new Intent(checkyou.this, markabsent.class);
                                        queen.putExtra("iwant",iwant);
                                        queen.putExtra("tobreak",tobreak);
                                        queen.putExtra("free",free);
                                        queen.putExtra("one",onevision);
                                        queen.putExtra("key",key);
                                        startActivity(queen);

                                    }
                                });



                            }

                            @NonNull
                            @Override
                            public KingdomHearts onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                                View views = LayoutInflater.from(viewGroup.getContext()).
                                        inflate(R.layout.finalerow, viewGroup, false);
                                return new KingdomHearts(views);
                            }
                        };
                recyclerView.setAdapter(FaceMyFears);
                FaceMyFears.startListening();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        FirebaseRecyclerOptions<memberlist> options = new
                FirebaseRecyclerOptions.Builder<memberlist>()
                .setQuery(refl, memberlist.class).build();


        FirebaseRecyclerAdapter<memberlist, KingdomHearts> FaceMyFears =
                new FirebaseRecyclerAdapter<memberlist, KingdomHearts>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull KingdomHearts holder, int position, @NonNull memberlist model) {

                        holder.fn.setText(model.getFullname());
                        holder.sta.setText(model.getStatus());
                        holder.uid.setText(model.getUid());
                        holder.uid.setVisibility(View.INVISIBLE);
                        holder.keyso.setVisibility(View.INVISIBLE);


                    }

                    @NonNull
                    @Override
                    public KingdomHearts onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                        View views = LayoutInflater.from(viewGroup.getContext()).
                                inflate(R.layout.finalerow, viewGroup, false);
                        return new KingdomHearts(views);
                    }
                };
        recyclerView.setAdapter(FaceMyFears);
        FaceMyFears.startListening();


    }

    public static class KingdomHearts extends RecyclerView.ViewHolder {
        TextView keyso = itemView.findViewById(R.id.keyso);
        TextView fn = itemView.findViewById(R.id.fullname);
        TextView sta = itemView.findViewById(R.id.Statsu);
        TextView uid = itemView.findViewById(R.id.Uid);
        CircleImageView circleImageView = itemView.findViewById(R.id.kagerou);

        public KingdomHearts(@NonNull View itemView) {
            super(itemView);


        }
    }

}
