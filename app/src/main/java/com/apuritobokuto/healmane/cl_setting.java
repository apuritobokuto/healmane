package com.apuritobokuto.healmane;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.AlarmManager;
import android.widget.Toast;
import android.util.Log;
import android.content.Context;
import android.app.PendingIntent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import java.util.Calendar;
import android.app.AlertDialog;
import android.widget.TextView;
import android.content.DialogInterface;

/**
 * Created by RyuSato on 2016/12/09.
 */

public class cl_setting extends Activity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup _radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Button setalle = (Button) findViewById(R.id.setalle);
        setalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), cl_settingsAllergy.class);
                startActivity(intent);
            }
        });

        Button hozon = (Button) findViewById(R.id.hozon);
        hozon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
            }
        });


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        _radioGroup = (RadioGroup) findViewById(R.id.RadioGroup1);
        _radioGroup.setOnCheckedChangeListener(this);



    }

    public void onCheckedChanged(RadioGroup arg0, int arg1) {

        Calendar cal = Calendar.getInstance();
        int iYear = cal.get(Calendar.YEAR);         //年を取得
        int iMonth = cal.get(Calendar.MONTH);       //月を取得
        int iDate = cal.get(Calendar.DATE);         //日を取得
        int iHour = cal.get(Calendar.HOUR);         //時を取得
        int iMinute = cal.get(Calendar.MINUTE);    //分を取得
        int iSecond = cal.get(Calendar.SECOND);    //秒を取得

        //閉店時のお知らせ時刻
        int Open1 = iHour;
        int Open2 = iMinute - 30;



        if (arg0 == _radioGroup) {
            switch (arg1) {
                case R.id.checkBox42:
                   // if(Open1==8 && Open2==30) //8
                     //   {
                        AlertDialog.Builder alertDlg = new AlertDialog.Builder(this);
                        alertDlg.setTitle("Helmane");
                        alertDlg.setMessage("閉店時間30分前になりました。");
                        alertDlg.setPositiveButton(
                                "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // OK ボタンクリック処理
                                    }
                                });
                        // 表示
                        alertDlg.create().show();
                    //}
                    break;
                case R.id.checkBox43:
                    break;
                default:
                    break;
            }

        }

    }
}