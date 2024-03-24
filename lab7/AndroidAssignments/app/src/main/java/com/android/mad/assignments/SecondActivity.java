package com.android.mad.assignments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    public static final String TRANSMITTED_STRING = "transmittedString";
    public static final String TRANSMITTED_INT = "transmittedInt";
    public static final String TRANSMITTED_BOOL = "transmittedBool";


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView textview = findViewById(R.id.second_activity_text_view);

        String transmittedString = getIntent().getStringExtra(TRANSMITTED_STRING);
        int transmittedInt = getIntent().getIntExtra(TRANSMITTED_INT, -1);
        boolean transmittedBool = getIntent().getBooleanExtra(TRANSMITTED_BOOL, false);

        textview.setText("These values were passed from previous screen"
                + ": transmittedString " + transmittedString
                + ", transmittedInt " + transmittedInt
                + ", transmittedBool " + transmittedBool);

    }
}