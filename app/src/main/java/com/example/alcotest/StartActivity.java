package com.example.alcotest;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.alcotest.entities.User;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class StartActivity extends AppCompatActivity {
    public static  DBUserORM userORM;
    private User user;
    private ImageView imageMood1, imageMood2, imageMood3, imageMood4, imageOptions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
//        this.deleteDatabase("AlcoTestDB");
        DBHelper dh = new DBHelper(this);

        SQLiteDatabase sd = dh.getWritableDatabase();
        userORM = new DBUserORM(sd);
        imageMood1 = (ImageView)findViewById(R.id.imageStartActivityMood1);
        imageMood2 = (ImageView)findViewById(R.id.imageStartActivityMood2);
        imageMood3 = (ImageView)findViewById(R.id.imageStartActivityMood3);
        imageMood4 = (ImageView)findViewById(R.id.imageStartActivityMood4);
        imageOptions = (ImageView)findViewById(R.id.imageStartActivityOptions);
        user = userORM.getById(1);
        imageMood1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setMood(1);
                userORM.save(user);

                Intent mainIntent = new Intent(StartActivity.this, DrinksSelectActivity.class);
                mainIntent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(mainIntent);
            }
        });

        imageMood2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setMood(2);
                userORM.save(user);

                Intent mainIntent = new Intent(StartActivity.this, DrinksSelectActivity.class);
                mainIntent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(mainIntent);
            }
        });

        imageMood3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setMood(3);
                userORM.save(user);

                Intent mainIntent = new Intent(StartActivity.this, DrinksSelectActivity.class);
                mainIntent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(mainIntent);
            }
        });

        imageMood4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setMood(4);
                userORM.save(user);

                Intent mainIntent = new Intent(StartActivity.this, DrinksSelectActivity.class);
                mainIntent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(mainIntent);
            }
        });

        imageOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(StartActivity.this, UserOptionsActivity.class);
                mainIntent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(mainIntent);
            }
        });

    }



}
