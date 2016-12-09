package com.apuritobokuto.healmane;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by RyuSato on 2016/12/09.
 */

public class cl_setting extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Button setalle = (Button) findViewById(R.id.setalle);
        setalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), cl_settingsAllergy.class);
                startActivity(intent);
            }
        });
    }

}
