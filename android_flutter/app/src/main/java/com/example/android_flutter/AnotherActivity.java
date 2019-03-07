package com.example.android_flutter;

import android.app.ActionBar;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import io.flutter.app.FlutterActivity;
import io.flutter.facade.Flutter;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugins.GeneratedPluginRegistrant;
import io.flutter.view.FlutterMain;
import io.flutter.view.FlutterNativeView;
import io.flutter.view.FlutterView;

public class AnotherActivity extends FlutterActivity {
    private static final String CHANNEL = "samples.flutter.io/message";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FlutterMain.startInitialization(this.getApplicationContext());
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        GeneratedPluginRegistrant.registerWith(this);
        new MethodChannel(getFlutterView(),CHANNEL).setMethodCallHandler(
                (methodCall, result) -> {
                    if (methodCall.method.equals("getBatteryLevel")) {
                        int batteryLevel = getBatteryLevel();

                        if(batteryLevel != -1){
                            result.success(batteryLevel);
                        }else{
                            result.error("UNAVAILABLE", "Battery level not available.", null);
                        }
                    }else if("jumpTo".equals(methodCall.method)){
                        result.success("123555");
                        startActivity(new Intent(AnotherActivity.this,MainActivity.class));
                    }else{
                        result.notImplemented();
                    }
                }
        );

    }


    @Override
    public FlutterView createFlutterView(Context context) {

        WindowManager.LayoutParams matchParent = new WindowManager.LayoutParams(-1, -1);
        FlutterNativeView nativeView = this.createFlutterNativeView();
        FlutterView flutterView = new FlutterView(this, (AttributeSet) null, nativeView);
        flutterView.setInitialRoute("/route1");
        flutterView.setLayoutParams(matchParent);
        this.setContentView(flutterView);
        return flutterView;
    }

    private int getBatteryLevel(){
        int batteryLevel = -1;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            BatteryManager batteryManager = (BatteryManager) getSystemService(BATTERY_SERVICE);
            batteryLevel = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
        }else{
            Intent intent = new ContextWrapper(getApplicationContext())
                    .registerReceiver(null,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
            batteryLevel = (intent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1)*100)/
                    intent.getIntExtra(BatteryManager.EXTRA_SCALE,-1);
        }
        return batteryLevel;
    }

}
