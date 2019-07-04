package com.example.alcotest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.alcotest.entities.User;
import com.example.alcotest.entities.User;

import java.util.ArrayList;

public class DBUserORM {
    private final String LOG_TAG = "DBUserORM";
    private SQLiteDatabase db ;
    private String tableName;
    private ContentValues cv ;
    private User user;
    

    public DBUserORM(SQLiteDatabase db){
        this.db = db;

       
    }

    

    public User getById(long userId){

        Cursor c = db.query(tableName, null, "id = ?", new String[]{Long.toString(userId)}, null, null, null);

        user = new User();
        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
            if (c.moveToFirst()) {

            //  определяемномера столбцов по имени в выборке

                int idColIndex = c.getColumnIndex("id");
                int weightColIndex = c.getColumnIndex("weight");
                int alcInfluenceColIndex = c.getColumnIndex("alcInfluence");
                int moodIdColIndex = c.getColumnIndex("mood");


            user.setId(c.getInt(idColIndex));
            user.setWeight(c.getInt(weightColIndex));
            user.setAlcInfluence(c.getInt(alcInfluenceColIndex));
            user.setMood(c.getInt(moodIdColIndex));

            Log.d(LOG_TAG, "found");

        }else Log.d(LOG_TAG, "not found");
        c.close();
        return user;
    }

    public long add(User user){
        cv = new ContentValues();
        cv.put("id", user.getId());
        cv.put("weight", user.getWeight());
        cv.put("alcInfluence", user.getAlcInfluence());
        cv.put("mood", user.getMood());


        // вставляем запись и получаем ее ID

        Log.d(LOG_TAG,"Added: " );
        long idOut = db.insert(tableName, null, cv);
        cv.clear();
        return idOut;
    }

    public void save(User user){
        cv = new ContentValues();
        cv.put("id", user.getId());
        cv.put("weight", user.getWeight());
        cv.put("alcInfluence", user.getAlcInfluence());
        cv.put("mood", user.getMood());
        int updCount = db.update(tableName, cv, "id = ?",
                new String[] { Long.toString(user.getId()) });
        Log.d(LOG_TAG, "updated rows count = " + updCount);
        cv.clear();

    }

    public void delete(long userId){
        int delCount = db.delete(tableName, "id = " + userId, null);
        Log.d(LOG_TAG, "deleted rows count = " + delCount);
    }


}
