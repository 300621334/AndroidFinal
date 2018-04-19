/*
package com.agenda;
import android.app.Service;

import android.bluetooth.BluetoothClass;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;

*/
/**
 * Created by AN on 4/12/2018.
 *//*


public class MyService extends Service {
    MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Initializing the MediaPlayer
        mediaPlayer=MediaPlayer.create(this, Settings.System.DEFAULT_ALARM_ALERT_URI);

        //making the sound infinate looping
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        //if it is true it will return 1 and if false will return -1
        return START_STICKY;

    }

    @Override
    public void onDestroy() {
        //stopping player.
        mediaPlayer.stop();
        super.onDestroy();
    }
}
*/
