package com.example.demo.activity;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.demo.R;
import com.example.demo.dao.UserInfo;

public class SplashActivity extends Activity {
    public static final int REQUEST_CODE = 100001;
    private static final String TAG = SplashActivity.class.getSimpleName();
    private Handler mHandler = new Handler();
    private TextView tv_welcome;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");
        setContentView(R.layout.activity_splash);
        tv_welcome = findViewById(R.id.tv_welcome);
        String title = tv_welcome.getText().toString();
        UserInfo userInfo = new UserInfo("宋仙女", 99);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //跳转到MainActivity
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("userInfo", userInfo);
                startActivityForResult(intent, REQUEST_CODE);

            }
        }, 1000);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "onActivityResult: ");
        Log.i(TAG, "onActivityResult: requestCode="+requestCode+",resultCode="+resultCode);
        if(requestCode==SplashActivity.REQUEST_CODE&&resultCode==MainActivity.RESULT_CODE){
            if(data!=null){
                String str=data.getStringExtra("str");
                tv_welcome.setText(str);
            }
        }
    }
}
