package com.example.alcotest;

import android.content.ContentValues;
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
     //       db.execSQL("DROP TABLE IF EXISTS " + "drinks");
            Log.d(LOG_TAG, "--- onCreate database ---");
            // создаем таблицу с полями
            db.execSQL("create table user ("
                    + "id integer primary key,"
                    + "mood integer,"
                    + "weight integer,"
                    + "alcInfluence integer"+ ");");
            db.execSQL("create table drinks("
                    + "id integer primary key ,"
                    + "name text,"
                    + "alc_interest integer,"
                    + "icon_id integer,"
                    + "color_id integer,"
                    + "isfavorite integer"+ ");");
            ContentValues cv = new ContentValues();

            cv.put("id", 1);
            cv.put("weight", 80);
            cv.put("alcInfluence", 2);
            cv.put("mood", 1);


            // вставляем запись и получаем ее ID

            Log.d(LOG_TAG,"Added: " );
            long idOut = db.insert("user", null, cv);
            cv.clear();
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }


}
