package com.example.stockmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.CollationElementIterator;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static ListView listView;
    public static TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.AddStockbtn).setOnClickListener(this);
        textView = (TextView) findViewById(R.id.JsonTextView);
        listView = (ListView) findViewById(R.id.listaNakyma);

        ArrayList<String> listItems = new ArrayList<String>();

        AsyncStock asyncStock = new AsyncStock();
        asyncStock.execute();

       ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listItems);
       listView.setAdapter(arrayAdapter);

    }

    @Override
    public void onClick (View v){

    }


}
