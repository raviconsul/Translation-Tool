package com.example.ravi.transitiontool;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Splash activity for the app
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this, HomeScreen.class);
                startActivity(intent);
                MainActivity.this.finish();

                overridePendingTransition(R.anim.anim_fade_in,R.anim.anim_fade_out);
            }
        },4000);
    }
}
