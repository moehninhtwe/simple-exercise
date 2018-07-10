package com.weightwatchers.ww_exercise_01.data.service;

import com.weightwatchers.ww_exercise_01.BuildConfig;
import com.weightwatchers.ww_exercise_01.Constant;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIService {
    private static HttpUrl baseURL = HttpUrl.parse(Constant.BASE_URL);
    private static Interceptor interceptor = chain -> {
        Request request = chain.request();
        request = request.newBuilder().build();
        return chain.proceed(request);
    };

    private static Retrofit provideRetrofit() {
        okhttp3.OkHttpClient client = new OkHttpClient();
        final OkHttpClient.Builder builder = client.newBuilder().addInterceptor(interceptor);
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
        Retrofit retrofit = new Retrofit.Builder().client(builder.build())
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        return retrofit;
    }

    public static GetCollectionService getCollectionService() {
        return provideRetrofit().create(GetCollectionService.class);
    }
}
