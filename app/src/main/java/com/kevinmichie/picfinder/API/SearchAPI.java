package com.kevinmichie.picfinder.API;

import com.google.gson.JsonObject;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


public interface SearchAPI {

    @GET("api")
    Observable<Response<JsonObject>> searchForImage(@Query("key") String key, @Query("q") String query);
}
