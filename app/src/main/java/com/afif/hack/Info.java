package com.afif.hack;

import com.google.gson.annotations.SerializedName;

public class Info {

    @SerializedName("code")
    int code;

    @SerializedName("message")
    String message;

    @SerializedName("body")
    public Body body;

    public Info(int code, String message, Body body) {
        this.code = code;
        this.message = message;
        this.body = body;
    }


    public Body getBody(){
        return body;
    }


}
