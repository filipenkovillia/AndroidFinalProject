package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Laboratorna3ViewAsGraph extends AppCompatActivity {

    private LineGraphSeries<DataPoint> series1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laboratorna3_view_as_graph);

        Intent intent = getIntent();
        double[] arrX = intent.getDoubleArrayExtra(Laboratorna3EnterValuesActivity.EXTRA_ARRAYX);
        double[] arrY = intent.getDoubleArrayExtra(Laboratorna3EnterValuesActivity.EXTRA_ARRAYY);
        int length = intent.getIntExtra(Laboratorna3EnterValuesActivity.EXTRA_LENGTH, 0);

        GraphView graph = (GraphView) findViewById(R.id.viewGraph);
        series1 = new LineGraphSeries<>();

        for(int i = 0; i < length; i++)
        {
            series1.appendData(new DataPoint(arrX[i], arrY[i]), true, 100);
        }
        graph.addSeries(series1);
    }
}
