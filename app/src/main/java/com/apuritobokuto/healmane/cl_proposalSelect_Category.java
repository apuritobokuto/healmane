package com.apuritobokuto.healmane;

/**
 * Created by RyuSato on 2016/11/29.
 */

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class cl_proposalSelect_Category extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposalcategory);

        Button shu = (Button) findViewById(R.id.shu);
        shu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), cl_proposalSelect_Choice.class);
                intent.putExtra("data","11");
                startActivity(intent);
            }
        });

        Button don = (Button) findViewById(R.id.don);
        don.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), cl_proposalSelect_Choice.class);
                intent.putExtra("data","12");
                startActivity(intent);
            }
        });

        Button men = (Button) findViewById(R.id.men);
        men.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), cl_proposalSelect_Choice.class);
                intent.putExtra("data","13");
                startActivity(intent);
            }
        });


    }

}
