package com.apuritobokuto.healmane;

/**
 * Created by rusiddo1011 on 2017/01/20.
 */

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class AlertDialogActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialogFragment fragment = new AlertDialogFragment();
        fragment.show(getSupportFragmentManager(), "alert_dialog");
    }
}