package com.himanshuvirmani.androidreactivearch.base.dependencyinjection.modules;

import android.support.annotation.NonNull;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.himanshuvirmani.androidreactivearch.BuildConfig;
import com.himanshuvirmani.androidreactivearch.data.ApiConfig;
import com.himanshuvirmani.androidreactivearch.data.api.ServerApi;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = {
    NetworkModule.class
}) public class ApiModule {

  @Provides @NonNull @Singleton public ServerApi provideServerApi(@NonNull Retrofit build) {
    return build.create(ServerApi.class);
  }


  @Provides @NonNull @Singleton
  public Retrofit provideRetrofitBuild(@NonNull OkHttpClient okHttpClient) {
    Gson gson =
        new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .create();

    final Retrofit.Builder builder = new Retrofit.Builder().baseUrl(ApiConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create());


    // Fail early: check Retrofit configuration at creation time
    if (BuildConfig.DEBUG) {
      builder.validateEagerly(true);
    }

    return builder.build();
  }
}
