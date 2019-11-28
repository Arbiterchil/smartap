package com.example.arbiterchil.smartap;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class xyz extends Application {

public  static final String CHANNEL_ID = "ExampleNotification";

    @Override
    public void onCreate() {
        super.onCreate();

        CreateNotif();

    }

    private void CreateNotif() {

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                NotificationChannel ser = new NotificationChannel(

                        CHANNEL_ID,
                        "ExampleNotification",
                        NotificationManager.IMPORTANCE_DEFAULT
                );
                NotificationManager manager = getSystemService(NotificationManager.class);
                manager.createNotificationChannel(ser);
        }
    }
}
