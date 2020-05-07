package com.amanmehta.covid19_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    private Handler mhandler;
    private Runnable mrunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mrunnable = new Runnable() {
            @Override
            public void run() {
                finish();
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        };

        mhandler = new Handler();
        mhandler.postDelayed(mrunnable,2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mhandler!=null && mhandler!=null)
        {
            mhandler.removeCallbacks(mrunnable);
        }
    }
}
