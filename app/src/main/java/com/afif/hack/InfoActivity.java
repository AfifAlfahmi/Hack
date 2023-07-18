package com.afif.hack;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    TextView tvFlag  = null;
    int level = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        tvFlag = findViewById(R.id.tvFlag);
        showFlag();
        
        Log.d("lifecycle","onCreate");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("lifecycle","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("lifecycle","onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("lifecycle","onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("lifecycle","onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("lifecycle","onDestroy");

    }

    protected void showFlag(){
        level  = getIntent().getIntExtra("level",0);
        String congMessage = "Congratulations\n you have solved the challenge level "+level;

        tvFlag.setText(congMessage);
        tvFlag.setTextColor(Color.parseColor("#00A300"));
    }

}