package com.apuritobokuto.healmane;

/**
 * Created by RyuSato on 2016/11/29.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class cl_proposalSelect_Rice extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposalrice);

        Button menu1 = (Button) findViewById(R.id.button7);
        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), cl_proposalSelect_Details2.class);
                startActivity(intent);
            }
        });


    }

}