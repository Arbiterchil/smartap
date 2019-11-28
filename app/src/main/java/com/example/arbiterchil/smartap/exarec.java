package com.example.arbiterchil.smartap;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

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
public class exarec extends Fragment {

    View view;
    DatabaseReference reference;
    Button save,views;
    Spinner type,selectgrad,selname;
    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> arrayList =  new ArrayList<>();

    public exarec() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_exarec,container,false);

        save = view.findViewById(R.id.gradcom);
        selectgrad = view.findViewById(R.id.gradingsel);
        selname = view.findViewById(R.id.selsname);
        listView = view.findViewById(R.id.listgrad);
        views = view.findViewById(R.id.viewss);
        arrayAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);
        SpinGrad();
        SpinName();
        Viewgrad();
        return view;
    }
    private void Viewgrad() {
        final String key = getActivity().getIntent().getExtras().get("gcid").toString();

       views.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String name = selname.getSelectedItem().toString();
               Query query = FirebaseDatabase.getInstance().getReference()
                       .child("Records").child("ExamRecords").child(key).orderByChild("fullname").equalTo(name);

               query.addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       arrayList.clear();
                       for (DataSnapshot ds : dataSnapshot.getChildren()){

                           final String types = ds.child("type").getValue(String.class);
                           final String seldara = ds.child("status").getValue(String.class);
                           final String sc = ds.child("score").getValue(String.class);



                           arrayList.add(types+","+seldara+",       "+sc);
                           arrayAdapter.notifyDataSetChanged();
                       }




//                            type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                                @Override
//                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//                                    Query query1 = FirebaseDatabase.getInstance().getReference("SmartDab")
//                                            .child("Records").child("ExamRecords").child(key)
//                                            .orderByChild(types).equalTo(typeless);
//
//                                    query1.addListenerForSingleValueEvent(new ValueEventListener() {
//                                        @Override
//                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                            for (DataSnapshot fuck : dataSnapshot.getChildren()){
//
//                                                final String grading = fuck.child("status").getValue(String.class);
//
//                                                final String selgrad = selectgrad.getSelectedItem().toString();
//
//                                                selectgrad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                                                    @Override
//                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                                                        Query query2 = FirebaseDatabase.getInstance().getReference("SmartDab")
//                                                                .child("Records").child("ExamRecords").
//                                                                        child(key).orderByChild(grading).equalTo(selgrad);
//                                                        query2.addListenerForSingleValueEvent(new ValueEventListener() {
//                                                            @Override
//                                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                                                                for (DataSnapshot hime: dataSnapshot.getChildren()){
//
//                                                                    String sco = hime.child("score").getValue(String.class);
//                                                                    arrayList.add(sco);
//                                                                    arrayAdapter.notifyDataSetChanged();
//                                                                    Toast.makeText(getActivity(), "Score is "
//                                                                            +sco, Toast.LENGTH_SHORT).show();
//                                                                }
//
//                                                            }
//
//                                                            @Override
//                                                            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                                                            }
//                                                        });
//
//                                                    }
//
//                                                    @Override
//                                                    public void onNothingSelected(AdapterView<?> parent) {
//
//                                                    }
//                                                });
//
//                                            }
//
//                                        }
//
//                                        @Override
//                                        public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                                        }
//                                    });
//
//                                }
//
//                                @Override
//                                public void onNothingSelected(AdapterView<?> parent) {
//
//                                }
//                            });
//                            DatabaseReference databaseReference = FirebaseDatabase.getInstance()
//                                    .getReference("SmartDab").child("Records").child("ExamRecords").child(key);
//
//                            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//                                @Override
//                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                    for (DataSnapshot kmb : dataSnapshot.getChildren()){
//                                        String dsd = kmb.child("type").getValue(String.class);
//
//                                        switch (dsd){
//                                            case "Laboratory Exam":
//
//
//                                                break;
//
//
//
//                                        }
//
//                                    }
//
//                                }
//
//                                @Override
//                                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                                }
//                            });


                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError databaseError) {

                   }
               });

           }
       });



    }


    private void SpinName() {
        final String key = getActivity().getIntent().getExtras().get("gcid").toString();
        reference =
                FirebaseDatabase.getInstance().getReference().child("Groups");
        reference.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String data = dataSnapshot.child("Member").getKey();

                DatabaseReference refer = FirebaseDatabase.getInstance().getReference().child("Groups")
                        .child(key).child(data);
                refer.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        List<String > are = new ArrayList<>();
                        for (DataSnapshot ds: dataSnapshot.getChildren()){
                            String Fullname = ds.child("fullname").getValue(String.class);

                            are.add(Fullname);
                            Collections.sort(are);
                            ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>
                                    (getActivity(), android.R.layout.simple_spinner_item,
                                            are);
                            areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            selname.setAdapter(areasAdapter);
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



    private void SpinGrad() {

        String[] a = new String[]{
                "1st Grading","2nd Grading","3rd Grading","4th Grading"
        };

        ArrayAdapter<String> areasAdapter = new ArrayAdapter<>
                (getActivity(), android.R.layout.simple_spinner_item,
                        a);
        areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectgrad.setAdapter(areasAdapter);

    }

}
