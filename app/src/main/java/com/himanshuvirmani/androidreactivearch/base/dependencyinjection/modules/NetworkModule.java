package com.himanshuvirmani.androidreactivearch.base.dependencyinjection.modules;

import android.support.annotation.NonNull;
import com.himanshuvirmani.androidreactivearch.BuildConfig;
import dagger.Module;
import dagger.Provides;
import java.io.IOException;
import javax.inject.Singleton;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by rohith.mohan on 28/01/16.
 */
@Module public class NetworkModule {

  public NetworkModule() {

  }

  @Provides @NonNull @Singleton
  public OkHttpClient provideOkHttpClient(@NonNull HttpLoggingInterceptor httpLoggingInterceptor,
      @NonNull Interceptor requestInterceptor) {
    OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(requestInterceptor)
        .addInterceptor(httpLoggingInterceptor)
        .build();
    return okHttpClient;
  }

  @Provides @NonNull @Singleton public HttpLoggingInterceptor provideHttpLoggingInterceptor() {
    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
    httpLoggingInterceptor.setLevel(
        BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
    return httpLoggingInterceptor;
  }

  @Provides @NonNull @Singleton public Interceptor provideRequestInterceptor() {
    Interceptor requestInterceptor = new Interceptor() {
      @Override public Response intercept(Interceptor.Chain chain) throws IOException {
        Request original = chain.request();

        // Here you can add request header
        Request request = original.newBuilder()
            .header("Content-Type", "application/json; charset=utf-8")
            .method(original.method(), original.body())
            .build();

        return chain.proceed(request);
      }
    };

    return requestInterceptor;
  }


}
