package com.example.arbiterchil.smartap;

import android.content.res.Configuration;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class seatplanauto extends AppCompatActivity {


    List<seatplan> list;
    RecyclerView studviewseat;
    DatabaseReference mdatabase;
    DatabaseReference fenris;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seatplanauto);

        mdatabase = FirebaseDatabase.getInstance().getReference().child("StudentList");
        fenris = FirebaseDatabase.getInstance().getReference().child("User");
        studviewseat = findViewById(R.id.seats);



            studviewseat.setLayoutManager

                    (new GridLayoutManager(this,10,GridLayoutManager.VERTICAL,false));
        int spanCount = 10;
        int spacing = 50;
        boolean includeEdge = true;
            studviewseat.addItemDecoration(new Gridspcae(spanCount, spacing, includeEdge));
            studviewseat.setItemAnimator(new DefaultItemAnimator());
            list = new ArrayList<>();


//        if(studviewseat.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
//            studviewseat.setLayoutManager(new GridLayoutManager
//                    (this, 10,LinearLayoutManager.VERTICAL,false));
//            studviewseat.setHorizontalScrollBarEnabled(true);
//
//        }
//        else if (studviewseat.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
//            studviewseat.setLayoutManager(new GridLayoutManager(this, 10));
//            GridLayoutManager layoutManagero = new GridLayoutManager(this,10
//                    ,LinearLayoutManager.HORIZONTAL, false);
//            studviewseat.setLayoutManager(layoutManagero);
//        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        final String key = getIntent().getExtras().get("gcid").toString();
        mdatabase = FirebaseDatabase.getInstance().getReference().child("StudentList").child(key);

        FirebaseRecyclerOptions<seatplan>options = new FirebaseRecyclerOptions.Builder<seatplan>(
        ).setQuery(mdatabase,seatplan.class)
                .build();

        final FirebaseRecyclerAdapter<seatplan,Mobs>adapter = new FirebaseRecyclerAdapter<seatplan, Mobs>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final Mobs holder, int position, @NonNull seatplan model) {
                String nameniya = model.getFullname();

                Query query = FirebaseDatabase.getInstance().getReference().child("User")
                        .orderByChild("fullname").equalTo(nameniya);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot ds: dataSnapshot.getChildren()){

                            String oon = ds.child("state").getValue(String.class);

                            if ("online".equals(oon)){

                                holder.online.setVisibility(View.VISIBLE);
                                holder.oofline.setVisibility(View.GONE);

                            }else if ("offline".equals(oon)){
                                holder.online.setVisibility(View.GONE);
                                holder.oofline.setVisibility(View.VISIBLE);
                            }


                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


                holder.kath.setText(model.getId());
                holder.niko.setText(model.getFullname());


                if (model.getArc().equals("not here")){

                    holder.cogs.setVisibility(View.VISIBLE);
                    holder.check.setVisibility(View.GONE);

                }else if (model.getArc().equals("here")){
                    holder.cogs.setVisibility(View.GONE);
                    holder.check.setVisibility(View.VISIBLE);
                }





            }

            @NonNull
            @Override
            public Mobs onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
               View vovo = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.seatdecor,viewGroup,false);
               return  new Mobs(vovo);
            }
        };


        studviewseat.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        adapter.startListening();

    }

    private static class Mobs extends RecyclerView.ViewHolder{
            TextView kath = itemView.findViewById(R.id.kath);
            TextView niko = itemView.findViewById(R.id.niko);
            ImageView cogs = itemView.findViewById(R.id.incognito);
            ImageView check = itemView.findViewById(R.id.heremeout);
            CircleImageView online = itemView.findViewById(R.id.green);
            CircleImageView oofline = itemView.findViewById(R.id.redd);

        public Mobs(@NonNull View itemView) {
            super(itemView);
        }
    }


}
