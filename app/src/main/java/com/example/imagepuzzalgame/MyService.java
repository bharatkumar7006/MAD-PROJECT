package com.example.imagepuzzalgame;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MyService extends Service {

    private MediaPlayer myTrack;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it is stopped.

        myTrack = MediaPlayer.create(MyService.this, R.raw.melodyloops);
        myTrack.setLooping(true);
        myTrack.start();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        myTrack.stop();

    }

}
