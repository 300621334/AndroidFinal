package com.agenda;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
/**
 * Created by Shafiq on 2018-04-14.
 */
/* Group Members are:-
Abdulghafor Nurali
Dennis Kanzira
Md Mamunur Rahman
Shafiq-Ur-Rehman
*/

public class SMSActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_sms);
        try {

//            Intent sendIntent = new Intent(Intent.ACTION_VIEW);
//            sendIntent.putExtra("sms_body", "Hello Friends!");
//            sendIntent.setType("vnd.android-dir/mms-sms");

//================Following works for both Emulator & older phones but above doesn't work w emulaotr==============
            Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
            sendIntent.setData(Uri.parse("smsto:" + Uri.encode("")));//https://stackoverflow.com/questions/19853220/android4-4-can-not-handle-sms-intent-with-vnd-android-dir-mms-sms?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
//==============================
// https://stackoverflow.com/questions/20079047/android-kitkat-4-4-hangouts-cannot-handle-sending-sms-intent

            startActivity(sendIntent);

        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "SMS faild, please try again later!", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
