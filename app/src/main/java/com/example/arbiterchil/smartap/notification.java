package com.example.arbiterchil.smartap;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class notification extends Fragment {

    View v;
    RecyclerView recyclerView;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    List<notif> list;
    TextView textView,getTextView;
    FirebaseRecyclerAdapter adapter;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference mDatabase;
    public notification() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_notification, container, false);

        recyclerView = v.findViewById(R.id.betternow);
        firebaseAuth =FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        textView = v.findViewById(R.id.tong);
//        textView.setVisibility(View.GONE);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(getActivity(),linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adapter);
        list = new ArrayList<>();




        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        final String RegisteredUserID = currentUser.getUid();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        mDatabase = FirebaseDatabase.getInstance().
                getReference().child("User").
                child(firebaseAuth.getCurrentUser().getUid());

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String keyran = dataSnapshot.getKey();

                try {
                    smartdab smartdab = dataSnapshot.getValue(smartdab.class);
                    textView.setText(smartdab.getFullname());
                }catch(Exception e){
                    Log.d("dfrag", e.getMessage());
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        textView.setVisibility(View.GONE);

            Post();




    return v;
    }


    private void Post() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        final String RegisteredUserID = currentUser.getUid();
        final String name = textView.getText().toString();

        final Query querys = FirebaseDatabase.getInstance().getReference().child("Notifications")
                .child(RegisteredUserID).orderByChild("uid").equalTo(RegisteredUserID);
        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<notif>()
                .setQuery(querys,notif.class).build();

        final FirebaseRecyclerAdapter<notif,tv> adapter = new FirebaseRecyclerAdapter<notif, tv>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final tv holder, final int position, @NonNull notif model) {

              holder.mind.setText(model.getNote());



            }

            @NonNull
            @Override
            public tv onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notifrow,viewGroup,false);
                return new tv(v);
            }

        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                Toast.makeText(getActivity(), "Swipe to Delete Left or Right", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                int position = viewHolder.getAdapterPosition();
                adapter.getRef(position).removeValue();

            }
        }).attachToRecyclerView(recyclerView);


    }
    public static class tv extends RecyclerView.ViewHolder {

        TextView mind = itemView.findViewById(R.id.notifword);

        public tv(@NonNull View itemView) {
            super(itemView);
        }
    }

}
