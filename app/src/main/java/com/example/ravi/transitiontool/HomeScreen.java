package com.example.ravi.transitiontool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeScreen extends AppCompatActivity {

    Button detect, translate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        detect = (Button)findViewById(R.id.detect);
        translate = (Button) findViewById(R.id.translate);

        Toolbar toolbar = (Toolbar) findViewById(R.id.include);
        toolbar.setTitle("Welcome to Translation Tool !!");
    }

    // when detect language is pressed....not ready yet
    public void detectLanguage(final View view)
    {
        Toast.makeText(getApplicationContext(), "Under Construction", Toast.LENGTH_SHORT).show();
    }

    // when translate text is pressed
    public void translateText(View view)
    {

        Intent i = new Intent(HomeScreen.this, transIt.class);
        startActivity(i);

    }
}
