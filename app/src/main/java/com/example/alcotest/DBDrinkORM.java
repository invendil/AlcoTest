package com.example.alcotest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.alcotest.entities.Drink;

import java.util.ArrayList;

public class DBDrinkORM {
    private final String LOG_TAG = "DBDrinkORM";
    private SQLiteDatabase db ;
    private ContentValues cv ;
    private Drink drink;
    private ArrayList<Drink> drinks;
    private long currentId;


    public DBDrinkORM(SQLiteDatabase db){
        this.db = db;

        Cursor cursor = db.query("drinks", new String[]{"id"}, null, null, null, null, "id DESC", "1");
        if (cursor.moveToFirst()) {
            currentId = cursor.getInt(cursor.getColumnIndex("id"));
        } else {
            currentId = 0;
        }
        cursor.close();
    }

    public ArrayList<Drink> getAll(){
        Cursor c = db.query("drinks", null,null,null, null, null, null);

        drinks = new ArrayList<Drink>();
        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        if (c.moveToFirst()) {

            // определяем номера столбцов по имени в выборке
            int idColIndex = c.getColumnIndex("id");
            int nameColIndex = c.getColumnIndex("name");
            int interestColIndex = c.getColumnIndex("alc_interest");
            int iconIdColIndex = c.getColumnIndex("icon_id");
            int colorIdColIndex = c.getColumnIndex("color_id");
            int isfavoriteColIndex = c.getColumnIndex("isfavorite");
            do {
                drink = new Drink();
                drink.setId(c.getInt(idColIndex));
                drink.setName(c.getString(nameColIndex));
                drink.setAlcInterest(c.getInt(interestColIndex));
                drink.setIconId(c.getInt(iconIdColIndex));
                drink.setColorFilterId(c.getInt(colorIdColIndex));
                drink.setChoosen(c.getInt(isfavoriteColIndex));
                drinks.add(drink);
                Log.d(LOG_TAG, "found");
            } while (c.moveToNext());
        }
        Log.d(LOG_TAG, "0 rows");
        c.close();
        return drinks;
    }

    public Drink getById(long drinkId){

        Cursor c = db.query("drinks", null, "id = ?", new String[]{Long.toString(drinkId)}, null, null, null);

        drink = new Drink();
        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
            if (c.moveToFirst()) {

            //  определяемномера столбцов по имени в выборке
            int idColIndex = c.getColumnIndex("id");
            int nameColIndex = c.getColumnIndex("name");
            int interestColIndex = c.getColumnIndex("alc_interest");
            int iconIdColIndex = c.getColumnIndex("icon_id");
            int colorIdColIndex = c.getColumnIndex("color_id");
            int isfavoriteColIndex = c.getColumnIndex("isfavorite");

            drink.setId(c.getInt(idColIndex));
            drink.setName(c.getString(nameColIndex));
            drink.setAlcInterest(c.getInt(interestColIndex));
            drink.setIconId(c.getInt(iconIdColIndex));
            drink.setColorFilterId(c.getInt(colorIdColIndex));
            drink.setChoosen(c.getInt(isfavoriteColIndex));
            Log.d(LOG_TAG, "found");

        }else Log.d(LOG_TAG, "not found");
        c.close();
        return drink;
    }

    public long add(Drink drink){
        cv = new ContentValues();
        currentId++;
        cv.put("id", currentId);
        cv.put("name", drink.getName());
        cv.put("alc_interest", drink.getAlcInterest());
        cv.put("icon_id", drink.getIconId());
        cv.put("color_id", drink.getColorFilterId());
        cv.put("isfavorite", drink.isChoosen());

        // вставляем запись и получаем ее ID

        Log.d(LOG_TAG,"Added: " );
        long idOut = db.insert("drinks", null, cv);
        cv.clear();
        return idOut;
    }

    public void save(Drink drink){
        cv = new ContentValues();
        cv.put("id", drink.getId());
        cv.put("name", drink.getName());
        cv.put("alc_interest", drink.getAlcInterest());
        cv.put("icon_id", drink.getIconId());
        cv.put("color_id", drink.getColorFilterId());
        cv.put("isfavorite", drink.isChoosen());
        int updCount = db.update("drinks", cv, "id = ?",
                new String[] { Long.toString(drink.getId()) });
        Log.d(LOG_TAG, "updated rows count = " + updCount);
        cv.clear();

    }

    public void delete(long drinkId){
        int delCount = db.delete("drinks", "id = " + drinkId, null);
        Log.d(LOG_TAG, "deleted rows count = " + delCount);
    }

    public ArrayList<Drink> getAll(String selection, String selArgument){
        Cursor c = db.query("drinks", null,selection,new String[]{selArgument}, null, null, null);

        drinks = new ArrayList<Drink>();
        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        if (c.moveToFirst()) {

            // определяем номера столбцов по имени в выборке
            int idColIndex = c.getColumnIndex("id");
            int nameColIndex = c.getColumnIndex("name");
            int interestColIndex = c.getColumnIndex("alc_interest");
            int iconIdColIndex = c.getColumnIndex("icon_id");
            int colorIdColIndex = c.getColumnIndex("color_id");
            int isfavoriteColIndex = c.getColumnIndex("isfavorite");
            do {
                drink = new Drink();
                drink.setId(c.getInt(idColIndex));
                drink.setName(c.getString(nameColIndex));
                drink.setAlcInterest(c.getInt(interestColIndex));
                drink.setIconId(c.getInt(iconIdColIndex));
                drink.setColorFilterId(c.getInt(colorIdColIndex));
                drink.setChoosen(c.getInt(isfavoriteColIndex));
                drinks.add(drink);
                Log.d(LOG_TAG, "found");
            } while (c.moveToNext());
        }
        Log.d(LOG_TAG, "0 rows");
        c.close();
        return drinks;
    }
}
