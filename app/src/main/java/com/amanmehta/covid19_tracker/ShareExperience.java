package com.amanmehta.covid19_tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.leo.simplearcloader.SimpleArcLoader;

public class ShareExperience extends AppCompatActivity implements View.OnClickListener{

    EditText message;
    Button share;
    SimpleArcLoader simpleArcLoader;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_experience);

        getSupportActionBar().setTitle("Share Experience");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        
        message = findViewById(R.id.shareEt);
        share = findViewById(R.id.shareBtn);
        simpleArcLoader = findViewById(R.id.loader);
        mAuth = FirebaseAuth.getInstance();
        
        share.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == share){
            String experience = message.getText().toString();
            String email = mAuth.getCurrentUser().getEmail().toString();

            if (experience.isEmpty()) {
                message.setError("Experience is required");
                message.requestFocus();
                return;
            }

            addExperience(email,experience);
        }
    }

    private void addExperience(String email, String experience) {

        simpleArcLoader.setVisibility(View.VISIBLE);
        simpleArcLoader.start();

        Messages message = new Messages(email,experience);
        FirebaseDatabase.getInstance().getReference().child("experiences").push().setValue(message).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(),"Experience Shared",Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
