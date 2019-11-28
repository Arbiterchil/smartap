package com.example.arbiterchil.smartap;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
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
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.IOException;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import de.hdodenhof.circleimageview.CircleImageView;

public class Register extends AppCompatActivity {

    EditText emailed;
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
    TextView c;

    private static final int GAL = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userprof = FirebaseStorage.getInstance().getReference().child("image");
        prog = new ProgressDialog(this);
//
//        int width = dm.widthPixels;
//        int hiegth = dm.heightPixels;
//
//        getWindow().setLayout((int)(width*.9),(int)(hiegth*.4));
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

//        mDatabase = FirebaseDatabase.getInstance().
//                getReference("SmartDab").child("User").
//                child(firebaseAuth.getCurrentUser().getUid());

//        setImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////
////                Intent gallery = new Intent();
////                gallery.setAction(Intent.ACTION_GET_CONTENT);
////                gallery.setType("image/*");
////                startActivityForResult(gallery,GAL);
//                androidver();
//            }
//        });
        flname = findViewById(R.id.flname);
        idnumer = findViewById(R.id.idnumer);
        pen = findViewById(R.id.pen);
        emailed = findViewById(R.id.emailed);
        passwords = findViewById(R.id.passwords);
        rge = findViewById(R.id.rge);
        firebaseAuth = FirebaseAuth.getInstance();
        logs = findViewById(R.id.logs);
        lastname = findViewById(R.id.lname);

        nani();

        logs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prog.setTitle("Please Wait.....");
                prog.setMessage("We are Saving Up your Data...");
                prog.setCanceledOnTouchOutside(false);
                prog.show();
                logs.setVisibility(View.INVISIBLE);
                final String conpass = pen.getText().toString();
                final String email = emailed.getText().toString().trim();
                final String password = passwords.getText().toString().trim();
                final String fn = flname.getText().toString().trim();
                final String ln = lastname.getText().toString().trim();
                final String fullname = lastname.getText().toString().trim()+", "+flname.getText().toString().trim();
                final  String idnumber = idnumer.getText().toString().trim();
                final int selectedId = rge.getCheckedRadioButtonId();
                final RadioButton radioButton = findViewById(selectedId);

                if(selectedId  == -1) {
                    Toast.makeText(Register.this, "Please Choose Status", Toast.LENGTH_SHORT).show();
                    rge.requestFocus();
                    prog.dismiss();
                    logs.setVisibility(View.VISIBLE);
                }else if(fn.isEmpty()){
                    flname.requestFocus();
                    Toast.makeText(Register.this, "Please put First Name", Toast.LENGTH_SHORT).show();
                    prog.dismiss();
                    logs.setVisibility(View.VISIBLE);
                }else if(ln.isEmpty()){
                    lastname.requestFocus();
                    Toast.makeText(Register.this, "Put Last Name", Toast.LENGTH_SHORT).show();
                    prog.dismiss();
                    logs.setVisibility(View.VISIBLE);
                }else if(!password.equals(conpass)){
                    pen.requestFocus();
                    Toast.makeText(Register.this, "Confirm Password Error ", Toast.LENGTH_SHORT).show();
                    prog.dismiss();
                    logs.setVisibility(View.VISIBLE);
                }else if(email.isEmpty()){
                    emailed.requestFocus();
                    Toast.makeText(Register.this, "Gmail or Email Required", Toast.LENGTH_SHORT).show();
                    prog.dismiss();
                    logs.setVisibility(View.VISIBLE);
                }else if( password.isEmpty()){
                    passwords.requestFocus();
                    Toast.makeText(Register.this, "Password Required", Toast.LENGTH_SHORT).show();
                    prog.dismiss();
                    logs.setVisibility(View.VISIBLE);
                }else if( idnumber.isEmpty()){
                    idnumer.requestFocus();
                    Toast.makeText(Register.this, "Please Put an ID", Toast.LENGTH_SHORT).show();
                    prog.dismiss();
                    logs.setVisibility(View.VISIBLE);
                }else{
                    final String status = radioButton.getText().toString().trim();
                    firebaseAuth.createUserWithEmailAndPassword
                            (email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                smartdab smartasslove = new smartdab(email, password, fullname, idnumber, status,"");

                                FirebaseDatabase.getInstance().getReference().child("User").
                                        child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(smartasslove).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(Register.this, "Save Successfully", Toast.LENGTH_SHORT).show();
                                            pen.setText("");
                                            emailed.setText("");
                                            passwords.setText("");
                                            flname.setText("");
                                            lastname.setText("");
                                            lastname.setText("");
                                            flname.setText("");
                                            idnumer.setText("");
                                            prog.dismiss();
                                            logs.setVisibility(View.VISIBLE);
                                        } else {

                                            Toast.makeText(Register.this, "Please try again!.", Toast.LENGTH_SHORT).show();
                                            logs.setVisibility(View.VISIBLE);
                                            prog.dismiss();
                                        }
                                    }

                                });
                            } else {

                                Toast.makeText(Register.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                                logs.setVisibility(View.VISIBLE);
//                                loadProg.setVisibility(View.INVISIBLE);
                                prog.dismiss();
                            }
                        }
                    });


                }


            }
        });
    }

    private void nani() {
        idnumer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                final String idnums = idnumer.getText().toString();
//                Query query =  FirebaseDatabase.getInstance().getReference("SmartDab").child("User").
//                        orderByChild("idnumber").equalTo(idnums);
//                query.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                        Toast.makeText(getActivity(), "Already Exists ID Number", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                final String idnums = idnumer.getText().toString();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("User");

                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){

                            for (DataSnapshot ds : dataSnapshot.getChildren()){

                                String same = ds.child("idnumber").getValue(String.class);

                                if (same.equals(idnums)){
                                    Toast.makeText(Register.this, "ID Number Already Exists", Toast.LENGTH_SHORT).show();
                                    idnumer.setText("");

                                }


                            }


                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });






            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        if(firebaseAuth.getCurrentUser() != null){

        }
    }
    public  void androidver(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            try{


                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},555);
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

                prog.setTitle("Were Setting Your Profile");
                prog.setMessage("Please Wait were Putting you image.");
                prog.setCanceledOnTouchOutside(false);
                prog.show();
                Uri re = result.getUri();
                Picasso.get().load(re).into(setImage);

                final StorageReference fearme  = userprof.child(re.getLastPathSegment().trim());

                fearme.putFile(re).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            prog.dismiss();
                        fearme.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                final String tapload = uri.toString();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(Register.this, "Fail Uploading Your Image", Toast.LENGTH_SHORT).show();
                        prog.dismiss();
                    }
                });
            }
        }
    }
//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//
//    }
}