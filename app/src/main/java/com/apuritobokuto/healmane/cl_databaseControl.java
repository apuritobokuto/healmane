package com.apuritobokuto.healmane;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by Yuto on 2017/01/12.
 */

public class cl_databaseControl {
    // Databaseバージョン
    private static final int DATABASR_VERSION = 1;

    // Databaseファイル名
    private static final String DATABASE_FILE_NAME = "cl_healmane.db";

    private DatabaseOpenHelper mDbOpenHelper;
    protected SQLiteDatabase mDb;

    /**
     * コンストラクタ
     * @param context
     */
    public cl_databaseControl(Context context) {
        mDbOpenHelper = new DatabaseOpenHelper(context, DATABASE_FILE_NAME, null, DATABASR_VERSION);
    }

    /**
     * DBオープン
     */
    public void openWrite() {
        // データベースのオープン
        mDb = mDbOpenHelper.getWritableDatabase();
    }

    /**
     * Close
     */
    public void close() {
        mDbOpenHelper.close();
    }

    /**
     * データベースオープン用のヘルパー
     */
    private class DatabaseOpenHelper extends SQLiteOpenHelper {

        /**
         * コンストラクタ
         * @param context
         * @param dbName
         * @param factory
         * @param version
         */
        public DatabaseOpenHelper(Context context, String dbName, CursorFactory factory, int version) {
            super(context, dbName, factory, version);
        }

        /**
         * DB生成
         */
        @Override
        public void onCreate(SQLiteDatabase db) {

            // 健康管理テーブルの作成
            db.execSQL(
                    "CREATE TABLE cl_healmane_table ("
                            + "_id intger primary key autoincrement"
                            + "day integer"
                            + "red real"
                            + "green real"
                            + "yellow real"
                            + ")"
            );
        }

        /**
         * バージョンアップ
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(
                    "INSERT INTO cl_healmane_table VALUES(0, 23, 0.8, 0.7, 0.8)"
            );
        }
    }

}

