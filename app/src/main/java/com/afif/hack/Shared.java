package com.afif.hack;

import android.content.Context;
import android.content.SharedPreferences;

public class Shared {

    private static final byte[] PASS_BYTE_ARR = new byte[] {102, 108, 97, 103, 123, 112, 97,115,115,95,115,104,97,114,101,100,125};
    public static void setPassword(Context context){

        char[] cbuf = new char[PASS_BYTE_ARR.length];
        for (int i = 0; i < PASS_BYTE_ARR.length; i++) {
            cbuf[i] = (char) PASS_BYTE_ARR[i];
        }
        String password = new String(cbuf);

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
