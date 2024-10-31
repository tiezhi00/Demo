package com.example.demo.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.example.demo.broadcast.MyBroadcastReceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import com.example.demo.R;

public class SendBroadcastActivity extends AppCompatActivity {

    public static final String COM_EXAMPLE_DEMO = "com.example.demo";
    private MyBroadcastReceiver myBroadcastReceiver = new MyBroadcastReceiver();
    private Button mSendBroadcastButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_broadcast);


        mSendBroadcastButton = findViewById(R.id.btn_sendBroadcast);
        mSendBroadcastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(COM_EXAMPLE_DEMO);
                intent.putExtra("toast", "收到了我的广播");
                sendBroadcast(intent);
//                sendOrderedBroadcast(intent);
//                LocalBroadcastManager

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //动态注册广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(COM_EXAMPLE_DEMO);
        registerReceiver(myBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(myBroadcastReceiver);
    }
}