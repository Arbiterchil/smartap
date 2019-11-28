package com.example.arbiterchil.smartap;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class actrec extends Fragment {

View view;
    TextView vilat;
    Spinner spin,spins;
    DatabaseReference data;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> arrayList =  new ArrayList<>();
    ListView listView;
    public actrec() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_actrec,container,false);
        vilat = view.findViewById(R.id.strix);


        final String key = getActivity().getIntent().getExtras().get("gcid").toString();
        arrayAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,arrayList);

        listView = view.findViewById(R.id.listograph);
        listView.setAdapter(arrayAdapter);
        spin = view.findViewById(R.id.namaeh);
        spinThename();

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                final String key = getActivity().getIntent().getExtras().get("gcid").toString();
                String name = spin.getSelectedItem().toString();
                Query query = FirebaseDatabase.getInstance().getReference()
                        .child("Records").child("ActivityRecords").child(key);
                query.orderByChild("fullname").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        arrayList.clear();
                        for (DataSnapshot ds: dataSnapshot.getChildren()){
                            String scars = ds.child("score").getValue(String.class);
                            arrayList.add(scars);
                            arrayAdapter.notifyDataSetChanged();

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
    }
    private void spinThename() {


        final String key = getActivity().getIntent().getExtras().get("gcid").toString();
        final DatabaseReference refer = FirebaseDatabase.getInstance().getReference().child("Groups");
        refer.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final String data = dataSnapshot.child("Member").getKey();

                DatabaseReference ferer = FirebaseDatabase.getInstance().
                        getReference().child("Groups").child(key).child(data);
                ferer.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        List<String > are = new ArrayList<>();
                        for (DataSnapshot ds: dataSnapshot.getChildren()) {
                            String Fullname = ds.child("fullname").getValue(String.class);

                            are.add(Fullname);
                            Collections.sort(are);
                            ArrayAdapter<String> areasAdapter = new ArrayAdapter<>
                                    (getActivity(), android.R.layout.simple_spinner_item,
                                            are);
                            areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spin.setAdapter(areasAdapter);
                        }

                    }



                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
