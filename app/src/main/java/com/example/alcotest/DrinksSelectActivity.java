package com.example.alcotest;

import java.math.*;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.alcotest.adapters.DrinksIconsAdapter;
import com.example.alcotest.adapters.DrinksSelectAdapter;
import com.example.alcotest.adapters.DrinksSelectChoosenAdapter;
import com.example.alcotest.entities.Drink;

import java.util.ArrayList;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static com.example.alcotest.DrinksEditor.COLOR_LIST;
import static com.example.alcotest.DrinksEditor.DRINKS_ITEM_ID;

public class DrinksSelectActivity extends AppCompatActivity implements DrinksSelectAdapter.OnDrinksSelectListner, DrinksSelectChoosenAdapter.OnDrinksSelectChoosenListner {
    static final String LOG_TAG = "DrinksSelectActivity";
    private RecyclerView recyclerViewDrinksSelect;
    private DrinksSelectAdapter drinksSelectAdapter;
    private DrinksSelectChoosenAdapter drinksSelectChoosenAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Drink> drinksList = new ArrayList<Drink>();
    private ImageView imageDrinkIconPreview, imageColor;
    private Button btnChoosen, btnAll, btnAdd;
    public static  DBDrinkORM drinkORM;
    private int currentIcon = DRINKS_ITEM_ID[0], currentColor = 0;
    private ProgressBar progressBar;
    private TextView progressBarPercent;
    private Button incButton;

    private int mProgressBarStatus;

    private Handler mHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks_select);
 //    this.deleteDatabase("AlcoTestDB");
        DBHelper dh = new DBHelper(this);

        SQLiteDatabase sd = dh.getWritableDatabase();

        drinkORM = new DBDrinkORM(sd);

   //   initializeData();
        drinksList = drinkORM.getAll();

        btnAdd = (Button)findViewById(R.id.btnDrinksSelectAddNewDrink);
        btnChoosen = (Button)findViewById(R.id.btnDrinksSelectChoosen);
        btnAll = (Button)findViewById(R.id.btnDrinksSelectAll);
        btnInit();
        recyclerViewDrinksSelect = (RecyclerView) findViewById(R.id.recyclerDrinksSelect);
        recyclerViewDrinksSelect.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewDrinksSelect.setLayoutManager(layoutManager);
        drinksSelectAdapter = new DrinksSelectAdapter( drinksList, this, getApplicationContext()  );
        drinksSelectChoosenAdapter = new DrinksSelectChoosenAdapter( drinksList, this, getApplicationContext()  );

        Log.d(LOG_TAG,"recycle Init");
        recyclerViewDrinksSelect.setAdapter(drinksSelectAdapter);

        progressBar = findViewById(R.id.progressBar);
        progressBarPercent = findViewById(R.id.textProgressBarPercent);



        new Thread(new Runnable() {
            @Override
            public void run() {while(true){
                android.os.SystemClock.sleep(1000);
                synchronized (progressBar){
                    mProgressBarStatus = progressBar.getProgress();
                }
                while(mProgressBarStatus > 0){
                    android.os.SystemClock.sleep(100);
                    synchronized (progressBar){
                        mProgressBarStatus = progressBar.getProgress();
                    }
                    mProgressBarStatus--;
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(mProgressBarStatus);
                            progressBarPercent.setText(mProgressBarStatus+"%");
                        }
                    });
                }
            }
            }
        }).start();

    }


    private void btnInit(){
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(DrinksSelectActivity.this, DrinksEditor.class);
                mainIntent.putExtra("drink_edit_id", -2);
                mainIntent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(mainIntent);
            }
        });
        btnChoosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drinksSelectChoosenAdapter.updateData();
                recyclerViewDrinksSelect.setAdapter(drinksSelectChoosenAdapter);

            }
        });
        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drinksSelectAdapter.updateData();
                recyclerViewDrinksSelect.setAdapter(drinksSelectAdapter);

            }
        });
    }


    private void initializeData(){
        drinkORM.add(new Drink("Napitok1", 1,3));
        drinkORM.add(new Drink("Napitok2", 2,2));
        drinkORM.add(new Drink("Napitok3", 3,4));
        drinkORM.add(new Drink("Napitok1", 1,3));
        drinkORM.add(new Drink("Napitok2", 2,2));
        drinkORM.add(new Drink("Napitok3", 3,4));
        drinkORM.add(new Drink("Napitok1", 1,3));
        drinkORM.add(new Drink("Napitok2", 2,2));
        drinkORM.add(new Drink("Napitok3", 3,4));


        Log.d(LOG_TAG, "init bd");
    }





    @Override
    public void onDrinksSelectClick(int position) {
        drinkSomeDrink(position);
    }

    @Override
    public void onDrinksSelectChoosenClick(int position) {
        drinkSomeDrink(position);
    }

    @Override
    protected void onResume() {
        drinksSelectAdapter.updateData();
        drinksSelectChoosenAdapter.updateData();
        super.onResume();
    }

    private void drinkSomeDrink(int position){
        mProgressBarStatus = mProgressBarStatus + 5 > 100? mProgressBarStatus = 100:mProgressBarStatus + 5;
        progressBar.setProgress(mProgressBarStatus);
        progressBarPercent.setText(mProgressBarStatus+"%");

    }
}
