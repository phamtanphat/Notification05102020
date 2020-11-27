package com.example.notification05102020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button mBtnCreateNotifi, mBtnCloseNotifi;
    String CHANNEL_ID = "MY_CHANNEL";

    NotificationManager mNotificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnCloseNotifi = findViewById(R.id.buttonCloseNotifi);
        mBtnCreateNotifi = findViewById(R.id.buttonCreateNotifi);

        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        mBtnCreateNotifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder builder =
                        new NotificationCompat
                                .Builder(MainActivity.this, CHANNEL_ID)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setShowWhen(true)
                                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round))
                                .setContentTitle("Thong bao")
                                .setContentText("App co phien ban moi")
                                .setNumber(3)
                                .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel notificationChannel = new NotificationChannel(
                            CHANNEL_ID,
                            "MY_APP",
                            NotificationManager.IMPORTANCE_HIGH);
                    notificationChannel.enableVibration(true);
                    notificationChannel.enableLights(true);
                    mNotificationManager.createNotificationChannel(notificationChannel);
                }

                mNotificationManager.notify(1, builder.build());
            }
        });
    }
}