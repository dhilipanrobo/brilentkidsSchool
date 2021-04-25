package com.notesboard.admin.bkschool;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class SelectDate extends AppCompatActivity implements CalendarView.OnDateChangeListener {


    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_select_date);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDialog(999);

    }
    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);


    }

    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
        //  Toast.makeText(getApplicationContext(),"date:"+dayOfMonth+":"+month+":"+year, Toast.LENGTH_SHORT).show();
        Intent v=new Intent(this,Select_subject.class);
        int mon=month+1;
        String date=dayOfMonth+"-"+mon+"-"+year;
        v.putExtra("ref",date);

        startActivity(v);
        finish();

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog (this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    date ( arg3,arg2+1,arg1 );
                    //showDate(arg1, arg2+1, arg3);
                }
            };
    void date(int day,int mon,int year){ Intent v=new Intent(this,Select_subject.class);

        String date=day+"-"+mon+"-"+year;
        v.putExtra("ref",date);

        startActivity(v);
        finish();}

}
