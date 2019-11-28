package com.example.arbiterchil.smartap;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class attenAbs extends Fragment {

    View s;
    EditText sear;
    DatabaseReference fap;
    RecyclerView recyclerView;
    Button search;
    FirebaseDatabase kolkol;
    List<getAb> list;

    public attenAbs() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        s = inflater.inflate(R.layout.fragment_atten_abs, container, false);

        sear = s.findViewById(R.id.sear);
        search = s.findViewById(R.id.searching);
        recyclerView = s.findViewById(R.id.recio);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        fap = FirebaseDatabase.getInstance().getReference().child("Groups");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(getActivity(), linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        list = new ArrayList<>();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String seacrh = sear.getText().toString();

                sea(seacrh);

            }
        });

        fap = FirebaseDatabase.getInstance().getReference().child("Groups");

        return s;

    }

    private void sea(final String seacrh) {
        final String key = getActivity().getIntent().getExtras().get("gcid").toString();

        final DatabaseReference fear = fap.child(key);
        fear.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String hahawe = dataSnapshot.child("Records").getKey();

                    DatabaseReference me = fear.child(hahawe).child("AbsentRecord");

                    Query query = me.orderByChild("fullname").startAt(seacrh).endAt(seacrh+"\uf8ff");

                FirebaseRecyclerOptions<getAb> options = new FirebaseRecyclerOptions.Builder<getAb>()
                        .setQuery(query,getAb.class)
                        .build();
                FirebaseRecyclerAdapter<getAb , Kingdom> adapter  = new FirebaseRecyclerAdapter<getAb, Kingdom>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull Kingdom holder, int position, @NonNull getAb model) {

                        holder.fn.setText(model.getFullname());
                        holder.sta.setText(model.getDate());
                        holder.uid.setText(model.getAbsenttimes());


                    }

                    @NonNull
                    @Override
                    public Kingdom onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                        View viewers = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.abslats,viewGroup,false);
                        return new Kingdom(viewers);

                    }
                } ;

                recyclerView.setAdapter(adapter);
                adapter.startListening();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public static class Kingdom extends RecyclerView.ViewHolder{

        TextView fn = itemView.findViewById(R.id.fullname);
        TextView sta = itemView.findViewById(R.id.Statsu);
        TextView uid = itemView.findViewById(R.id.Uid);
        public Kingdom(@NonNull View itemView) {
            super(itemView);
        }
    }

}