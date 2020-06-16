package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Laboratorna3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laboratorna3);
    }

    public void onClickBtnEnterValues(View v)
    {
        Intent intent1 = new Intent(this, Laboratorna3EnterValuesActivity.class);
        startActivity(intent1);
    }

    public void onClickBtnLoadFileAsPairs(View v)
    {
        Intent intent1 = new Intent(this, Laboratorna3LoadFileAsPairsActivity.class);
        startActivity(intent1);
    }

    public void onClickBtnLoadFileAsGraph(View v)
    {
        Intent intent1 = new Intent(this, Laboratorna3LoadFileAsGraphActivity.class);
        startActivity(intent1);
    }
}
