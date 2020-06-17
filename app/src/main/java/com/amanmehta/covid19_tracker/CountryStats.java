package com.amanmehta.covid19_tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class CountryStats extends AppCompatActivity {

    private int countryPosition;

    TextView tvCases,tvRecovered,tvCritical,tvActive,tvTodayCases,tvTotalDeaths,tvTodayDeaths,tvCountryName,tvTests;
    ScrollView scrollStats;
    SimpleArcLoader simpleArcLoader;
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_stats);

        Intent intent = getIntent();
        countryPosition = intent.getIntExtra("position",0);

        getSupportActionBar().setTitle("Details of " + AffectedCountries.countryModellist.get(countryPosition).getCountry());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tvCases = findViewById(R.id.tvCases);
        tvRecovered = findViewById(R.id.tvRecovered);
        tvCritical = findViewById(R.id.tvCritical);
        tvActive = findViewById(R.id.tvActive);
        tvTodayCases = findViewById(R.id.tvTodayCases);
        tvTotalDeaths = findViewById(R.id.tvTotalDeaths);
        tvTodayDeaths = findViewById(R.id.tvTodayDeaths);
        tvCountryName = findViewById(R.id.tvCountryName);
        tvTests = findViewById(R.id.tvTests);

        scrollStats = findViewById(R.id.scrollStats);
        pieChart = findViewById(R.id.piechart);
        simpleArcLoader = findViewById(R.id.loader);

        tvCases.setText(AffectedCountries.countryModellist.get(countryPosition).getCases());
        tvRecovered.setText(AffectedCountries.countryModellist.get(countryPosition).getRecovered());
        tvCritical.setText(AffectedCountries.countryModellist.get(countryPosition).getCritical());
        tvActive.setText(AffectedCountries.countryModellist.get(countryPosition).getActive());
        tvTodayCases.setText(AffectedCountries.countryModellist.get(countryPosition).getTodayCases());
        tvTotalDeaths.setText(AffectedCountries.countryModellist.get(countryPosition).getDeaths());
        tvTodayDeaths.setText(AffectedCountries.countryModellist.get(countryPosition).getTodayDeaths());
        tvTests.setText(AffectedCountries.countryModellist.get(countryPosition).getTests());
        tvCountryName.setText(AffectedCountries.countryModellist.get(countryPosition).getCountry());

        //Creating Pie Chart
        //pieChart.addPieSlice(new PieModel("Cases",Integer.parseInt(tvCases.getText().toString()), Color.parseColor("#ffa726")));
        pieChart.addPieSlice(new PieModel("Recovered",Integer.parseInt(tvRecovered.getText().toString()), Color.parseColor("#66bb6a")));
        pieChart.addPieSlice(new PieModel("Total Deaths",Integer.parseInt(tvTotalDeaths.getText().toString()), Color.parseColor("#ef5350")));
        pieChart.addPieSlice(new PieModel("Active",Integer.parseInt(tvActive.getText().toString()), Color.parseColor("#29b6f6")));
        pieChart.startAnimation();
        simpleArcLoader.stop();
        simpleArcLoader.setVisibility(View.GONE);
        scrollStats.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
