package com.amanmehta.covid19_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class checkingSymptoms extends AppCompatActivity {

    protected ListView listView;
    Button submit;
    Float count;
    Float selectedItem;
    Float percentage;
    EditText cityV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checking_symptomps);
        listView = findViewById(R.id.listview);
        submit = findViewById(R.id.submit);
        cityV = findViewById(R.id.city);

        getSupportActionBar().setTitle("Take a Test");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        ArrayList <String> symp = new ArrayList <String> ();
        symp.add("Difficulty in Breathing");
        symp.add("Shortness of breath");
        symp.add("Chest Pain");
        symp.add("Loss of Speech");
        symp.add("Loss of Movement");
        symp.add("Aches and Pain");
        symp.add("Sore Throat");
        symp.add("Headache");
        symp.add("Diarrhoea");
        symp.add("Conjunctivitis");
        symp.add("Loss of Smell or Taste");
        symp.add("Skin Rashes");


        final ArrayAdapter <String> array = new ArrayAdapter<String>(this, android.R.layout.select_dialog_multichoice,symp);
        listView.setAdapter(array);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        count = (float) listView.getCount();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (!listView.isItemChecked(i)){
                    selectedItem = (float)listView.getCheckedItemCount();


                } else {
                    selectedItem = (float) listView.getCheckedItemCount();
                }
            }
        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dash = new Intent(checkingSymptoms.this,DashBoard.class);
                dash.putExtra("chance", selectedItem);
                dash.putExtra("city",cityV.getText().toString());
                startActivity(dash);
                finish();
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
