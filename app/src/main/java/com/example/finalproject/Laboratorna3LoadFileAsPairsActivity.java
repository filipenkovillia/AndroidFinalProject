package com.example.finalproject;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Laboratorna3LoadFileAsPairsActivity extends AppCompatActivity {

    final String FILENAME = "fileLab3";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laboratorna3_load_file_as_pairs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView textView1 = findViewById(R.id.tvFileContent);
        String globalStr = "";
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput(FILENAME)));
            String str = "";

            while((str = br.readLine()) != null){
                globalStr += str + "\n";

            }
        }
        catch(FileNotFoundException ex){
            showValidationToast("FileNotFoundException");
            ex.printStackTrace();
        }
        catch(IOException ex){
            showValidationToast("IOException");
            ex.printStackTrace();
        }

        textView1.setText(globalStr);
    }

    public void showValidationToast(String ex)
    {
        String errorText = "Error occurred " + ex;

        TextView textView = (TextView) findViewById(R.id.tvFileContent);
        textView.setText(errorText);

        Toast.makeText(Laboratorna3LoadFileAsPairsActivity.this, errorText, Toast.LENGTH_LONG).show();
    }
}
