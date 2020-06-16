package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Laboratorna1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laboratorna1);
    }

    public void buttonCalculateOnClick(View v)
    {
        TextView resultValue = findViewById(R.id.tvResult);
        String res = Calculate();
        resultValue.setText(res);
    }

    public String Calculate()
    {

        EditText textX = (EditText)findViewById(R.id.etValueX);
        String valueX = textX.getText().toString();
        if(valueX.isEmpty())
        {
            showValidationToast(1);
            return "Incorrect Input";
        }
        double x = Double.parseDouble(valueX);
        EditText textY = (EditText)findViewById(R.id.etValueY);
        String valueY = textY.getText().toString();
        if(valueY.isEmpty())
        {
            showValidationToast(2);
            return "Incorrect Input";
        }
        double y = Double.parseDouble(valueY);
        EditText textD = (EditText)findViewById(R.id.etValueD);
        String valueD = textD.getText().toString();
        if(valueD.isEmpty())
        {
            showValidationToast(3);
            return "Incorrect Input";
        }
        double d = Double.parseDouble(valueD);
        double res = (Math.cos(y) * Math.cos(y) + 2.4 * d)/(Math.exp(y) + Math.log(Math.sin(x) * Math.sin(x) + 6.0));
        return Double.toString(res);
    }

    public void showValidationToast(int number)
    {
        String errorText = "";
        switch (number)
        {
            case 1:
                errorText = "X cannot be null";
                break;
            case 2:
                errorText = "Y cannot be null";
                break;
            case 3:
                errorText = "D cannot be null";
                break;
        }
        Toast.makeText(Laboratorna1Activity.this, errorText, Toast.LENGTH_LONG).show();
    }
    //Toast.makeText(DatabaseActivity.this, "Club name cannot be null", Toast.LENGTH_LONG).show();
}
