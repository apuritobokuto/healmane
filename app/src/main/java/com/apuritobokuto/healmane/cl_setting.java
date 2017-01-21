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

/**
 * Created by RyuSato on 2016/12/09.
 */

public class cl_setting extends Activity {
    private static final int bid1 = 1;
    private static final int bid2 = 2;

    private Button button1, button2, button3;
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
                int m = calendar.get(Calendar.MINUTE);
                int s = calendar.get(Calendar.SECOND);
                calendar.set(y,m,date);
                if(h < 18){
                    calendar.set(y,m,date,18,30,0);
                }else if(h >=18 && m > 30){
                    calendar.set(y,m,date,21,30,0);
                }else{
                    calendar.set(y,m,date+1,18,30,0);
                }
                Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
                intent.putExtra("intentId", 1);
                // PendingIntentが同じ物の場合は上書きされてしまうので requestCode で区別する
                PendingIntent pending = PendingIntent.getBroadcast(getApplicationContext(), bid1, intent, 0);

                // アラームをセットする
                AlarmManager am = (AlarmManager) cl_setting.this.getSystemService(ALARM_SERVICE);

                // トーストで設定されたことをを表示
                Toast.makeText(getApplicationContext(), "ALARM 1", Toast.LENGTH_SHORT).show();

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
                Toast.makeText(getApplicationContext(), "Cancel ALARM 1", Toast.LENGTH_SHORT).show();

            }
        });

        textView = (TextView) findViewById(R.id.text_view);
        // 協定世界時 (UTC)です適宜設定してください
        year = 2016;
        month = 4;// 4=>5月
        date = 21;
        hour = 5;
        minute = 8;
        second = 30;
        msecond = 0;

        // 日時を指定したアラーム
        button3 = (Button) this.findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar2 = Calendar.getInstance();
                // 過去の時間は即実行されます
                calendar2.set(Calendar.YEAR, year);
                calendar2.set(Calendar.MONTH, month);
                calendar2.set(Calendar.DATE, date);
                calendar2.set(Calendar.HOUR_OF_DAY, hour);
                calendar2.set(Calendar.MINUTE, minute);
                calendar2.set(Calendar.SECOND, second);
                calendar2.set(Calendar.MILLISECOND, msecond);

                Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
                intent.putExtra("intentId", 2);
                PendingIntent pending = PendingIntent.getBroadcast(getApplicationContext(), bid2, intent, 0);

                // アラームをセットする
                AlarmManager am = (AlarmManager) cl_setting.this.getSystemService(ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(), pending);
                Toast.makeText(getApplicationContext(), "ALARM 2", Toast.LENGTH_SHORT).show();

                String setTime = "設定時間(UTC)：" + year + "/" + (month + 1) + "/" + date + " " + hour + ":" + minute + ":" + second + "." + msecond;
                textView.setText(setTime);
            }
        });
    }

    private void close() {
        finish();
    }
}