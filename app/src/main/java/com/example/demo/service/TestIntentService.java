package com.example.demo.service;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class TestIntentService extends IntentService {
    /**
     * @param name
     * @deprecated
     */
    public TestIntentService(String name) {
        super(name);
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        //do more works    UI线程    》10s   ---->ANR   Application not resopnsed
        Thread thread =new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //排队--》MessageQueue 同步操作：排队领书、处理Intent数据
        if(intent!=null){
            //
        }
    }
}