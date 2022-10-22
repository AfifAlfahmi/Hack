package com.afif.hack;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Boolean isAirplanModeOn = intent.getBooleanExtra("state",false);

        if(isAirplanModeOn){
            //Toast.makeText(context, "AIRPLANE MODE On", Toast.LENGTH_SHORT).show();
            Log.d("MyBroadcastReceiver","AIRPLANE MODE On");
        }
        else{
            //Toast.makeText(context, "AIRPLANE MODE Off", Toast.LENGTH_SHORT).show();
            Log.d("MyBroadcastReceiver","AIRPLANE MODE Off");

        }
    }
}
