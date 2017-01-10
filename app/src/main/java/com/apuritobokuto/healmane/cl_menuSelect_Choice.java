package com.apuritobokuto.healmane;

/**
 * Created by RyuSato on 2016/11/29.
 */

import android.app.Activity;
import android.content.Intent;
import android.view.WindowManager;
import android.view.Display;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.view.ViewGroup;

public class cl_menuSelect_Choice extends Activity {
    String[] n = {"karaage","b","c","d","e","f","g"};
    private TextView textView;
    private boolean flag = false;
    private  Button button[] = new Button[n.length];
    private  LinearLayout.LayoutParams buttonLayoutParams;
    ScrollView scrollView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScrollView scrollView = new ScrollView(this);

        setContentView(scrollView);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        layout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));

        scrollView.addView(layout);


        for(int i=0; i< button.length ;i++) {
            button[i] = new Button(this);
            // Tag を設定する
            button[i].setTag(String.valueOf(i));
            button[i].setText("Button " + String.valueOf(i));
            float scale = getResources().getDisplayMetrics().density;
            int buttonWidth = (int)(360 * scale);
            int buttonHeight = (int)(100 * scale);
            LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams(
                    buttonWidth,buttonHeight);


            button[i].setLayoutParams(buttonLayoutParams);
            layout.addView(button[i]);

            // Listnerをセット
            button[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(getApplication(), cl_menuSelect_Details.class);
                    startActivity(intent);
                }
            });
        }
    }


        /*button = new Button(this);
        button.setText(n[1]);
        float scale = getResources().getDisplayMetrics().density;
        int buttonWidth = (int)(150 * scale);

        buttonLayoutParams = new LinearLayout.LayoutParams(
                buttonWidth, LinearLayout.LayoutParams.WRAP_CONTENT);

        button.setLayoutParams(buttonLayoutParams);
        layout.addView(button);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                    if (flag){
                    textView.setText(n[1]);
                    }
                    else {
                        float scale = getResources().getDisplayMetrics().density;
                        int buttonWidth = (int)(360 * scale);
                        int buttonHeight = (int)(100 * scale);
                        // 横幅 250dp に設定
                        buttonLayoutParams = new LinearLayout.LayoutParams(
                                buttonWidth, buttonHeight);
                        int margins = (int)(20 * scale);
                        // setMargins (int left, int top, int right, int bottom)

                        button.setLayoutParams(buttonLayoutParams);
                    }
                }
             });*/
}




        /*setContentView(R.layout.activity_menuselectcategory);
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

    }*/

