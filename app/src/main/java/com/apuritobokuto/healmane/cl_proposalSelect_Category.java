package com.apuritobokuto.healmane;

/**
 * Created by RyuSato on 2016/11/29.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class cl_proposalSelect_Category extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuli);

        Button menu1 = (Button) findViewById(R.id.menu1);
        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), cl_menuSelect_Details.class);
                startActivity(intent);
            }
        });


    }

}
