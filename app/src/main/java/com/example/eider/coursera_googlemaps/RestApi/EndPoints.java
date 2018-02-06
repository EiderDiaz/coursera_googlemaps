package com.example.eider.coursera_googlemaps.RestApi;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface EndPoints {

    @FormUrlEncoded
    @POST(ContantesRestApi.KEY_POST_ID_TOKEN)
Call<UsuarioResponce> registarTokenId(@Field("token") String token);
}