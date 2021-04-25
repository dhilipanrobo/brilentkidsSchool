package com.notesboard.admin.bkschool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class NoticeBoard extends AppCompatActivity {
    long Delay = 2500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_notice_board);

        Timer RunSplash = new Timer();


        TimerTask ShowSplash = new TimerTask() {
            @Override
            public void run() {
                // Close SplashScreenActivity.class
                finish();

                Intent vv=new Intent(NoticeBoard.this,Login.class);
                startActivity(vv);

            }


        };
        RunSplash.schedule(ShowSplash, Delay);

    }
    }

