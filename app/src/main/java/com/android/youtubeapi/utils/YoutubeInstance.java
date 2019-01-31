package com.android.youtubeapi.utils;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class YoutubeInstance {
    private static YoutubeInstance instance = null;
    private Retrofit retrofit;

    private YoutubeAPI youtubeAPI;

    public YoutubeInstance() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        retrofit = new Retrofit.Builder().baseUrl(Constant.YOUTUBE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        youtubeAPI = retrofit.create(YoutubeAPI.class);
    }

    public static YoutubeInstance getInstance() {
        if (instance == null) {
            instance = new YoutubeInstance();
        }

        return instance;
    }

    public YoutubeAPI getYoutubeAPI() {
        return youtubeAPI;
    }
}
