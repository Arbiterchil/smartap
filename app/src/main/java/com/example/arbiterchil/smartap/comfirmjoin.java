package com.example.arbiterchil.smartap;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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

public class comfirmjoin extends AppCompatActivity {

    Button erchil;
    RecyclerView recyclerView;
    DatabaseReference ref;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    List<seatplan> list;
    DatabaseReference mdatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comfirmjoin);


        erchil = findViewById(R.id.erchil);
        recyclerView = findViewById(R.id.recu);

        recyclerView.setNestedScrollingEnabled(true);

        GridLayoutManager layoutManager = new GridLayoutManager(this,2,
                GridLayoutManager.HORIZONTAL,
                false);
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
        else{
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        }

    erchil.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            final String key = getIntent().getExtras().get("gcid").toString();
            Intent hehe = new Intent(comfirmjoin.this,alllist.class);
            hehe.putExtra("gcid",key);
            startActivity(hehe);

        }
    });


    }





    @Override
    protected void onStart() {
        super.onStart();
        final String key = getIntent().getExtras().get("gcid").toString();
        mdatabase = FirebaseDatabase.getInstance().getReference().child("StudentList").child(key);

        FirebaseRecyclerOptions<seatplan> options = new FirebaseRecyclerOptions.Builder<seatplan>(
        ).setQuery(mdatabase,seatplan.class)
                .build();

        FirebaseRecyclerAdapter<seatplan,Mobs> adapter = new FirebaseRecyclerAdapter<seatplan, Mobs>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final Mobs holder, int position, @NonNull final seatplan model) {

                holder.kath.setText(model.getId());
                holder.niko.setText(model.getFullname());



                switch (model.getStatus()) {
                    case "Entered":

                        holder.enter.setVisibility(View.VISIBLE);
                        holder.notenter.setVisibility(View.GONE);
                        holder.drop.setVisibility(View.GONE);

                        break;

                    case "not Entered":

                        holder.enter.setVisibility(View.GONE);
                        holder.notenter.setVisibility(View.VISIBLE);
                        holder.drop.setVisibility(View.GONE);

                        break;

                    case "Drop":

                        holder.enter.setVisibility(View.GONE);
                        holder.notenter.setVisibility(View.GONE);
                        holder.drop.setVisibility(View.VISIBLE);

                        break;
                }


               holder.itemView.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       AlertDialog.Builder dial = new AlertDialog.Builder(comfirmjoin.this);
                       dial.setTitle("Change Status of Student.");
                       dial.setIcon(R.drawable.flower);
                       dial.setMessage("Student Already have Valid Reason.");
                       dial.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               final String key = getIntent().getExtras().get("gcid").toString();
                               DatabaseReference fergio = FirebaseDatabase.getInstance().getReference().child("StudentList")
                                       .child(key);
                               fergio.addListenerForSingleValueEvent(new ValueEventListener() {
                                   @Override
                                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        String name = model.getFullname();
                                       if (dataSnapshot.exists()){

                                           Query query1 = FirebaseDatabase.getInstance().getReference().child("StudentList")
                                                   .child(key).orderByChild("fullname").equalTo(name);

                                           query1.addListenerForSingleValueEvent(new ValueEventListener() {
                                               @Override
                                               public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                   for (DataSnapshot ds : dataSnapshot.getChildren()){
                                                       String fuck = ds.getKey();
                                                       String stats = ds.child("status").getValue(String.class);

                                                       DatabaseReference dearfred = FirebaseDatabase.getInstance().
                                                               getReference().child("StudentList")
                                                               .child(key).child(fuck);
                                                       dearfred.child("status").setValue("not Entered");

                                                   }

                                               }

                                               @Override
                                               public void onCancelled(@NonNull DatabaseError databaseError) {

                                               }
                                           });


                                       }

                                   }

                                   @Override
                                   public void onCancelled(@NonNull DatabaseError databaseError) {

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
               });
            }
            @NonNull
            @Override
            public Mobs onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View vovo = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.yubi,viewGroup,false);
                return  new Mobs(vovo);
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }

    private static class Mobs extends RecyclerView.ViewHolder{
        TextView kath = itemView.findViewById(R.id.kath);
        TextView niko = itemView.findViewById(R.id.niko);
        ImageView enter = itemView.findViewById(R.id.enters);
        ImageView notenter = itemView.findViewById(R.id.noenters);
        ImageView drop = itemView.findViewById(R.id.drop);
        public Mobs(@NonNull View itemView) {
            super(itemView);
        }
    }
}
