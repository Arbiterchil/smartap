package com.example.arbiterchil.smartap;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class records extends Fragment {

    public records(){

    }

    Button Recite,Quiz,Assign,Aciti,Exams,ComLab,comrec;

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.records, container, false);

        Assign = view.findViewById(R.id.assi);

        comrec = view.findViewById(R.id.Compute);

        comrec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),Compute.class));
            }
        });
        Assign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),Assign.class));
            }
        });


        return view;
    }
}
