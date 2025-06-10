package com.afif.hack;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Connection {
    private static final String URL = "https://api.afifhack.link";

    public static void getInfo(Context context){


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
            }

            @Override
            public void onFailure(Call<Info> call, Throwable t) {
                Log.d("HTTP","onFailure "+t.getMessage());
                Toast.makeText(context,"request failed "+t.getMessage(),Toast.LENGTH_LONG).show();

            }

        });

    }



    public static void getInfoSSL(Context context) throws Exception {

        CertificatePinner certificatePinner = new CertificatePinner.Builder()
                .add("api.afifhack.link", "sha256/C9HSXxS4xMCoyAY9jQAdZQrOKEn/D0L6kFFhbS75K54=")
                .build();
        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        final OkHttpClient client = httpBuilder.certificatePinner(certificatePinner).build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        RemoteApi remoteApi = retrofit.create(RemoteApi.class);

        Call<Info> call = remoteApi.getInfoSSL();

        call.enqueue(new Callback<Info>() {
            @Override
            public void onResponse(Call<Info> call, Response<Info> response) {
                Info res = (Info) response.body();

            }

            @Override
            public void onFailure(Call<Info> call, Throwable t) {
                Log.d("HTTP","onFailure "+t.getMessage());
                Toast.makeText(context,"request failed "+t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }

}
