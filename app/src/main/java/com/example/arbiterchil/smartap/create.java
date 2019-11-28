package com.example.arbiterchil.smartap;


import android.app.Activity;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.nio.file.ProviderMismatchException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class create extends Fragment {

    Button erchil;
    RelativeLayout fragcreate;
    View viewfrags;
    DatabaseReference ref;

    ArrayAdapter<String>arrayAdapter;
    ArrayList<String>arrayList =  new ArrayList<>();

    FirebaseDatabase kolkol;
    FirebaseAuth firebaseAuth;
    FirebaseListAdapter listAdapter;
    String user;
    ListView list;

    public create() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        viewfrags = inflater.inflate(R.layout.fragment_create, container, false);
            erchil = viewfrags.findViewById(R.id.erchil);
//        rear();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String RegisteredUserID = currentUser.getUid();
            list = viewfrags.findViewById(R.id.list);
            kolkol = FirebaseDatabase.getInstance();
            user = FirebaseAuth.getInstance().getCurrentUser().getUid();


            erchil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), Pop.class);
                    startActivity(intent);
                    }});
//        FieldDatabase();
//        DisplayRet();

        Query query = FirebaseDatabase.getInstance().getReference().child("Groups")
                .orderByChild("uid")
                .equalTo(RegisteredUserID);
        FirebaseListOptions<Group> options =new FirebaseListOptions.Builder<Group>()
                .setLayout(R.layout.row)
                .setLifecycleOwner(create.this)
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
                    Intent gcroom = new Intent(getContext(), Poplist.class);
                    Group g = (Group)parent.getItemAtPosition(position);
                    gcroom.putExtra("groupname",g.getGroupname());
                    gcroom.putExtra("gcid",g.getGcid());
                    startActivity(gcroom);

                } catch (Exception e) {

                } }});

//
//        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, final long id) {
//
////                arrayAdapter.notifyDataSetChanged();
//                listAdapter.notifyDataSetChanged();
//                AlertDialog.Builder dial = new AlertDialog.Builder(getContext());
//                dial.setTitle("Alert!");
//                dial.setMessage("Are Your Sure want to Delete this Group?!");
//                dial.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
////                        final String uids = firebaseAuth.getCurrentUser().getUid();
////                        Query query = FirebaseDatabase.getInstance().getReference("SmartDab").child("Groups").
////                                orderByChild("uid").equalTo(uids);
////                    query.getRef().removeValue();
////                deletenode(ref);
//                        ref.removeValue();
//
//                ref.getRoot().child(arrayList.get(position)).removeValue();
//                arrayList.remove(position);
//
////                arrayAdapter.notifyDataSetChanged();
//                        listAdapter.notifyDataSetChanged();
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

        return viewfrags;
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
    //
//    private void deletenode(DatabaseReference ref) {
//
//        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot p : dataSnapshot.getChildren()) {
//                    rear();
//                        String childKey = p.getKey();
//                    Query query = FirebaseDatabase.getInstance().getReference().child("Groups").
//                            orderByChild("uid").equalTo(childKey);
//                    query.getRef().removeValue();
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//    }
//    private void rear() {
//        ref=  FirebaseDatabase.getInstance().getReference("SmartDab").child("Groups");
//
//        }
//
//
//    private void FieldDatabase() {
//
//
//                list = viewfrags.findViewById(R.id.list);
//                arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,arrayList);
//                list.setAdapter(arrayAdapter);
//
//
//
//
//    }
//
//
//
//    private void DisplayRet() {
//
//
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                    Set<String> set = new HashSet<>();
//
//
//
//                    Iterator iterator = dataSnapshot.getChildren().iterator();
//
//                    while (iterator.hasNext()) {
//                            set.add(((DataSnapshot) iterator.next()).getValue(Group.class).getGroupname());
//
//
//                        }
//
//                    arrayList.clear();
//                    arrayList.addAll(set);
//
//                    arrayAdapter.notifyDataSetChanged();
//
//                }
//
//
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//
//
//    }


}
