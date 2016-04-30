package com.dnsoftindia.notificationsdemo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnNotification;
    private Button btnNewNotification;
    private Button btnBigPictureNotification;

    private PendingIntent pendingIntent;
    private NotificationManager notificationManager;
    private NotificationCompat.Builder notificationBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNotification = (Button) findViewById(R.id.btnNotification);
        btnNewNotification = (Button) findViewById(R.id.btnNewNotification);
        btnBigPictureNotification = (Button) findViewById(R.id.btnBigPictureNotification);

        Intent i = new Intent(MainActivity.this, CatchNotificationActivity.class);
        pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, i, 0);

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(MainActivity.this)
                        .setContentIntent(pendingIntent)
                        .setContentTitle("Notifications Demo")
                        .setContentText("Notified you, don't tell me I didn't.")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setSubText("Told you, I've notified already. ;)")
                        .setAutoCancel(true)
                        .setTicker("Notifying...");

                Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                notificationBuilder.setSound(uri);
                notificationBuilder.setVibrate(new long[]{300, 1000});

                notificationManager.notify(1, notificationBuilder.build());
            }
        });

        btnNewNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(MainActivity.this)
                        .setContentIntent(pendingIntent)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .setAutoCancel(true)
                        .setTicker("Newly Notifying...");

                Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                notificationBuilder.setSound(uri);
                notificationBuilder.setVibrate(new long[]{300, 1000});

                NotificationCompat.BigTextStyle bts = new android.support.v4.app.NotificationCompat.BigTextStyle();
                bts.setBigContentTitle("New notification");
                bts.bigText("New way to show notification");
                bts.setSummaryText("New summary");
                notificationBuilder.setStyle(bts);
                notificationManager.notify(1, notificationBuilder.build());
            }
        });

        btnBigPictureNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(MainActivity.this)
                        .setContentIntent(pendingIntent)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .setAutoCancel(true)
                        .setContentTitle("Big Picture")
                        .setContentText("Picture notification")
                        .setSubText("Picture summary")
                        .setTicker("Picturing...");

                Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                notificationBuilder.setSound(uri);
                notificationBuilder.setVibrate(new long[]{300, 1000});

                NotificationCompat.BigPictureStyle bps = new android.support.v4.app.NotificationCompat.BigPictureStyle();
                bps.setBigContentTitle("Big Picture");
                bps.setSummaryText("Picture summary");
                bps.bigPicture(BitmapFactory.decodeResource(MainActivity.this.getResources(), R.drawable.logo_rounded));
                notificationBuilder.setStyle(bps);
                notificationManager.notify(1, notificationBuilder.build());
            }
        });

    }
}
