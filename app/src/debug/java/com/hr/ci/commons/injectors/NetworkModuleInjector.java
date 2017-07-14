package com.hr.ci.commons.injectors;

import com.hr.ci.commons.util.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;
import timber.log.Timber;

public class NetworkModuleInjector {

    private static Retrofit retrofit;

    public static Retrofit getRetrofit(OkHttpClient client) {
        if (retrofit == null) {

            synchronized (NetworkModuleInjector.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(Constants.BASE_URL)
                            .addConverterFactory(MoshiConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .client(client)
                            .build();
                    return retrofit;
                } else {
                    return retrofit;
                }
            }
        } else {
            return retrofit;
        }
    }

    public static OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor(message -> Timber.d(message)).setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(Constants.TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(Constants.TIMEOUT, TimeUnit.MILLISECONDS)
                .build();

    }
}
