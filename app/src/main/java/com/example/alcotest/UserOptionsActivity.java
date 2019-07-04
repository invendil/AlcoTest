package com.example.alcotest;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alcotest.entities.User;

import java.util.ArrayList;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static com.example.alcotest.StartActivity.userORM;

public class UserOptionsActivity extends AppCompatActivity {

    private TextView textSeekbar, textWeight;
    private SeekBar seekBar;
    private RadioButton radioButton;
    private RadioGroup radioGroup;
    private Button btnSave;
    private User user;
    private ArrayList<Integer> radioButtonsId = new ArrayList<Integer>();
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_options);
        initRadioButtonsId();

        user = userORM.getById(1);
        textWeight = findViewById(R.id.textUserOptionsWeight);
        btnSave = findViewById(R.id.btnUserOptionssSave);
        radioGroup = findViewById(R.id.radioGroupUserOptions);
        seekBar = findViewById(R.id.seekBarUserOptionsWeight);
        seekBar.setMax(200);
        seekBar.setMin(40);
        seekBar.setProgress(user.getWeight());
        textWeight.setText("Ваш вес: " + Integer.toString(seekBar.getProgress()) + "кг");
        radioGroup.check(radioButtonsId.get(user.getAlcInfluence()));
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textWeight.setText("Ваш вес: " + Integer.toString(seekBar.getProgress()) + "кг");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (radioGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(), "Please select something", Toast.LENGTH_SHORT).show();
                } else {

                    int selectedId = radioButtonsId.indexOf(radioGroup.getCheckedRadioButtonId()) ;
                    user.setWeight(seekBar.getProgress());
                    user.setAlcInfluence(selectedId);
                    userORM.save(user);


                }

                Intent mainIntent = new Intent(UserOptionsActivity.this, StartActivity.class);
                mainIntent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(mainIntent);

            }
            }
        );
    }

    private void initRadioButtonsId(){
        radioButtonsId.add(R.id.radioBtnUserOptionsLow);
        radioButtonsId.add(R.id.radioBtnUserOptionsMiddle);
        radioButtonsId.add(R.id.radioBtnUserOptionsHigh);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}