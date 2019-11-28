package com.example.arbiterchil.smartap;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.net.PasswordAuthentication;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class forget extends AppCompatActivity {

    CircularProgressButton foe;
    EditText reset;
    FirebaseAuth firebaseAuth;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        foe = findViewById(R.id.foe);
        reset = findViewById(R.id.reset);
        firebaseAuth = FirebaseAuth.getInstance();
        reference  = FirebaseDatabase.getInstance().getReference();

        foe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oten = reset.getText().toString();
                    firebaseAuth.sendPasswordResetEmail(oten).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()|reset.equals("")){
                                Toast.makeText(forget.this, "Password reset Email send", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(forget.this, login.class));
                                    finish();

                            }else{
                                Toast.makeText(forget.this, "Error Sending password reset", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }

        });
    }

}
