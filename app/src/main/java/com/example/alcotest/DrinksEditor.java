package com.example.alcotest;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.alcotest.adapters.DrinksIconsAdapter;
import com.example.alcotest.entities.Drink;

import java.util.ArrayList;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static com.example.alcotest.DrinksSelectActivity.drinkORM;


public class DrinksEditor extends AppCompatActivity implements DrinksIconsAdapter.OnDrinkIconListner, View.OnClickListener {

    static final String LOG_TAG = "DrinksEditorActivity";
    public static final int[] DRINKS_ITEM_ID = {R.drawable.drink1, R.drawable.drink2, R.drawable.drink3, R.drawable.drink4, R.drawable.drink5, R.drawable.drink6, R.drawable.drink7, R.drawable.drink8};
    public static final int[] COLOR_LIST = {R.color.colorBlack,R.color.colorRed,R.color.colorBlue,R.color.colorGreen,R.color.colorYellow,R.color.colorCyan};
    private RecyclerView recyclerViewDrinksIcons;
    private DrinksIconsAdapter drinksIconsAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Integer> drinksIconsIdList = new ArrayList<Integer>();
    private ImageView imageDrinkIconPreview, imageColor;
    private Button btnColorLeft, btnColorRight, btnSave;
    private EditText editTextDrink;
    private SeekBar seekBarDrinkInterest;
    private TextView textSeekBarState;
    private Drink drinkEdit;
    private int currentIconId = 0;
    private long drinkEditId;
    private int currentIcon = DRINKS_ITEM_ID[0], currentColor = COLOR_LIST[0], currentColorId = 0;
    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks_editor);
        Intent intent = getIntent();
        String drinkEditName = intent.getStringExtra("drink_edit_name");
        drinkEditId = intent.getLongExtra("drink_edit_id", -1);
        Log.d(LOG_TAG, "drinkID: "+ drinkEditId + ";" );
        if (drinkEditId > 0) {
            drinkEdit = drinkORM.getById(drinkEditId);
            currentIcon = DRINKS_ITEM_ID[drinkEdit.getIconId()];
            currentColorId =drinkEdit.getColorFilterId();
            currentIconId = drinkEdit.getIconId();
            currentColor = COLOR_LIST[currentColorId];
        } else{
            drinkEdit = new Drink("",0,0,0);
        }
        initializeData();

        editTextDrink = (EditText)findViewById(R.id.textEditDrinkName);
        editTextDrink.setText(drinkEdit.getName());
        seekBarDrinkInterest = (SeekBar)findViewById(R.id.seekBarDrinkInterest);
        textSeekBarState = (TextView)findViewById(R.id.textSeekBarState);
        imageDrinkIconPreview = (ImageView)findViewById(R.id.imageDrinkIconPreview);
        imageDrinkIconPreview.setImageResource(currentIcon);
        recyclerViewDrinksIcons = (RecyclerView) findViewById(R.id.recyclerDrinksIcons);
        btnColorLeft = (Button) findViewById(R.id.btnDrinkColorLeft);
        btnSave = (Button) findViewById(R.id.btnSaveDrink);
        btnColorRight = (Button) findViewById(R.id.btnDrinkColorRight);
        btnColorLeft.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnColorRight.setOnClickListener(this);
        imageColor = (ImageView)findViewById(R.id.imageDrinkIconColor);
        imageColor.setImageResource(currentColor);


        recyclerViewDrinksIcons.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewDrinksIcons.setLayoutManager(layoutManager);
        drinksIconsAdapter = new DrinksIconsAdapter(drinksIconsIdList, this);
        recyclerViewDrinksIcons.setAdapter(drinksIconsAdapter);

        renderIconColor(currentIcon, currentColor);

        seekBarUse();

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
    private void seekBarUse(){
        seekBarDrinkInterest.setMax(100);

        seekBarDrinkInterest.setProgress(drinkEdit.getAlcInterest());
        textSeekBarState.setText(Integer.toString(drinkEdit.getAlcInterest())+'%');
        seekBarDrinkInterest.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textSeekBarState.setText(Integer.toString((int)((float)progress/seekBarDrinkInterest.getMax()*100))+'%');
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
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
        currentIconId = position;
        renderIconColor(currentIcon, currentColor);
    }



    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void renderIconColor(int iconId, int iconColorId){
        Drawable renderIcon  = getResources().getDrawable(iconId).getConstantState().newDrawable().mutate();
        renderIcon.setColorFilter(getColor(iconColorId), PorterDuff.Mode.SRC_IN );
        imageDrinkIconPreview.setImageDrawable(renderIcon);
        imageColor.setImageResource(iconColorId);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v){

        switch (v.getId()){
            case R.id.btnDrinkColorLeft :

                if(currentColorId - 1 < 0){
                    currentColorId = COLOR_LIST.length - 1;
                } else{
                    currentColorId --;
                }
                currentColor = COLOR_LIST[currentColorId];
                renderIconColor(currentIcon, currentColor);
                Log.d(LOG_TAG, Integer.toString(currentColorId));
                break;

            case R.id.btnDrinkColorRight :

                if(currentColorId + 1 == COLOR_LIST.length){
                    currentColorId = 0;
                } else{
                    currentColorId ++;
                }
                currentColor = COLOR_LIST[currentColorId];
                renderIconColor(currentIcon, currentColor);
                Log.d(LOG_TAG, Integer.toString(currentColorId));
                break;
            case R.id.btnSaveDrink :
                Log.d(LOG_TAG, "btnSaveBtn");
                drinkEdit.setName(editTextDrink.getText().toString());
                drinkEdit.setColorFilterId(currentColorId);
                drinkEdit.setIconId(currentIconId);
                drinkEdit.setAlcInterest(seekBarDrinkInterest.getProgress());
                if(drinkEditId < 0){
                    drinkORM.add(drinkEdit);
                } else {drinkORM.save(drinkEdit);}
                break;

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent mainIntent = new Intent(DrinksEditor.this, DrinksSelectActivity.class);
  //      mainIntent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(mainIntent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
