package com.notesboard.admin.BKTeachers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Mainpage extends AppCompatActivity implements View.OnClickListener {
    Button bt3,bt4,bt6,bt7,bt8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_mainpage);
        bt3=(Button) findViewById(R.id.button3);
        bt3.setOnClickListener(this);
        bt4=(Button) findViewById(R.id.button4);
        bt4.setOnClickListener(this);
        bt6=(Button) findViewById(R.id.button8);
        bt6.setOnClickListener(this);
        bt7=(Button) findViewById(R.id.button7);
        bt7.setOnClickListener(this);
        bt8=(Button) findViewById(R.id.button16);
        bt8.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {


        int id = view.getId();
        if (id== R.id.button3){

            Intent v=new Intent(this,Home.class);


            startActivity(v);

finish();
        }

        else if(id== R.id.button8) {


            Intent v=new Intent(this,Attendance.class);


            startActivity(v);




        }
        else if(id== R.id.button7){

            Intent v=new Intent(this,HomeWork.class);


            startActivity(v);


        }

        else if(id== R.id.button4){
            Intent v=new Intent(this,RanckCard.class);


            startActivity(v);


        }

        else if(id== R.id.button16){
            Intent v=new Intent(this,Food.class);


            startActivity(v);


        }


    }

    }

