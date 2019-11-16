package com.example.mvpdemo.net;

import com.example.mvpdemo.Constants;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static ApiClient mApiClient;
    private Retrofit mRetrofit;

    private ApiClient(){
        mRetrofit = createRetrofit();
    }

    public static ApiClient getInstance(){
        if(mApiClient == null){
            synchronized (ApiClient.class){
                if(mApiClient == null){
                    mApiClient = new ApiClient();
                }
            }
        }
        return mApiClient;
    }

    private Retrofit createRetrofit(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS);
        if(BuildConfig.DEBUG){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }

        return new Retrofit.Builder()
                .baseUrl(Constants.JOKE_URL)
                .client(builder.build())
                .addCallAdapterFactory((RxJava2CallAdapterFactory.create()))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public <T> T create(Class<T> clazz){
        return mRetrofit.create(clazz);
    }
}
