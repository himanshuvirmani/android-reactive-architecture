package com.himanshuvirmani.androidreactivearch.base;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import com.frogermcs.dagger2metrics.Dagger2Metrics;
import com.himanshuvirmani.androidreactivearch.BuildConfig;
import com.himanshuvirmani.androidreactivearch.base.dependencyinjection.components.ApplicationComponent;
import com.himanshuvirmani.androidreactivearch.base.dependencyinjection.components.DaggerApplicationComponent;
import com.himanshuvirmani.androidreactivearch.base.dependencyinjection.modules.ApiModule;
import com.himanshuvirmani.androidreactivearch.base.dependencyinjection.modules.DataModule;
import com.himanshuvirmani.androidreactivearch.base.dependencyinjection.modules.MainApplicationModule;
import com.himanshuvirmani.androidreactivearch.base.dependencyinjection.modules.ModelsModule;
import com.himanshuvirmani.androidreactivearch.base.dependencyinjection.modules.NetworkModule;
import com.himanshuvirmani.androidreactivearch.base.dependencyinjection.modules.SystemServiceModule;
import com.himanshuvirmani.androidreactivearch.logger.FileLog;
import com.himanshuvirmani.androidreactivearch.logger.Log;
import java.io.File;

/**
 * Created by himanshu.virmani on 08/08/15.
 */
public class MainApplication extends Application {

  private ApplicationComponent applicationComponent;

  @Override public void onCreate() {
    super.onCreate();
    applicationComponent = prepareApplicationComponent().build();
    applicationComponent.inject(this);
    assignLogger();
    if (BuildConfig.DEBUG) {
      Dagger2Metrics.enableCapturing(this);
    }
  }


  public ApplicationComponent component() {
    return applicationComponent;
  }

  private void assignLogger() {
    final int LOG_FILE_SIZE = 1000000; //1mb
    if (BuildConfig.BUILD_TYPE.equals("debug")) {
      FileLog.open("sdcard/" + File.separator + BuildConfig.APPLICATION_ID + ".log"
          , android.util.Log.VERBOSE, LOG_FILE_SIZE); Log.plant(new Log.DebugTree());
    } else if (BuildConfig.BUILD_TYPE.equals("preRelease")) {
      Log.plant(new Log.ErrorWarningTree());
    } else {
      Log.plant(new CrashReportingTree());
    }
  }

  /**
   * A tree which logs important information for crash reporting.
   */
  private static class CrashReportingTree extends Log.HollowTree {
    @Override
    public void i(String message, Object... args) {
      // TODO e.g., Crashlytics.log(String.format(message, args));
    }

    @Override
    public void i(Throwable t, String message, Object... args) {
      i(message, args); // Just add to the log.
    }

    @Override
    public void e(String message, Object... args) {
      i("ERROR: " + message, args); // Just add to the log.
    }

    @Override
    public void e(Throwable t, String message, Object... args) {
      e(message, args);

      // TODO e.g., Crashlytics.logException(t);
    }
  }

  @NonNull protected DaggerApplicationComponent.Builder prepareApplicationComponent() {

    return DaggerApplicationComponent.builder()
        .mainApplicationModule(new MainApplicationModule(this))
        .systemServiceModule(new SystemServiceModule(this))
        .dataModule(new DataModule(this))
        .apiModule(new ApiModule())
        .networkModule(new NetworkModule())
        .modelsModule(new ModelsModule());
  }


  @NonNull public static MainApplication get(Context context) {
    return (MainApplication) context.getApplicationContext();
  }
}