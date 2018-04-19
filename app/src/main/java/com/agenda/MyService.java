package com.agenda;
import android.app.IntentService;
import android.app.Service;

import android.bluetooth.BluetoothClass;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;

/**
 * Created by AN on 4/12/2018.
 */

public class MyService extends IntentService
{


//MUST add a constructor that takes NO arg. or else Manifest file gives errthat default constructor missing
    public MyService()
    {
        super("MyService");
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        //set up MediaPlayer
        //MediaPlayer mp = new MediaPlayer();
        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.intro);//https://stackoverflow.com/questions/22906068/how-to-get-file-path-of-asset-folder-in-android


        try {
            //mp.setDataSource(path + File.separator + fileName);//https://stackoverflow.com/questions/7291731/how-to-play-audio-file-in-android
            //mp.prepare();
            mp.start();
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
