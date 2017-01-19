package com.apuritobokuto.healmane;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.animation.Easing;
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
        if (cursor == null) { //データベースがからの場合の処理

            return;
        }

        //データベースから取得した値を格納する変数
        String day;
        float red;
        float green;
        float yellow;

        ArrayList<Entry> RedEntries = new ArrayList<Entry>(); //グラフ用データ(赤)
        ArrayList<Entry> GreenEntries = new ArrayList<Entry>(); //グラフ用データ(緑)
        ArrayList<Entry> YellowEntries = new ArrayList<Entry>(); //グラフ用データ(黄)

        int loopCounter = 0;

        //ラベル(x軸)
        ArrayList<String> labels = new ArrayList<String>();

        while (cursor.moveToNext()) { //データが無くなるまで読む
            //データベースから値を取得
            day = cursor.getString(cursor.getColumnIndex("day"));
            red = cursor.getFloat(cursor.getColumnIndex("red"));
            green = cursor.getFloat(cursor.getColumnIndex("green"));
            yellow = cursor.getFloat(cursor.getColumnIndex("yellow"));

            //グラフ識別子の割り当て
            LineChart RLineChart = (LineChart) findViewById(R.id.RChart);
            LineChart GLineChart = (LineChart) findViewById(R.id.GChart);
            LineChart YLineChart = (LineChart) findViewById(R.id.YChart);

            //グラフの右側にy軸を表示しない
            RLineChart.getAxisRight().setEnabled(false);
            GLineChart.getAxisRight().setEnabled(false);
            YLineChart.getAxisRight().setEnabled(false);

            //ArrayListに値を格納していく
            RedEntries.add(new Entry(red, loopCounter));//loopCounter));
            GreenEntries.add(new Entry(green, loopCounter));//loopCounter));
            YellowEntries.add(new Entry(yellow, loopCounter));//loopCounter));

            //データをセットし、それぞれのインスタンスを生成
            LineDataSet RedDataSet = new LineDataSet(RedEntries, "赤");
            LineDataSet GreenDataSet = new LineDataSet(GreenEntries, "緑");
            LineDataSet YellowDataSet = new LineDataSet(YellowEntries, "黄");

            //線の色付け
            RedDataSet.setColor(Color.RED);
            GreenDataSet.setColor(Color.GREEN);
            YellowDataSet.setColor(Color.YELLOW);

            labels.add(day); //1ループごとにx軸に日付を設定する

            //LineDataのインスタンス生成
            LineData RedData = new LineData(labels, RedDataSet);
            LineData GreenData = new LineData(labels, GreenDataSet);
            LineData YellowData = new LineData(labels, YellowDataSet);

            //LineDataをLineChartにセット
            RLineChart.setData(RedData);
            GLineChart.setData(GreenData);
            YLineChart.setData(YellowData);

            //説明文
            RLineChart.setDescription("栄養素");
            GLineChart.setDescription("栄養素");
            YLineChart.setDescription("栄養素");

            //背景色
            RLineChart.setBackgroundColor(Color.WHITE);
            GLineChart.setBackgroundColor(Color.WHITE);
            YLineChart.setBackgroundColor(Color.WHITE);

            //アニメーション
            RLineChart.animateXY(2000, 2000, Easing.EasingOption.EaseInBack, Easing.EasingOption.EaseInBounce);
            GLineChart.animateXY(2000, 2000, Easing.EasingOption.EaseInBack, Easing.EasingOption.EaseInBounce);
            YLineChart.animateXY(2000, 2000, Easing.EasingOption.EaseInBack, Easing.EasingOption.EaseInBounce);

            loopCounter++; //ループカウンター

        }
    }
}



