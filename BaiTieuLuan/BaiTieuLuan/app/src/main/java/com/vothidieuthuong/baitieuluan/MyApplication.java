package com.vothidieuthuong.baitieuluan;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class MyApplication extends Application {
    public  static  final String CHANEL_ID="channel_service_example";

    @Override
    public void onCreate() {
        super.onCreate();
        createChnnelNotifiaction();
    }

    private void createChnnelNotifiaction() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel=new NotificationChannel(CHANEL_ID,
              "Channel Service Example", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager=getSystemService(NotificationManager.class);
            if(manager!=null) {
                manager.createNotificationChannel(channel);
            }

        }
    }
}
