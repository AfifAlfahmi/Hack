package com.afif.hack;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public String remoteURL = "https://api.afif.hack";
    EditText etPassword = null;
    TextView tvPassStatus = null;
    String log_pass = "p125522";


    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Shared.setPassword(this);


        Log.d("AdbTag", "adb logcat test");


        Button infoBtn = findViewById(R.id.info_btn);

        etPassword = findViewById(R.id.etPassword);
        tvPassStatus = findViewById(R.id.tvPassStatus);


        Intent intent = new Intent(this,InfoActivity.class);

        Intent serviceIntent = new Intent(this,MyService.class);

       //startService(serviceIntent);


        infoBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //

                if(isCorrectPassword(etPassword.getText().toString())){

                    if(etPassword.getText().toString().equals(Shared.getPassword(getApplicationContext()))){
                        intent.putExtra("level",2);
                    }
                     else if ((getAppVersion() == 20200) && !etPassword.getText().toString().equals(log_pass)) {
                         intent.putExtra("level",3);
                     }
                    else{
                        intent.putExtra("level",1);
                    }


                    startActivity(intent);
                }
                else{
                    tvPassStatus.setText("Wrong password");
                }
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

    public boolean isCorrectPassword(String inpPass){

        boolean result = false;

        if(inpPass.equals(log_pass)){
            result = true;
        }

        else if(inpPass.equals(Shared.getPassword(this))){
            result = true;
        }
        return result;
    }

    public int getAppVersion(){
        int versionCode = 0 ;
        PackageManager pm = getApplicationContext().getPackageManager();
        String pkgName = getApplicationContext().getPackageName();
        PackageInfo pkgInfo = null;
        try {
            pkgInfo = pm.getPackageInfo(pkgName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }
         versionCode = pkgInfo.versionCode;
        return versionCode;
    }

    }
