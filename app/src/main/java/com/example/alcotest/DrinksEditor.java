package com.example.alcotest;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.alcotest.adapters.DrinksIconsAdapter;

import java.util.ArrayList;


public class DrinksEditor extends AppCompatActivity implements DrinksIconsAdapter.OnDrinkIconListner, View.OnClickListener {

    static final String LOG_TAG = "Editor";
    static final int[] DRINKS_ITEM_ID = {R.drawable.drink1, R.drawable.drink2, R.drawable.drink3, R.drawable.drink4, R.drawable.drink5, R.drawable.drink6, R.drawable.drink7, R.drawable.drink8};
    static final int[] COLOR_LIST = {R.color.colorBlack,R.color.colorRed,R.color.colorBlue,R.color.colorGreen,R.color.colorYellow,R.color.colorCyan};
    private RecyclerView recyclerViewDrinksIcons;
    private DrinksIconsAdapter drinksIconsAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Integer> drinksIconsIdList = new ArrayList<Integer>();
    private ImageView imageDrinkIconPreview, imageColor;
    private Button btnColorLeft, btnColorRight;
    private int currentIcon = DRINKS_ITEM_ID[0], currentColor = 0;
    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks_editor);

        initializeData();

        imageDrinkIconPreview = (ImageView)findViewById(R.id.imageDrinkIconPreview);
        imageDrinkIconPreview.setImageResource(currentIcon);
        recyclerViewDrinksIcons = (RecyclerView) findViewById(R.id.recyclerDrinksIcons);
        btnColorLeft = (Button) findViewById(R.id.btnDrinkColorLeft);
        btnColorRight = (Button) findViewById(R.id.btnDrinkColorRight);
        btnColorLeft.setOnClickListener(this);
        btnColorRight.setOnClickListener(this);
        imageColor = (ImageView)findViewById(R.id.imageDrinkIconColor);
        imageColor.setImageResource(COLOR_LIST[currentColor]);
        renderIconColor(currentIcon, currentColor);
        recyclerViewDrinksIcons.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewDrinksIcons.setLayoutManager(layoutManager);

        drinksIconsAdapter = new DrinksIconsAdapter(drinksIconsIdList, this);

        recyclerViewDrinksIcons.setAdapter(drinksIconsAdapter);



        //ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams., ViewGroup.LayoutParams.WRAP_CONTENT);

       /* linearLayoutDrinksIcons = (LinearLayout)findViewById(R.id.linearLayoutDrinksIcons);
        for (int i = 0; i < DRINKS_ITEM_ID.length; i++) {
            btnDrink = new Button(this);
            btnDrink.setBackground(getResources().getDrawable(DRINKS_ITEM_ID[i]));
            btnDrink.setLa(250);
            btnDrink.setHeight(250);
            btnDrink.setId(i+253253);


            linearLayoutDrinksIcons.addView(btnDrink);
        }*/
        /*myButton.setBackground(drinkFullMap);

        LinearLayout ll = (LinearLayout)findViewById(R.id.buttonlayout);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        ll.addView(myButton, lp);*/
    }
    private void initializeData(){
        drinksIconsIdList.add(R.drawable.drink1);
        drinksIconsIdList.add(R.drawable.drink2);
        drinksIconsIdList.add(R.drawable.drink3);
        drinksIconsIdList.add(R.drawable.drink4);
        drinksIconsIdList.add(R.drawable.drink5);
        drinksIconsIdList.add(R.drawable.drink6);
        drinksIconsIdList.add(R.drawable.drink7);
        drinksIconsIdList.add(R.drawable.drink8);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onDrinkIconClick(int position) {
        currentIcon = DRINKS_ITEM_ID[position];
        renderIconColor(currentIcon, currentColor);
    }



    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void renderIconColor(int iconId, int iconColorId){
        Drawable renderIcon = getResources().getDrawable(iconId);
        renderIcon.setColorFilter(getColor(COLOR_LIST[iconColorId]), PorterDuff.Mode.SRC_IN );
        imageDrinkIconPreview.setImageDrawable(renderIcon);
        imageColor.setImageResource(COLOR_LIST[iconColorId]);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v){

        switch (v.getId()){
            case R.id.btnDrinkColorLeft :
                Log.d(LOG_TAG, Integer.toString(currentColor));
                if(currentColor - 1 < 0){
                    currentColor = COLOR_LIST.length - 1;
                } else{
                    currentColor --;
                }
                renderIconColor(currentIcon, currentColor);
                Log.d(LOG_TAG, Integer.toString(currentColor));
                break;

            case R.id.btnDrinkColorRight :
                Log.d(LOG_TAG, Integer.toString(currentColor));
                if(currentColor + 1 == COLOR_LIST.length){
                    currentColor = 0;
                } else{
                    currentColor ++;
                }
                renderIconColor(currentIcon, currentColor);
                Log.d(LOG_TAG, Integer.toString(currentColor));
                break;
        }
    }
}
