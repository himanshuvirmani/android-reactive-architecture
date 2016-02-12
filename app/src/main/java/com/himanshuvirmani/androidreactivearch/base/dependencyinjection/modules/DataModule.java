package com.himanshuvirmani.androidreactivearch.base.dependencyinjection.modules;

import android.app.Application;
import com.himanshuvirmani.androidreactivearch.BuildConfig;
import com.himanshuvirmani.androidreactivearch.data.api.ApiManager;
import com.himanshuvirmani.androidreactivearch.logger.Log;
import com.himanshuvirmani.androidcache.Cache;
import com.himanshuvirmani.androidcache.CacheManager;
import com.himanshuvirmani.androidcache.DiskCache;
import dagger.Module;
import dagger.Provides;
import java.io.File;
import javax.inject.Singleton;

/**
 * Created by himanshu.virmani on 09/05/15.
 */
@Module
public class DataModule {

  private final Application application;

  public DataModule(Application application) {
    this.application = application;
  }

  @Provides @Singleton CacheManager provideCacheManager() {
    final String ANDROID_DISK_CACHE = "androiddiskcache";
    CacheManager cacheManager = null;
    final int DEFAULT_CACHE_SIZE = 1024 * 1024 * 10; //10mb
    final String cachePath = application.getCacheDir().getPath();
    final File cacheFile = new File(cachePath + File.separator + BuildConfig.APPLICATION_ID + ANDROID_DISK_CACHE);
    try {
      Cache diskCache = new DiskCache(cacheFile, BuildConfig.VERSION_CODE, DEFAULT_CACHE_SIZE);
      cacheManager = CacheManager.getInstance(diskCache);
      cacheManager.setDebug(true); //Do this if you want to see logs from cachemanager
    } catch (Exception e) {
      Log.e("Failed to instantiate cache manager");
      e.printStackTrace();
    }
    return cacheManager;
  }


}
