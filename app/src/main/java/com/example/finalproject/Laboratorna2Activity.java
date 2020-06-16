package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Laboratorna2Activity extends AppCompatActivity {

    final String FILENAME = "fileLab2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laboratorna2);
    }

    public void onClickBtnCalculate(View v)
    {
        int error = validateNumbers();
        if(error > 0)
        {
            showValidationToast(error);
        }
        else{
            EditText textY = (EditText)findViewById(R.id.etValueY);
            String valueY = textY.getText().toString();
            double y = Double.parseDouble(valueY);

            EditText textD = (EditText)findViewById(R.id.etValueD);
            String valueD = textD.getText().toString();
            double d = Double.parseDouble(valueD);

            EditText textX1 = (EditText)findViewById(R.id.etValueX1);
            String valueX1 = textX1.getText().toString();
            double x1 = Double.parseDouble(valueX1);

            EditText textX2 = (EditText)findViewById(R.id.etValueX2);
            String valueX2 = textX2.getText().toString();
            double x2 = Double.parseDouble(valueX2);

            EditText textStep = (EditText)findViewById(R.id.etValueStep);
            String valueStep = textStep.getText().toString();
            double step = Double.parseDouble(valueStep);

            int length = (int) ((x2 - x1) / step + 1);
            double[] ans = Calculate(y, d, x1, x2, step, length);

            writeFile(valueY, valueD, valueX1, valueX2, valueStep, x1, step, ans, length);
            showValidationToast(6);
        }
    }

    public int validateNumbers()
    {
        EditText textY = (EditText)findViewById(R.id.etValueY);
        String valueY = textY.getText().toString();
        if(valueY.isEmpty())
            return 1;
        EditText textD = (EditText)findViewById(R.id.etValueD);
        String valueD = textD.getText().toString();
        if(valueD.isEmpty())
            return 2;
        EditText textX1 = (EditText)findViewById(R.id.etValueX1);
        String valueX1 = textX1.getText().toString();
        if(valueX1.isEmpty())
            return 3;
        EditText textX2 = (EditText)findViewById(R.id.etValueX2);
        String valueX2 = textX2.getText().toString();
        if(valueX2.isEmpty())
            return 4;
        EditText textStep = (EditText)findViewById(R.id.etValueStep);
        String valueStep = textStep.getText().toString();
        if(valueStep.isEmpty())
            return 5;
        double x1 = Double.parseDouble(valueX1);
        double x2 = Double.parseDouble(valueX2);
        if(x1 > x2)
            return 7;
        double step = Double.parseDouble(valueStep);
        if(step == 0.0)
            return 5;
        return 0;
    }

    public void showValidationToast(int number)
    {
        String errorText = "";
        switch (number){
            case 1:
                errorText = "Y cannot be null";
                break;
            case 2:
                errorText = "D cannot be null";
                break;
            case 3:
                errorText = "X1 cannot be null";
                break;
            case 4:
                errorText = "X2 cannot be null";
                break;
            case 5:
                errorText = "Step cannot be null or zero";
                break;
            case 6:
                errorText = "Data was successfully written into the file";
                break;
            case 7:
                errorText = "X1 cannot be more than X2";
                break;
            default:
                break;
        }
        Toast.makeText(Laboratorna2Activity.this, errorText, Toast.LENGTH_LONG).show();
    }

    public void onClickBtnViewFile(View v)
    {
        Intent intent1 = new Intent(this, Laboratorna2ViewFileActivity.class);
        startActivity(intent1);
    }

    public double[] Calculate(double y, double d, double x1, double x2, double step, int length)
    {
        int i = 0;
        //int count = (int) ((x2 - x1) / step + 1);
        double[] arr = new double[length];
        while(x1 <= x2 && i < length)
        {
            arr[i] = (Math.cos(y) * Math.cos(y) + 2.4 * d)/(Math.exp(y) + Math.log(Math.sin(x1) * Math.sin(x1) + 6.0));
            i++;
            x1 += step;
        }
        return arr;
    }

    public void writeFile(String valueY, String valueD, String valueX1, String valueX2, String valueStep,
                          double x1, double step, double[] arr, int length)
    {
        try{
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(openFileOutput(FILENAME, MODE_PRIVATE)));

            bw.write(valueY + "\n" + valueD + "\n" + valueX1 + "\n" + valueX2 + "\n" + valueStep + "\n");
            for(int i = 0; i < length; i++)
            {
                String str = Double.toString(x1 + step * i) + " " + Double.toString(arr[i]);
                bw.write(str + "\n");
            }
            bw.close();
        }
        catch(FileNotFoundException ex){
            ex.printStackTrace();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
