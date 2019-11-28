  package com.example.arbiterchil.smartap;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

import de.hdodenhof.circleimageview.CircleImageView;

  public class Compute extends AppCompatActivity {
      DatabaseReference ferer;
      DatabaseReference defer;
      DatabaseReference refer;
      Spinner spintheshit;
      TextView uidpor;
      ListView list;
      FirebaseListAdapter listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compute);
        refer = FirebaseDatabase.getInstance().getReference().child("RecitationData");
        ferer = FirebaseDatabase.getInstance().getReference().child("Group");
        defer = FirebaseDatabase.getInstance().getReference().child("GroupMember");
        list = findViewById(R.id.listtheshit);



        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String RegisteredUserID = currentUser.getUid();
        Query query = FirebaseDatabase.getInstance().getReference().child("Groups")
                .orderByChild("uid")
                .equalTo(RegisteredUserID);
        FirebaseListOptions<Group> options =new FirebaseListOptions.Builder<Group>()
                .setLayout(R.layout.row)
                .setLifecycleOwner(Compute.this)
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
                    Intent gcroom = new Intent(Compute.this, computes.class);
                    Group g = (Group)parent.getItemAtPosition(position);
                    gcroom.putExtra("groupname",g.getGroupname());
                    gcroom.putExtra("gcid",g.getGcid());

                    startActivity(gcroom);

                } catch (Exception e) {

                } }});
    }



