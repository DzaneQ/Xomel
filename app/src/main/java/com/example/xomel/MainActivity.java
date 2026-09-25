package com.example.xomel;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner fileList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button loadXML = findViewById(R.id.loadxml);
        loadXML.setOnClickListener(this);

        fileList = findViewById(R.id.xmlfile);
        List<String> xmlNames = new ArrayList<String>();
        addListOfFiles(xmlNames);
        ArrayAdapter<String> fileAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, xmlNames);
        fileAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fileList.setAdapter(fileAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.loadxml) {
            String fileName = String.valueOf(fileList.getSelectedItem());
            Intent newWindow = new Intent(this, XmlTemplate.class);
            newWindow.putExtra("transfer", fileName);
            startActivity(newWindow);
        }
    }

    private void addListOfFiles(List<String> fileList) {
        try {
            String[] assets = getAssets().list("");

            if (assets != null) {
                for (String i : assets)
                {
                    if (i.endsWith(".xml")) fileList.add(i);
                }
}
        } catch (IOException e) {
            Log.e("MainActivity", "Nie wczytano listy plików", e);
        }
    }
}



