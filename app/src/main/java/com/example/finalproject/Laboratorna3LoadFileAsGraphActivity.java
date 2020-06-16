package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Laboratorna3LoadFileAsGraphActivity extends AppCompatActivity {

    final String FILENAME = "fileLab3";
    private LineGraphSeries<DataPoint> series1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laboratorna3_load_file_as_graph);

        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput(FILENAME)));
            String str = "";

            GraphView graph = (GraphView) findViewById(R.id.readGraph);
            series1 = new LineGraphSeries<>();

            while((str = br.readLine()) != null){
                String[] a = str.split(" ");
                double x = Double.parseDouble(a[0]);
                double y = Double.parseDouble(a[1]);

                series1.appendData(new DataPoint(x, y), true, 100);
            }

            graph.addSeries(series1);
        }
        catch(FileNotFoundException ex){
            showValidationToast("FileNotFoundException");
            ex.printStackTrace();
        }
        catch(IOException ex){
            showValidationToast("IOException");
            ex.printStackTrace();
        }
    }

    public void showValidationToast(String ex)
    {
        String errorText = "Error occurred " + ex;
        Toast.makeText(Laboratorna3LoadFileAsGraphActivity.this, errorText, Toast.LENGTH_LONG).show();
    }
}
