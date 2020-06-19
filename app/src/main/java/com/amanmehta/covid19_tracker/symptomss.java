package com.amanmehta.covid19_tracker;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class symptomss extends AppCompatActivity {
    ListView list23;
    String[] symp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptomss);
        list23= findViewById(R.id.list23);
        symp =new String[] {"1. Difficulty in Breathing", "2. Shortness of breath","3. Chest Pain","4. Loss of Speech","5. Loss of Movement","6. Aches and Pain","7. Sore Throat","8. Headache","9. Diarrhoea","10. Conjunctivitis","11. Loss of Smell or Taste","12. Skin Rashes"};
        ArrayAdapter symptoms = new ArrayAdapter<>( this, android.R.layout.simple_list_item_1, symp);
        list23.setAdapter(symptoms);
    }



}
