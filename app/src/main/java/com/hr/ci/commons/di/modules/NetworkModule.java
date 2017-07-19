package com.hr.ci.commons.di.modules;

import com.hr.ci.commons.util.Constants;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;
import timber.log.Timber;

import static com.hr.ci.commons.util.Constants.BASE_URL;

@Singleton
@Module
public class NetworkModule {


    public static final String BASE_URL_KEY = "BASE_URL_KEY";

    public NetworkModule() {
    }

    @Singleton
    @Inject
    @Provides
    public Retrofit provideRetrofit(OkHttpClient client, @Named(BASE_URL_KEY) String baseUrl, Converter.Factory converterFactory, CallAdapter.Factory adapterFactory) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(adapterFactory)
                .client(client)
                .build();

    }

    @Singleton
    @Provides
    public OkHttpClient provideOkHttp(Interceptor interceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(Constants.TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(Constants.TIMEOUT, TimeUnit.MILLISECONDS)
                .build();

    }

    @Provides
    public Interceptor provideInterceptor() {
        return new HttpLoggingInterceptor(message -> Timber.d(message)).setLevel(HttpLoggingInterceptor.Level.BODY);
    }


    @Provides
    public CallAdapter.Factory provideAdapter() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    public Converter.Factory provideConverter() {
        return MoshiConverterFactory.create();
    }

    @Provides
    @Named(BASE_URL_KEY)
    String provideBaseUrl() {
        return BASE_URL;
    }
}
