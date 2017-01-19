package com.apuritobokuto.healmane;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * SQLite処理Helper
 */
public class DatabaseHelper extends SQLiteOpenHelper{


    // Databaseバージョン
    private static final int DB_VER = 1;

    // Databaseファイル名
    private static final String DB_NAME = "health.db";

    /*
    * コンストラクタ
     */
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    /*
     * onCreateメソッド
     * データベースが作成された時に呼ばれます。
     * テーブルの作成などを行います。
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "";
        sql += "create table healmane (";
        sql += " id integer primary key autoincrement";
        sql += ",day string not null";
        sql += ",red real";
        sql += ",green real";
        sql += ",yellow real";
        sql += ")";
        db.execSQL(sql);
    }

    /*
     * onUpgradeメソッド
     * onUpgrade()メソッドはデータベースをバージョンアップした時に呼ばれます。
     * 現在のレコードを退避し、テーブルを再作成した後、退避したレコードを戻すなどの処理を行います。
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}