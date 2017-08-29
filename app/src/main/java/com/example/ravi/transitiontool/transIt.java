package com.example.ravi.transitiontool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class transIt extends AppCompatActivity {

    Spinner textL, convertL;
    EditText text;
    Button button;
    String bLanCode, toLanCode;
    TextView result, APItext;

    // API parameters
    private String baseURL = "https://translate.yandex.net/api/v1.5/tr.json/translate?lang=";
    private String APIKey = "&key=trnsl.1.1.20170708T180725Z.7b0f1dcf46c77c1c.62be11091b799574c148225c326a58617ede51c5";


    // String for spinners
    String[] tLang = {"From...", "English", "Hindi", "French", "Bengali", "Gujarati"};
    String[] cLang = {"To...", "Hindi", "English", "French", "Bengali", "Gujarati"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans_it);

        textL = (Spinner) findViewById(R.id.textL);
        convertL = (Spinner) findViewById(R.id.convertL);
        text = (EditText) findViewById(R.id.text);
        button = (Button) findViewById(R.id.button);
        result = (TextView) findViewById(R.id.result);
        APItext = (TextView) findViewById(R.id.APItext);
        //APItext.setText("Powered by Yandex. Translate");

       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //toolbar.setTitle("Translate Text");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeButtonEnabled(true);

        // setting lists to spinners
        ArrayAdapter arrayAdapter1 = new ArrayAdapter(getApplicationContext(), R.layout.spin,tLang);
        textL.setAdapter(arrayAdapter1);

        ArrayAdapter arrayAdapter2 = new ArrayAdapter(getApplicationContext(), R.layout.spin,cLang);
        convertL.setAdapter(arrayAdapter2);






    }

    // home button of toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                Intent homeIntent = new Intent(this, HomeScreen.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    // on clicking proceed
    public void proceed(View view)
    {
        String finalText = text.getText().toString();

        final String baseLan = textL.getSelectedItem().toString();
        final String toLan = convertL.getSelectedItem().toString();

        // removing all the white spaces from the string
        finalText = finalText.replaceAll("\\s+", "%20");

        Log.i("finalText", finalText);

        switch (baseLan)
        {
            case "English":
                bLanCode = "en";
                break;
            case "Hindi":
                bLanCode = "hi";
                break;
            case "French":
                bLanCode = "fr";
                break;
            case "Bengali":
                bLanCode = "bn";
                break;
            case "Gujarati":
                bLanCode = "gu";
                break;
            default:
                bLanCode = null;
                Toast.makeText(getApplicationContext(), "Choose valid language", Toast.LENGTH_SHORT).show();

        }

        switch (toLan)
        {
            case "English":
                toLanCode = "en";
                break;
            case "Hindi":
                toLanCode = "hi";
                break;
            case "French":
                toLanCode = "fr";
                break;
            case "Bengali":
                toLanCode = "bn";
                break;
            case "Gujarati":
                toLanCode = "gu";
                break;
            default:
                toLanCode = null;
                Toast.makeText(getApplicationContext(), "Choose valid language", Toast.LENGTH_SHORT).show();
        }

        // final API call URL
        String finalURL = baseURL + bLanCode + "-" + toLanCode + APIKey + "&text=" + finalText;

        Log.i("myURL", finalURL);


        // Use of Volley Library for getting JSON data
        if( bLanCode != null && toLanCode != null)
        {
            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, finalURL, null,

                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                String r = response.getString("text");
                                Log.i("JSON", response.toString());
                                String trans = response.getString("text");
                                JSONArray translate = new JSONArray(trans);
                                String res = translate.getString(0);
                                //Toast.makeText(getApplicationContext(), res, Toast.LENGTH_LONG).show();
                                result.setText(res);
                                result.setTextSize(25);

                            } catch (JSONException e) {
                                Toast.makeText(getApplicationContext(), "Server Error !!", Toast.LENGTH_LONG).show();
                                e.printStackTrace();
                            }
                        }
                    },

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "Server Error !!", Toast.LENGTH_LONG).show();
                            Log.i("JSON error", error.toString());
                        }
                    }

            );

            MySingleton.getInstance(getApplicationContext()).addToRequestQue(jsonObjectRequest);
        }

    }

    public void APIClick(View view)
    {
       // Not ready yet.
    }
}
