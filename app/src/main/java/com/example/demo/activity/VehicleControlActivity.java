package com.example.demo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.ArraySet;
import android.view.View;
import android.widget.Toast;

import com.example.demo.R;
import com.example.demo.util.MegaSignalUtil;

import java.util.Set;

import mega.car.CarPropertyManager;
import mega.car.VehicleArea;
import mega.car.config.Windows;
import mega.car.hardware.CarPropertyValue;

public class VehicleControlActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_control);
        //获取初始值
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int l= MegaSignalUtil.INSTANCE.getIntProp(Windows.ID_WINDOW, VehicleArea.FRONT_LEFT);
                Toast.makeText(VehicleControlActivity.this, "左前车窗开啦"+l, Toast.LENGTH_SHORT).show();
            }
        });

        //下发
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MegaSignalUtil.INSTANCE.setIntProp(Windows.ID_WINDOW, VehicleArea.ALL, 100);

            }
        });
        Set<Integer> propertyIds = new ArraySet<>();
        propertyIds.add(Windows.ID_WINDOW);
        //回调
        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MegaSignalUtil.INSTANCE.registerCallback( new CarPropertyManager.CarPropertyEventCallback() {
                    @Override
                    public void onChangeEvent(CarPropertyValue carPropertyValue) {
                        if(carPropertyValue.getPropertyId()==Windows.ID_WINDOW
                                &&carPropertyValue.getAreaId()==VehicleArea.FRONT_LEFT){
                            Toast.makeText(VehicleControlActivity.this, "当前左车窗的值变化了，现在左车窗开度为"+carPropertyValue.getValue(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onErrorEvent(int i, int i1) {
                    }
                },propertyIds);
            }
        });
    }
    }


