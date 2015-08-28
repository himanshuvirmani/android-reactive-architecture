package com.himanshuvirmani.androidreactivearch.base.dependencyinjection.components;

/**
 * Created by himanshu.virmani on 08/05/15.
 */

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import com.himanshuvirmani.androidreactivearch.base.MainApplication;
import com.himanshuvirmani.androidreactivearch.base.dependencyinjection.modules.DataModule;
import com.himanshuvirmani.androidreactivearch.base.dependencyinjection.modules.MainApplicationModule;
import com.himanshuvirmani.androidreactivearch.base.dependencyinjection.modules.SystemServiceModule;
import com.himanshuvirmani.androidreactivearch.data.api.ApiManager;
import com.himanshuvirmani.androidreactivearch.utils.NetworkStateManager;
import com.himanshuvirmani.androidcache.CacheManager;
import dagger.Component;
import javax.inject.Singleton;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = { MainApplicationModule.class, SystemServiceModule.class, DataModule.class })
public interface ApplicationComponent extends ApplicationServicesGraph {
  void inject(MainApplication application);

  Application application();

  Context appContext();

  SharedPreferences preferenceManager();

  ConnectivityManager connectivityManager();

  NetworkStateManager networkStateManager();

  LocationManager locationManager();

  CacheManager cacheManager();

  ApiManager apiManager();
}
