package com.apuritobokuto.healmane;

/**
 * Created by RyuSato on 2017/01/12.
 */
import android.app.Application;

import java.util.Arrays;


public class Global extends Application {
    String menu1;
    String menu2;
    String menu3;
    String menu4;
    String name1;
    String name2;
    String name3;
    String name4;
    String b;
    String m[][]=new String[4][5];
    String green;
    String green1;
    String green2;
    String totalgreen;
    public void GlobalAllInit(){
        menu1 = "nodata";
        menu2 = "nodata";
        menu3 = "nodata";
        menu4 = "nodata";
        name1 = "メニューを選択してください";
        name2 = "メニューを選択してくだちい";
        name3 = "メニューを選択してください";
        name4 = "メニューを選択してくだちい";
        for(String[] row:m){
            Arrays.fill(row,"0");
        }
        green="nodata";
        green1 = "nodata";
        green2 = "nodata";
        totalgreen = "nodata";
        b="nodata";
    }

}
