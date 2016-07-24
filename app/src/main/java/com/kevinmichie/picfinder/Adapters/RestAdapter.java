package com.kevinmichie.picfinder.Adapters;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

public class RestAdapter {
    private static Retrofit retrofit;

    public static Retrofit getInstance() {

        if (retrofit != null) {

            return retrofit;

        } else {

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://pixabay.com/")
                    .client(new OkHttpClient())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            return retrofit;

        }

    }

}
