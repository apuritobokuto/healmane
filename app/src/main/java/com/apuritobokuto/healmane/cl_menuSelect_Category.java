package com.apuritobokuto.healmane;

/**
 * Created by RyuSato on 2016/11/29.
 */


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;



public class cl_menuSelect_Category extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuchoice);

        Button menuM = (Button) findViewById(R.id.menuM);
        menuM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), cl_menuSelect_Choice.class);
                //intent.putExtra("data","1");
                intent.putExtra("data","1");
                startActivity(intent);
            }
        });
        Button menuS = (Button) findViewById(R.id.menuS);
        menuS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), cl_menuSelect_Choice.class);
                intent.putExtra("data","2");
                startActivity(intent);
            }
        });
        Button menuSS = (Button) findViewById(R.id.menuSS);
        menuSS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), cl_menuSelect_Choice.class);
                intent.putExtra("data","3");
                startActivity(intent);
            }
        });

    }

}