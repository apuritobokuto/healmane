package com.apuritobokuto.healmane;

/**
 * Created by RyuSato on 2016/11/29.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class cl_menuSelect_Choice extends Activity {
    String[] n = {"a","b","c"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuselectcategory);
        Button menu1 = (Button) findViewById(R.id.menu1);
        menu1.setText(n[0]);
        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),cl_menuSelect_Details.class);
                startActivity(intent);
            }
        });
        Button menu2 = (Button) findViewById(R.id.menu2);
        menu2.setText(n[1]);
        menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), cl_menuSelect_Details.class);
                startActivity(intent);
            }
        });
        Button menu3 = (Button) findViewById(R.id.menu3);
        menu3.setText(n[2]);
        menu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), cl_menuSelect_Details.class);
                startActivity(intent);
            }
        });

    }

}