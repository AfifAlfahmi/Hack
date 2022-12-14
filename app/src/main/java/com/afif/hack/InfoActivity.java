package com.afif.hack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        
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

}