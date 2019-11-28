package com.example.arbiterchil.smartap;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class posting extends Fragment {

    EditText sear;
    ListView listView;
    private View viewfrags;
    private DatabaseReference ref;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> arrayList =  new ArrayList<>();
    FirebaseDatabase kolkol;
    private FirebaseAuth auth;
    private String current_state,sender;
    private FirebaseUser user;
    FirebaseListAdapter listAdapter;
    DatabaseReference fap;
    RecyclerView recyclerView;
    Button search;
    View view;

    public posting() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=  inflater.inflate(R.layout.fragment_posting, container, false);


        search = view.findViewById(R.id.searching);
        kolkol = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        sear = view.findViewById(R.id.sear);
        sender = auth.getCurrentUser().getUid();
        ref=  FirebaseDatabase.getInstance().getReference().child("Groups");

        recyclerView = view.findViewById(R.id.recio);


        fap = FirebaseDatabase.getInstance().getReference().child("Groups");
        recyclerView.setLayoutManager( new LinearLayoutManager(getContext()));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(getActivity(),linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String search = sear.getText().toString();

                SpiderMan(search);

                sear.setText("");

            }
        });
    return view;
    }

    private void SpiderMan(String search) {
        Query query = fap.orderByChild("groupname").startAt(search).endAt(search+"\uf8ff");

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Group>()
                .setQuery(query,Group.class)
                .build();
        FirebaseRecyclerAdapter<Group,joinsubject.Myall> adapter = new FirebaseRecyclerAdapter<Group, joinsubject.Myall>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final joinsubject.Myall holder, int position, @NonNull Group model) {

                holder.gago.setText(model.getGroupname());
                holder.pisti.setText(model.getGcid());

                final String uid = model.getGcid();
                final String gpname = model.getGroupname();
                Picasso.get().load(model.getUrl()).into(holder.circleImageView);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent gcroom = new Intent(getActivity(), joinreq.class);

                        gcroom.putExtra("groupname", gpname);
                        gcroom.putExtra("gcid", uid);

                        startActivity(gcroom);

                    }
                });
            }
            @NonNull
            @Override
            public joinsubject.Myall onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                View vop = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row,viewGroup,false);
                return new joinsubject.Myall(vop);
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }

    public static class Myall extends RecyclerView.ViewHolder{
        CircleImageView circleImageView;
        TextView gago;
        TextView pisti;
        public Myall(@NonNull View itemView) {
            super(itemView);

            gago = itemView.findViewById(R.id.Ties);
            pisti = itemView.findViewById(R.id.gg);

            circleImageView = itemView.findViewById(R.id.hehey);
        }
    }
}
