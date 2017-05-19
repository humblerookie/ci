package com.hr.ci.commons.injectors;

import com.hr.ci.commons.util.Constants;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class NetworkModuleInjector {

    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {

            synchronized (NetworkModuleInjector.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(Constants.BASE_URL)
                            .addConverterFactory(MoshiConverterFactory.create())
                            .client(new OkHttpClient())
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
}
