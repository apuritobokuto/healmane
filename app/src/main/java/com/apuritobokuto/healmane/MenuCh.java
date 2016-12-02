package com.apuritobokuto.healmane;

/**
 * Created by RyuSato on 2016/11/29.
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.os.AsyncTask;


public class MenuCh extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuch);


        Button menul = (Button) findViewById(R.id.menul);
        menul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), MenuLi.class);
                startActivity(intent);
            }
        });

    }

}
