package com.example.demo.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.demo.activity.SendBroadcastActivity;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //接收广播
        //>10s ---ANR---work thread
        if(intent!=null){
            if(TextUtils.equals(intent.getAction(),SendBroadcastActivity.COM_EXAMPLE_DEMO)){
                String toastString= intent.getStringExtra("toast");
                Toast.makeText(context, toastString, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
