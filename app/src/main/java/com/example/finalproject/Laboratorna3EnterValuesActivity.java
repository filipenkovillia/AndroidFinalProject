package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Laboratorna3EnterValuesActivity extends AppCompatActivity {

    public static final String EXTRA_ARRAYX = "com.example.lab3.example.EXTRA_ARRAYX";
    public static final String EXTRA_ARRAYY = "com.example.lab3.example.EXTRA_ARRAYY";
    public static final String EXTRA_LENGTH = "com.example.lab3.example.EXTRA_LENGTH";
    final String FILENAME = "fileLab3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laboratorna3_enter_values);
    }

    public void onClickBtnViewAsPairs(View v)
    {
        execute(1);
    }

    public void onClickBtnViewAsGraph(View v)
    {
        execute(2);
    }

    public void execute(int activityNumber)
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
            double[] xes = new double[length];
            int i = 0;
            double x = x1;
            while(x <= x2)
            {
                xes[i] = x;
                x += step;
                i++;
            }

            writeFile(valueY, valueD, valueX1, valueX2, valueStep, x1, step, ans, length);
            showValidationToast(6);

            Intent intent1 = new Intent();
            if(activityNumber == 1)
                intent1 = new Intent(this, Laboratorna3ViewAsPairs.class);
            else
                intent1 = new Intent(this, Laboratorna3ViewAsGraph.class);
            intent1.putExtra(EXTRA_ARRAYX, xes);
            intent1.putExtra(EXTRA_ARRAYY, ans);
            intent1.putExtra(EXTRA_LENGTH, length);
            startActivity(intent1);
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
                errorText = "Step cannot be null";
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
        Toast.makeText(Laboratorna3EnterValuesActivity.this, errorText, Toast.LENGTH_LONG).show();
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

            //bw.write(valueY + "\n" + valueD + "\n" + valueX1 + "\n" + valueX2 + "\n" + valueStep + "\n");
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
