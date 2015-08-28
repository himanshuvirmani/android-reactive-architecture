package com.himanshuvirmani.androidreactivearch.base.dependencyinjection.modules;

/**
 * Created by himanshu.virmani on 09/05/15.
 */

import android.app.Activity;
import com.himanshuvirmani.androidreactivearch.base.dependencyinjection.qualifiers.PerActivity;
import dagger.Module;
import dagger.Provides;

/**
 * A module to wrap the Activity state and expose it to the graph.
 */
@Module
public class ActivityModule {
  private final Activity activity;

  public ActivityModule(Activity activity) {
    this.activity = activity;
  }

  /**
   * Expose the activity to dependents in the graph.
   */
  @Provides @PerActivity Activity activity() {
    return this.activity;
  }
}
