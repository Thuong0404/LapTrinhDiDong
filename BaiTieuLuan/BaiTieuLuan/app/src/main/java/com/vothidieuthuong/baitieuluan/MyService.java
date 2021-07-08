package com.vothidieuthuong.baitieuluan;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.ListView;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import static com.vothidieuthuong.baitieuluan.MyApplication.CHANEL_ID;

public class MyService extends Service {
    @Override
    public void onCreate() {

        super.onCreate();

        Log.e("Tincoder","My Service");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("Tincoder","My Service");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //String anh=intent.getStringExtra("Anh");
        String strDataIntent = intent.getStringExtra("Ten_ba_hat");
        String strDataIntentCasi = intent.getStringExtra("Ca_si");
        setNotification(strDataIntent, strDataIntentCasi);
        return START_NOT_STICKY;
    }

    private void setNotification( String strDataIntent, String strDataIntentCasi) {
        Intent intent=new Intent(this, ActivityBaihat.class);
        PendingIntent pendingIntent= PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.song_custom);

        remoteViews.setImageViewResource(R.id.imgsong,R.drawable.dia);
        remoteViews.setTextViewText(R.id.titlesong, strDataIntentCasi);
        remoteViews.setTextViewText(R.id.singglesong, strDataIntent);
        remoteViews.setImageViewResource(R.id.pause,R.drawable.dung1);
        remoteViews.setImageViewResource(R.id.btnnext, R.drawable.next);
        remoteViews.setImageViewResource(R.id.back, R.drawable.ql);
        Notification notification= new NotificationCompat.Builder(this,CHANEL_ID)
                .setContentText(strDataIntent)
                .setContentTitle(strDataIntentCasi)
                .setSmallIcon(R.drawable.ic_baseline_music_note_24)
                .setContentIntent(pendingIntent)
                .setCustomContentView(remoteViews)
                .build();
        startForeground(1,notification);
    }




    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }
}
