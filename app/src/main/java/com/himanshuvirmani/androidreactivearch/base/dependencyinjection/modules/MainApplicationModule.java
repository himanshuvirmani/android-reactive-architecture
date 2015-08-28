package com.himanshuvirmani.androidreactivearch.base.dependencyinjection.modules;

import android.app.Application;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by himanshu.virmani on 08/05/15.
 */
@Module public class MainApplicationModule {

  private final Application application;

  public MainApplicationModule(Application application) {
    this.application = application;
  }

  /**
   * Expose the application to the graph.
   */
  @Provides @Singleton Application application() {
    return application;
  }
}
