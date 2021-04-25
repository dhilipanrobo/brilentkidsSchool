package com.skyparking.admin.checkbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText et1, et2;
    CheckBox ch1, ch2;
    String n1, n2;
    int a, b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        et1 = findViewById ( R.id.editText6 );
        et2 = findViewById ( R.id.editText7 );
        ch1 = findViewById ( R.id.checkBox6 );
        ch1.setOnClickListener ( this );
        ch2 = findViewById ( R.id.checkBox7 );
        ch2.setOnClickListener ( this );
    }

    @Override
    public void onClick(View view) {
        n1 = et1.getText ( ).toString ( );
        n2 = et2.getText ( ).toString ( );
        if (a>b)
        {
            Toast.makeText ( this, "greater than "+a, Toast.LENGTH_SHORT ).show ( );
        }
        else
        {
            Toast.makeText ( this, "lesser than "+b, Toast.LENGTH_SHORT ).show ( );
        }

    }
}
