package com.example.finalproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Laboratorna4Activity extends AppCompatActivity {

    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laboratorna4);
        myDb = new DatabaseHelper(this);
    }

    public void onClickBtnAddData(View v)
    {
        AddData();
    }

    public void onClickBtnViewAll(View v)
    {
        ViewAll();
    }

    public void onClickBtnUpdate(View v)
    {
        Update();
    }

    public void onClickBtnDelete(View v)
    {
        Delete();
    }

    public void onClickBtnSelectUkr(View v)
    {
        SelectUkr();
    }

    public void onClickBtnUkrainianRanking(View view)
    {
        CountUkrRanking();
    }

    public void AddData()
    {
        EditText textName = (EditText)findViewById(R.id.etName);
        String valueName = textName.getText().toString();

        EditText textCountry = (EditText)findViewById(R.id.etCountry);
        String valueCountry = textCountry.getText().toString();

        EditText textYear = (EditText)findViewById(R.id.etYear);
        String valueYear = textYear.getText().toString();

        EditText textRanking = (EditText)findViewById(R.id.etRanking);
        String valueRanking = textRanking.getText().toString();

        if(valueName.isEmpty())
        {
            Toast.makeText(Laboratorna4Activity.this, "Club name cannot be null", Toast.LENGTH_LONG).show();
            return;
        }

        if(valueCountry.isEmpty())
        {
            Toast.makeText(Laboratorna4Activity.this, "Country cannot be null", Toast.LENGTH_LONG).show();
            return;
        }

        if(valueYear.isEmpty())
        {
            Toast.makeText(Laboratorna4Activity.this, "Foundation year cannot be null", Toast.LENGTH_LONG).show();
            return;
        }

        if(valueRanking.isEmpty())
        {
            Toast.makeText(Laboratorna4Activity.this, "UEFA ranking cannot be null", Toast.LENGTH_LONG).show();
            return;
        }

        boolean isInserted = myDb.insertData(valueName, valueCountry, valueYear, valueRanking);

        if(isInserted == true)
            Toast.makeText(Laboratorna4Activity.this, "Data inserted", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(Laboratorna4Activity.this, "Data not inserted", Toast.LENGTH_LONG).show();
    }

    public void ViewAll()
    {
        Cursor res = myDb.getAllData();
        /*
        if(res.getCount() == 0)
        {
            showMessage("Error", "Nothing found");
        }
         */
        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext())
        {
            buffer.append("Id: " + res.getString(0) + "\n");
            buffer.append("Name: " + res.getString(1) + "\n");
            buffer.append("Country: " + res.getString(2) + "\n");
            buffer.append("Year: " + res.getString(3) + "\n");
            buffer.append("Ranking: " + res.getString(4) + "\n\n");
        }
        showMessage("Data", buffer.toString());
    }

    public void showMessage(String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void Update()
    {
        EditText textName = (EditText)findViewById(R.id.etName);
        String valueName = textName.getText().toString();

        EditText textCountry = (EditText)findViewById(R.id.etCountry);
        String valueCountry = textCountry.getText().toString();

        EditText textYear = (EditText)findViewById(R.id.etYear);
        String valueYear = textYear.getText().toString();

        EditText textRanking = (EditText)findViewById(R.id.etRanking);
        String valueRanking = textRanking.getText().toString();

        EditText textId = (EditText)findViewById(R.id.etId);
        String valueId = textId.getText().toString();

        if(valueName.isEmpty())
        {
            Toast.makeText(Laboratorna4Activity.this, "Club name cannot be null", Toast.LENGTH_LONG).show();
            return;
        }

        if(valueCountry.isEmpty())
        {
            Toast.makeText(Laboratorna4Activity.this, "Country cannot be null", Toast.LENGTH_LONG).show();
            return;
        }

        if(valueYear.isEmpty())
        {
            Toast.makeText(Laboratorna4Activity.this, "Foundation year cannot be null", Toast.LENGTH_LONG).show();
            return;
        }

        if(valueRanking.isEmpty())
        {
            Toast.makeText(Laboratorna4Activity.this, "UEFA ranking cannot be null", Toast.LENGTH_LONG).show();
            return;
        }

        if(valueId.isEmpty())
        {
            Toast.makeText(Laboratorna4Activity.this, "Id cannot be null", Toast.LENGTH_LONG).show();
            return;
        }

        boolean isUpdated = myDb.updateData(valueId, valueName, valueCountry, valueYear, valueRanking);

        if(isUpdated)
            Toast.makeText(Laboratorna4Activity.this, "Data updated", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(Laboratorna4Activity.this, "Data not updated", Toast.LENGTH_LONG).show();
    }

    public void Delete()
    {
        EditText textId = (EditText)findViewById(R.id.etId);
        String valueId = textId.getText().toString();

        if(valueId.isEmpty())
        {
            Toast.makeText(Laboratorna4Activity.this, "Id cannot be null", Toast.LENGTH_LONG).show();
            return;
        }

        Integer deletedRows = myDb.deleteData(valueId);
        if(deletedRows > 0)
            Toast.makeText(Laboratorna4Activity.this, "Data deleted", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(Laboratorna4Activity.this, "Data not deleted", Toast.LENGTH_LONG).show();
    }

    public void SelectUkr()
    {
        try
        {
            Cursor res = myDb.getAllUkrainianClubs();

            /*
            if(res.getCount() == 0)
            {
                showMessage("Error", "Nothing found");
            }

             */
            StringBuffer buffer = new StringBuffer();
            while(res.moveToNext())
            {
                buffer.append("Name: " + res.getString(1) + "\n");
                buffer.append("Ranking: " + res.getString(4) + "\n\n");
            }
            showMessage("Data", buffer.toString());
        }
        catch(Exception ex)
        {
            showMessage("Error", ex.getStackTrace().toString());
        }

    }

    public void CountUkrRanking()
    {
        Cursor res = myDb.getAllUkrainianClubs();

        /*
        if(res.getCount() == 0)
        {
            showMessage("Error", "Nothing found");
        }

         */
        StringBuffer buffer = new StringBuffer();
        double totalRanking = 0;
        while(res.moveToNext())
        {
            String ranking = res.getString(4);
            double rank = Double.parseDouble(ranking);
            totalRanking += rank;
        }
        buffer.append("Ukrainian clubs ranking sum: " + totalRanking);
        showMessage("Data", buffer.toString());
    }
}
