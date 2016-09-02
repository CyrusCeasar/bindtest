package com.example.chenlei2.databindtest;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by chenlei2 on 2016/9/1 0001.
 */
public class ServMusicPlayer extends Service{

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
        }
    };

    final MediaPlayer mediaPlayer = new MediaPlayer();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MsgBinder();
    }

    public class MsgBinder extends Binder{
        public ServMusicPlayer getService(){
            return ServMusicPlayer.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    private void stopMusic(){
    }

    private void startMusic(String path){

    }

    private void restartMusic(){

    }

    private void startMusic(String path,long begin){

    }
}
