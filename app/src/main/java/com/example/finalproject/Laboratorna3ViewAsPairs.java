package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

public class Laboratorna3ViewAsPairs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laboratorna3_view_as_pairs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        double[] arrX = intent.getDoubleArrayExtra(Laboratorna3EnterValuesActivity.EXTRA_ARRAYX);
        double[] arrY = intent.getDoubleArrayExtra(Laboratorna3EnterValuesActivity.EXTRA_ARRAYY);
        int length = intent.getIntExtra(Laboratorna3EnterValuesActivity.EXTRA_LENGTH, 0);

        TextView textView = (TextView) findViewById(R.id.tvResultPairs);
        String str = "";
        for(int i = 0; i < length; i++)
        {
            str += arrX[i] + " " + arrY[i] + "\n";
        }
        textView.setText(str);
    }
}
