package com.apuritobokuto.healmane;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Date;
import java.util.Calendar;
import java.util.TimeZone;

import static android.R.attr.value;
import static android.app.AlarmManager.INTERVAL_DAY;

/**
 * Created by RyuSato on 2016/12/09.
 */

public class cl_setting extends Activity {
    private static final int bid1 = 1;
    private static final int bid2 = 2;
    private static final int bid3 = 3;
    private static final int bid4 = 4;

    private Button button1, button2, button5,  button3, button4, button6,button7,button8;
    private TextView textView;
    private int year, month, date, hour, minute, second, msecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        // 10秒で繰り返しアラーム
        button1 = (Button) this.findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                // 5秒後に設定
                int h = calendar.get(Calendar.HOUR_OF_DAY);
                int y = calendar.get(Calendar.YEAR);
                int m = calendar.get(Calendar.MONTH);
                int d = calendar.get(Calendar.DATE);
                System.out.println(y + "+" + m + "+" + d + "h" + h);
                if(h <= 18 && m <= 30){
                    calendar.set(y,m,d,18,30,0);
                }else {
                    d = calendar.get(Calendar.DATE) + 1;
                    calendar.set(y,m,d,18,30,0);
                    System.out.println(y + "+" + m + "+" + d);
                }

                Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
                intent.putExtra("intentId", 1);
                // PendingIntentが同じ物の場合は上書きされてしまうので requestCode で区別する
                PendingIntent pending = PendingIntent.getBroadcast(getApplicationContext(), bid1, intent, 0);

                // アラームをセットする
                AlarmManager am = (AlarmManager) cl_setting.this.getSystemService(ALARM_SERVICE);

