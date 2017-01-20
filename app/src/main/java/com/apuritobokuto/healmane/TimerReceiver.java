package com.apuritobokuto.healmane;

/**
 * Created by rusiddo1011 on 2017/01/20.
 */

//package jp.classmethod.android.sample.dialogonhome;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class TimerReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent data) {
        Intent intent = new Intent(context, AlertDialogActivity.class);
          PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        try {
            pendingIntent.send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }
}