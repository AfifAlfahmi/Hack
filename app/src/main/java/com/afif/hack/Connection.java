package com.afif.hack;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Connection {
    private static final String URL = "https://api.afifhack.link";

    public static void getInfo(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RemoteApi remoteApi = retrofit.create(RemoteApi.class);

        Call<Info> call = remoteApi.getInfo();

        call.enqueue(new Callback<Info>() {

            @Override

            public void onResponse(Call<Info> call, Response<Info> response) {

                Info res = (Info) response.body();
                Log.d("HTTP","Response body "+ res.getBody().getFlag());
            }

            @Override
            public void onFailure(Call<Info> call, Throwable t) {
                Log.d("HTTP","onFailure "+t.getMessage());
            }

        });

    }



    public static void getInfoSSl(){

        CertificatePinner certificatePinner = new CertificatePinner.Builder()
                .add("api.afifhack.link", "sha256/HyiS4ywppV3Y46jJp3yBfVtdfrI0daAIcbfV2+TmOiw=")
                .build();
        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        final OkHttpClient client = httpBuilder.certificatePinner(certificatePinner).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        RemoteApi remoteApi = retrofit.create(RemoteApi.class);

        Call<Info> call = remoteApi.getInfo();

        call.enqueue(new Callback<Info>() {

            @Override

            public void onResponse(Call<Info> call, Response<Info> response) {

                Info res = (Info) response.body();
                Log.d("HTTP","Response body "+ res.getBody().getFlag());
            }

            @Override
            public void onFailure(Call<Info> call, Throwable t) {
                Log.d("HTTP","onFailure "+t.getMessage());
            }

        });

    }


}
