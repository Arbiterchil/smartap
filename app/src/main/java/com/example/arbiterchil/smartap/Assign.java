package com.example.arbiterchil.smartap;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
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

public class Assign extends AppCompatActivity {

    DatabaseReference ferer;
    DatabaseReference defer;
    DatabaseReference refer;
    Spinner spintheshit;
    TextView uidpor;
    ListView list;
    FirebaseListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign);


        refer = FirebaseDatabase.getInstance().getReference().child("RecitationData");
        ferer = FirebaseDatabase.getInstance().getReference().child("Group");
        defer = FirebaseDatabase.getInstance().getReference().child("GroupMember");
        list = findViewById(R.id.theHood);

        Familia();

    }
    private void Familia() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String RegisteredUserID = currentUser.getUid();
        Query query = FirebaseDatabase.getInstance().getReference().child("Groups")
                .orderByChild("uid")
                .equalTo(RegisteredUserID);
        FirebaseListOptions<Group> options =new FirebaseListOptions.Builder<Group>()
                .setLayout(R.layout.row)
                .setLifecycleOwner(Assign.this)
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
        list.setAdapter(listAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {

//                    String gc =   parent.getItemAtPosition(position).toString();
//                    Intent gcroom = new Intent(getContext(), Poplist.class);
//
//                    gcroom.putExtra("groupname", gc);
//
//                    startActivity(gcroom);
                    Intent gcroom = new Intent(Assign.this, Assign2.class);
                    Group g = (Group)parent.getItemAtPosition(position);
                    gcroom.putExtra("groupname",g.getGroupname());
                    gcroom.putExtra("gcid",g.getGcid());

                    startActivity(gcroom);

                } catch (Exception e) {

                } }});
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
