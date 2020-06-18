package com.amanmehta.covid19_tracker;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class DashBoard extends AppCompatActivity {
    TextView title, percnt,visitedPlace;
    float value;
    String Valuecity;
    float per;
    ImageButton img;
    Button testAgain;
    FirebaseAuth mFireBaseAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        title=findViewById(R.id.textView);
        percnt = findViewById(R.id.textView2);
        img = findViewById(R.id.imageButton);
        testAgain = findViewById(R.id.button);
        visitedPlace = findViewById(R.id.textView3);
        mFireBaseAuth = FirebaseAuth.getInstance();


        Bundle data = getIntent().getExtras();
        if (data != null) {
            value = data.getFloat("chance");
            Valuecity = data.getString("city");

        }

        if (value == 0){
            percnt.setText(String.valueOf(value) + "%");
            title.setText("You are Safe");
            title.setBackgroundColor(Color.parseColor("#1FAC1F"));
            visitedPlace.setText(Valuecity);

        } else {
            per = (value / 12) * 100;
            percnt.setText(String.valueOf(per) + "%");
            title.setText("You are at Risk");
            title.setBackgroundColor(Color.RED);
            visitedPlace.setText(Valuecity);
        }

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent m = new Intent(DashBoard.this,mediaActivity.class);
                startActivity(m);
            }
        });


        testAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent testA = new Intent(DashBoard.this, checkingSymptoms.class);
                startActivity(testA);
            }
        });

    }

    }



