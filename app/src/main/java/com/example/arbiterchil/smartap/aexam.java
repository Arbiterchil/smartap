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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
public class aexam extends Fragment {


    View b;
    TextView datetoday;
    Spinner spintype,spinname,spingrad;
    Button savingu;
    EditText inputescore;
    DatabaseReference reference;
    public aexam() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        b = inflater.inflate(R.layout.fragment_aexam, container, false);

        datetoday = b.findViewById(R.id.datenow);
        spinname = b.findViewById(R.id.spinname);
        spingrad = b.findViewById(R.id.gradin);
        savingu = b.findViewById(R.id.saveexam);
        inputescore = b.findViewById(R.id.inputexam);
        spintype = b.findViewById(R.id.namestudes);

        DateNow();
        SpinGrad();
        SpinTheNameBitch();
        SaveThatDog();

        return b;
    }

    private void SaveThatDog() {

        final DatabaseReference reff = FirebaseDatabase.getInstance().getReference().child("Records");
        savingu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String key = getActivity().getIntent().getExtras().get("gcid").toString();

                String keys = reff.push().getKey();
                Map<String, Object> input = new HashMap<>();
                String wir = "Written Exam";
                input.put("fullname",spinname.getSelectedItem().toString());
                input.put("date",datetoday.getText().toString());
                input.put("score",inputescore.getText().toString());
                input.put("status",spingrad.getSelectedItem().toString());
                input.put("type",wir);
                input.put("overall",spintype.getSelectedItem().toString());
                reff.child("ExamRecords").child(key).child(keys).setValue(input);
                Toast.makeText(getActivity(), "Save Successfulyy", Toast.LENGTH_SHORT).show();

                inputescore.setText("");

            }
        });

        quva();

    }

    private void quva() {
        String[] a = new String[]{
                "5","10","15","20","25","30","35","40","45","50","55","60","65","70","75","80","85","90","95","100"
        };

        ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>
                (getActivity(), android.R.layout.simple_spinner_item,
                        a);
        areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spintype.setAdapter(areasAdapter);

    }

    private void SpinTheNameBitch() {
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
                        spinname.setAdapter(areasAdapter);
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
    private void SpinGrad()
    {
        String[] a = new String[]{"1st Grading","2nd Grading","3rd Grading","4th Grading","5th Grading",
                "6th Grading","7th Grading","8th Grading"};
        ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>
                (getActivity(), android.R.layout.simple_spinner_item,a);
        areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spingrad.setAdapter(areasAdapter);

    }
    private void DateNow()
    {
        Calendar calendar = Calendar.getInstance();
        String GetCurrentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        datetoday.setText(GetCurrentDate);
    }

}
