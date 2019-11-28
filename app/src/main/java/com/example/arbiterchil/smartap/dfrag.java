package com.example.arbiterchil.smartap;


import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.jackandphantom.blurimage.BlurImage;
import com.jgabrielfreitas.core.BlurImageView;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.IOException;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class dfrag extends Fragment {


    private int RESULT_OK;

    public dfrag() {
        // Required empty public constructor
    }
    SeekBar seekBar;
    BlurImageView blur;
    CircleImageView setimage;
    TextView fullnames,stats,emails,udis,idnum;
    ImageView reseta,update;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    private static final int GalleryPick = 1;
    private StorageReference Userprof;
    private ProgressDialog loadingbar;
    DatabaseReference fearme;
    Uri pickUri;
    private String curredntid;
    private DatabaseReference mDatabase;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)


    {
        // Inflate the layout for this fragment

        Log.d("dfrag", "Nisulod");
        View view = inflater.inflate(R.layout.fragment_dfrag, container, false);
//        getActivity().getWindow().setBackgroundDrawableResource(R.drawable.bgbock);

        view.clearFocus();
        blur = view.findViewById(R.id.blurry);
        loadingbar = new ProgressDialog(getActivity());
        setimage = view.findViewById(R.id.setImage);
        fullnames = view.findViewById(R.id.fullnames);
        stats = view.findViewById(R.id.stats);
        emails = view.findViewById(R.id.emails);
        udis = view.findViewById(R.id.udis);
        idnum = view.findViewById(R.id.idnum);






        Userprof = FirebaseStorage.getInstance().getReference().child("images");

        udis.setVisibility(View.GONE);

        fullnames.setEnabled(false);
        stats.setEnabled(false);
        emails.setEnabled(false);
        udis.setEnabled(false);
        idnum.setEnabled(false);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        mDatabase = FirebaseDatabase.getInstance().
                getReference().child("User").
                child(firebaseAuth.getCurrentUser().getUid());

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    smartdab smartdab = dataSnapshot.getValue(smartdab.class);
                    idnum.setText(smartdab.getIdnumber());
                    fullnames.setText(smartdab.getFullname());
                    stats.setText(smartdab.getStatus());
                    emails.setText(smartdab.getEmail());
                    udis.setText(firebaseAuth.getCurrentUser().getUid());

//                    Glide.with(getActivity()).load(smartdab.getUrl()).into(setimage);



                }catch(Exception e){
                    Log.d("dfrag", e.getMessage());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        setimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uid = udis.getText().toString();
                Intent pushkey = new Intent(getActivity(),Updateform.class);
                pushkey.putExtra("key",uid);
                startActivity(pushkey);
            }
        });



//        fearme = FirebaseDatabase.getInstance().getReference("SmartDab").child("User")
//                .child(firebaseAuth.getCurrentUser().getUid());
//        fearme.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//
//                    String va = dataSnapshot.child("image").getValue(String.class);
//
//                    Picasso.get().load(va).into(setimage);
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



//           setimage.setOnClickListener(new View.OnClickListener() {
//               @Override
//               public void onClick(View v) {
//                   startActivity(new Intent(getActivity(),Register.class));
//               }
//           });

voidstar();

    return view;
    }

    private void voidstar() {

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        final String RegisteredUserID = currentUser.getUid();

        final Bundle b = new Bundle();
        b.putString("userId", RegisteredUserID);

        DatabaseReference jLoginDatabase = FirebaseDatabase.getInstance().getReference().child("User").child(RegisteredUserID);

        jLoginDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String userType = dataSnapshot.child("status").getValue().toString();
                switch (userType) {
                    case "Teacher":



                        break;
                    case "Student":

                        UpdateStatus("online");


                        break;


                    default:
                        Toast.makeText(getActivity(), "Can't Identify User", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    private void UpdateStatus(String state) {


        HashMap<String,Object> online = new HashMap<>();
        online.put("state",state);

        curredntid = firebaseAuth.getCurrentUser().getUid();
        mDatabase.updateChildren(online);



    }

//    private void UpdateKamu() {
//
//        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("User")
//                .child(firebaseAuth.getCurrentUser().getUid());
//
//            ref.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//
//
//                    String full = fullnames.getText().toString();
//                    String email = emails.getText().toString();
//                    String id = idnum.getText().toString();
//
//                    ref.child("fullname").setValue(full);
//                    ref.child("email").setValue(email);
//                    ref.child("idnumber").setValue(id);
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                }
//            });
//
//
//    }

    @Override
    public void onStart() {
        super.onStart();

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String gago = dataSnapshot.child("url").getValue(String.class);
                Glide.with(getActivity()).load(gago).into(setimage);
                Glide.with(getActivity()).load(gago)
                        .centerCrop()
                        .into(blur);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
//    @Override
//    public void onStop() {
//        super.onStop();
//
//
//        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
//        final String RegisteredUserID = currentUser.getUid();
//
//        final Bundle b = new Bundle();
//        b.putString("userId", RegisteredUserID);
//
//        DatabaseReference jLoginDatabase = FirebaseDatabase.getInstance().getReference().child("User").child(RegisteredUserID);
//
//        jLoginDatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                String userType = dataSnapshot.child("status").getValue().toString();
//                switch (userType) {
//                    case "Teacher":
//
//
//
//                        break;
//                    case "Student":
//
//
//                        UpdateStatus("offline");
//
//
//
//                        break;
//
//
//                    default:
//                        Toast.makeText(getActivity(), "Can't Identify User", Toast.LENGTH_SHORT).show();
//                }
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//    }
//
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//
//        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
//        final String RegisteredUserID = currentUser.getUid();
//
//        final Bundle b = new Bundle();
//        b.putString("userId", RegisteredUserID);
//
//        DatabaseReference jLoginDatabase = FirebaseDatabase.getInstance().getReference().child("User").child(RegisteredUserID);
//
//        jLoginDatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                String userType = dataSnapshot.child("status").getValue().toString();
//                switch (userType) {
//                    case "Teacher":
//
//
//
//                        break;
//                    case "Student":
//
//
//                        UpdateStatus("offline");
//
//
//                        break;
//
//
//                    default:
//                        Toast.makeText(getActivity(), "Can't Identify User", Toast.LENGTH_SHORT).show();
//                }
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }


}
