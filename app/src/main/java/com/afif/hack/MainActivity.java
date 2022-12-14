package com.afif.hack;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public String remoteURL = "https://api.afif.hack";

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button infoBtn = findViewById(R.id.info_btn);

        Intent intent = new Intent(this,InfoActivity.class);

        Intent serviceIntent = new Intent(this,MyService.class);

       //startService(serviceIntent);


        infoBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //
                startActivity(intent);
            }
        });

        BroadcastReceiver broadcastReceiver = new MyBroadcastReceiver();

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);

        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);

        registerReceiver(broadcastReceiver,filter);


        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.CONTACT_NAME,"Afif");
        values.put(DatabaseHelper.PHONE_NUM,"000000000001");

        // Insert a new contact

        getContentResolver().insert(MyContentProvider.CONTENT_URI, values);
        // Retrieve all contacts records and print it to the logcat

        Cursor cursor = getContentResolver().query(MyContentProvider.CONTENT_URI, null, null, null, null);

        if (cursor.moveToFirst()) {
            do{
                Log.d("content_provider", "Contact name: "+cursor.getString(cursor.getColumnIndex(DatabaseHelper.CONTACT_NAME)));
                Log.d("content_provider", "Phone num: "+cursor.getString(cursor.getColumnIndex(DatabaseHelper.PHONE_NUM)));

            } while (cursor.moveToNext());
        }






    }

    }
