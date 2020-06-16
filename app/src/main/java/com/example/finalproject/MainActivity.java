package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickBtnStudentInfo(View v)
    {
        Intent intent1 = new Intent(this, StudentInfoActivity.class);
        startActivity(intent1);
    }

    public void onClickBtnLab1(View v)
    {
        Intent intent1 = new Intent(this, Laboratorna1Activity.class);
        startActivity(intent1);
    }

    public void onClickBtnLab2(View v)
    {
        Intent intent1 = new Intent(this, Laboratorna2Activity.class);
        startActivity(intent1);
    }

    public void onClickBtnLab3(View v)
    {
        Intent intent1 = new Intent(this, Laboratorna3Activity.class);
        startActivity(intent1);
    }

    public void onClickBtnLab4(View v)
    {
        Intent intent1 = new Intent(this, Laboratorna4Activity.class);
        startActivity(intent1);
    }

    public void onClickBtnLab5(View v)
    {
        Intent intent1 = new Intent(this, Laboratorna5Activity.class);
        startActivity(intent1);
    }

    public void onClickBtnHelp(View v)
    {
        Intent intent1 = new Intent(this, HelpActivity.class);
        startActivity(intent1);
    }
}
