package com.afif.hack;

import android.content.Context;
import android.content.SharedPreferences;

public class Shared {

    public static void setPassword(Context context){
        String password = "flag{pass_shared}";
        String sharedPrefKey = "pKey";
        SharedPreferences sharedPreferences = context.getSharedPreferences("HackSharedPref",Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Storing the key and its value
        editor.putString(sharedPrefKey, password);

        // commit to apply the changes
        editor.commit();
    }

    public static String getPassword(Context context){
        String sharedPrefKey = "pKey";
        SharedPreferences sharedPreferences = context.getSharedPreferences("HackSharedPref",Context.MODE_PRIVATE);

        return sharedPreferences.getString(sharedPrefKey, "");

    }
}
