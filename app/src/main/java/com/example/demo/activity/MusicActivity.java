package com.example.demo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import com.example.demo.service.MusicService;
import com.example.demo.R;

public class MusicActivity extends AppCompatActivity implements View.OnClickListener {
    private MusicService musicService;
    private ServiceConnection mServiceConnection=new ServiceConnection(){

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MusicService.LocalBinder localBinder=(MusicService.LocalBinder) iBinder;
            musicService=localBinder.getService();

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        findViewById(R.id.btn_start).setOnClickListener(this);
        findViewById(R.id.btn_stop).setOnClickListener(this);
        if(musicService!=null){
            int progress=musicService.getMusicProgress();
        }

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btn_start){
            bindService(new Intent(MusicActivity.this,MusicService.class),mServiceConnection,BIND_AUTO_CREATE);
//            startService(new Intent(MusicActivity.this,MusicService.class));
        }
        if(view.getId()==R.id.btn_stop){
            unbindService(mServiceConnection);
//            stopService(new Intent(MusicActivity.this,MusicService.class));

        }
    }
}