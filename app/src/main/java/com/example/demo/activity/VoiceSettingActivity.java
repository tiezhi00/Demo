package com.example.demo.activity;


import android.os.Bundle;
import android.util.ArraySet;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demo.R;
import com.example.demo.util.JsonParserUtil;
import com.example.demo.util.MegaSignalUtil;
import com.example.demo.util.TripInfo;
import com.lanyou.ai.sdk.LySpeechSDK;


import java.util.Objects;
import java.util.Set;

import mega.car.CarPropertyManager;
import mega.car.VehicleArea;
import mega.car.config.Driving;
import mega.car.config.EntryLocks;
import mega.car.config.ParamsCommon;
import mega.car.config.VehicleBody;
import mega.car.config.Windows;
import mega.car.hardware.CarPropertyValue;


public class VoiceSettingActivity extends AppCompatActivity {
    private int flag =0;
    private CarPropertyValue mCarPropertyValue;
    private TripInfo tripInfo = new TripInfo();

    private Set<Integer> propertyIds = new ArraySet<>();
    private MegaSignalUtil megaSignalUtil = MegaSignalUtil.INSTANCE;
    private static final String TAG = VoiceSettingActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_voice_setting);

        propertyIds.add(Driving.ID_DRV_INFO_GEAR_POSITION);
        propertyIds.add(VehicleBody.ID_VEHICLE_USAGEMODE);
        propertyIds.add(EntryLocks.ID_DOOR);
//        propertyIds.add(Driving.ID_DRV_INFO_GEAR_POSITION);
//        propertyIds.add(Driving.ID_DRV_INFO_GEAR_POSITION);
//        propertyIds.add(Driving.ID_DRV_INFO_GEAR_POSITION);
//        propertyIds.add(Driving.ID_DRV_INFO_GEAR_POSITION);

        //测试语音播报
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarPropertyValue carPropertyValue=MegaSignalUtil.INSTANCE.getPropertyRaw(Driving.ID_DRV_DISPLAY_INFO);
                showTripStatistics((String)carPropertyValue.getValue());

                if (LySpeechSDK.isSpeechReady()) {
                    speekByregister(propertyIds);
                } else {
                    Log.i(TAG, "onClick: LySpeechSDK not ready");
                }
            }
        });

    }

    public void speekByregister(Set<Integer> propertyIds) {
        final int[] flag = {1,1};
        megaSignalUtil.registerCallback(new CarPropertyManager.CarPropertyEventCallback() {
            @Override
            public void onChangeEvent(CarPropertyValue carPropertyValue) {
                mCarPropertyValue=carPropertyValue;


                Object value = carPropertyValue.getValue();
                //一进来会onchange一次
                //读档位
                if (carPropertyValue.getPropertyId() == Driving.ID_DRV_INFO_GEAR_POSITION) {
                    if(flag[0] >1) {
                        speakGear((int) value);
                    }
                    flag[0]++;
                }
                //读Ready&^&展示行程信息
                if (carPropertyValue.getPropertyId() == VehicleBody.ID_VEHICLE_USAGEMODE) {
                    if(flag[1] >1) {
                        speakReady((int) value);
                    }
                    flag[1]++;
                }
                //读车门
                if (carPropertyValue.getPropertyId() == EntryLocks.ID_DOOR) {
                    int areaId=carPropertyValue.getAreaId();
                    speakDoor((int)value,areaId);
                }
            }

            @Override
            public void onErrorEvent(int i, int i1) {
                Log.i(TAG, "onErrorEvent: i=" + i + ",i1=" + i1);

            }
        }, propertyIds);

    }

    public void speakGear(int value) {
        if (value == Driving.ParamsDrvInfoGearPosition.DRIVING) {
            Log.i(TAG, "onChangeEvent: 当前档位为D档（drive）：前进挡");
            LySpeechSDK.speak("D档，前进");
        } else if (value == Driving.ParamsDrvInfoGearPosition.PARKING) {
            Log.i(TAG, "onChangeEvent: 当前档位为P档（parking）：驻车档");
            LySpeechSDK.speak("P档，已驻车");
        } else if (value == Driving.ParamsDrvInfoGearPosition.REVERSE) {
            Log.i(TAG, "onChangeEvent: 当前档位为R位（倒车挡）");
            LySpeechSDK.speak("R档，倒车请注意");
        } else if (value == Driving.ParamsDrvInfoGearPosition.NEUTRAL) {
            Log.i(TAG, "onChangeEvent: 当前档位为N档（neutral）：空挡");
            LySpeechSDK.speak("N档");
        } else if (value == Driving.ParamsDrvInfoGearPosition.INVALID) {
        }
    }

    public void speakReady(int value) {
        if (value == VehicleBody.UsageMode.DRIVING) {
            Log.i(TAG, "onChangeEvent: 当前车辆模式为Driving");
            LySpeechSDK.speak("准备出发咯");
            flag = 1;
            //开始统计行程
        } else if (value == VehicleBody.UsageMode.CONVENIENT) {
            Log.i(TAG, "onChangeEvent: 当前车辆模式为CONVENIENT");
            if (flag == 1) {
                LySpeechSDK.speak("旅途愉快");
//                CarPropertyValue carPropertyValue=MegaSignalUtil.INSTANCE.getPropertyRaw(Driving.ID_DRV_DISPLAY_INFO);
//                showTripStatistics((String)carPropertyValue.getValue());


            }
            flag = 0;
        }
    }


    public void showTripStatistics(String value) {
        dealDriveInfoData(value);
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();

    }

    /**
     * 处理行车信息数据
     */
    public void dealDriveInfoData(String jsonString) {
        Log.i(TAG, "dealDriveInfoData: jsonString=" + jsonString);
        Toast.makeText(this, "Json 数据为" + jsonString, Toast.LENGTH_SHORT).show();
        JsonParserUtil.jsonToTrpInfo(jsonString, tripInfo);
        Toast.makeText(this, "自启动后行车信息 平均电耗为" + tripInfo.getIc_diPwrOnAvgEgyConsump() + "kWh /100km", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "dealDriveInfoData: 自启动后行车信息 平均电耗为" + tripInfo.getIc_diPwrOnAvgEgyConsump() + "kWh /100km");
    }
    public void speakDoor(int value,int areaId) {
        String doorName="";
        if (areaId == VehicleArea.OUTSIDE_FRONT) {
            doorName="前舱盖";
        } else if (areaId ==VehicleArea.FRONT_LEFT) {
            doorName="左前车门";
        } else if (areaId ==VehicleArea.FRONT_RIGHT) {
            doorName="右前车门";
        } else if (areaId ==VehicleArea.REAR_LEFT) {
            doorName="左后车门";
        } else if (areaId ==VehicleArea.REAR_RIGHT) {
            doorName="右后车门";
        }
        if (value== ParamsCommon.OpenClose.OPENED) {
            LySpeechSDK.speak(doorName+"打开了");
        }
        }
    }


