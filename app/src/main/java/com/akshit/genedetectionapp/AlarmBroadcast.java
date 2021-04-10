package com.akshit.genedetectionapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AlarmBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String relation=intent.getStringExtra("relation");
        String text = intent.getStringExtra("event");
        String date = intent.getStringExtra("date") + " " + intent.getStringExtra("time");



        //Click on Notification

        Intent intent1 = new Intent(context, NotificationMessage.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent1.putExtra("relationship",relation);
        //Toast.makeText(context, "relationship"+relation, Toast.LENGTH_SHORT).show();
        intent1.putExtra("message", text);


        //Notification Builder
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intent1, PendingIntent.FLAG_ONE_SHOT);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "notify_001");

        PendingIntent pendingSwitchIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        mBuilder.setSmallIcon(R.drawable.geneicon);
        mBuilder.setContentText("Traverse Indoor");
        mBuilder.setContentText("Reminder");
        mBuilder.setAutoCancel(true);
        mBuilder.setOngoing(true);
        mBuilder.setPriority(Notification.PRIORITY_HIGH);
        mBuilder.setOnlyAlertOnce(true);
        mBuilder.setCategory(Notification.CATEGORY_EVENT);
        mBuilder.build().flags = Notification.FLAG_NO_CLEAR | Notification.PRIORITY_HIGH;
        mBuilder.setContentIntent(pendingIntent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "channel_id";
            NotificationChannel channel = new NotificationChannel(channelId, "channel1", NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("This is channel 1");
            channel.enableVibration(true);
            notificationManager.createNotificationChannel(channel);
            mBuilder.setChannelId(channelId);
        }

        Notification notification = mBuilder.build();
        // notificationManager.notify(1, notification);
        NotificationManagerCompat notify=NotificationManagerCompat.from(context);
        notify.notify(1,notification);


    }
}