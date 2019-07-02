package com.example.alcotest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    private final String LOG_TAG  = "AlcoTestDB";


        public DBHelper(Context context) {
            // конструктор суперкласса

            super(context, "AlcoTestDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG, "--- onCreate database ---");
            // создаем таблицу с полями
            db.execSQL("create table users ("
                    + "id integer primary key autoincrement,"
                    + "name text,"
                    + "weight integer,"
                    + "alc_influence integer"+ ");");
            db.execSQL("create table drinks("
                    + "id integer primary key autoincrement,"
                    + "name text,"
                    + "alc_interest real,"
                    + "favorites integer"+ ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
}
