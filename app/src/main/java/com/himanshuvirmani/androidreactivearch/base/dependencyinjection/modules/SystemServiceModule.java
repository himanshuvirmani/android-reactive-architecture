package com.himanshuvirmani.androidreactivearch.base.dependencyinjection.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.preference.PreferenceManager;
import com.himanshuvirmani.androidreactivearch.utils.NetworkStateManager;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by himanshu.virmani on 09/05/15.
 */
@Module public class SystemServiceModule {

  private final Application application;

  public SystemServiceModule(Application application) {
    this.application = application;
  }

  @Provides Context provideContext() {
    return application;
  }

  @Provides @Singleton SharedPreferences providePreferenceManager() {
    return PreferenceManager.getDefaultSharedPreferences(application);
  }

  @Provides @Singleton ConnectivityManager provideConnectivityManager() {
    return (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
  }

  @Provides @Singleton NetworkStateManager provideNetworkStateManager(
      ConnectivityManager connectivityManagerCompat) {
    return new NetworkStateManager(connectivityManagerCompat);
  }

  @Provides @Singleton LocationManager provideLocationManager() {
    return (LocationManager) application.getSystemService(LOCATION_SERVICE);
  }
}
