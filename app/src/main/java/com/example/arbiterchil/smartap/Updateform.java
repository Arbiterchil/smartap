package com.example.arbiterchil.smartap;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import br.com.simplepass.loading_button_lib.Utils;
import de.hdodenhof.circleimageview.CircleImageView;

public class Updateform extends AppCompatActivity {

    EditText passwords;
    EditText pen;
    EditText flname;
    EditText idnumer,lastname;
    Button logs;
    ImageView userimage;
    DatabaseReference mDatabase;
    FirebaseAuth firebaseAuth;
    RadioGroup rge;
    RadioButton Teacher, Student;
    FirebaseUser user;
    String uid;
    static int Preqcode = 1;
    static int REQUESCODE = 1;
    Uri pickedImage;
    private FirebaseStorage mStorage;
    ProgressBar progslog;
    FirebaseUser curus;
    CircleImageView setImage;
    StorageReference userprof;
    ProgressDialog prog;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser firebaseUser;
    EditText fl,ln,idnum;
    Button up;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateform);



        DisplayMetrics dm =  new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int hiegth = dm.heightPixels;

        getWindow().setLayout((int)(width*.9),(int)(hiegth*.7));



        userprof = FirebaseStorage.getInstance().getReference().child("image");
        prog = new ProgressDialog(this);
        final String key = getIntent().getExtras().get("key").toString();
        mDatabase = FirebaseDatabase.getInstance().
                getReference().child("User").
                child(key);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        fl = findViewById(R.id.firn);
        ln= findViewById(R.id.lan);
        up = findViewById(R.id.upda);
        idnum= findViewById(R.id.idnub);
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                try {
                    smartdab smartdab = dataSnapshot.getValue(smartdab.class);

                    Picasso.get().load(smartdab.getUrl()).into(setImage);

//                    Glide.with(getActivity()).load(smartdab.getUrl()).into(setimage);



                }catch(Exception e){
                    Log.d("dfrag", e.getMessage());
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prog.setTitle("We are Updating your Things");
                prog.setMessage("Please Wait The Data is Still Saving...");
                prog.setCanceledOnTouchOutside(false);
                prog.show();
                final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("User")
                        .child(firebaseAuth.getCurrentUser().getUid());

                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String flname= fl.getText().toString();
                        String ltname = ln.getText().toString();
                        String fullname = ltname+", "+flname;
                        String id = idnum.getText().toString();
                        if (TextUtils.isEmpty(flname)){
                            fl.requestFocus();

                        }else if(TextUtils.isEmpty(ltname)){
                            ln.requestFocus();
                            prog.dismiss();
                        }else if(TextUtils.isEmpty(id)){
                            idnum.requestFocus();
                            prog.dismiss();
                        }else{
                            ref.child("fullname").setValue(fullname);
                            ref.child("idnumber").setValue(id);
                            prog.dismiss();
                            Toast.makeText(Updateform.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                        }




                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });




        setImage = findViewById(R.id.setImage);
        setImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                androidver();
            }
        });


    }
    public  void androidver(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            try{


                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE},555);
            }catch(Exception e){

            }
        }else {
            PickedImage();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 555 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            PickedImage();
        }else{
            androidver();
        }

    }
    private void PickedImage() {

        CropImage.startPickImageActivity(this);


    }
    private void watafak(Uri im){
        CropImage.activity(im)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setMultiTouchEnabled(true)
                .start(this);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode== CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == Activity.RESULT_OK){

            Uri im = CropImage.getPickImageResultUri(this,data);
            watafak(im);

        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){

            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK){

                prog.setTitle("We are Setting Your Profile");
                prog.setMessage("Please Wait were Putting your Image.");
                prog.setCanceledOnTouchOutside(false);
                prog.show();
                Uri re = result.getUri();
                Picasso.get().load(re).into(setImage);
                final String key = getIntent().getExtras().get("key").toString();
                final StorageReference fearme  = userprof.child(key+".jpg");

                fearme.putFile(re).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        prog.dismiss();
                        fearme.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {


                                final String tapload = uri.toString();

                                mDatabase.child("url").setValue(tapload);




                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(Updateform.this, "Fail", Toast.LENGTH_SHORT).show();
                        prog.dismiss();
                    }
                });






            }



        }

    }
}
