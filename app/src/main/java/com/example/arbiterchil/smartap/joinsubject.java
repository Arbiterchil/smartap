package com.example.arbiterchil.smartap;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class joinsubject extends Fragment {


    EditText sear;
    ListView listView;
    private View viewfrags;
   private DatabaseReference ref;
    private ArrayAdapter<String>arrayAdapter;
    private ArrayList<String>arrayList =  new ArrayList<>();
    FirebaseDatabase kolkol;
    private FirebaseAuth auth;
    private String current_state,sender;
    private FirebaseUser user;
    FirebaseListAdapter listAdapter;
    DatabaseReference fap;
    RecyclerView recyclerView;
    Button search;
    public joinsubject() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewer = inflater.inflate(R.layout.fragment_joinsubject, container, false);


//        listView = viewer.findViewById(R.id.listdis);
//
//        arrayAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,arrayList);
//        listView.setAdapter(arrayAdapter);
        search = viewer.findViewById(R.id.searching);
        kolkol = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        sear = viewer.findViewById(R.id.sear);
        sender = auth.getCurrentUser().getUid();
        ref=  FirebaseDatabase.getInstance().getReference().child("Groups");
//
//        arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,arrayList);
//        listView.setAdapter(arrayAdapter);


        recyclerView = viewer.findViewById(R.id.recio);


        fap = FirebaseDatabase.getInstance().getReference().child("Groups");
        recyclerView.setLayoutManager( new LinearLayoutManager(getContext()));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(getActivity(),linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

    //        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                try {
//                    Intent gcroom = new Intent(getContext(), Poplist.class);
//                    Group g = (Group)parent.getItemAtPosition(position);
//                    gcroom.putExtra("groupname",g.getGroupname());
//                    gcroom.putExtra("gcid",g.getGcid());
//
//                    startActivity(gcroom);
//
//                } catch (Exception e)
//
//                {
//
//                } }});
//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, final long id) {
//                arrayAdapter.notifyDataSetChanged();
//
//
//                AlertDialog.Builder dial = new AlertDialog.Builder(getContext());
//                dial.setTitle("Send Request to Join subject");
//                dial.setMessage("Please Clarify yout form 1 if you are under this Subject.");
//                dial.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                        RequestJoin();
//
//                    }
//                })
//                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//
//                                dialog.dismiss();
//
//                            }
//                        });
//
//                dial.show();
//                return true;
//            }
//        });







        //        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                Set<String> set = new HashSet<>();
//
//
//                Iterator iterator = dataSnapshot.getChildren().iterator();
//
//                while (iterator.hasNext()) {
//                    set.add(((DataSnapshot) iterator.next()).getValue(Group.class).getGroupname());
//
//                }
//
//                arrayList.clear();
//                arrayList.addAll(set);
//
//                arrayAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                }});
//
//        GodDamnit();
//GodDamnit();

                search.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String search = sear.getText().toString();

                        SpiderMan(search);

                        sear.setText("");

                    }
                });



        return  viewer;
    }
//
//    private void displatrek() {
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                    Set<String> set = new HashSet<>();
//                    Iterator iterator = dataSnapshot.getChildren().iterator();
//                    while (iterator.hasNext()) {
//                            set.add(((DataSnapshot) iterator.next()).getValue(Group.class).getGroupname());
//                        }
//                    arrayList.clear();
//                    arrayList.addAll(set);
//                    arrayAdapter.notifyDataSetChanged();
//                }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });
//    }
//
//    private void GodDamnit() {
//        Query query = FirebaseDatabase.getInstance().getReference("SmartDab").child("Groups");
//        FirebaseListOptions<Group> options =new FirebaseListOptions.Builder<Group>()
//                .setLayout(R.layout.row)
//                .setLifecycleOwner(joinsubject.this)
//                .setQuery(query,Group.class)
//                .build();
//        listAdapter = new FirebaseListAdapter(options) {
//            @Override
//            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
//                TextView Groupname = v.findViewById(R.id.Ties);
//                TextView gcids = v.findViewById(R.id.gg);
//
//                Group grp = (Group) model;
//                Groupname.setText(grp.getGroupname());
//                gcids.setText(grp.getGcid());
//                gcids.setVisibility(View.INVISIBLE);
//
//            }
//        };
//
//        listView.setAdapter(listAdapter);
//
//        sear.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
//    }




    @Override
    public void onStart() {
        super.onStart();
//        listAdapter.startListening();

    }

    private void SpiderMan(String search) {
        Query query = fap.orderByChild("groupname").startAt(search).endAt(search+"\uf8ff");

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Group>()
                .setQuery(query,Group.class)
                .build();
        FirebaseRecyclerAdapter<Group,Myall> adapter = new FirebaseRecyclerAdapter<Group, Myall>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final Myall holder, int position, @NonNull Group model) {

//                holder.fn.setText(model.getGroupname());
//              holder.uidso.setText(model.getGcid());
//
//                holder.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        Intent gcroom = new Intent(getContext(), Poplist.class);
//
//                        startActivity(gcroom);
//                    }
//                });
                holder.gago.setText(model.getGroupname());
                holder.pisti.setText(model.getGcid());
                Picasso.get().load(model.getUrl()).into(holder.circleImageView);
                final String uid = model.getGcid();
                final String gpname = model.getGroupname();
                final String teachuid = model.getUid();

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent gcroom = new Intent(getContext(), StudeAtten.class);

                        gcroom.putExtra("groupname", gpname);
                        gcroom.putExtra("gcid", uid);
                        gcroom.putExtra("ted",teachuid);

                        startActivity(gcroom);
                    }
                });
            }
            @NonNull
            @Override
            public Myall onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                View vop = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row,viewGroup,false);
                return new Myall(vop);
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }

    public static class Myall extends RecyclerView.ViewHolder{

       TextView gago;
       TextView pisti;
       CircleImageView circleImageView;
        public Myall(@NonNull View itemView) {
            super(itemView);

            gago = itemView.findViewById(R.id.Ties);
            pisti = itemView.findViewById(R.id.gg);
            circleImageView = itemView.findViewById(R.id.hehey);

        }
    }

    @Override
    public void onStop() {
        super.onStop();
//        listAdapter.stopListening();
    }



}
