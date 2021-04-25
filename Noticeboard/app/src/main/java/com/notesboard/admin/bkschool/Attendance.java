package com.notesboard.admin.bkschool;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

public class Attendance extends AppCompatActivity implements CalendarView.OnDateChangeListener {
CalendarView calender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_attendance);
        calender = (CalendarView) findViewById(R.id.calendarView);
        calender.setOnDateChangeListener(this);


    }

    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
        Toast.makeText(getApplicationContext(),"date:"+dayOfMonth+":"+month+":"+year, Toast.LENGTH_SHORT).show();
        Intent v=new Intent(this,Select_subject.class);
        String date=dayOfMonth+"-"+month+"-"+year;
        v.putExtra("ref",date);

        startActivity(v);
        finish();




    }
}
