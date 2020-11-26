package com.example.sellingapp08.util;



import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class Notification extends Application {
    public static final String CHANNEL_ID = "channel";

    public Notification() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.createNotificationChannels();
    }

    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,"Channel", NotificationManager.IMPORTANCE_HIGH
            );
            channel.setDescription("This is channel");

            NotificationManager manager = this.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);

        }
    }

}