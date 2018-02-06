package com.example.eider.coursera_googlemaps.RestApi;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestAPIAdapter {
    public  EndPoints establecerConexionRestAPI(){
        Retrofit  retrofit = new Retrofit.Builder()
                .baseUrl(ContantesRestApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return  retrofit.create(EndPoints.class);
    }

}