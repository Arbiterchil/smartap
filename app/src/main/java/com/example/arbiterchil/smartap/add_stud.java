package com.example.arbiterchil.smartap;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class add_stud extends AppCompatActivity {

    Button erchil;
    RecyclerView recyclerView;
    DatabaseReference ref;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    List<studlist> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stud);
        final String key = getIntent().getExtras().get("gcid").toString();
        ref = FirebaseDatabase.getInstance().getReference().child("StudentList").child(key);

        erchil = findViewById(R.id.erchil);
        recyclerView = findViewById(R.id.recu);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(this,linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        list = new ArrayList<>();

        erchil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String key = getIntent().getExtras().get("gcid").toString();
                Intent hehe = new Intent(add_stud.this,alllist.class);
                hehe.putExtra("gcid",key);
                startActivity(hehe);
            }
        });


    }
    @Override
    protected void onStart() {
        super.onStart();
        final String key = getIntent().getExtras().get("gcid").toString();
        ref = FirebaseDatabase.getInstance().getReference().child("StudentList").child(key);


        FirebaseRecyclerOptions<studlist> options = new
                FirebaseRecyclerOptions.Builder<studlist>()
                .setQuery(ref, studlist.class).build();

        FirebaseRecyclerAdapter<studlist, KingdomHearts> adapter =
                new FirebaseRecyclerAdapter<studlist, KingdomHearts>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull final KingdomHearts holder, int position, @NonNull final studlist model) {

                        holder.ids.setText(model.getId());
                        holder.names.setText(model.getFullname());

                        if (model.getStatus().equals("Entered")){

                            holder.enter.setVisibility(View.VISIBLE);
                            holder.notenter.setVisibility(View.GONE);

                        }else if (model.getStatus().equals("not Entered")){
                            holder.enter.setVisibility(View.GONE);
                            holder.notenter.setVisibility(View.VISIBLE);
                        }

                    }

                    @NonNull
                    @Override
                    public KingdomHearts onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                        View v = LayoutInflater.from(viewGroup.getContext()).
                                inflate(R.layout.list_layout, viewGroup, false);
                        return new KingdomHearts(v);
                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
    public static class KingdomHearts extends RecyclerView.ViewHolder {
        TextView ids = itemView.findViewById(R.id.idstud);
        TextView names = itemView.findViewById(R.id.namestud);
        ImageView enter = itemView.findViewById(R.id.enter);
        ImageView notenter = itemView.findViewById(R.id.notenter);


        public KingdomHearts(@NonNull View itemView) {
            super(itemView);


        }
    }
}
