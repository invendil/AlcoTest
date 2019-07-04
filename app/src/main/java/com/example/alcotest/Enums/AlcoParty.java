package com.example.alcotest.Enums;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alcotest.R;

public class AlcoParty extends AppCompatActivity implements View.OnClickListener{

    private ProgressBar progressBar;
    private TextView progressBarPercent;
    private Button incButton;

    private int mProgressBarStatus;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alco_party);

        progressBar = findViewById(R.id.progressBar);
        progressBarPercent = findViewById(R.id.progressBarPercent);
        incButton = findViewById(R.id.incbutton);

        incButton.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.incbutton){
            mProgressBarStatus = mProgressBarStatus + 5 > 100? mProgressBarStatus = 100:mProgressBarStatus + 5;
            progressBar.setProgress(mProgressBarStatus);
            progressBarPercent.setText(mProgressBarStatus+"%");
        }
    }
}
