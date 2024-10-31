package com.example.demo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.demo.R;
import com.example.demo.dao.Tap;
import com.example.demo.dao.UserInfo;
import com.example.demo.adapter.TapAtpter;
import com.example.demo.fragment.MyFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int RESULT_CODE = 10002;
    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView rl_tab;
    private LayoutInflater mLayoutInfalter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        管理fragment
         */
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MyFragment myFragment = MyFragment.newInstance("我是宋宜丹", 19);
        fragmentTransaction.add(R.id.rl_fragment, myFragment, "my_fragment_tag");
        fragmentTransaction.remove(myFragment).commit();
        Fragment fragment = fragmentManager.findFragmentById(R.id.rl_fragment);
        Fragment fragment2 = fragmentManager.findFragmentByTag("my_fragment_tag");

        if (fragment instanceof MyFragment) {

        } else {
//            throw new IllegalStateException("is not myfragment");
            Log.i(TAG, "onCreate: is not myfragment");
        }


        mLayoutInfalter = getLayoutInflater();
        mLayoutInfalter = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        mLayoutInfalter = LayoutInflater.from(MainActivity.this);
        View view = mLayoutInfalter.inflate(R.layout.activity_music, null);
        view.findViewById(R.id.btn_start);
        //RecyclerView使用
        //添加数据
        TextView tv_userInfo = findViewById(R.id.tv_userInfo);
        rl_tab = findViewById(R.id.rl_tab);
        List<Tap> taps = new ArrayList<>();
        taps.add(new Tap("列表", R.mipmap.ic_lantu));
        taps.add(new Tap("车控", R.mipmap.ic_lantu));
        taps.add(new Tap("语音", R.mipmap.ic_lantu));
        taps.add(new Tap("音乐", R.mipmap.ic_lantu));
        taps.add(new Tap("Handler", R.mipmap.ic_lantu));
        taps.add(new Tap("广播", R.mipmap.ic_lantu));
        taps.add(new Tap("网页", R.mipmap.ic_lantu));
        taps.add(new Tap("动画", R.mipmap.ic_lantu));

        //这时候就开始使用Adapter了
        //创建及配置Adapter
        TapAtpter tapAtpter = new TapAtpter(taps);
        //设置布局管理器
        rl_tab.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //添加项目装饰
        rl_tab.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = 20;

            }
        });
        //Adatpter的点击事件
        tapAtpter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener<Tap>() {
            @Override
            public void onClick(@NonNull BaseQuickAdapter<Tap, ?> baseQuickAdapter, @NonNull View view, int i) {
                if (i == 0) {
                    Log.i(TAG, "onClick: R.id.button_first");
                    startActivity(new Intent(MainActivity.this, ListActivity.class));
                }
                if (i == 1) {
                    Log.i(TAG, "onClick: R.id.button_second");
                    startActivity(new Intent(MainActivity.this, VehicleControlActivity.class));
                }
                if (i == 2) {
                    Log.i(TAG, "onClick: R.id.button_third");
                    startActivity(new Intent(MainActivity.this, VoiceSettingActivity.class));
                }
                if (i == 3) {
                    Log.i(TAG, "onClick: R.id.button_fourth");
                    startActivity(new Intent(MainActivity.this, MusicActivity.class));
                }
                if (i == 4) {
                    Log.i(TAG, "onClick: R.id.button_fifth");
                    startActivity(new Intent(MainActivity.this, HandlerActivity.class));
                }
                if (i == 5) {
                    Log.i(TAG, "onClick: R.id.button_sixth");
                    startActivity(new Intent(MainActivity.this, SendBroadcastActivity.class));
                }
                if (i == 6) {
                    Log.i(TAG, "onClick: R.id.button_seventh");
                    startActivity(new Intent(MainActivity.this, WebViewActivity.class));
                }
                if (i == 7) {
                    Log.i(TAG, "onClick: R.id.button_eighth");
                    startActivity(new Intent(MainActivity.this, AnimationActivity.class));
                }
            }
        });
        rl_tab.setAdapter(tapAtpter);

        EditText et_phone = findViewById(R.id.et_phone);
        et_phone.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //进行长按事件操作
                return false;
            }
        });
        et_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.i(TAG, "beforeTextChanged: charSequence:" + charSequence.toString() + ",i:" + i + ",i1:" + i1 + ",i2:" + i2);

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.i(TAG, "onTextChanged: charSequence:" + charSequence.toString() + ",i:" + i + ",i1:" + i1 + ",i2:" + i2);

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.i(TAG, "afterTextChanged: editable:" + editable.toString());
                if (editable.toString().length() > 5) {
                    Toast.makeText(MainActivity.this, "啊，救命，超过5个字啦", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Intent intent = getIntent();
        if (intent != null) {
            String title = intent.getStringExtra("title");
            UserInfo userInfo = (UserInfo) intent.getSerializableExtra("userInfo");
            tv_userInfo.setText(userInfo.getUsername() + "    lv: " + userInfo.getLevel());
            setTitle(title);
        }
        //SeekBar进度条
        SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                //当焦点改变时
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //ProgressBar进度条
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(90);
    }
}