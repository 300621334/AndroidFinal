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

public class ToDo extends AppCompatActivity {

    //region >>> Variables
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
    String weatherStr;
    AlertDialog.Builder alert;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        //AppCompatDelegate.setCompatVectorFromResourcesEnabled(true); //https://stackoverflow.com/questions/47526417/binary-xml-file-line-0-error-inflating-class-imageview

        //getApplication().deleteDatabase("myDb");//ONLY if db gets corupt then uncomment this line & run to delete db

        //initialize db helper class
        db = new db(getApplicationContext());
        listV = (ListView)findViewById(R.id.listV);
        layout = (LinearLayout)findViewById(R.id.layTasks);

        //find all students
        String sql = "select userId AS _id, firstName, status from users JOIN payments using (userId);";
        cursor = db.getAll();
        if(cursor.moveToFirst())
        {
            int index = cursor.getColumnIndex("_desc");
            String aDescription = cursor.getString(index);
        }




        //set CursorAdapter for ListView
        myCurAdaptor = new MyCursorAdapter(this, cursor);
        listV.setAdapter(myCurAdaptor);
       }

    //Create new task
    public void btnClick_NewTask(View view)
    {
        Intent i = new Intent(this, Task.class);
        startActivity(i);
        finish();
    }

    //Edit PostCode
    public void click_editZip(View view)
    {
        Intent i = new Intent(this, ZipCode.class);
        startActivity(i);
    }

    //for debugging ONLY. Delete whole DB
    public void DeleteWholeDB(View view)
    {
        //alert dialogue before deleting db
        alert = new AlertDialog.Builder(this);//https://stackoverflow.com/questions/2115758/how-do-i-display-an-alert-dialog-on-android
        alert.setTitle("About to delete database!").setMessage("Are you sure you want to delete all tasks?").show();

        getApplication().deleteDatabase("myDb");
    }

    //start activity to send SMS
    public void btn_StartSmsActivity(View view)
    {
        startActivity(new Intent(this, SMS.class));
    }
}
