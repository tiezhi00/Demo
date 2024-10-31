package com.example.demo.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demo.R;

import java.lang.ref.WeakReference;


public class HandlerActivity extends AppCompatActivity {
    public static final int WHAT = 10001;
    public Handler mHandler = new MyHandler(this);
    private TextView mTV_Str;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        mTV_Str = findViewById(R.id.tv_str);

        Message message = mHandler.obtainMessage();
        message.arg1 = 1;
        message.arg2 = 2;
        message.obj = 10;
        message.what = WHAT;
        mHandler.sendMessageDelayed(message, 1000);


    }

    public TextView getmTV_Str() {
        return mTV_Str;
    }

    public class MyHandler extends Handler{
        public final HandlerActivity mHandlerActivity;
        public  MyHandler(HandlerActivity handlerActivity){
            mHandlerActivity=handlerActivity;
        }



        @SuppressLint("HandlerLeak")
        @Override
        public void handleMessage(android.os.Message msg) {
            /* compiled code */
            if (msg.what ==WHAT) {
                //接收消息
                int number = (int) msg.obj;
                mHandlerActivity.getmTV_Str().setText(number + "");
                msg = Message.obtain();
                msg.arg1=1;
                msg.arg2=2;
                msg.obj=number-1;
                msg.what=WHAT;
                if (number > 0) {
                    sendMessageDelayed(msg, 1000);
                }
            }

        }
    }
}