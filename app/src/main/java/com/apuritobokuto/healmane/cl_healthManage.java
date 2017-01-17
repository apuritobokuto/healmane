package com.apuritobokuto.healmane;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;



public class cl_healthManage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

        //データベースヘルパーのインスタンス生成
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        //データベースオブジェクトを取得
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT day, red, green, yellow FROM healmane WHERE day >= ?;", new String[]{"25"});
        if (cursor == null) {
            return;
        }

        String day;
        float red;
        float green;
        float yellow;

        ArrayList<Entry> RedEntries = new ArrayList<Entry>(); //グラフ用データ(赤)
        ArrayList<Entry> GreenEntries = new ArrayList<Entry>(); //グラフ用データ(緑)
        ArrayList<Entry> YellowEntries = new ArrayList<Entry>(); //グラフ用データ(黄)

        int loopCounter = 0;
        while (cursor.moveToNext()) {
            day = cursor.getString(cursor.getColumnIndex("day"));
            red = cursor.getFloat(cursor.getColumnIndex("red"));
            green = cursor.getFloat(cursor.getColumnIndex("green"));
            yellow = cursor.getFloat(cursor.getColumnIndex("yellow"));

            //for (int r = 0; r < 7; r++) {
                RedEntries.add(new Entry(red, loopCounter));
            /*RedEntries.add(new Entry(50f, 1));
            RedEntries.add(new Entry(58f, 2));
            RedEntries.add(new Entry(23f, 3));
            RedEntries.add(new Entry(56f, 4));
            RedEntries.add(new Entry(90f, 5));
            RedEntries.add(new Entry(13f, 6));
            */
            //}


            //for (int  g = 0; g < 7; g++) {
                GreenEntries.add(new Entry(green, loopCounter));
            /*GreenEntries.add(new Entry(50f, 1));
            GreenEntries.add(new Entry(58f, 2));
            GreenEntries.add(new Entry(62f, 3));
            GreenEntries.add(new Entry(66f, 4));
            GreenEntries.add(new Entry(80f, 5));
            GreenEntries.add(new Entry(43f, 6));
            */
            //}

            //for (int  y = 0; y < 7; y++) {
                YellowEntries.add(new Entry(yellow, loopCounter));
            /*YellowEntries.add(new Entry(50f, 1));
            YellowEntries.add(new Entry(58f, 2));
            YellowEntries.add(new Entry(45f, 3));
            YellowEntries.add(new Entry(65f, 4));
            YellowEntries.add(new Entry(67f, 5));
            YellowEntries.add(new Entry(78f, 6));
            */
            //}
            // do something with the result...
            //}
            //} finally {
            //cursor.close();
            //}

            //System.out.println("2");


            //データベースを閉じる
            //db.close();
            //}

            //public void graphDraw (){
            //  dbSelect(); //グラフデータの取得

            //グラフ識別子の割り当て
            LineChart RLineChart = (LineChart) findViewById(R.id.RChart);
            LineChart GLineChart = (LineChart) findViewById(R.id.GChart);
            LineChart YLineChart = (LineChart) findViewById(R.id.YChart);

            //グラフの右側にy軸を表示しない
            RLineChart.getAxisRight().setEnabled(false);
            GLineChart.getAxisRight().setEnabled(false);
            YLineChart.getAxisRight().setEnabled(false);




            //データをセットし、それぞれのインスタンスを生成
            LineDataSet RedDataSet = new LineDataSet(RedEntries, "赤");
            LineDataSet GreenDataSet = new LineDataSet(GreenEntries, "緑");
            LineDataSet YellowDataSet = new LineDataSet(YellowEntries, "黄");



            //線の色付け
            RedDataSet.setColor(Color.RED);
            GreenDataSet.setColor(Color.GREEN);
            YellowDataSet.setColor(Color.YELLOW);

            //ラベル(x軸)
            String[] labels = {day, day, day};

            //LineDataのインスタンス生成
            LineData RedData = new LineData(labels, RedDataSet);
            LineData GreenData = new LineData(labels, GreenDataSet);
            LineData YellowData = new LineData(labels, YellowDataSet);

            //LineDataをLineChartにセット
            RLineChart.setData(RedData);
            GLineChart.setData(GreenData);
            YLineChart.setData(YellowData);

            //RLineChart.setDrawBorders(true);
            //GLineChart.setDrawBorders(true);
            //YLineChart.setDrawBorders(true);


            //説明分
            RLineChart.setDescription("栄養素");
            GLineChart.setDescription("栄養素");
            YLineChart.setDescription("栄養素");

            //背景色
            RLineChart.setBackgroundColor(Color.WHITE);
            GLineChart.setBackgroundColor(Color.WHITE);
            YLineChart.setBackgroundColor(Color.WHITE);

            loopCounter++;
        }
    }
}



