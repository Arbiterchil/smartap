package com.example.arbiterchil.smartap;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import static com.example.arbiterchil.smartap.xyz.CHANNEL_ID;

public class ServicesUid extends com.google.firebase.messaging.FirebaseMessagingService {


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        String notif = remoteMessage.getNotification().getTitle();
        String notifbod = remoteMessage.getNotification().getBody();
        String click = remoteMessage.getNotification().getClickAction();
        String userdata = remoteMessage.getData().get("from_user_id");
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.flower)
                .setContentTitle(notif)
                .setContentText(notifbod);

        Intent resultent = new Intent(click);
        resultent.putExtra("user_id",userdata);
        PendingIntent resultpent = PendingIntent.getActivity(
                this,0,resultent,PendingIntent.FLAG_UPDATE_CURRENT
        );
        builder.setContentIntent(resultpent);

        int note = (int) System.currentTimeMillis();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(note,builder.build());
    }
}
