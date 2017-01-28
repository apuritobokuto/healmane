package com.apuritobokuto.healmane;


import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ryu on 2016/11/17.
 */

public class cl_menuSelect extends AppCompatActivity {
    Global global;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;

    private double sum;
    private double g;
    private double y;
    private double r;
    private double c;
    private int money;
    private SimpleDateFormat dateformat;
    private static final String dkey="date";
    private String dtmp;
    private Date today;

    private void savedate(){
        SharedPreferences registered = PreferenceManager.getDefaultSharedPreferences(getApplication());
        SharedPreferences.Editor setdate = registered.edit();
        setdate.putString(dkey,dtmp);
        setdate.commit();
    }

    private void insertdb(String day,double r,double y,double g){
        DatabaseHelper dbHelper = new DatabaseHelper(getApplication());
        SQLiteDatabase db  = dbHelper.getWritableDatabase();
        ContentValues insertValues = new ContentValues();
        System.out.println("insert"+"start");
        insertValues.put("day",day);
        insertValues.put("red",r);
        insertValues.put("yellow",y);
        insertValues.put("green",g);
        System.out.println(insertValues);
        db.insert("healmane",null,insertValues);
        System.out.println("insert"+"end");
    }

    public void cal(){
        r=Double.parseDouble(global.m[0][0])+Double.parseDouble(global.m[1][0])
                +Double.parseDouble(global.m[2][0])+Double.parseDouble(global.m[3][0]);
        g=Double.parseDouble(global.m[0][1])+Double.parseDouble(global.m[1][1])
                +Double.parseDouble(global.m[2][1])+Double.parseDouble(global.m[3][1]);
        y=Double.parseDouble(global.m[0][2])+Double.parseDouble(global.m[1][2])
                +Double.parseDouble(global.m[2][2])+Double.parseDouble(global.m[3][2]);
        c=Double.parseDouble(global.m[0][3])+Double.parseDouble(global.m[1][3])
                +Double.parseDouble(global.m[2][3])+Double.parseDouble(global.m[3][3]);
        money=Integer.parseInt(global.m[0][4])+Integer.parseInt(global.m[1][4])
                +Integer.parseInt(global.m[2][4])+Integer.parseInt(global.m[3][4]);
        System.out.println("calちゅう");
        System.out.println("r:g:y:c|"+r+":"+g+":"+y+":"+c);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        global = (Global) this.getApplication();
        //global.GlobalAllInit();
        screen();

    }
    private void screen(){
        System.out.println("cal前");
        cal();
        System.out.println("cal後");

        today = new Date();
        dateformat = new SimpleDateFormat("MMdd");
        dtmp=dateformat.format(today);
        System.out.println("date:"+dtmp);

        TextView text = (TextView) findViewById(R.id.sum);
        c=Math.round(c*10);
        r=Math.round(r*10);
        g=Math.round(g*10);
        y=Math.round(y*10);

        text.setText("合計:"+money+"円"+"　カロリー"+c/10+"kcal"+"\n"+"赤:"+r/10+"点 緑:"+g/10+"点 黄:"+y/10+"点");
        button1 = (Button) findViewById(R.id.button1);
        button1.setText(global.name1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), cl_menuSelect_Category.class);
                global.b="1";
                startActivity(intent);
            }
        });
        button1.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v){
                global.m[0][0]="0.0";
                global.m[0][1]="0.0";
                global.m[0][2]="0.0";
                global.m[0][3]="0.0";
                global.m[0][4]="0";
                global.name1="メニューを選択してください";
                //cal();
                screen();
                return true;
            }
        });
        button2 = (Button) findViewById(R.id.button2);
        button2.setText(global.name2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), cl_menuSelect_Category.class);
                global.b="2";
                startActivity(intent);
            }
        });
        button2.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v){
                global.m[1][0]="0.0";
                global.m[1][1]="0.0";
                global.m[1][2]="0.0";
                global.m[1][3]="0.0";
                global.m[1][4]="0";
                global.name2="メニューを選択してくだちい";
                //cal();
                screen();
                return true;
            }
        });
        button3 = (Button) findViewById(R.id.button3);
        button3.setText(global.name3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), cl_menuSelect_Category.class);
                global.b="3";
                startActivity(intent);
            }
        });
        button3.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v){
                global.m[2][0]="0.0";
                global.m[2][1]="0.0";
                global.m[2][2]="0.0";
                global.m[2][3]="0.0";
                global.m[2][4]="0";
                global.name3="メニューを選択してください";
                //cal();
                screen();
                return true;
            }
        });
        button4 = (Button) findViewById(R.id.button4);
        button4.setText(global.name4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), cl_menuSelect_Category.class);
                global.b="4";
                startActivity(intent);
            }
        });
        button4.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v){
                global.m[3][0]="0.0";
                global.m[3][1]="0.0";
                global.m[3][2]="0.0";
                global.m[3][3]="0.0";
                global.m[3][4]="0";
                global.name4="メニューを選択してください";
                //cal();
                screen();
                return true;
            }
        });
        SharedPreferences registered = PreferenceManager.getDefaultSharedPreferences(getApplication());
        //System.out.println(registered.getString("date",null));
        if(dtmp.equals(registered.getString("date",null))) {
            button5 = (Button) findViewById(R.id.button5);
            button5.setText("戻る");
            button5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    global.GlobalAllInit();
                    //Toast.makeText(getApplicationContext(), "登録は１日1回です。", Toast.LENGTH_LONG).show();
                    finish();
                }
            });
            Toast.makeText(getApplicationContext(), "登録は１日1回です。", Toast.LENGTH_LONG).show();
        }else if(money!=0){
            button5 = (Button) findViewById(R.id.button5);
            button5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    insertdb(dtmp,r/10,y/10,g/10);
                    global.GlobalAllInit();
                    savedate();
                    finish();
                }
            });
        }else{
            button5 = (Button) findViewById(R.id.button5);
            button5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "メニューを選択してください。", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    /*
    @Override
    protected void onResume() {
        super.onResume();
        System.out.println(global.menu1);
        button.setText(global.menu1);
    }
    */

}



