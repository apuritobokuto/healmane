package com.apuritobokuto.healmane;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;


public class cl_healthManage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

        //グラフのインスタンス
        LineChart RLineChart = (LineChart)findViewById(R.id.RChart);
        LineChart GLineChart = (LineChart)findViewById(R.id.GChart);
        LineChart YLineChart = (LineChart)findViewById(R.id.YChart);

        //グラフの右側にy軸を表示しない
        RLineChart.getAxisRight().setEnabled(false);
        GLineChart.getAxisRight().setEnabled(false);
        YLineChart.getAxisRight().setEnabled(false);

        //グラフ用データ(赤)
        ArrayList<Entry> RedEntries = new ArrayList<Entry>();
        RedEntries.add(new Entry(60f,0));
        RedEntries.add(new Entry(50f,1));
        RedEntries.add(new Entry(58f,2));
        RedEntries.add(new Entry(23f,3));
        RedEntries.add(new Entry(56f,4));
        RedEntries.add(new Entry(90f,5));
        RedEntries.add(new Entry(13f,6));

        //グラフ用データ(緑)
        ArrayList<Entry> GreenEntries = new ArrayList<Entry>();
        GreenEntries.add(new Entry(60f,0));
        GreenEntries.add(new Entry(50f,1));
        GreenEntries.add(new Entry(58f,2));
        GreenEntries.add(new Entry(62f,3));
        GreenEntries.add(new Entry(66f,4));
        GreenEntries.add(new Entry(80f,5));
        GreenEntries.add(new Entry(43f,6));

        //グラフ用データ(黄)
        ArrayList<Entry> YellowEntries = new ArrayList<Entry>();
        YellowEntries.add(new Entry(60f,0));
        YellowEntries.add(new Entry(50f,1));
        YellowEntries.add(new Entry(58f,2));
        YellowEntries.add(new Entry(45f,3));
        YellowEntries.add(new Entry(65f,4));
        YellowEntries.add(new Entry(67f,5));
        YellowEntries.add(new Entry(78f,6));

        //データをセット
        LineDataSet RedDataSet = new LineDataSet(RedEntries,"赤");
        LineDataSet GreenDataSet = new LineDataSet(GreenEntries,"緑");
        LineDataSet YellowDataSet = new LineDataSet(YellowEntries, "黄");

        //線の色付け
        RedDataSet.setColor(Color.RED);
        GreenDataSet.setColor(Color.GREEN);
        YellowDataSet.setColor(Color.YELLOW);

        //ラベル
        String[] labels = {"1日","2日","3日", "4日", "5日", "6日", "7日"};

        //LineDataインスタンス生成
        LineData RedData = new LineData(labels,RedDataSet);
        LineData GreenData = new LineData(labels, GreenDataSet);
        LineData YellowData = new LineData(labels,YellowDataSet);

        //LineDataをLineChartにセット
        RLineChart.setData(RedData);
        GLineChart.setData(GreenData);
        YLineChart.setData(YellowData);

        //説明分
        RLineChart.setDescription("栄養素");
        GLineChart.setDescription("栄養素");
        YLineChart.setDescription("栄養素");

        //背景色
        RLineChart.setBackgroundColor(Color.WHITE);
        GLineChart.setBackgroundColor(Color.WHITE);
        YLineChart.setBackgroundColor(Color.WHITE);
    }
}
