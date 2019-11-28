package com.example.arbiterchil.smartap;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class arecit extends Fragment {

    View s;
    DatabaseReference reference,refl;
    Button tryit;
    TextView tryone,DateDotay;
    ListView listView;
    EditText input;
    Button save,compute;
    Spinner spin,spinn;
    ListView listViews;
    EditText scores;
    TextView names,namse;
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter;
    RecyclerView listnotcold;
    Button start,reset;
    List<memberlist> list;
    DatabaseReference fearme;


    public arecit() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        s=  inflater.inflate(R.layout.fragment_arecit, container, false);

        DateDotay = s.findViewById(R.id.datetoday);
        tryit = s.findViewById(R.id.randomize);
        listnotcold = s.findViewById(R.id.listnotcold);
        start = s.findViewById(R.id.starto);
        reset = s.findViewById(R.id.reseto);
        listViews = s.findViewById(R.id.theHeck);
//        listViews.setVisibility(View.INVISIBLE);
        save =s.findViewById(R.id.saves);
        spinn = s.findViewById(R.id.namestudes);
        reference = FirebaseDatabase.getInstance().getReference().child("Groups");
        listViews.setVisibility(View.GONE    );
        scores = s.findViewById(R.id.mobscore);
        names = s.findViewById(R.id.mobname);
        namse = s.findViewById(R.id.namse);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),1);
        listnotcold.setLayoutManager(new GridLayoutManager(getActivity(),2));
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(getActivity(), gridLayoutManager.getOrientation());
        listnotcold.addItemDecoration(dividerItemDecoration);
        list = new ArrayList<>();
        namse.setVisibility(View.GONE);
        cdDate();
        textArraygetname();
        textonhyeag();
        equva();

        final String key = getActivity().getIntent().getExtras().get("gcid").toString();

        final DatabaseReference logssave = FirebaseDatabase.
                getInstance().getReference();
        arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, arrayList);
        listViews.setAdapter(arrayAdapter);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Freedie();
                final String Date = DateDotay.getText().toString();
                String getK = logssave.push().getKey();
                String over = spinn.getSelectedItem().toString();

                Map<String, Object> grade = new HashMap<>();
                grade.put("fullname",names.getText().toString());
                grade.put("score",scores.getText().toString());
                grade.put("Date",Date);
                grade.put("overall",over);

                logssave.child("Records").child("RecitationRecord").child(key).child(getK).setValue(grade);
                Toast.makeText(getActivity(), "Save Successfully", Toast.LENGTH_SHORT).show();

                names.setText("");
                scores.setText("");
                namse.setText("");


            }
        });

            reset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String key = getActivity().getIntent().getExtras().get("gcid").toString();
                    final DatabaseReference reference = FirebaseDatabase.getInstance().getReference()
                            .child("Groups").child(key);
                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String punhing = dataSnapshot.child("Member").getKey();

                            final DatabaseReference amofala = FirebaseDatabase.getInstance().getReference()
                                    .child("Groups").child(key).child(punhing);
                            amofala.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for (DataSnapshot ds: dataSnapshot.getChildren()){
                                        String haha = ds.child("uid").getValue(String.class);
                                        String wewo = ds.child("trickart").getValue(String.class);

                                      switch (wewo){
                                          case "Called":
                                              String wop = "not Called";
                                              amofala.child(haha).child("trickart").setValue(wop);
                                              break;
                                      }




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
            });

        return s;
    }

    private void removal() {
        final String key = getActivity().getIntent().getExtras().get("gcid").toString();
        DatabaseReference mref = FirebaseDatabase.getInstance().getReference().child("Groups").child(key);

        mref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String datamen = dataSnapshot.child("Member").getKey();
                DatabaseReference refer = FirebaseDatabase.getInstance().getReference()
                        .child("Groups")
                        .child(key).child(datamen);

                refer.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot ds: dataSnapshot.getChildren()){
                            final String notcalled = ds.child("trickart").getValue(String.class);

                            if ("Called".equals(notcalled)){
                                final String Fullname = ds.child("fullname").getValue(String.class);
                                arrayList.remove(Fullname);
                                arrayAdapter.notifyDataSetChanged();

                            }



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

    private void uidshow() {
        final String key = getActivity().getIntent().getExtras().get("gcid").toString();
        final DatabaseReference radio = FirebaseDatabase.getInstance().getReference()
                .child("Groups").child(key);
        radio.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String mervury = dataSnapshot.child("Member").getKey();
                final String getname = names.getText().toString();

                Query query = FirebaseDatabase.getInstance().getReference().child("Groups")
                        .child(key).child(mervury).orderByChild("fullname").equalTo(getname);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot ds:dataSnapshot.getChildren()){
                            String godknows = ds.child("uid").getValue(String.class);

                            namse.setText(godknows);
                            Toast.makeText(getActivity(),godknows, Toast.LENGTH_SHORT).show();
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
    private void Freedie() {
        final String key = getActivity().getIntent().getExtras().get("gcid").toString();
        final String sd = namse.getText().toString();
//        final DatabaseReference dabest = FirebaseDatabase.getInstance().getReference("SmartDab").child("Groups")
//                .child(key);
//        dabest.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//              final String swallow = dataSnapshot.child("Member").getKey();
//                final String getuid = namse.getText().toString();
//                DatabaseReference ds = FirebaseDatabase.getInstance().getReference("SmartDab")
//                        .child("Groups").child(key).child(swallow);
//                ds.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                       Query query = FirebaseDatabase.getInstance().getReference("SmartDab")
//                               .child("Groups").child(key).child(swallow).orderByKey().equalTo(getuid);
//                       query.addListenerForSingleValueEvent(new ValueEventListener() {
//                           @Override
//                           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                           }
//
//                           @Override
//                           public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                           }
//                       });
//
//
//
//
//
//
//
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
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
        final DatabaseReference radio = FirebaseDatabase.getInstance().getReference()
                .child("Groups").child(key);
        radio.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final String mervury = dataSnapshot.child("Member").getKey();
                final String getname = names.getText().toString();

               final DatabaseReference query = FirebaseDatabase.getInstance().getReference().child("Groups")
                        .child(key).child(mervury);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            final String godknows = ds.child("uid").getValue(String.class);

//                          if (sd.equals(godknows)){
//

//                              Toast.makeText(getActivity(), "gana man", Toast.LENGTH_SHORT).show();
//
//                          }else{
                          if (dataSnapshot.exists()) {

                              final DatabaseReference fer = FirebaseDatabase.getInstance().getReference().child("Groups")
                                      .child(key).child(mervury);
                              fer.addListenerForSingleValueEvent(new ValueEventListener() {
                                  @Override
                                  public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (sd.equals(godknows)) {
                                    fer.child(godknows).child("trickart").setValue("Called");

                                }else{

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

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    private void equva() {

        String[] a = new String[]{
                "5","10","15","20","25","30"
        };

        ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>
                (getActivity(), android.R.layout.simple_spinner_item,
                        a);
        areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinn.setAdapter(areasAdapter);

    }

    private void textonhyeag() {
        tryit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                removal();
                Random random = new Random();
                String lit = arrayList.get(random.nextInt(arrayList.size()));
                names.setText(lit);
                uidshow();
                arrayAdapter.notifyDataSetChanged();

                if (arrayList.isEmpty()){
                    Toast.makeText(getActivity(), "All is Called.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), "GAHIG ULO ignang HUMANA...", Toast.LENGTH_SHORT).show();
                }
                                }
        });
    }
    private void cdDate() {

        Calendar calendar = Calendar.getInstance();
        String GetCurrentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        DateDotay.setText(GetCurrentDate);

    }
    private void textArraygetname() {
        final String key = getActivity().getIntent().getExtras().get("gcid").toString();
        DatabaseReference mref = FirebaseDatabase.getInstance().getReference().child("Groups").child(key);

        mref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String datamen = dataSnapshot.child("Member").getKey();
                DatabaseReference refer = FirebaseDatabase.getInstance().getReference()
                        .child("Groups")
                        .child(key).child(datamen);

                refer.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot ds: dataSnapshot.getChildren()){
                            final String notcalled = ds.child("trickart").getValue(String.class);

                          if ("not Called".equals(notcalled)){
                              final String Fullname = ds.child("fullname").getValue(String.class);
                              arrayList.add(Fullname);
                              arrayAdapter.notifyDataSetChanged();

                          }



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
        refl = FirebaseDatabase.getInstance().getReference().child("Groups").child(key);
        refl.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final String datamen = dataSnapshot.child("Member").getKey();
                DatabaseReference refer = FirebaseDatabase.getInstance().getReference()
                        .child("Groups")
                        .child(key).child(datamen);
                refer.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds: dataSnapshot.getChildren()){
                            final String notcalled = ds.child("trickart").getValue(String.class);
                            String heyhey = "not Called";
                            DatabaseReference query = FirebaseDatabase.getInstance().getReference()
                                    .child("Groups").child(key).child(datamen);

                            FirebaseRecyclerOptions<memberlist> options =
                                    new FirebaseRecyclerOptions.Builder<memberlist>()
                                    .setQuery(query,memberlist.class)
                                    .build();

                            FirebaseRecyclerAdapter<memberlist,Nani> adapter =
                                    new FirebaseRecyclerAdapter<memberlist, Nani>(options) {
                                @Override
                                protected void onBindViewHolder(@NonNull Nani holder, int position, @NonNull memberlist model) {

                                    holder.fn.setText(model.getFullname());
                                    holder.sta.setText(model.getTrickart());
                                    holder.uid.setText(model.getUid());
                                    holder.uid.setVisibility(View.INVISIBLE);
                                    holder.keyso.setVisibility(View.INVISIBLE);
                                    Picasso.get().load(model.getUrl()).into(holder.circleImageView);

                                }

                                @NonNull
                                @Override
                                public Nani onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                                   View V = LayoutInflater.from(viewGroup.getContext()).inflate(
                                           R.layout.finalerow,viewGroup,false);
                                    return new Nani(V);
                                }
                            };
                        listnotcold.setAdapter(adapter);
                        adapter.startListening();





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
    public static class Nani extends RecyclerView.ViewHolder{
        TextView keyso = itemView.findViewById(R.id.keyso);
        TextView fn = itemView.findViewById(R.id.fullname);
        TextView sta = itemView.findViewById(R.id.Statsu);
        TextView uid = itemView.findViewById(R.id.Uid);
        CircleImageView circleImageView = itemView.findViewById(R.id.kagerou);
        public Nani(@NonNull View itemView) {
            super(itemView);
        }
    }
}