//      private void spingroup() {
//          FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
//          final String RegisteredUserID = currentUser.getUid();
//          Query query = FirebaseDatabase.getInstance().getReference("SmartDab").child("Groups")
//                  .orderByChild("uid")
//                  .equalTo(RegisteredUserID);
//            query.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    List<String> are = new ArrayList<>();
//                  for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                      String NameG = ds.child("groupname").getValue(String.class);
//
//                      are.add(NameG);
//                      Collections.sort(are);
//                      ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>
//                              (Compute.this, android.R.layout.simple_spinner_item,
//                                      are);
//                      areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                      spin.setAdapter(areasAdapter);
//
//
//                      spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                          @Override
//                          public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//                              final String gcname = spin.getSelectedItem().toString();
//                          Query query1 = FirebaseDatabase.getInstance().getReference("SmartDab").child("Groups")
//                                  .orderByChild("groupname")
//                                  .equalTo(gcname);
//
//                          query1.addListenerForSingleValueEvent(new ValueEventListener() {
//                              @Override
//                              public void onDataChange( DataSnapshot dataSnapshot) {
//                                  for (DataSnapshot oh : dataSnapshot.getChildren()) {
//
//                                      String id = oh.child("gcid").getValue(String.class);
//
//                                      ohshit.setText(id);
//                                      final String uid = ohshit.getText().toString();
//                                      DatabaseReference ref = FirebaseDatabase.getInstance().getReference("SmartDab")
//                                              .child("Groups").child(uid);
//
//                                      ref.addListenerForSingleValueEvent(new ValueEventListener() {
//                                          @Override
//                                          public void onDataChange(DataSnapshot dataSnapshot) {
//                                              String de = dataSnapshot.child("Member").getKey();
//                                              DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("SmartDab")
//                                                      .child("Groups").child(uid).child(de);
//                                              databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//                                                  @Override
//                                                  public void onDataChange( DataSnapshot dataSnapshot) {
//
//                                                      List<String> are = new ArrayList<>();
//                                                      for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                                                          String Name = ds.child("fullname").getValue(String.class);
//                                                          are.add(Name);
//                                                          Collections.sort(are);
//                                                          ArrayAdapter<String> areasAdapter = new ArrayAdapter<>
//                                                                  (Compute.this, android.R.layout.simple_spinner_item,
//                                                                          are);
//                                                          areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                                                          spins.setAdapter(areasAdapter);
//                                                      }
//
//                                                  }
//
//                                                  @Override
//                                                  public void onCancelled( DatabaseError databaseError) {
//
//                                                  }
//                                              });
//                                          }
//
//                                          @Override
//                                          public void onCancelled( DatabaseError databaseError) {
//
//                                          }
//                                      });
//                                  }
//                              }
//
//                              @Override
//                              public void onCancelled( DatabaseError databaseError) {
//
//                              }
//                          });
//
//
//
//                          }
//
//                          @Override
//                          public void onNothingSelected(AdapterView<?> parent) {
//
//                          }
//                      });
//
//
//                  }
//                }
//
//                @Override
//                public void onCancelled( DatabaseError databaseError) {
//
//                }
//            });
//
//      private void spinGrading() {
//          String[] a = new String[]{
//                  "1st Grading","2nd Grading","3rd Grading","4th Grading"
//          };
//
//          ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>
//                  (Compute.this, android.R.layout.simple_spinner_item,
//                          a);
//          areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//          spinss.setAdapter(areasAdapter);
//    }
////      private void spinthegroup() {
////          FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
////          final String RegisteredUserID = currentUser.getUid();
////          Query query = FirebaseDatabase.getInstance().getReference("SmartDab").child("Groups")
////                  .orderByChild("uid")
////                  .equalTo(RegisteredUserID);
////
////          query.addListenerForSingleValueEvent(new ValueEventListener() {
////              @Override
////              public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
////                  List<String> are = new ArrayList<>();
////                  for (DataSnapshot ds : dataSnapshot.getChildren()) {
////                    String NameG = ds.child("groupname").getValue(String.class);
////
////                      are.add(NameG);
////                      Collections.sort(are);
////                      ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>
////                              (Compute.this, android.R.layout.simple_spinner_item,
////                                      are);
////                      areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
////                      spins.setAdapter(areasAdapter);
////                  spins.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
////                      @Override
////                      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
////                                final String gcname = spins.getSelectedItem().toString();
////                          Query query1 = FirebaseDatabase.getInstance().getReference("SmartDab").child("Groups")
////                                  .orderByChild("groupname")
////                                  .equalTo(gcname);
////
////
////                          query1.addListenerForSingleValueEvent(new ValueEventListener() {
////                              @Override
////                              public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
////
////                                  for (DataSnapshot oh : dataSnapshot.getChildren()){
////
////                                      String id = oh.child("gcid").getValue(String.class);
////
////                                      ohshit.setText(id);
////
////                                      final String uid = ohshit.getText().toString();
////
////                                       DatabaseReference ref = FirebaseDatabase.getInstance().getReference("SmartDab")
////                                              .child("Groups").child(uid);
////
////                                      ref.addListenerForSingleValueEvent(new ValueEventListener() {
////                                          @Override
////                                          public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
////
////                                              String de = dataSnapshot.child("Member").getKey();
////
////                                              DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("SmartDab")
////                                                      .child("Groups").child(uid).child(de);
////                                              databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
////                                                  @Override
////                                                  public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
////                                                      List<String> are = new ArrayList<>();
////                                                      for (DataSnapshot ds : dataSnapshot.getChildren()) {
////                                                          String Name = ds.child("fullname").getValue(String.class);
////                                                          are.add(Name);
////                                                          Collections.sort(are);
////                                                          ArrayAdapter<String> areasAdapter = new ArrayAdapter<>
////                                                                  (Compute.this, android.R.layout.simple_spinner_item,
////                                                                          are);
////                                                          areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
////                                                          spinss.setAdapter(areasAdapter);
////
////                                                          DatabaseReference refg = FirebaseDatabase.getInstance().getReference("SmartDab")
////                                                                  .child("Records");
////                                                          refg.addListenerForSingleValueEvent(new ValueEventListener() {
////                                                              @Override
////                                                              public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
////                                                                  List<String> are = new ArrayList<>();
////                                                                  for (DataSnapshot ds:dataSnapshot.getChildren()){
////                                                                      String getAll = ds.getKey();
////                                                                      are.add(getAll);
////                                                                      Collections.sort(are);
////                                                                      ArrayAdapter<String> areasAdapter = new ArrayAdapter<>
////                                                                              (Compute.this, android.R.layout.simple_spinner_item,
////                                                                                      are);
////                                                                      areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
////                                                                      spin.setAdapter(areasAdapter);
////                                                                      spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
////                                                                          @Override
////                                                                          public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
////                                                                              final String gt = spin.getSelectedItem().toString();
////                                                                              final String piti = spinses.getSelectedItem().toString();
////                                                                              final String name = spinss.getSelectedItem().toString();
////
////                                                                              Query query2 = FirebaseDatabase.getInstance().getReference("SmartDab")
////                                                                                      .child("Records").child(gt).child(uid)
////                                                                                      .orderByChild("fullname").equalTo(name);
////
////                                                                              query2.addListenerForSingleValueEvent(new ValueEventListener() {
////                                                                                  @Override
////                                                                                  public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
////                                                                                      List<String> wew = new ArrayList<>();
////                                                                                      for (DataSnapshot ds:dataSnapshot.getChildren()){
////                                                                                          String scores = ds.child("score").getValue(String.class);
////
////
////
////                                                                                          wew.add(scores);
//////                                                                                          int s= 0;
//////                                                                                          int weww = Integer.parseInt(scores);
//////                                                                                         s = (int) Double.parseDouble(wew.get(weww));
//////
//////                                                                                         String result = String.valueOf(s);
//////
//////                                                                                          Toast.makeText(Compute.this,
//////                                                                                                  "OverAll " + result,
//////                                                                                                  Toast.LENGTH_SHORT).show();
////
////
////
////                                                                                          Collections.sort(wew);
////                                                                                          ArrayAdapter<String> areasAdapter = new ArrayAdapter<>
////                                                                                                  (Compute.this, android.R.layout.simple_spinner_item,
////                                                                                                          wew);
////                                                                                             areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
////                                                                                          homan.setAdapter(areasAdapter);
////
////
////
////
////
////                                                                                      }
////
////                                                                                  }
////
////                                                                                  @Override
////                                                                                  public void onCancelled(@NonNull DatabaseError databaseError) {
////
////                                                                                  }
////                                                                              });
////
////
////
////
////                                                                          }
////
////                                                                          @Override
////                                                                          public void onNothingSelected(AdapterView<?> parent) {
////
////                                                                          }
////                                                                      });
////                                                                  }
////                                                              }
////
////                                                              @Override
////                                                              public void onCancelled(@NonNull DatabaseError databaseError) {
////
////                                                              }
////                                                          });
////
////
////                                                      }
////                                                  }
////
////                                                  @Override
////                                                  public void onCancelled(@NonNull DatabaseError databaseError) {
////
////                                                  }
////                                              });
////
////
////                                          }
////
////                                          @Override
////                                          public void onCancelled(@NonNull DatabaseError databaseError) {
////
////                                          }
////                                      });
////
////
////                                  }
////
////                              }
////
////                              @Override
////                              public void onCancelled(@NonNull DatabaseError databaseError) {
////
////                              }
////                          });
////
////                      }
////
////                      @Override
////                      public void onNothingSelected(AdapterView<?> parent) {
////
////                      }
////                  });
////
////                  }
////
////              }
////
////              @Override
////              public void onCancelled(@NonNull DatabaseError databaseError) {
////
////              }
////          });
////    }

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

  }
