package com.example.arbiterchil.smartap;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class quizrec extends Fragment {

View view;
TextView vilat,urls;
Spinner spin,spins,spints;
    CardView cardView;
    DatabaseReference data;
    ArrayAdapter<String>arrayAdapter;
    ArrayAdapter<String>arrayAdapterover;
    ArrayAdapter<String>arrayAdapter1;
    ArrayAdapter<String>arrayAdapterovers;
    ArrayAdapter<String>arrayAdapter2;
    ArrayAdapter<String>arrayAdapteroverso;
    ArrayAdapter<String>arrayAdapter3;
    ArrayAdapter<String>arrayAdapteroversos;
    ArrayList<String>arrayListoversos=new ArrayList<>();
    ArrayList<String>arrayList3=new ArrayList<>();
    ArrayList<String>arrayListoverso = new ArrayList<>();
    ArrayList<String>arrayList2=new ArrayList<>();
    ArrayList<String>arrayListovers = new ArrayList<>();
    ArrayList<String>arrayList1 = new ArrayList<>();
    ArrayList<String>arrayListover = new ArrayList<>();
    ArrayList<String>arrayList =  new ArrayList<>();
    ListView listView,listView1,listView2,listView3;
    ListView overlist,overlists,overlistso,overlistsos;
    ListView lav,lavs,lavo,lavso;
    ArrayList<String>arrayList0 =new ArrayList<>();
    ArrayAdapter<String>arrayAdapter0;
    ArrayList<String>arrayList00=new ArrayList<>();
    ArrayAdapter<String>arrayAdapter00;
    ArrayList<String>arrayList01 =new ArrayList<>();
    ArrayAdapter<String>arrayAdapter01;
    ArrayList<String>arrayList001=new ArrayList<>();
    ArrayAdapter<String>arrayAdapter001;
    Button comp,sav,saveInstance;
    DBlite lite;
    EditText eq,eqs,ase,awe,recit,recito,acp,acp1;
    EditText f1,f1m,s2,s2m,t3,t3m,f4,f4m,f5n,f5m,f6,f6m,f7,f7m,f8,f8m;
    EditText l1,l1m,l2,l2m,l3,l3m,l4,l4m,lf,lfm,lf1,lfm1;
    TextView totaly,totaleq;
    ProgressDialog prog;
    public quizrec() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_quizrec,container,false);
        prog = new ProgressDialog(getActivity());
        totaly = view.findViewById(R.id.totalall);
        totaleq = view.findViewById(R.id.totaleqa);
        sav = view.findViewById(R.id.sav);
        lite = new DBlite(getActivity());
        f1 = view.findViewById(R.id.f1);
        f1m = view.findViewById(R.id.f1m);
        s2 = view.findViewById(R.id.s2);
        s2m = view.findViewById(R.id.s2m);
        t3 = view.findViewById(R.id.t3);
        t3m = view.findViewById(R.id.t3m);
        f4 = view.findViewById(R.id.f4);
        f4m = view.findViewById(R.id.f4m);
        cardView = view.findViewById(R.id.cardbay);
        f1.setEnabled(false);
        f1m.setEnabled(false);
        s2.setEnabled(false);
        s2m.setEnabled(false);
        t3.setEnabled(false);
        t3m.setEnabled(false);
        f4.setEnabled(false);
        f4m.setEnabled(false);
        f5n = view.findViewById(R.id.f5n);
        f5m = view.findViewById(R.id.f5m);
        f6 = view.findViewById(R.id.f6);
        f6m = view.findViewById(R.id.f6m);
        f7 = view.findViewById(R.id.f7);
        f7m = view.findViewById(R.id.f7m);
        f8 = view.findViewById(R.id.f8);
        f8m = view.findViewById(R.id.f8m);
        f5n.setEnabled(false);
        f5m.setEnabled(false);
        f6.setEnabled(false);
        f6m.setEnabled(false);
        f7.setEnabled(false);
        f7m.setEnabled(false);
        f8.setEnabled(false);
        f8m.setEnabled(false);


        urls = view.findViewById(R.id.urls);


        l1 = view.findViewById(R.id.l1);
        l1m = view.findViewById(R.id.l1m);
        l2 = view.findViewById(R.id.l2);
        l2m = view.findViewById(R.id.l2m);
        l3 = view.findViewById(R.id.l3);
        l3m = view.findViewById(R.id.l3m);
        lf = view.findViewById(R.id.lf);
        lfm = view.findViewById(R.id.lfm);
        lf1 = view.findViewById(R.id.lf1);
        lfm1 = view.findViewById(R.id.lfm1);
        l1.setEnabled(false);
        l1m.setEnabled(false);
        l2.setEnabled(false);
        l2m.setEnabled(false);
        l3.setEnabled(false);
        l3m.setEnabled(false);
        lf.setEnabled(false);
        lfm.setEnabled(false);
        lf1.setEnabled(false);
        lfm1.setEnabled(false);
        l4 = view.findViewById(R.id.l4);
        l4m  = view.findViewById(R.id.l4m);

        cardView.setVisibility(View.GONE);
        spints = view.findViewById(R.id.namaehwa);
        vilat = view.findViewById(R.id.strix);
        eq = view.findViewById(R.id.eqa);
        eqs = view.findViewById(R.id.eqaw);
        ase = view.findViewById(R.id.ase);
        awe = view.findViewById(R.id.asew);
        recit = view.findViewById(R.id.recit);
        recito = view.findViewById(R.id.recito);
        acp = view.findViewById(R.id.acp);
        acp1 = view.findViewById(R.id.acp1);
        acp.setEnabled(false);
        acp1.setEnabled(false);
        recit.setEnabled(false);
        recito.setEnabled(false);
        ase.setEnabled(false);
        awe.setEnabled(false);
        eq.setEnabled(false);
        eqs.setEnabled(false);

        saveInstance = view.findViewById(R.id.saveinstance);


        final String key = getActivity().getIntent().getExtras().get("gcid").toString();

        arrayAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,arrayList);
        listView = view.findViewById(R.id.listograph);
        listView.setAdapter(arrayAdapter);

        arrayAdapter1 = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,arrayList1);
        listView1 = view.findViewById(R.id.listographo);
        listView1.setAdapter(arrayAdapter1);

        arrayAdapter2 = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,arrayList2);
        listView2 = view.findViewById(R.id.listographos);
        listView2.setAdapter(arrayAdapter2);

        arrayAdapter3 = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,arrayList3);
        listView3 = view.findViewById(R.id.listographose);
        listView3.setAdapter(arrayAdapter3);

        arrayAdapterover = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,arrayListover);
        overlist = view.findViewById(R.id.over);
        overlist.setAdapter(arrayAdapterover);


        arrayAdapterovers = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,arrayListovers);
        overlists = view.findViewById(R.id.overs);
        overlists.setAdapter(arrayAdapterovers);

        arrayAdapteroverso = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,arrayListoverso);
        overlistso = view.findViewById(R.id.overso);
        overlistso.setAdapter(arrayAdapteroverso);

        arrayAdapteroversos = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,arrayListoversos);
        overlistsos = view.findViewById(R.id.oversos);
        overlistsos.setAdapter(arrayAdapteroversos);

        arrayAdapter0 = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,arrayList0);
        lav = view.findViewById(R.id.lav);
        lav.setAdapter(arrayAdapter0);

        arrayAdapter00 = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,arrayList00);
        lavs = view.findViewById(R.id.lavs);
        lavs.setAdapter(arrayAdapter00);

        arrayAdapter01 = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,arrayList01);
        lavo = view.findViewById(R.id.lavo);
        lavo.setAdapter(arrayAdapter01);

        arrayAdapter001 = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,arrayList001);
        lavso = view.findViewById(R.id.lavso);
        lavso.setAdapter(arrayAdapter001);

        spin = view.findViewById(R.id.namaeh);
        comp = view.findViewById(R.id.compute);
        spinThename();

        comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardView.setVisibility(View.INVISIBLE);
             if (spints.getSelectedItem().equals("Term")){

                 QuizCompute();
                 AssignCompute();
                 RecitCompute();
                 AcpCompyte();
                 ComCom();
                 FirstPick();
                 LabPick();
                 ComQCom();
                 f5n.setVisibility(View.GONE);
                 f5m.setVisibility(View.GONE);
                 f6.setVisibility(View.GONE);
                 f6m.setVisibility(View.GONE);
                 f7.setVisibility(View.GONE);
                 f7m.setVisibility(View.GONE);
                 f8.setVisibility(View.GONE);
                 f8m.setVisibility(View.GONE);
                 l4.setVisibility(View.GONE);
                 l4m.setVisibility(View.GONE);
             }else if(spints.getSelectedItem().equals("Semestral")){
                 l4.setVisibility(View.VISIBLE);
                 l4m.setVisibility(View.VISIBLE);
                 f5n.setVisibility(View.VISIBLE);
                 f5m.setVisibility(View.VISIBLE);
                 f6.setVisibility(View.VISIBLE);
                 f6m.setVisibility(View.VISIBLE);
                 f7.setVisibility(View.VISIBLE);
                 f7m.setVisibility(View.VISIBLE);
                 f8.setVisibility(View.VISIBLE);
                 f8m.setVisibility(View.VISIBLE);
                 QuizAllEx();
                    ExLabPick();
                    comAssignCompute();
                    CpmRecitCompute();
                    Acc();
                 alLabExx();
                 ComrecLa();
                 windblowS();
                }



            }});

        quva();

        sav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spints.getSelectedItem().equals("Term")){
                    resulta();
                    cardView.setVisibility(View.VISIBLE);
                }
                else if(spints.getSelectedItem().equals("Semestral")){

                    resoltu();
                    cardView.setVisibility(View.VISIBLE);
                }



            }
        });
        saveInstance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prog.setTitle("Please Wait.......");
                prog.setMessage("Saving Remark of the Student.");
                prog.setCanceledOnTouchOutside(false);
                prog.show();
                final String type = spints.getSelectedItem().toString();
                final String name = spin.getSelectedItem().toString();
                final String total = totaly.getText().toString();
                final String remark = totaleq.getText().toString();
                final String url = urls.getText().toString();
                final String key = getActivity().getIntent().getExtras().get("gcid").toString();
                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Map<String, Object> rem = new HashMap<>();
                        rem.put("fullname", name);
                        rem.put("type", type);
                        rem.put("total", total);
                        rem.put("remark", remark);
                        rem.put("url",url);
                        reference.child("PostRecord").child(key).child(reference.push().getKey()).setValue(rem);
                        prog.dismiss();
                        Toast.makeText(getActivity(), "Successfully Post and Save", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }


        });


       spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               String selectedItem = spin.getSelectedItem().toString();
               Query query = FirebaseDatabase.getInstance().getReference().child("User")
                       .orderByChild("fullname").equalTo(selectedItem);

               query.addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                       for (DataSnapshot  sd : dataSnapshot.getChildren()){
                           String url = sd.child("url").getValue(String.class);
                           urls.setText(url);
                           urls.setVisibility(View.GONE);

                           if (spints.getSelectedItem().equals("Term")){
                               QuizPick();
                               AssignPick();
                               RecitoPick();
                               AcpPick();
                               Comlabpick();
                               ComQuiz();
                           }else if(spints.getSelectedItem().equals("Semestral")){
                               Quizlab();
                               windBlow();
                               AssinLab();
                               RecitoLab();
                               AcpLab();
                               Comlabrec();
                               QuizLabCom();
                           }
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



    private void resoltu() {

        String quiz = eqs.getText().toString();
        String assign = awe.getText().toString();
        String recit = recito.getText().toString();
        String acp = acp1.getText().toString();

        double quize = Double.parseDouble(quiz);
        double assigne = Double.parseDouble(assign);
        double recite = Double.parseDouble(recit);
        double alle = Double.parseDouble(acp);

        double wew = quize + assigne + recite + alle;

        String f1 = f1m.getText().toString();
        String f2 = s2m.getText().toString();
        String f3 = t3m.getText().toString();
        String f4 = f4m.getText().toString();
        String f5 = f5m.getText().toString();
        String f6 = f6m.getText().toString();
        String f7 = f7m.getText().toString();
        String f8 = f8m.getText().toString();
        double dof = Double.parseDouble(f1);
        double fl = Double.parseDouble(f2);
        double mi = Double.parseDouble(f3);
        double ng = Double.parseDouble(f4);
        double go = Double.parseDouble(f5);
        double pi = Double.parseDouble(f6);
        double ra = Double.parseDouble(f7);
        double te = Double.parseDouble(f8);

        double weo =
                dof + fl + mi + ng + go + pi + ra + te;


        String l1 = l1m.getText().toString();
        String l2 = l2m.getText().toString();
        String l3 = l3m.getText().toString();
        String l4 = l4m.getText().toString();
        String l5 = lfm.getText().toString();
        String l6 = lfm1.getText().toString();

        double q = Double.parseDouble(l1);
        double qw = Double.parseDouble(l2);
        double qwe = Double.parseDouble(l3);
        double qwer = Double.parseDouble(l4);
        double qwert = Double.parseDouble(l5);
        double qwerty = Double.parseDouble(l6);

        double resultlab = q + qw + qwe + qwer + qwert + qwerty;
        double dividdis = resultlab / 4;
        double last = dividdis / 100.0f * 60;

        double result1 = wew + weo;
        double remik = result1 / 100.0f * 40;

        double wtfresult = last + remik;
        int ciel = (int) Math.ceil(wtfresult);
        totaly.setText(String.valueOf(ciel));
        String totalresult  = totaly.getText().toString();
        switch (totalresult) {
            case "75":
                totaleq.setText("3.5");
                break;
            case "76":
                totaleq.setText("3.4");
                break;
            case "77":
                totaleq.setText("3.3");
                break;
            case "78":
                totaleq.setText("3.2");
                break;
            case "79":
                totaleq.setText("3.1");
                break;
            case "80":
                totaleq.setText("3.0");
                break;
            case "81":
                totaleq.setText("2.1");
                break;
            case "82":
                totaleq.setText("2.2");
                break;
            case "83":
                totaleq.setText("2.3");
                break;
            case "84":
                totaleq.setText("2.4");
                break;
            case "85":
                totaleq.setText("2.5");
                break;
            case "86":
                totaleq.setText("2.6");
                break;
            case "87":
                totaleq.setText("2.7");
                break;
            case "88":
                totaleq.setText("2.8");
                break;
            case "89":
                totaleq.setText("2.9");
                break;
            case "90":
                totaleq.setText("2.0");
                break;
            case "91":
                totaleq.setText("1.1");
                break;
            case "92":
                totaleq.setText("1.2");
                break;
            case "93":
                totaleq.setText("1.3");
                break;
            case "94":
                totaleq.setText("1.4");
                break;
            case "95":
                totaleq.setText("1.5");
                break;
            case "96":
                totaleq.setText("1.6");
                break;
            case "97":
                totaleq.setText("1.7");
                break;
            case "98":
                totaleq.setText("1.8");
                break;
            case "99":
                totaleq.setText("1.9");
                break;
            case "60":
            case "61":
            case "62":
            case "63":
            case "64":
            case "65":
            case "66":
            case "67":
            case "68":
            case "69":
            case "70":
            case "71":
            case "72":
            case "73":
            case "74":
                totaleq.setText("5.0");
                break;
        }
    }

    private void resulta() {

        String quiz = eqs.getText().toString();
        String assign = awe.getText().toString();
        String recit = recito.getText().toString();
        String acp = acp1.getText().toString();

        double quize = Double.parseDouble(quiz);
        double assigne = Double.parseDouble(assign);
        double recite = Double.parseDouble(recit);
        double alle = Double.parseDouble(acp);

        double wew = quize+assigne+recite+alle;
        float rest = (float) (wew/100.0f*40);

        String f1 = f1m.getText().toString();
        String f2 = s2m.getText().toString();
        String f3 = t3m.getText().toString();
        String f4 = f4m.getText().toString();
//
        double f1m = Double.parseDouble(f1);
        double f1mn = Double.parseDouble(f2);
        double f1mni = Double.parseDouble(f3);
        double f1mnio = Double.parseDouble(f4);

        double wews= f1m+f1mn+f1mni+f1mnio;
        float rests = (float) wews / 100.0f*60;
//

        String l1 = l1m.getText().toString();
        String l2 = l2m.getText().toString();
        String l3 = l3m.getText().toString();
        String l4 = lfm.getText().toString();
        String l5 = lfm1.getText().toString();

        double q = Double.parseDouble(l1);
        double qw = Double.parseDouble(l2);
        double qwe = Double.parseDouble(l3);
        double qwer = Double.parseDouble(l4);
        double qwert = Double.parseDouble(l5);
//
        double wewso = q+qw+qwe+qwer+qwert;
        float restso = (float) wewso/100.0f*60;
//
//
        double addboth = rest+rests/100.0f*40;
        double addall = addboth + restso;
        int ciel = (int) Math.ceil(addall);
        totaly.setText(String.valueOf(ciel));
        String totalresult  = totaly.getText().toString();
        switch (totalresult){
            case "75":
                totaleq.setText("3.5");
                break;
            case "76":
                totaleq.setText("3.4");
                break;
            case "77":
                totaleq.setText("3.3");
                break;
            case "78":
                totaleq.setText("3.2");
                break;
            case "79":
                totaleq.setText("3.1");
                break;
            case "80":
                totaleq.setText("3.0");
                break;
            case "81":
                totaleq.setText("2.1");
                break;
            case "82":
                totaleq.setText("2.2");
                break;
            case "83":
                totaleq.setText("2.3");
                break;
            case "84":
                totaleq.setText("2.4");
                break;
            case "85":
                totaleq.setText("2.5");
                break;
            case "86":
                totaleq.setText("2.6");
                break;
            case "87":
                totaleq.setText("2.7");
                break;
            case "88":
                totaleq.setText("2.8");
                break;
            case "89":
                totaleq.setText("2.9");
                break;
            case "90":
                totaleq.setText("2.0");
                break;
            case "91":
                totaleq.setText("1.1");
                break;
            case "92":
                totaleq.setText("1.2");
                break;
            case "93":
                totaleq.setText("1.3");
                break;
            case "94":
                totaleq.setText("1.4");
                break;
            case "95":
                totaleq.setText("1.5");
                break;
            case "96":
                totaleq.setText("1.6");
                break;
            case "97":
                totaleq.setText("1.7");
                break;
            case "98":
                totaleq.setText("1.8");
                break;
            case "99":
                totaleq.setText("1.9");
                break;
            case "60":
            case "61":
            case "62":
            case "63":
            case "64":
            case "65":
            case "66":
            case "67":
            case "68":
            case "69":
            case "70":
            case "71":
            case "72":
            case "73":
            case "74":
                totaleq.setText("5.0");
                break;
        }
    }


    private void windblowS() {

        double sum =0;
        double sumi = 0;
        for (int i = 0 ; i <arrayList01.size(); i++) {

            for (int h= 0; h<arrayList001.size();h++) {
                String s = arrayList01.get(i);
                String w = arrayList001.get(h);
                int rd = Integer.parseInt(String.valueOf(w));
                int r = Integer.parseInt(String.valueOf(s));
                double st = sum += r;
                double sta = sumi +=rd;
                lf1.setText(String.valueOf(st));
                lfm1.setText(String.valueOf(sta));
            } }}
    private void windBlow() {
        arrayList01.clear();
        arrayList001.clear();
        final String key = getActivity().getIntent().getExtras().get("gcid").toString();
        final String name = spin.getSelectedItem().toString();
        Query query = FirebaseDatabase.getInstance().getReference()
                .child("Records").child("ComLabQuizRecords").child(key);
        query.orderByChild("fullname").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()){

                    final String scars = ds.child("score").getValue(String.class);
                    final String ov = ds.child("overall").getValue(String.class);

                    arrayList001.add(ov);
                    arrayAdapter001.notifyDataSetChanged();
                    lavso.setVisibility(View.GONE);
                    arrayList01.add(scars);
                    arrayAdapter01.notifyDataSetChanged();
                    lavo.setVisibility(View.GONE);
                } }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void ComrecLa() {
        double sum =0;
        double sumi = 0;
        for (int i = 0 ; i <arrayList0.size(); i++) {

            for (int h= 0; h<arrayList00.size();h++) {
                String s = arrayList0.get(i);
                String w = arrayList00.get(h);
                int rd = Integer.parseInt(String.valueOf(w));
                int r = Integer.parseInt(String.valueOf(s));
                double st = sum += r;
                double sta = sumi +=rd;
                lf.setText(String.valueOf(st));
                lfm.setText(String.valueOf(sta));
            }
        }
    }
    private void Comlabrec() {
        arrayList0.clear();
        arrayList00.clear();
        final String key = getActivity().getIntent().getExtras().get("gcid").toString();
        final String name = spin.getSelectedItem().toString();
        Query query = FirebaseDatabase.getInstance().getReference()
                .child("Records").child("ComLabRecords").child(key);
        query.orderByChild("fullname").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()){

                    final String scars = ds.child("score").getValue(String.class);
                    final String ov = ds.child("overall").getValue(String.class);

                    arrayList00.add(ov);
                    arrayAdapter00.notifyDataSetChanged();
                    lavs.setVisibility(View.GONE);
                    arrayList0.add(scars);
                    arrayAdapter0.notifyDataSetChanged();
                    lav.setVisibility(View.GONE);
                } }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void Acc() {
        final String name = spin.getSelectedItem().toString();
        double sum =0;
        double sumi = 0;
        for (int i = 0 ; i <arrayList3.size(); i++) {

            for (int h= 0; h<arrayListoversos.size();h++) {
                String s = arrayList3.get(i);
                String w = arrayListoversos.get(h);
                int rd = Integer.parseInt(String.valueOf(w));
                int r = Integer.parseInt(String.valueOf(s));
                double st = sum += r;
                double sta = sumi +=rd;
                float res = (float) ((st / sta) * 85 + 15);
                float result = res / 100.0f * 15;
                BigDecimal bd = new BigDecimal(res);
                BigDecimal wews = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
                BigDecimal bds = new BigDecimal(result);
                BigDecimal wewso = bds.setScale(2, BigDecimal.ROUND_HALF_UP);
                acp.setText(String.valueOf(wews));
                acp1.setText(String.valueOf(wewso));
            }
        }

    }
    private void CpmRecitCompute() {
        final String name = spin.getSelectedItem().toString();
        double sum =0;
        double sumi = 0;
        for (int i = 0 ; i <arrayList2.size(); i++) {
            for (int g = 0 ; g <arrayListoverso.size(); g++) {
                String s = arrayList2.get(i);
                String sd = arrayListoverso.get(g);
                int rd = Integer.parseInt(String.valueOf(sd));
                int r = Integer.parseInt(String.valueOf(s));
                double st = sum += r;
                double sta = sumi += rd;
                float res = (float) ((st / sta) * 85 + 15);
                float result = res / 100.0f * 10;
                BigDecimal bd = new BigDecimal(res);
                BigDecimal wews = bd.setScale(2,BigDecimal.ROUND_HALF_UP);
                BigDecimal bds = new BigDecimal(result);
                BigDecimal wewso = bds.setScale(2,BigDecimal.ROUND_HALF_UP);

                recit.setText(String.valueOf(wews));
                recito.setText(String.valueOf(wewso));
            }
        }
    }
    private void comAssignCompute() {
        final String name = spin.getSelectedItem().toString();
        double sum =0;
        double sumi= 0;
        for (int i = 0 ; i <arrayList1.size(); i++){
            for (int ss=0 ; ss<arrayListover.size();ss++) {
                String wa = arrayListover.get(ss);
                String s = arrayList1.get(i);
                int rd = Integer.parseInt(String.valueOf(wa));
                int r = Integer.parseInt(String.valueOf(s));
                double st = sum += r;
                double sta = sumi += rd;
                float res = (float) ((st / sta) * 85 + 15);
                float result = res / 100.0f * 5;
                BigDecimal bd = new BigDecimal(res);
                BigDecimal wews = bd.setScale(2,BigDecimal.ROUND_HALF_UP);
                BigDecimal bds = new BigDecimal(result);
                BigDecimal wewso = bds.setScale(2,BigDecimal.ROUND_HALF_UP);

                ase.setText(String.valueOf(wews));
                awe.setText(String.valueOf(wewso));
            }
        }

    }
    private void ExLabPick() {
        final String key = getActivity().getIntent().getExtras().get("gcid").toString();
        final String name = spin.getSelectedItem().toString();
        final Query query = FirebaseDatabase.getInstance().getReference()
                .child("Records").child("ComLabExamRecords").child(key);
        query.orderByChild("fullname").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds: dataSnapshot.getChildren()){

                    String gg = ds.child("status").getValue(String.class);

                    switch (gg){
                        case "1st Grading":

                            String exam = ds.child("score").getValue(String.class);
                            String ov = ds.child("overall").getValue(String.class);
                            double rd = Integer.parseInt(String.valueOf(exam));
                            double r = Integer.parseInt(String.valueOf(ov));
                            l1.setText(String.valueOf(r));
                            l1m.setText(String.valueOf(rd));


                            break;
                        case "2nd Grading" :
                            String exam2 = ds.child("score").getValue(String.class);
                            String ov2 = ds.child("overall").getValue(String.class);
                            double rd2 = Integer.parseInt(String.valueOf(exam2));
                            double r2 = Integer.parseInt(String.valueOf(ov2));

                            l2.setText(String.valueOf(r2));
                            l2m.setText(String.valueOf(rd2));


                            break;
                        case "3rd Grading":

                            String exam3 = ds.child("score").getValue(String.class);
                            String ov3 = ds.child("overall").getValue(String.class);

                            double rd3 = Integer.parseInt(String.valueOf(exam3));
                            double r3 = Integer.parseInt(String.valueOf(ov3));

                            l3.setText(String.valueOf(r3));
                            l3m.setText(String.valueOf(rd3));

                            break;
                        case "4th Grading":

                            String exam4 = ds.child("score").getValue(String.class);
                            String ov4 = ds.child("overall").getValue(String.class);
                            double rd4 = Integer.parseInt(String.valueOf(exam4));
                            double r4 = Integer.parseInt(String.valueOf(ov4));

                            l4.setText(String.valueOf(r4));
                            l4m.setText(String.valueOf(rd4));

                            break;

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void QuizAllEx() {
        final String name = spin.getSelectedItem().toString();
        double sum =0;
        double sumi = 0;
        for (int i = 0 ; i <arrayList.size(); i++) {
            for (int ss = 0; ss < arrayListover.size(); ss++) {
                String wa = arrayListover.get(ss);
                String s = arrayList.get(i);
                int rd = Integer.parseInt(String.valueOf(wa));
                int r = Integer.parseInt(String.valueOf(s));
                double st = sum += r;
                double sta = sumi += rd;
                float res = (float) ((st / sta) * 85 + 15);
                BigDecimal bd = new BigDecimal(res);
                BigDecimal wews = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
                float result = res / 100.0f * 10;
                BigDecimal bds = new BigDecimal(result);
                BigDecimal wewso = bds.setScale(2, BigDecimal.ROUND_HALF_UP);
                eq.setText(String.valueOf(wews));
                eqs.setText(String.valueOf(wewso));
            }
        }
    }
    private void QuizLabCom() {
        arrayList01.clear();
        arrayList001.clear();
        final String key = getActivity().getIntent().getExtras().get("gcid").toString();
        final String name = spin.getSelectedItem().toString();
        Query query = FirebaseDatabase.getInstance().getReference()
                .child("Records").child("ComLabQuizRecords").child(key);
        query.orderByChild("fullname").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()){

                    final String scars = ds.child("score").getValue(String.class);
                    final String ov = ds.child("overall").getValue(String.class);

                    arrayList001.add(ov);
                    arrayAdapter001.notifyDataSetChanged();
                    lavso.setVisibility(View.GONE);
                    arrayList01.add(scars);
                    arrayAdapter01.notifyDataSetChanged();
                    lavo.setVisibility(View.GONE);
                } }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
//    private void ComLabEx() {
//        arrayList0.clear();
//        arrayList00.clear();
//        final String key = getActivity().getIntent().getExtras().get("gcid").toString();
//        final String name = spin.getSelectedItem().toString();
//        Query query = FirebaseDatabase.getInstance().getReference("SmartDab")
//                .child("Records").child("ComLabRecords").child(key);
//        query.orderByChild("fullname").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot ds: dataSnapshot.getChildren()){
//
//                    final String scars = ds.child("score").getValue(String.class);
//                    final String ov = ds.child("overall").getValue(String.class);
//
//                    arrayList00.add(ov);
//                    arrayAdapter00.notifyDataSetChanged();
//                    lavs.setVisibility(View.GONE);
//                    arrayList0.add(scars);
//                    arrayAdapter0.notifyDataSetChanged();
//                    lav.setVisibility(View.GONE);
//                } }
//
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }

    private void alLabExx() {

        final String key = getActivity().getIntent().getExtras().get("gcid").toString();
        final String name = spin.getSelectedItem().toString();
        final Query query = FirebaseDatabase.getInstance().getReference()
                .child("Records").child("ExamRecords").child(key);
        query.orderByChild("fullname").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()){

                    String stats = ds.child("status").getValue(String.class);

                    switch (stats){
                        case "1st Grading":
                            String exam = ds.child("score").getValue(String.class);
                            String ov = ds.child("overall").getValue(String.class);
                            double rd = Integer.parseInt(String.valueOf(exam));
                            double r = Integer.parseInt(String.valueOf(ov));
                            float res = (float) ((rd / r) * 85 + 15);
                            float result = res / 100.0f * 5;
                            BigDecimal bd = new BigDecimal(res);
                            BigDecimal wews = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
                            BigDecimal bds = new BigDecimal(result);
                            BigDecimal wewso = bds.setScale(2, BigDecimal.ROUND_HALF_UP);
                            f1.setText(String.valueOf(wews));
                            f1m.setText(String.valueOf(wewso));
                            break;
                        case "2nd Grading":

                            String exam2 = ds.child("score").getValue(String.class);
                            String ov2 = ds.child("overall").getValue(String.class);
                            double rd2 = Integer.parseInt(String.valueOf(exam2));
                            double r2 = Integer.parseInt(String.valueOf(ov2));
                            float res2 = (float) ((rd2 / r2) * 85 + 15);
                            float result2 = res2 / 100.0f * 5;
                            BigDecimal bd2 = new BigDecimal(res2);
                            BigDecimal wews2 = bd2.setScale(2, BigDecimal.ROUND_HALF_UP);
                            BigDecimal bds2 = new BigDecimal(result2);
                            BigDecimal wewso2 = bds2.setScale(2, BigDecimal.ROUND_HALF_UP);

                            s2.setText(String.valueOf(wews2));
                            s2m.setText(String.valueOf(wewso2));


                            break;

                        case "3rd Grading":
                            String exam3 = ds.child("score").getValue(String.class);
                            String ov3 = ds.child("overall").getValue(String.class);

                            double rd3 = Integer.parseInt(String.valueOf(exam3));
                            double r3 = Integer.parseInt(String.valueOf(ov3));
                            float res3 = (float) ((rd3 / r3) * 85 + 15);
                            float result3 = res3 / 100.0f * 5;
                            BigDecimal bd3 = new BigDecimal(res3);
                            BigDecimal wews3 = bd3.setScale(2, BigDecimal.ROUND_HALF_UP);
                            BigDecimal bds3 = new BigDecimal(result3);
                            BigDecimal wewso3 = bds3.setScale(2, BigDecimal.ROUND_HALF_UP);

                            t3.setText(String.valueOf(wews3));
                            t3m.setText(String.valueOf(wewso3));
                            break;
                        case "4th Grading":
                            String exam4 = ds.child("score").getValue(String.class);
                            String ov4 = ds.child("overall").getValue(String.class);

                            double rd4 = Integer.parseInt(String.valueOf(exam4));
                            double r4 = Integer.parseInt(String.valueOf(ov4));
                            float res4 = (float) ((rd4 / r4) * 85 + 15);
                            float result4 = res4 / 100.0f * 5;
                            BigDecimal bd4 = new BigDecimal(res4);
                            BigDecimal wews4 = bd4.setScale(2, BigDecimal.ROUND_HALF_UP);
                            BigDecimal bds4 = new BigDecimal(result4);
                            BigDecimal wewso4 = bds4.setScale(2, BigDecimal.ROUND_HALF_UP);

                            f4.setText(String.valueOf(wews4));
                            f4m.setText(String.valueOf(wewso4));


                            break;

                        case "5th Grading":
                            String exam5 = ds.child("score").getValue(String.class);
                            String ov5 = ds.child("overall").getValue(String.class);

                            double rd5 = Integer.parseInt(String.valueOf(exam5));
                            double r5 = Integer.parseInt(String.valueOf(ov5));
                            float res5 = (float) ((rd5 / r5) * 85 + 15);
                            float result5 = res5 / 100.0f * 5;
                            BigDecimal bd5 = new BigDecimal(res5);
                            BigDecimal wews5 = bd5.setScale(2, BigDecimal.ROUND_HALF_UP);
                            BigDecimal bds5 = new BigDecimal(result5);
                            BigDecimal wewso5 = bds5.setScale(2, BigDecimal.ROUND_HALF_UP);
                            f5n.setText(String.valueOf(wews5));
                            f5m.setText(String.valueOf(wewso5));
                            break;
                        case "6th Grading":
                            String exam6 = ds.child("score").getValue(String.class);
                            String ov6 = ds.child("overall").getValue(String.class);

                            double rd6 = Integer.parseInt(String.valueOf(exam6));
                            double r6 = Integer.parseInt(String.valueOf(ov6));
                            float res6 = (float) ((rd6 / r6) * 85 + 15);
                            float result6 = res6 / 100.0f * 5;
                            BigDecimal bd6 = new BigDecimal(res6);
                            BigDecimal wews6 = bd6.setScale(2, BigDecimal.ROUND_HALF_UP);
                            BigDecimal bds6 = new BigDecimal(result6);
                            BigDecimal wewso6 = bds6.setScale(2, BigDecimal.ROUND_HALF_UP);
                            f6.setText(String.valueOf(wews6));
                            f6m.setText(String.valueOf(wewso6));
                            break;
                        case "7th Grading":
                            String exam7 = ds.child("score").getValue(String.class);
                            String ov7 = ds.child("overall").getValue(String.class);

                            double rd7 = Integer.parseInt(String.valueOf(exam7));
                            double r7 = Integer.parseInt(String.valueOf(ov7));
                            float res7 = (float) ((rd7 / r7) * 85 + 15);
                            float result7 = res7 / 100.0f * 5;
                            BigDecimal bd7 = new BigDecimal(res7);
                            BigDecimal wews7 = bd7.setScale(2, BigDecimal.ROUND_HALF_UP);
                            BigDecimal bds7 = new BigDecimal(result7);
                            BigDecimal wewso7 = bds7.setScale(2, BigDecimal.ROUND_HALF_UP);
                            f7.setText(String.valueOf(wews7));
                            f7m.setText(String.valueOf(wewso7));
                            break;
                        case "8th Grading":
                            String exam8 = ds.child("score").getValue(String.class);
                            String ov8 = ds.child("overall").getValue(String.class);

                            double rd8 = Integer.parseInt(String.valueOf(exam8));
                            double r8 = Integer.parseInt(String.valueOf(ov8));
                            float res8 = (float) ((rd8 / r8) * 85 + 15);
                            float result8 = res8 / 100.0f * 25;
                            BigDecimal bd8 = new BigDecimal(res8);
                            BigDecimal wews8 = bd8.setScale(2, BigDecimal.ROUND_HALF_UP);
                            BigDecimal bds8 = new BigDecimal(result8);
                            BigDecimal wewso8 = bds8.setScale(2, BigDecimal.ROUND_HALF_UP);
                            f8.setText(String.valueOf(wews8));
                            f8m.setText(String.valueOf(wewso8));
                            break;


                    }



                } }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void AcpLab() {
        arrayList3.clear();
        final String key = getActivity().getIntent().getExtras().get("gcid").toString();
        final String name = spin.getSelectedItem().toString();
        Query query = FirebaseDatabase.getInstance().getReference()
                .child("Records").child("ActivityRecords").child(key);
        query.orderByChild("fullname").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()){

                    final String scars = ds.child("score").getValue(String.class);
                    final String ov = ds.child("overall").getValue(String.class);

                    arrayListoversos.add(ov);
                    arrayAdapteroversos.notifyDataSetChanged();
                    overlistsos.setVisibility(View.GONE);
                    arrayList3.add(scars);
                    arrayAdapter3.notifyDataSetChanged();
                    listView3.setVisibility(View.GONE);
                } }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void RecitoLab() {
        arrayList2.clear();
        final String key = getActivity().getIntent().getExtras().get("gcid").toString();
        final String name = spin.getSelectedItem().toString();
        Query query = FirebaseDatabase.getInstance().getReference()
                .child("Records").child("RecitationRecord").child(key);
        query.orderByChild("fullname").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()){

                    final String scars = ds.child("score").getValue(String.class);
                    String ov = ds.child("overall").getValue(String.class);

                    arrayListoverso.add(ov);
                    arrayAdapteroverso.notifyDataSetChanged();
                    overlistso.setVisibility(View.GONE);

                    arrayList2.add(scars);
                    arrayAdapter2.notifyDataSetChanged();
                    listView2.setVisibility(View.GONE);
                } }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void AssinLab() {
        arrayList1.clear();
        final String key = getActivity().getIntent().getExtras().get("gcid").toString();
        final String name = spin.getSelectedItem().toString();
        Query query = FirebaseDatabase.getInstance().getReference()
                .child("Records").child("AssignmentRecords").child(key);
        query.orderByChild("fullname").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()){

                    final String scars = ds.child("score").getValue(String.class);
                    final String ove = ds.child("overall").getValue(String.class);
                    arrayListovers.add(ove);
                    arrayAdapterovers.notifyDataSetChanged();
                    overlists.setVisibility(View.GONE);
                    arrayList1.add(scars);
                    arrayAdapter1.notifyDataSetChanged();
                    listView1.setVisibility(View.GONE);
                } }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void Quizlab() {

        arrayList.clear();
        final String key = getActivity().getIntent().getExtras().get("gcid").toString();

        final String name = spin.getSelectedItem().toString();


        Query query = FirebaseDatabase.getInstance().getReference()
                .child("Records").child("QuizRecords").child(key);
        query.orderByChild("fullname").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()){

                    final String scars = ds.child("score").getValue(String.class);
                    final String oven = ds.child("overall").getValue(String.class);


                    arrayList.add(scars);
                    arrayListover.add(oven);
                    arrayAdapterover.notifyDataSetChanged();
                    arrayAdapter.notifyDataSetChanged();
                    overlist.setVisibility(View.GONE);
                    listView.setVisibility(View.GONE);

                } }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void ComQCom() {

        double sum =0;
        double sumi = 0;
        for (int i = 0 ; i <arrayList01.size(); i++) {

            for (int h= 0; h<arrayList001.size();h++) {
                String s = arrayList01.get(i);
                String w = arrayList001.get(h);
                int rd = Integer.parseInt(String.valueOf(w));
                int r = Integer.parseInt(String.valueOf(s));
                double st = sum += r;
                double sta = sumi +=rd;
                float res = (float) ((st / sta) * 85 + 15);
                float result = res / 100.0f * 10;
                BigDecimal bd = new BigDecimal(res);
                BigDecimal wews = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
                BigDecimal bds = new BigDecimal(result);
                BigDecimal wewso = bds.setScale(2, BigDecimal.ROUND_HALF_UP);
                lf1.setText(String.valueOf(wews));
                lfm1.setText(String.valueOf(wewso));
            }
        }

    }
    private void ComQuiz() {
        arrayList01.clear();
        arrayList001.clear();
        final String key = getActivity().getIntent().getExtras().get("gcid").toString();
        final String name = spin.getSelectedItem().toString();
        Query query = FirebaseDatabase.getInstance().getReference()
                .child("Records").child("ComLabQuizRecords").child(key);
        query.orderByChild("fullname").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()){

                    final String scars = ds.child("score").getValue(String.class);
                    final String ov = ds.child("overall").getValue(String.class);

                    arrayList001.add(ov);
                    arrayAdapter001.notifyDataSetChanged();
                    lavso.setVisibility(View.GONE);
                    arrayList01.add(scars);
                    arrayAdapter01.notifyDataSetChanged();
                    lavo.setVisibility(View.GONE);
                } }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void ComCom() {

        double sum =0;
        double sumi = 0;
        for (int i = 0 ; i <arrayList0.size(); i++) {

            for (int h= 0; h<arrayList00.size();h++) {
                String s = arrayList0.get(i);
                String w = arrayList00.get(h);
                int rd = Integer.parseInt(String.valueOf(w));
                int r = Integer.parseInt(String.valueOf(s));
                double st = sum += r;
                double sta = sumi +=rd;
                float res = (float) ((st / sta) * 85 + 15);
                float result = res / 100.0f * 40;
                BigDecimal bd = new BigDecimal(res);
                BigDecimal wews = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
                BigDecimal bds = new BigDecimal(result);
                BigDecimal wewso = bds.setScale(2, BigDecimal.ROUND_HALF_UP);
                lf.setText(String.valueOf(wews));
                lfm.setText(String.valueOf(wewso));
            }
        }


    }
    private void Comlabpick() {
        arrayList0.clear();
        arrayList00.clear();
        final String key = getActivity().getIntent().getExtras().get("gcid").toString();
        final String name = spin.getSelectedItem().toString();
        Query query = FirebaseDatabase.getInstance().getReference()
                .child("Records").child("ComLabRecords").child(key);
        query.orderByChild("fullname").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()){

                    final String scars = ds.child("score").getValue(String.class);
                    final String ov = ds.child("overall").getValue(String.class);

                    arrayList00.add(ov);
                    arrayAdapter00.notifyDataSetChanged();
                    lavs.setVisibility(View.GONE);
                    arrayList0.add(scars);
                    arrayAdapter0.notifyDataSetChanged();
                    lav.setVisibility(View.GONE);
                } }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void LabPick() {

        final String key = getActivity().getIntent().getExtras().get("gcid").toString();
        final String name = spin.getSelectedItem().toString();
        final Query query = FirebaseDatabase.getInstance().getReference()
                .child("Records").child("ComLabExamRecords").child(key);
        query.orderByChild("fullname").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds: dataSnapshot.getChildren()){

                    String gg = ds.child("status").getValue(String.class);

                    switch (gg){
                        case "1st Grading":

                            String exam = ds.child("score").getValue(String.class);
                            String ov = ds.child("overall").getValue(String.class);
                            double rd = Integer.parseInt(String.valueOf(exam));
                            double r = Integer.parseInt(String.valueOf(ov));
                            float res = (float) ((rd / r) * 85 + 15);
                            float result = res / 100.0f * 15;
                            BigDecimal bd = new BigDecimal(res);
                            BigDecimal wews = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
                            BigDecimal bds = new BigDecimal(result);
                            BigDecimal wewso = bds.setScale(2, BigDecimal.ROUND_HALF_UP);

                            l1.setText(String.valueOf(wews));
                            l1m.setText(String.valueOf(wewso));


                            break;
                        case "2nd Grading" :
                            String exam2 = ds.child("score").getValue(String.class);
                            String ov2 = ds.child("overall").getValue(String.class);
                            double rd2 = Integer.parseInt(String.valueOf(exam2));
                            double r2 = Integer.parseInt(String.valueOf(ov2));
                            float res2 = (float) ((rd2 / r2) * 85 + 15);
                            float result2 = res2 / 100.0f * 15;
                            BigDecimal bd2 = new BigDecimal(res2);
                            BigDecimal wews2 = bd2.setScale(2, BigDecimal.ROUND_HALF_UP);
                            BigDecimal bds2 = new BigDecimal(result2);
                            BigDecimal wewso2 = bds2.setScale(2, BigDecimal.ROUND_HALF_UP);

                            l2.setText(String.valueOf(wews2));
                            l2m.setText(String.valueOf(wewso2));


                            break;
                        case "3rd Grading":

                            String exam3 = ds.child("score").getValue(String.class);
                            String ov3 = ds.child("overall").getValue(String.class);
                            double rd3 = Integer.parseInt(String.valueOf(exam3));
                            double r3 = Integer.parseInt(String.valueOf(ov3));
                            float res3 = (float) ((rd3 / r3) * 85 + 15);
                            float result3 = res3 / 100.0f * 20;
                            BigDecimal bd3 = new BigDecimal(res3);
                            BigDecimal wews3 = bd3.setScale(2, BigDecimal.ROUND_HALF_UP);
                            BigDecimal bds3 = new BigDecimal(result3);
                            BigDecimal wewso3 = bds3.setScale(2, BigDecimal.ROUND_HALF_UP);

                            l3.setText(String.valueOf(wews3));
                            l3m.setText(String.valueOf(wewso3));

                            break;

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    private void FirstPick() {

        final String key = getActivity().getIntent().getExtras().get("gcid").toString();
        final String name = spin.getSelectedItem().toString();
        final Query query = FirebaseDatabase.getInstance().getReference()
                .child("Records").child("ExamRecords").child(key);
        query.orderByChild("fullname").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()){

                    String stats = ds.child("status").getValue(String.class);

                    switch (stats){
                        case "1st Grading":

                            String exam = ds.child("score").getValue(String.class);
                            String ov = ds.child("overall").getValue(String.class);
                            double rd = Integer.parseInt(String.valueOf(exam));
                            double r = Integer.parseInt(String.valueOf(ov));
                            float res = (float) ((rd / r) * 85 + 15);
                            float result = res / 100.0f * 10;
                            BigDecimal bd = new BigDecimal(res);
                            BigDecimal wews = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
                            BigDecimal bds = new BigDecimal(result);
                            BigDecimal wewso = bds.setScale(2, BigDecimal.ROUND_HALF_UP);

                            f1.setText(String.valueOf(wews));
                            f1m.setText(String.valueOf(wewso));
                            break;
                        case "2nd Grading":

                            String exam2 = ds.child("score").getValue(String.class);
                            String ov2 = ds.child("overall").getValue(String.class);

                            double rd2 = Integer.parseInt(String.valueOf(exam2));
                            double r2 = Integer.parseInt(String.valueOf(ov2));
                            float res2 = (float) ((rd2 / r2) * 85 + 15);
                            float result2 = res2 / 100.0f * 10;
                            BigDecimal bd2 = new BigDecimal(res2);
                            BigDecimal wews2 = bd2.setScale(2, BigDecimal.ROUND_HALF_UP);
                            BigDecimal bds2 = new BigDecimal(result2);
                            BigDecimal wewso2 = bds2.setScale(2, BigDecimal.ROUND_HALF_UP);

                            s2.setText(String.valueOf(wews2));
                            s2m.setText(String.valueOf(wewso2));


                            break;

                        case "3rd Grading":
                            String exam3 = ds.child("score").getValue(String.class);
                            String ov3 = ds.child("overall").getValue(String.class);

                            double rd3 = Integer.parseInt(String.valueOf(exam3));
                            double r3 = Integer.parseInt(String.valueOf(ov3));
                            float res3 = (float) ((rd3 / r3) * 85 + 15);
                            float result3 = res3 / 100.0f * 10;
                            BigDecimal bd3 = new BigDecimal(res3);
                            BigDecimal wews3 = bd3.setScale(2, BigDecimal.ROUND_HALF_UP);
                            BigDecimal bds3 = new BigDecimal(result3);
                            BigDecimal wewso3 = bds3.setScale(2, BigDecimal.ROUND_HALF_UP);

                            t3.setText(String.valueOf(wews3));
                            t3m.setText(String.valueOf(wewso3));
                            break;
                        case "4th Grading":
                            String exam4 = ds.child("score").getValue(String.class);
                            String ov4 = ds.child("overall").getValue(String.class);

                            double rd4 = Integer.parseInt(String.valueOf(exam4));
                            double r4 = Integer.parseInt(String.valueOf(ov4));
                            float res4 = (float) ((rd4 / r4) * 85 + 15);
                            float result4 = res4 / 100.0f * 30;
                            BigDecimal bd4 = new BigDecimal(res4);
                            BigDecimal wews4 = bd4.setScale(2, BigDecimal.ROUND_HALF_UP);
                            BigDecimal bds4 = new BigDecimal(result4);
                            BigDecimal wewso4 = bds4.setScale(2, BigDecimal.ROUND_HALF_UP);

                            f4.setText(String.valueOf(wews4));
                            f4m.setText(String.valueOf(wewso4));


                            break;
                    }



                } }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void quva() {

            String[] a = new String[]{  "Term","Semestral"
            };

            ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>
                    (getActivity(), android.R.layout.simple_spinner_item,
                            a);
            areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spints.setAdapter(areasAdapter);



    }
    private void AcpCompyte() {
        final String name = spin.getSelectedItem().toString();
        double sum =0;
        double sumi = 0;
        for (int i = 0 ; i <arrayList3.size(); i++) {

            for (int h= 0; h<arrayListoversos.size();h++) {
                String s = arrayList3.get(i);
                String w = arrayListoversos.get(h);
                int rd = Integer.parseInt(String.valueOf(w));
                int r = Integer.parseInt(String.valueOf(s));
                double st = sum += r;
                double sta = sumi +=rd;
                float res = (float) ((st / sta) * 85 + 15);
                float result = res / 100.0f * 15;
                BigDecimal bd = new BigDecimal(res);
                BigDecimal wews = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
                BigDecimal bds = new BigDecimal(result);
                BigDecimal wewso = bds.setScale(2, BigDecimal.ROUND_HALF_UP);
                acp.setText(String.valueOf(wews));
                acp1.setText(String.valueOf(wewso));
            }
        }
    }
    private void AcpPick() {

        arrayList3.clear();
        final String key = getActivity().getIntent().getExtras().get("gcid").toString();
        final String name = spin.getSelectedItem().toString();
        Query query = FirebaseDatabase.getInstance().getReference()
                .child("Records").child("ActivityRecords").child(key);
        query.orderByChild("fullname").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()){

                    final String scars = ds.child("score").getValue(String.class);
                    final String ov = ds.child("overall").getValue(String.class);

                    arrayListoversos.add(ov);
                    arrayAdapteroversos.notifyDataSetChanged();
                    overlistsos.setVisibility(View.GONE);
                    arrayList3.add(scars);
                    arrayAdapter3.notifyDataSetChanged();
                    listView3.setVisibility(View.GONE);
                } }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void RecitoPick() {
        arrayList2.clear();
        final String key = getActivity().getIntent().getExtras().get("gcid").toString();
        final String name = spin.getSelectedItem().toString();
        Query query = FirebaseDatabase.getInstance().getReference()
                .child("Records").child("RecitationRecord").child(key);
        query.orderByChild("fullname").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()){

                    final String scars = ds.child("score").getValue(String.class);
                    String ov = ds.child("overall").getValue(String.class);

                    arrayListoverso.add(ov);
                    arrayAdapteroverso.notifyDataSetChanged();
                    overlistso.setVisibility(View.GONE);

                    arrayList2.add(scars);
                    arrayAdapter2.notifyDataSetChanged();
                    listView2.setVisibility(View.GONE);
                } }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void RecitCompute() {

        final String name = spin.getSelectedItem().toString();
        double sum =0;
        double sumi = 0;
        for (int i = 0 ; i <arrayList2.size(); i++) {
            for (int g = 0 ; g <arrayListoverso.size(); g++) {
                String s = arrayList2.get(i);
                String sd = arrayListoverso.get(g);
                int rd = Integer.parseInt(String.valueOf(sd));
                int r = Integer.parseInt(String.valueOf(s));
                double st = sum += r;
                double sta = sumi += rd;
                float res = (float) ((st / sta) * 85 + 15);
                float result = res / 100.0f * 10;
                BigDecimal bd = new BigDecimal(res);
                BigDecimal wews = bd.setScale(2,BigDecimal.ROUND_HALF_UP);
                BigDecimal bds = new BigDecimal(result);
                BigDecimal wewso = bds.setScale(2,BigDecimal.ROUND_HALF_UP);

                recit.setText(String.valueOf(wews));
                recito.setText(String.valueOf(wewso));
            }
            }
    }
    private void AssignCompute() {


        final String name = spin.getSelectedItem().toString();
        double sum =0;
        double sumi= 0;
        for (int i = 0 ; i <arrayList1.size(); i++){
            for (int ss=0 ; ss<arrayListover.size();ss++) {
                String wa = arrayListover.get(ss);
                String s = arrayList1.get(i);
                int rd = Integer.parseInt(String.valueOf(wa));
                int r = Integer.parseInt(String.valueOf(s));
                double st = sum += r;
                double sta = sumi += rd;
                float res = (float) ((st / sta) * 85 + 15);
                float result = res / 100.0f * 5;
                BigDecimal bd = new BigDecimal(res);
                BigDecimal wews = bd.setScale(2,BigDecimal.ROUND_HALF_UP);
                BigDecimal bds = new BigDecimal(result);
                BigDecimal wewso = bds.setScale(2,BigDecimal.ROUND_HALF_UP);

                ase.setText(String.valueOf(wews));
                awe.setText(String.valueOf(wewso));
            }
        }
    }
    private void AssignPick() {
        arrayList1.clear();
        final String key = getActivity().getIntent().getExtras().get("gcid").toString();
        final String name = spin.getSelectedItem().toString();
        Query query = FirebaseDatabase.getInstance().getReference()
                .child("Records").child("AssignmentRecords").child(key);
        query.orderByChild("fullname").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()){

                    final String scars = ds.child("score").getValue(String.class);
                    final String ove = ds.child("overall").getValue(String.class);
                    arrayListovers.add(ove);
                    arrayAdapterovers.notifyDataSetChanged();
                    overlists.setVisibility(View.GONE);
                    arrayList1.add(scars);
                    arrayAdapter1.notifyDataSetChanged();
                    listView1.setVisibility(View.GONE);
                } }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void QuizPick() {
        arrayList.clear();
        final String key = getActivity().getIntent().getExtras().get("gcid").toString();

        final String name = spin.getSelectedItem().toString();


        Query query = FirebaseDatabase.getInstance().getReference()
                .child("Records").child("QuizRecords").child(key);
        query.orderByChild("fullname").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()){

                    final String scars = ds.child("score").getValue(String.class);
                    final String oven = ds.child("overall").getValue(String.class);


                    arrayList.add(scars);
                    arrayListover.add(oven);
                    arrayAdapterover.notifyDataSetChanged();
                    arrayAdapter.notifyDataSetChanged();
                    overlist.setVisibility(View.GONE);
                    listView.setVisibility(View.GONE);

                } }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void QuizCompute() {
        final String name = spin.getSelectedItem().toString();
        double sum =0;
        double sumi = 0;
        for (int i = 0 ; i <arrayList.size(); i++){
            for (int ss=0 ; ss<arrayListover.size();ss++) {
                String wa = arrayListover.get(ss);
                String s = arrayList.get(i);
                int rd = Integer.parseInt(String.valueOf(wa));
                int r = Integer.parseInt(String.valueOf(s));
                double st = sum += r;
                double sta = sumi += rd;
                float res = (float) ((st / sta) * 85 + 15);
                BigDecimal bd = new BigDecimal(res);
                BigDecimal wews = bd.setScale(2,BigDecimal.ROUND_HALF_UP);
                float result = res / 100.0f * 10;
                BigDecimal bds = new BigDecimal(result);
                BigDecimal wewso = bds.setScale(2,BigDecimal.ROUND_HALF_UP);
                eq.setText(String.valueOf(wews));
                eqs.setText(String .valueOf(wewso));
            }
            }
    }
    private void spinthose() {
        String[] a = new String[]{
                "1st Grading","2nd Grading","3rd Grading","4th Grading"
        };

        ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>
                (getActivity(), android.R.layout.simple_spinner_item,
                        a);
        areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spins.setAdapter(areasAdapter);
    }
    private void spinThename() {

        final String key = getActivity().getIntent().getExtras().get("gcid").toString();
        data = FirebaseDatabase.getInstance().getReference().child("Groups");
        data.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String mem = dataSnapshot.child("Member").getKey();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference()
                                                      .child("Groups").child(key).child(mem);
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        List<String> are = new ArrayList<>();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String Name = ds.child("fullname").getValue(String.class);
                            final String url = ds.child("url").getValue(String.class);
                            are.add(Name);
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


    private void export(){




    }

}


