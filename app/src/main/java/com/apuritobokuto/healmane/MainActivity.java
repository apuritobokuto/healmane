package com.apuritobokuto.healmane;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import android.widget.TextView;
import android.os.AsyncTask;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button health =(Button)findViewById(R.id.health);
        health.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplication(),Health.class);
                startActivity(intent);
            }
        });

        Button menu =(Button)findViewById(R.id.menu);
        menu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplication(),Menu.class);
                startActivity(intent);
            }
        });
    }
}
