package com.example.lovechinaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class Main extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }


    public void InsertData(View v) {
        Intent i = new Intent(v.getContext(),InsertActivity.class);
        startActivity(i);
    }

    public void SearchData(View v) {
        Log.i("SearchData","Search");
        Log.i("SearchPart","Search");
    }
    public void ViewDataByMap(View v) {
        Log.i("ViewData","View");
        Log.i("ViewList","View");
    }
  //  test
}