                am.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), INTERVAL_DAY, pending);
                // トーストで設定されたことをを表示
                Toast.makeText(getApplicationContext(), "営業時間30分前を通知します", Toast.LENGTH_SHORT).show();

                // 無理やりですが、アプリを一旦終了します。この方法はバックグラウンドに移行させるための方便で推奨ではありません
                close();
            }
        });

        // アラームの取り消し
        button2 = (Button) this.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent indent = new Intent(getApplicationContext(), AlarmReceiver.class);
                PendingIntent pending = PendingIntent.getBroadcast(getApplicationContext(), bid1, indent, 0);

                // アラームを解除する
                AlarmManager am = (AlarmManager) cl_setting.this.getSystemService(ALARM_SERVICE);
                am.cancel(pending);
                Toast.makeText(getApplicationContext(), "営業時間通知を解除しました", Toast.LENGTH_SHORT).show();

            }
        });




        button3 = (Button) this.findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar2 = Calendar.getInstance();
                // 過去の時間は即実行されます
                int h = calendar2.get(Calendar.HOUR_OF_DAY);
                int y = calendar2.get(Calendar.YEAR);
                int m = calendar2.get(Calendar.MONTH);
                int d = calendar2.get(Calendar.DATE);
                if(h <= 20 && m <= 30){
                    calendar2.set(y,m,d,20,30,0);
                }else {
                    d = calendar2.get(Calendar.DATE) + 1;
                    calendar2.set(y,m,d,20,30,0);
                    System.out.println(y + "+" + m + "+" + d);
                }

                Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
                intent.putExtra("intentId", 2);
                PendingIntent pending = PendingIntent.getBroadcast(getApplicationContext(), bid2, intent, 0);

                // アラームをセットする
                AlarmManager am = (AlarmManager) cl_setting.this.getSystemService(ALARM_SERVICE);

                am.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(), INTERVAL_DAY, pending);
                // トーストで設定されたことをを表示
                Toast.makeText(getApplicationContext(), "終業時間30分前を通知します", Toast.LENGTH_SHORT).show();

                // 無理やりですが、アプリを一旦終了します。この方法はバックグラウンドに移行させるための方便で推奨ではありません
                close();
            }
        });
        button4 = (Button) this.findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent indent = new Intent(getApplicationContext(), AlarmReceiver.class);
                PendingIntent pending = PendingIntent.getBroadcast(getApplicationContext(), bid2, indent, 0);

                // アラームを解除する
                AlarmManager am = (AlarmManager) cl_setting.this.getSystemService(ALARM_SERVICE);
                am.cancel(pending);
                Toast.makeText(getApplicationContext(), "終業時間通知を終了します", Toast.LENGTH_SHORT).show();

            }
        });

        button5 = (Button) this.findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar2 = Calendar.getInstance();
                // 過去の時間は即実行されます
                int h = calendar2.get(Calendar.HOUR_OF_DAY);
                int y = calendar2.get(Calendar.YEAR);
                int m = calendar2.get(Calendar.MONTH);
                int d = calendar2.get(Calendar.DATE);
                if(h <= 13){
                    calendar2.set(y,m,d,13,0,0);
                }else {
                    d = calendar2.get(Calendar.DATE) + 1;
                    calendar2.set(y,m,d,13,0,0);
                    System.out.println(y + "+" + m + "+" + d);
                }

                Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
                intent.putExtra("intentId", 2);
                PendingIntent pending = PendingIntent.getBroadcast(getApplicationContext(), bid3, intent, 0);

                // アラームをセットする
                AlarmManager am = (AlarmManager) cl_setting.this.getSystemService(ALARM_SERVICE);

                am.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(), INTERVAL_DAY, pending);
                // トーストで設定されたことをを表示
                Toast.makeText(getApplicationContext(), "午前のポイント2倍期間開始を通知します", Toast.LENGTH_SHORT).show();

                // 無理やりですが、アプリを一旦終了します。この方法はバックグラウンドに移行させるための方便で推奨ではありません
                close();
            }
        });
        button6 = (Button) this.findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent indent = new Intent(getApplicationContext(), AlarmReceiver.class);
                PendingIntent pending = PendingIntent.getBroadcast(getApplicationContext(), bid3, indent, 0);

                // アラームを解除する
                AlarmManager am = (AlarmManager) cl_setting.this.getSystemService(ALARM_SERVICE);
                am.cancel(pending);
                Toast.makeText(getApplicationContext(), "午前のポイント2倍期間通知を終了します", Toast.LENGTH_SHORT).show();

            }
        });
        button7 = (Button) this.findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar2 = Calendar.getInstance();
                // 過去の時間は即実行されます
                int h = calendar2.get(Calendar.HOUR_OF_DAY);
                int y = calendar2.get(Calendar.YEAR);
                int m = calendar2.get(Calendar.MONTH);
                int d = calendar2.get(Calendar.DATE);
                if(h <= 19){
                    calendar2.set(y,m,d,19,0,0);
                }else {
                    d = calendar2.get(Calendar.DATE) + 1;
                    calendar2.set(y,m,d,19,0,0);
                    System.out.println(y + "+" + m + "+" + d);
                }

                Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
                intent.putExtra("intentId", 2);
                PendingIntent pending = PendingIntent.getBroadcast(getApplicationContext(), bid4, intent, 0);

                // アラームをセットする
                AlarmManager am = (AlarmManager) cl_setting.this.getSystemService(ALARM_SERVICE);

                am.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(), INTERVAL_DAY, pending);
                // トーストで設定されたことをを表示
                Toast.makeText(getApplicationContext(), "午後のポイント2倍期間の通知を開始します", Toast.LENGTH_SHORT).show();

                // 無理やりですが、アプリを一旦終了します。この方法はバックグラウンドに移行させるための方便で推奨ではありません
                close();
            }
        });
        button8 = (Button) this.findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent indent = new Intent(getApplicationContext(), AlarmReceiver.class);
                PendingIntent pending = PendingIntent.getBroadcast(getApplicationContext(), bid4, indent, 0);

                // アラームを解除する
                AlarmManager am = (AlarmManager) cl_setting.this.getSystemService(ALARM_SERVICE);
                am.cancel(pending);
                Toast.makeText(getApplicationContext(), "午後のポイント2倍期間の通知を終了します", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void close() {
        finish();
    }
}