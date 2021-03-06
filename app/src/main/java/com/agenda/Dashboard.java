package com.agenda;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
/* Group Members are:-
Abdulghafor Nurali
Dennis Kanzira
Md Mamunur Rahman
Shafiq-Ur-Rehman
*/

public class Dashboard extends AppCompatActivity {
  db db;
  Cursor cursor;
  ContentValues values;
  long rowsAffected;
  TextView txt;
  LinearLayout layout;
  Button btn;
  MyCursorAdapter myCurAdaptor;
  ListView listV;
  private LatLng myLoc;
  private String address = "M1G3S6";
  private TextView txtDisplayWeather;
  String weatherStr;
  AlertDialog.Builder alert;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_dashboard);

    db = new db(getApplicationContext());
    listV = (ListView)findViewById(R.id.dashboard_Todo_list);

 /*//Moved following to onResume() to keep list refreshed
//////////////////Handling ToDo list////////////////////////////////////////
    cursor = db.getAll();
    cursor.moveToFirst();
    myCurAdaptor = new MyCursorAdapter(this, cursor);
    //myCurAdaptor.btnDeleteTask.setHeight(20);
    //myCurAdaptor.btnDeleteTask.setBackgroundResource(R.drawable.checkbox);
    listV.setAdapter(myCurAdaptor);
//////////////////////////////////////////////////////////////////////////
*/
    //GetWeather();
  }

  @Override
  protected void onResume()
  {
    super.onResume();

    //////////////////Handling ToDo list////////////////////////////////////////
    //db = new db(getApplicationContext());
    //listV = (ListView)findViewById(R.id.dashboard_Todo_list);
    cursor = db.getAll();
    cursor.moveToFirst();
    myCurAdaptor = new MyCursorAdapter(this, cursor);
    //myCurAdaptor.btnDeleteTask.setHeight(20);
    //myCurAdaptor.btnDeleteTask.setBackgroundResource(R.drawable.checkbox);
    listV.setAdapter(myCurAdaptor);
//////////////////////////////////////////////////////////////////////////
  }

  public void goToToDoList(View view) {
    Intent i = new Intent(this, ToDo.class);
    startActivity(i);
  }

  public void goToSMS(View view) {
    Intent i = new Intent(this, SMSActivity.class);
    startActivity(i);
  }
  public void goToWeather(View view) {
    Intent i = new Intent(this, Weather.class);
    startActivity(i);
  }

//  private void GetWeather()
//  {
//    //get post code from shared prefs
//    SharedPreferences prefs = getSharedPreferences("PostCode", 0);
//    String pCode = prefs.getString("postCode", null);
//    if(pCode != null)
//    {
//      address = pCode.trim();
//    }
//
//    //=============Get coords===============
//    LocationManager location = (LocationManager)getSystemService(Context.LOCATION_SERVICE);//p-20 lecture
//    Geocoder coder = new Geocoder(this);//p-28
//
//    try //following MUST be in try blk or else won't compile
//    {
//      List<Address> geocodeResults = coder.getFromLocationName(address, 1);//p-33
//      Iterator<Address> locations = geocodeResults.iterator();
//      while (locations.hasNext())
//      {
//        Address loc = locations.next();
//        String locInfo = String.format(Locale.CANADA,"Location: %f, %f\n", loc.getLatitude(), loc.getLongitude());
//        myLoc = new LatLng(loc.getLatitude() , loc.getLongitude());
//      }
//    }
//    catch (IOException e)
//    {
//      //
//      Log.e("GeoAddress", "Failed to get location info", e);
//    }
//    //=====================================
//
//    String url = "http://api.openweathermap.org/data/2.5/weather?";
//    url+="lat="+myLoc.latitude;
//    url+="&lon="+myLoc.longitude;
//    //url+="&appid=13f04464b7119837cf1dc4fa8b39caa3"; //from OpenWeatherMap website
//    url+="&appid=6f118e8e10b5354720a4816bca7b0a7a";
//
//    Log.d("URL",url);
//    new ReadJSONFeedTask().execute(url);
//  }
//
//  //Async Task
//  private class ReadJSONFeedTask extends AsyncTask<String, Void, String>//must b sub-class. Perform background operations and publish results on the UI thread without having to manipulate threads
//  {
//    //at least this fn must be overridden
//    protected String doInBackground(String... urls)//(String ...) is an array of parameters of type String, where as String[] is a single parameter.
//    {
//      return readJSONFeed(urls[0]);
//    }
//
//    protected void onPostExecute(String result)
//    {
//      try
//      {
//        //parsing weather data from OpenWeatherMap
//        //JSONArray jsonArray = new JSONArray(result);
//        JSONObject weatherJson = new JSONObject(result);//json str into a json obj w name-value pairs
//        JSONObject main = weatherJson.getJSONObject("main");
//        weatherStr = main.getString("temp");
//        double celciusDouble = Double.parseDouble(weatherStr) - 273.16;
//        int celcius = Double.valueOf(celciusDouble).intValue();
//        txtDisplayWeather.setText(String.valueOf(celcius) + (char) 0x00B0 + "C");//must comment-out call to task in onCreate or else this doesn't display ath
//
//
//        ////uncomment the code below & comment-out above 2 lines for it to work
//        ////parsing survey data
//        //JSONArray jsonArray = new JSONArray(result);
//        //Log.i("JSON", "Number of surveys in feed: " + jsonArray.length());
//        ////---print out the content of the json feed---
//        //for (int i = 0; i < jsonArray.length(); i++)
//        //{
//        //    JSONObject jsonObject = jsonArray.getJSONObject(i);
//        //    Toast.makeText(getBaseContext(), "Time is: " + jsonObject.getString("surveyTime") + " & ID is:  " + jsonObject.getString("appeId"), Toast.LENGTH_LONG).show();
//        //    return;//just show 1st result = surveyTime & appeId == Time is: 12:19:47 & ID is: 1
//        //}
//      }
//      catch (Exception e)
//      {
//        e.printStackTrace();
//      }
//    }
//  }
//
//
//  //run in bg via doInBackground() of AsyncTask<>
//  public String readJSONFeed(String address) {
//    URL url = null;
//    try {
//      url = new URL(address);
//    } catch (MalformedURLException e) {
//      e.printStackTrace();
//    };
//
//    StringBuilder stringBuilder = new StringBuilder();//For example, if z refers to a string builder object whose current contents are "start", then the method call z.append("le") would cause the string builder to contain "startle", whereas z.insert(4, "le") would alter the string builder to contain "starlet"
//
//    HttpURLConnection urlConnection = null;
//
//    try {
//      urlConnection = (HttpURLConnection) url.openConnection();//returns remote obj representing what was returned by that URL
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//    try {
//      InputStream content = new BufferedInputStream( urlConnection.getInputStream() );//Returns an input stream that reads from this open connection
//      BufferedReader reader = new BufferedReader( new InputStreamReader(content) );
//      String line;
//
//      while ((line = reader.readLine()) != null)
//      {
//        stringBuilder.append(line);
//      }
//    } catch (IOException e) {
//      e.printStackTrace();
//    } finally {
//      urlConnection.disconnect();
//    }
//    return stringBuilder.toString();
//  }
}
