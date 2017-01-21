package com.apuritobokuto.healmane;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import static android.app.Service.START_STICKY;

/**
 * Created by Sogoh on 2017/01/19.
 */

public class AlarmService extends Service {
    private static final String TAG = AlarmService.class.getSimpleName();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Thread thr = new Thread(null, mTask, "MyAlarmServiceThread");
        thr.start();
        Log.v(TAG,"スレッド開始");
    }
    // アラーム用サービス
    Runnable mTask = new Runnable() {
        public void run() {
            // アラームを受け取るActivityを指定
            Intent alarmBroadcast = new Intent();
            // ここでActionをセットする(Manifestに書いたものと同じであれば何でもよい)
            alarmBroadcast.setAction("MyAlarmAction");
            // レシーバーへ渡す
            sendBroadcast(alarmBroadcast);
            // 役目を終えたサービスを止める
            AlarmService.this.stopSelf();
            Log.v(TAG,"サービス停止");
        }
    };
}