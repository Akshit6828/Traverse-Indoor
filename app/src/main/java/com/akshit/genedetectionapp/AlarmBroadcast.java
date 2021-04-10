package com.akshit.genedetectionapp;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class AlarmBroadcast extends BroadcastReceiver {

    Activity context;
    public AlarmBroadcast(Activity context){
        this.context=context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String text = intent.getStringExtra("event");
        String date = intent.getStringExtra("date") + " " + intent.getStringExtra("time");



        //Click on Notification

        Intent intent1 = new Intent(context, NotificationMessage.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent1.putExtra("message", text);

        //Notification Builder
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intent1, PendingIntent.FLAG_ONE_SHOT);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "notify_001");

        RemoteViews contentView = new RemoteViews(context.getPackageName(), R.layout.alarm_notification_manger);
        contentView.setImageViewResource(R.id.image, R.mipmap.ic_launcher);
        PendingIntent pendingSwitchIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        contentView.setOnClickPendingIntent(R.id.flashButton, pendingSwitchIntent);
       // contentView.setTextViewText(R.id.message, text);
       // contentView.setTextViewText(R.id.date, date);
        /*//------For setting light for Notification...
        mBuilder.setLights(Color.YELLOW,200,200);
        mBuilder.setContentTitle("Reminder !");
        //-----For setting popup notification ringtone
        Uri sound_rui= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        mBuilder.setSound(sound_rui);
        //mBuilder.setSmallIcon(R.drawable.ic_baseline_alarm_24);
        long [] vibrate={100,500,100,500};
        mBuilder.setVibrate(vibrate);


        //---Setting Family Icon to Notification
        mBuilder.setSmallIcon(R.drawable.ic_family);
        //Bitmap large_icon= BitmapFactory.decodeResource(getResources(),R.drawable.geneicon);
       // mBuilder.setLargeIcon(large_icon);*/
        mBuilder.setAutoCancel(true);
        mBuilder.setOngoing(true);
        mBuilder.setPriority(Notification.PRIORITY_HIGH);
        mBuilder.setOnlyAlertOnce(true);
        mBuilder.build().flags = Notification.FLAG_NO_CLEAR | Notification.PRIORITY_HIGH;
        mBuilder.setContent(contentView);
        mBuilder.setContentIntent(pendingIntent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "channel_id";
            NotificationChannel channel = new NotificationChannel(channelId, "Traverse Indoor", NotificationManager.IMPORTANCE_HIGH);
            channel.enableVibration(true);
            notificationManager.createNotificationChannel(channel);
            mBuilder.setChannelId(channelId);
        }

        Notification notification = mBuilder.build();
        notificationManager.notify(1, notification);


    }
}
