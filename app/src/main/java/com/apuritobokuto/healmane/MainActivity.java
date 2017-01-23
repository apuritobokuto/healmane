package com.apuritobokuto.healmane;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    Global global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        global = (Global) this.getApplication();
        global.GlobalAllInit();

        Button health =(Button)findViewById(R.id.health);
        health.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplication(),cl_healthManage.class);
                startActivity(intent);
            }
        });

        Button menu =(Button)findViewById(R.id.menu);
        menu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplication(),cl_menuSelect.class);
                startActivity(intent);
            }
        });

        Button proposal =(Button)findViewById(R.id.proposal);
        proposal.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), cl_proposalSelect_Category.class);
                startActivity(intent);
            }
        });

        Button setting =(Button)findViewById(R.id.setting);
        setting.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplication(),cl_setting.class);
                startActivity(intent);
            }
        });

        Button information =(Button)findViewById(R.id.information);
        information.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplication(),cl_information.class);
                startActivity(intent);
            }
        });
/*
        Button help =(Button)findViewById(R.id.help);
        help.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplication(),cl_help.class);
                startActivity(intent);
            }
        });
*/



    }
}
