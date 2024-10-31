package com.example.demo;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RemoteViews;

public class MyWidget extends AppWidgetProvider {

    public static final String ACTION = "100030";
    public static final String TAG = MyWidget.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if(intent!=null&& TextUtils.equals(intent.getAction(), ACTION)){
            Log.i(TAG, "onReceive: be clicked");
            RemoteViews remoteViews=new RemoteViews(context.getPackageName(),R.layout.layout_widget);
            remoteViews.setTextViewText(R.id.textView,"啊，我被点击了");
            remoteViews.setTextColor(R.id.textView, Color.RED);
            AppWidgetManager appWidgetManager=AppWidgetManager.getInstance(context);
            ComponentName componentName=new ComponentName(context,MyWidget.class);
            appWidgetManager.updateAppWidget(componentName,remoteViews);


        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        RemoteViews remoteViews=new RemoteViews(context.getPackageName(),R.layout.layout_widget);
        Intent intent=new Intent();
        intent.setClass(context, MyWidget.class);
        intent.setAction(ACTION);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(context,100010,intent,0);
        remoteViews.setOnClickPendingIntent(R.id.button,pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetIds,remoteViews);
    }
}
