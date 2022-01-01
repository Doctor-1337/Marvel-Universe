package com.oyelabs.marveluniverse.network;

import com.oyelabs.marveluniverse.model.RootObj;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {


    @GET("v1/public/characters")
    Call<RootObj> getCharList(
            @Query("nameStartsWith") String name,
            @Query("ts") int ts,
            @Query("apikey") String api,
            @Query("hash") String hash
    );
}
