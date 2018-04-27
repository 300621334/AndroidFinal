package com.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
/* Group Members are:-
Abdulghafor Nurali
Dennis Kanzira
Md Mamunur Rahman
Shafiq-Ur-Rehman
*/

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btn_StartToDo(View view)
    {
        Intent i = new Intent(this, ToDo.class);
        startActivity(i);
    }

    public void btn_StartDashboard(View view) {
        Intent i = new Intent(this, SplashScreen.class);
        startActivity(i);
    }


}
