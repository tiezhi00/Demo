package com.example.demo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demo.R;
import com.example.demo.dao.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class PhoneBookAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<UserInfo> mUserInfos = new ArrayList<>();

    public PhoneBookAdapter(Context context, List<UserInfo> userInfos) {
        Log.i("DandanTest", "count");
        mContext = context;
        mUserInfos = userInfos;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        //有多少条数据
        return mUserInfos.size();
    }

    @Override
    public Object getItem(int i) {
        //返回某一条数据对象
        return mUserInfos.get(i);
    }

    @Override
    public long getItemId(int i) {

        return i;
    }

    @Override
    public int getItemViewType(int position) {



        return super.getItemViewType(position);
    }

    /*
        每一行数据显示在界面，用户能看到时
         */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //返回一个视图 和数据进行绑定
        ViewHolder viewHolder;
        if (view == null) {
            view = mLayoutInflater.inflate(R.layout.item_phone_book_friend, null);
            viewHolder = new ViewHolder();
            //获取控件
            viewHolder.iv_avatar = view.findViewById(R.id.iv_avatar);
            viewHolder.tv_name = view.findViewById(R.id.tv_name);
            viewHolder.tv_level = view.findViewById(R.id.tv_level);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        //和数据进行绑定
        viewHolder.tv_name.setText(mUserInfos.get(i).getUsername());
        viewHolder.tv_level.setText("lv:" + mUserInfos.get(i).getLevel());
        viewHolder.iv_avatar.setImageResource(R.mipmap.ic_myworld);

        return view;
    }

    class ViewHolder {
        ImageView iv_avatar;
        TextView tv_name;
        TextView tv_level;
    }

    public void refreshData(List<UserInfo> userInfos) {
        mUserInfos = userInfos;
        notifyDataSetChanged();
    }
}
