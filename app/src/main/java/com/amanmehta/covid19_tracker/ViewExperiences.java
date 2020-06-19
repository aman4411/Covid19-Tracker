package com.amanmehta.covid19_tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.leo.simplearcloader.SimpleArcLoader;

public class ViewExperiences extends AppCompatActivity  {

    ListView list;
    SimpleArcLoader simpleArcLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_experiences);

        getSupportActionBar().setTitle("Experiences");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        list = findViewById(R.id.list);
        simpleArcLoader = findViewById(R.id.loader);
        simpleArcLoader.setVisibility(View.VISIBLE);
        simpleArcLoader.start();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);
            }
        }, 3000);

       Query query =  FirebaseDatabase.getInstance().getReference().child("experiences");
       FirebaseListOptions<Messages> options = new FirebaseListOptions.Builder<Messages>().setLayout(android.R.layout.simple_list_item_1).setQuery(query,Messages.class).build();
        FirebaseListAdapter<Messages> adapter = new FirebaseListAdapter<Messages>(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Messages model, int position) {
                TextView tv = (TextView) v;
                tv.setText("Email : " + model.getEmail() + "\n" + "Experience : " + model.getExperience() +"\n");
            }
        };
        adapter.startListening();
        list.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
