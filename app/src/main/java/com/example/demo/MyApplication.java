package com.example.demo;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.example.demo.activity.VoiceSettingActivity;
import com.example.demo.util.MegaSignalUtil;
import com.lanyou.ai.sdk.LySpeechSDK;
import com.lanyou.ai.sdk.listener.OnSpeechReadyListener;


import mega.car.VehicleArea;
import mega.car.config.Windows;
public class MyApplication extends Application {

    private static final String TAG = MyApplication.class.getSimpleName();
    private static MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ");
        myApplication = this;
        //初始化

        LySpeechSDK.initialize(this.getApplicationContext(), new OnSpeechReadyListener() {

            @Override
            public void onSpeechReady() {
                Toast.makeText(MyApplication.this, "我是语音，我准备好了", Toast.LENGTH_SHORT).show();
                LySpeechSDK.speak("语音连接成功");
            }
        });
    }

    public static MyApplication getApplication(){
        return myApplication;
    }
}
