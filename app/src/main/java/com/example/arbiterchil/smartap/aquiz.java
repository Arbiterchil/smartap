package com.example.arbiterchil.smartap;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class aquiz extends Fragment {


    View c;
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter;
    private DatabaseReference mref;
    DatabaseReference reference;
    EditText inputgrade;
    Spinner spin,spiname;
    Button SaveGrade;
    TextView dates ;


    public aquiz() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        c =inflater.inflate(R.layout.fragment_aquiz, container, false);

        final String key = getActivity().getIntent().getExtras().get("gcid").toString();
        Toast.makeText(getActivity(), "ID "+key, Toast.LENGTH_SHORT).show();

        reference = FirebaseDatabase.getInstance().getReference().child("Groups");
        dates = c.findViewById(R.id.datetoday);
        SaveGrade = c.findViewById(R.id.saves);
        spin = c.findViewById(R.id.namestud);
        spiname = c.findViewById(R.id.namestudes);
        inputgrade = c.findViewById(R.id.input);

        DateToday();
        Spinthename();
        SaveGradee();
        equva();

        return c;
    }

    private void SaveGradee() {
    SaveGrade.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            final String datetoday = dates.getText().toString();
            final String name = spin.getSelectedItem().toString();
            final String inputgrad = inputgrade.getText().toString();
            final String overall = spiname.getSelectedItem().toString();

            final String key = getActivity().getIntent().getExtras().get("gcid").toString();
            final DatabaseReference reff = FirebaseDatabase.getInstance()
                    .getReference().child("Records");
            reff.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    String keys = reff.push().getKey();
                    Map<String, Object> input = new HashMap<>();

                    input.put("fullname",name);
                    input.put("date",datetoday);
                    input.put("score",inputgrad);
                    input.put("overall",overall);
                    reff.child("QuizRecords").child(key).child(keys).setValue(input);

                    Toast.makeText(getActivity(), "Save Successfully", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });




        }
    });
    }

    private void Spinthename() {

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
    private void DateToday() {

        Calendar calendar = Calendar.getInstance();
        String getCurrrentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        dates.setText(getCurrrentDate);

    }

    private void equva(){

        String[] a = new String[]{
                "5","10","15","20","25","30"
        };

        ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>
                (getActivity(), android.R.layout.simple_spinner_item,
                        a);
        areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiname.setAdapter(areasAdapter);

    }

}
