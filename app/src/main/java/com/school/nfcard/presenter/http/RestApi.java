package com.school.nfcard.presenter.http;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RestApi {

    private static RestApi mInstance;
    public static boolean isDebug = false;

    public static synchronized RestApi getInstance() {
        if (mInstance == null)
            mInstance = new RestApi();
        return mInstance;
    }

    private Retrofit createApiClient(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(createOkHttpClient(isDebug))
                .build();
    }

    // create api service singleton
    public <T> T create(String baseUrl, Class<T> clz) {
        return createApiClient(baseUrl).create(clz);
    }

    // create okHttpClient singleton
    OkHttpClient createOkHttpClient(boolean debug) {
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(new HttpCacheInterceptor())
                .build();
    }
}
