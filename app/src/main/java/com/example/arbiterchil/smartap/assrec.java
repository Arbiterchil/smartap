package com.example.arbiterchil.smartap;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.squareup.picasso.Picasso;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class assrec extends Fragment {

    View view;
    TextView vilat;
    Spinner spin,spins;
    DatabaseReference data;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> arrayList =  new ArrayList<>();
    ListView listView;
    DatabaseReference reference;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    RecyclerView recyclerView;
    List<post> list;
    Button expot;
    TextView names;
    DatabaseReference ref;

    public assrec() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_assrec,container,false);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        expot = view.findViewById(R.id.export);

        //        vilat = view.findViewById(R.id.strix);
//
//
//        final String key = getActivity().getIntent().getExtras().get("gcid").toString();
//        arrayAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,arrayList);
//
//        listView = view.findViewById(R.id.listograph);
//        listView.setAdapter(arrayAdapter);
//        spin = view.findViewById(R.id.namaeh);
//        spinThename();
//
//        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//                final String key = getActivity().getIntent().getExtras().get("gcid").toString();
//                String name = spin.getSelectedItem().toString();
//                Query query = FirebaseDatabase.getInstance().getReference("SmartDab")
//                        .child("Records").child("AssignmentRecords").child(key);
//                query.orderByChild("fullname").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        arrayList.clear();
//                        for (DataSnapshot ds: dataSnapshot.getChildren()){
//                            String scars = ds.child("score").getValue(String.class);
//                            arrayList.add(scars);
//                            arrayAdapter.notifyDataSetChanged();
//
//                        }
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
//
//
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && getActivity().checkSelfPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        recyclerView = view.findViewById(R.id.recycleview);

        names = view.findViewById(R.id.name);

        ref = FirebaseDatabase.getInstance().getReference().child("User")
                .child(firebaseAuth.getCurrentUser().getUid());

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String dataname = dataSnapshot.child("fullname").getValue(String.class);
                names.setText(dataname);
                names.setVisibility(View.GONE);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),1);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(getActivity(), gridLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        list = new ArrayList<>();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        expot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                exportpSd();

            }
        });






        return view;
    }

    private void exportpSd() {
        final String key = getActivity().getIntent().getExtras().get("gcid").toString();
        final String namesing =  names.getText().toString();
        DatabaseReference disfuck = FirebaseDatabase.getInstance().getReference().child("Groups")
                .child(key);
        disfuck.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
if (dataSnapshot.exists()){
                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                    final String subjectname = ds.child("groupname").getValue(String.class);
                    String uid = ds.child("uid").getValue(String.class);

                    final ArrayList<String> log = new ArrayList<>();
                    DatabaseReference fearme = FirebaseDatabase.getInstance().getReference().child("PostRecord")
                            .child(key);
                    fearme.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            if (dataSnapshot.exists()) {

                                for (DataSnapshot ds : dataSnapshot.getChildren()) {




                                    final String name = ds.child("fullname").getValue(String.class);
                                    final String remark = ds.child("remark").getValue(String.class);
                                    final String total = ds.child("total").getValue(String.class);


                                    log.add(name +" | "+remark+" | "+total+" | ");

                                    final Document doc = new Document();
                                    doc.setPageSize(PageSize.A4);
                                    doc.addCreationDate();
                                    doc.addAuthor("SmarTap");
                                    doc.addCreator(namesing + " ," + subjectname);

                                    String pojo = Environment.getExternalStorageDirectory() + "/Grades";
                                    try {
                                        PdfWriter.getInstance(doc, new FileOutputStream(pojo));
                                        doc.open();
                                        Font fontrow = new Font(Font.FontFamily.COURIER, 14, Font.NORMAL, BaseColor.BLACK);
                                        Chunk chunky = new Chunk(namesing + " ," + subjectname+" Teacher " +
                                                "", fontrow);
                                        Paragraph mop = new Paragraph(chunky);
                                        doc.add(mop);

                                        doc.add(new Paragraph("  "));

                                        LineSeparator ls = new LineSeparator();
                                        ls.setLineColor(new BaseColor(0, 0, 0, 100));
                                        doc.add(ls);

                                        Font dep = new Font(Font.FontFamily.COURIER, 14, Font.NORMAL, BaseColor.ORANGE);
                                        Chunk chunko = new Chunk("Final Effort", dep);
                                        Paragraph pg = new Paragraph(chunko);
                                        doc.add(pg);
                                        doc.add(new Paragraph((" ")));
                                        for (int z = 0; z < log.size(); z++) {

                                            Paragraph deploy = new Paragraph("Final Grade  " + " " + log.get(z));
                                            PdfPCell cell = new PdfPCell(deploy);
                                            PdfPTable table = new PdfPTable(2);
                                            cell.setBorder(5);
                                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                            cell.setColspan(2);
                                            cell.setRunDirection(PdfWriter.RUN_DIRECTION_LTR);
                                            table.setWidthPercentage(100);
                                            table.setSpacingBefore(10f);
                                            table.setSpacingAfter(10f);
                                            table.addCell(cell);
                                            doc.add(table);
                                        }
                                        doc.add(new Paragraph(" "));
                                        Toast.makeText(getActivity(), "Done", Toast.LENGTH_LONG).show();
                                        doc.close();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });

                }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void spinThename() {

        final String key = getActivity().getIntent().getExtras().get("gcid").toString();
        data = FirebaseDatabase.getInstance().getReference("SmartDab").child("Groups");
        data.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String mem = dataSnapshot.child("Member").getKey();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("SmartDab")
                        .child("Groups").child(key).child(mem);
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        List<String> are = new ArrayList<>();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String Name = ds.child("fullname").getValue(String.class);
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

    @Override
    public void onStart() {
        super.onStart();
        final String key = getActivity().getIntent().getExtras().get("gcid").toString();

        reference = FirebaseDatabase.getInstance().getReference().child("PostRecord").child(key);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                DatabaseReference reff = FirebaseDatabase.getInstance().getReference().child("PostRecord")
                        .child(key);
                FirebaseRecyclerOptions<post> options = new FirebaseRecyclerOptions.Builder<post>()
                        .setQuery(reff,post.class)
                        .build();
                FirebaseRecyclerAdapter<post,BMTH> adapter = new FirebaseRecyclerAdapter<post, BMTH>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull BMTH holder, int position, @NonNull post model) {

                            holder.fme.setText(model.getFullname());
                            holder.total.setText(model.getTotal());
                            holder.remark.setText(model.getRemark());
                            Picasso.get().load(model.getUrl()).into(holder.circleImageView);


                    }

                    @NonNull
                    @Override
                    public BMTH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                       View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.postgrapg,viewGroup,false);
                        return new BMTH(v);
                    }
                };
                recyclerView.setAdapter(adapter);
                adapter.startListening();



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }
    public  static  class BMTH extends  RecyclerView.ViewHolder{
        TextView fme = itemView.findViewById(R.id.fl);
        TextView total = itemView.findViewById(R.id.tot);
        TextView remark = itemView.findViewById(R.id.rem);
        CircleImageView circleImageView = itemView.findViewById(R.id.kagerou);

        public BMTH(@NonNull View itemView) {
            super(itemView);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {

                }return;
        }
    }
}



