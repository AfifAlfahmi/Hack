package com.afif.hack;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;

public interface RemoteApi {

    @GET("info")
    Call<Info> getInfo();

    @GET("infossl")
    Call<Info> getInfoSSL();
}
