package com.example.demo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.demo.R;
import com.example.demo.dao.UserInfo;
import com.example.demo.adapter.PhoneBookAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private ListView mPhoneBookListView;
    private List<UserInfo> mUserInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mPhoneBookListView = findViewById(R.id.list_view);
        mUserInfos = new ArrayList<>();
        mUserInfos.add(new UserInfo("李小明", 21));
        mUserInfos.add(new UserInfo("张小虎", 75));
        mUserInfos.add(new UserInfo("刘雪华", 19));
        mUserInfos.add(new UserInfo("李雪琴", 20));
        mUserInfos.add(new UserInfo("张大锤", 14));
        mUserInfos.add(new UserInfo("王博士", 77));
        mUserInfos.add(new UserInfo("赵小四", 24));
        PhoneBookAdapter phoneBookAdapter = new PhoneBookAdapter(ListActivity.this, mUserInfos);
        //如何更新数据
        mPhoneBookListView.setAdapter(phoneBookAdapter);
        mPhoneBookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    //新建另外一批数据
                    //替换掉老数据
                    //刷新ListView,让它更新自己的视图
                    mUserInfos.clear();
                    mUserInfos.add(new UserInfo("我是新的数据一", 77));
                    mUserInfos.add(new UserInfo("我是新的数据二", 99));
                    mUserInfos.add(new UserInfo("我是新的数据三", 100));
                    phoneBookAdapter.refreshData(mUserInfos);
//                    phoneBookAdapter.notifyDataSetChanged();
                }
                Toast.makeText(ListActivity.this, mUserInfos.get(i).getUsername() + "被我点击了，怎么办?", Toast.LENGTH_LONG).show();
            }
        });
        mPhoneBookListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListActivity.this, mUserInfos.get(i).getUsername() + "被我长按了，怎么办?", Toast.LENGTH_LONG).show();
                return false;
            }
        });


    }
}