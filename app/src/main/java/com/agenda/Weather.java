package com.agenda;


import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.FontRequest;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;


public class Weather extends AppCompatActivity {

    EditText cityField;
    TextView resultWeather;
    String cityToFind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);



          /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        cityField=(EditText) findViewById(R.id.editText_EC);
        resultWeather=(TextView) findViewById(R.id.textView_Weather);

        //initial weather
        FindWeather(resultWeather);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Loading Weather", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                FindWeather(view);

            }
        });
    }

    //This Method will execute the link and find weather data and info
    public void FindWeather(View v){
        cityToFind=cityField.getText().toString();
        try{

            //Here we want to make asynTack to get the Data in Background
            ExecuteTask tasky=new ExecuteTask();
            tasky.execute("http://api.openweathermap.org/data/2.5/weather?q="+cityToFind+"&APPID=d905a0f7d1fc925f9df1cca7d7a1c8bf");

        }catch (Exception e){
            e.printStackTrace();

        }
    }

    //This task will get all data from website in background
    public class ExecuteTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            String  result="";
            URL url;
            HttpURLConnection urlConnection=null;

            try {
                url= new URL(strings[0]);
                urlConnection=(HttpURLConnection)url.openConnection();
                InputStream is=urlConnection.getInputStream();
                InputStreamReader reader=new InputStreamReader(is);

                int data = reader.read();
                while (data!=-1){
                    char current=(char)data;
                    result+=current;
                    data=reader.read();
                }

                return result;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //return  null;//Dennis's code

            //I changed it or else app crash if city name is misspelled & weather API returns err etc
            String jsonInCaseOfErr =
    "{\"weather\":[{\"id\":801,\"main\":\"City Not Found\",\"description\":\"City Not Found\",\"icon\":\"02d\"}]"
    +",\"main\":{\"temp\":275.72,\"pressure\":1019,\"humidity\":47,\"temp_min\":275.15,\"temp_max\":276.15}"
    +",\"visibility\":14484,\"wind\":{\"speed\":8.2,\"deg\":320,\"gust\":13.4}"
    +",\"clouds\":{\"all\":20}"
    +",\"dt\":1524175680"
    +",\"sys\":{\"type\":1,\"id\":3721,\"message\":0.0039,\"country\":\"CA\",\"sunrise\":1524133603,\"sunset\":1524182817}"
    +",\"id\":6167865,\"name\":\"Toronto\",\"cod\":200}";

            return  jsonInCaseOfErr;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try{
                String message="";
                JSONObject jsonObject = new JSONObject(s);
                String infoWeatherToday=jsonObject.getString("weather");
                JSONArray array = new JSONArray(infoWeatherToday);
                // now er want to get the texts as they are in JSON

                for(int i=0;i<array.length();i++){
                    JSONObject jsonSecondary=array.getJSONObject(i);
                    String main="";
                    String description="";
                    main=jsonSecondary.getString("main");
                    description=jsonSecondary.getString("description");
                    if (main!=""&& description!="" ){
                        message+=main + ": " + description + "\r\n";
                    }
                }
                if (message != ""){
                    resultWeather.setText(message);
                }else {
                    Toast.makeText(Weather.this,"An Error Occured!!!",Toast.LENGTH_SHORT).show();
                }

            }catch (JSONException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
