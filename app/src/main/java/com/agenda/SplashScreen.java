package com.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
/* Group Members are:-
Abdulghafor Nurali
Dennis Kanzira
Md Mamunur Rahman
Shafiq-Ur-Rehman
*/

public class SplashScreen extends AppCompatActivity
{

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash_screen);

    //IntentService
    startService(new Intent(this, MyService.class));


    Thread timer = new Thread(){
      public void run(){
        try{
//                  sleep(100);
          ImageView icon = (ImageView) findViewById(R.id.icon);
          Animation rotateAni = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
          icon.startAnimation(rotateAni);



          sleep(5500);
        }
        catch(InterruptedException e){
          e.printStackTrace();
        } finally {
    Intent i = new Intent(SplashScreen.this, Dashboard.class);
    startActivity(i);
          finish();
        }
      }
    };
    timer.start();
  }

  @Override
  protected void onPause() {
    super.onPause();
    finish();
  }


  }
//}

//    Button btn = (Button) findViewById(R.id.btnn);
//
//    btn.setOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View view) {
//  Intent i = new Intent(SplashScreen.this, Dashboard.class);
// startActivity(i);
//      }
//    });


