package com.example.arbiterchil.smartap;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class givegroup extends Fragment {


    public givegroup() {
        // Required empty public constructor
    }

    ListView recyclerView;
    FirebaseListAdapter listAdapter;
    DatabaseReference reference;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View getRek = inflater.inflate(R.layout.fragment_givegroup, container, false);

        recyclerView = getRek.findViewById(R.id.recyu);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String RegisteredUserID = currentUser.getUid();

        reference = FirebaseDatabase.getInstance().getReference().child("Groups");

        Query query = FirebaseDatabase.getInstance().getReference().child("Groups")
                .orderByChild("uid")
                .equalTo(RegisteredUserID);
        FirebaseListOptions<Group> options =new FirebaseListOptions.Builder<Group>()
                .setLayout(R.layout.row)
                .setLifecycleOwner(givegroup.this)
                .setQuery(query,Group.class)
                .build();
        listAdapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                TextView Groupname = v.findViewById(R.id.Ties);
                TextView Groupass = v.findViewById(R.id.gg);
                CircleImageView circleImageView = v.findViewById(R.id.hehey);


                Group grp = (Group) model;
                Groupname.setText(grp.getGroupname());
                Groupass.setText(grp.getGroupass());
                Picasso.get().load(grp.getUrl()).into(circleImageView);


            }
        };
        recyclerView.setAdapter(listAdapter);

        recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {

                    Intent gcroom = new Intent(getContext(), giftclass.class);
                    Group g = (Group)parent.getItemAtPosition(position);
                    gcroom.putExtra("groupname",g.getGroupname());
                    gcroom.putExtra("gcid",g.getGcid());
                    startActivity(gcroom);

                } catch (Exception e) {

                } }});

        return  getRek;
    }

    @Override
    public void onStart() {
        super.onStart();
        listAdapter.startListening();

    }

    @Override
    public void onStop() {
        super.onStop();
        listAdapter.stopListening();
    }
}
