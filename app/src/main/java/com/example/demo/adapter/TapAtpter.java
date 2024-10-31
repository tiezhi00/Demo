package com.example.demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.QuickViewHolder;
import com.example.demo.R;
import com.example.demo.dao.Tap;

import java.util.List;

public class TapAtpter extends BaseQuickAdapter<Tap, QuickViewHolder> {
    public TapAtpter(List<Tap> taps) {
        setItems(taps);
    }

    @Override
    protected void onBindViewHolder(@NonNull QuickViewHolder quickViewHolder, int i, @Nullable Tap tap) {
        // 设置item数据
        TextView tv_name=(TextView)quickViewHolder.getView(R.id.tv_name);
        tv_name.setText(getItem(i).getName());
        ImageView iv_icon=(ImageView)quickViewHolder.getView(R.id.iv_icon);
        iv_icon.setBackgroundResource(getItem(i).getIcon());

    }


    @NonNull
    @Override
    protected QuickViewHolder onCreateViewHolder(@NonNull Context context, @NonNull ViewGroup viewGroup, int i) {
        // 返回一个 ViewHolder
        return new QuickViewHolder(R.layout.item_tap, viewGroup);
    }
}
