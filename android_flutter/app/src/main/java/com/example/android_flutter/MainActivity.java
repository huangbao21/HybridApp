package com.example.android_flutter;


import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import java.util.Calendar;

import io.flutter.facade.Flutter;
import io.flutter.plugin.common.MethodChannel;

public class MainActivity extends AppCompatActivity {
    private static final String CHANNEL = "samples.flutter.io/message";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        Log.w("start", Calendar.getInstance().getTimeInMillis() + "");
//        startActivity(new Intent(MainActivity.this,AnotherActivity.class));
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fl_flutter_view, Flutter.createFragment("/route2/route1"));
        fragmentTransaction.commit();
//        FrameLayout.LayoutParams layout =
//                new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
//                        FrameLayout.LayoutParams.MATCH_PARENT);
//        View flutterView = Flutter.createView(
//                MainActivity.this,
//                getLifecycle(),
//                "route2"
//        );
//        addContentView(flutterView, layout);


    }


}
