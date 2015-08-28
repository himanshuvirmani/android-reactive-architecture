package com.himanshuvirmani.androidreactivearch.base.dependencyinjection.modules;

/**
 * Created by himanshu.virmani on 09/05/15.
 */

import android.support.v4.app.Fragment;
import com.himanshuvirmani.androidreactivearch.base.dependencyinjection.qualifiers.PerFragment;
import dagger.Module;
import dagger.Provides;

/**
 * A module to wrap the Activity state and expose it to the graph.
 */
@Module
public class FragmentModule {
  private final Fragment fragment;

  public FragmentModule(Fragment fragment) {
    this.fragment = fragment;
  }

  /**
   * Expose the activity to dependents in the graph.
   */
  @Provides @PerFragment Fragment fragment() {
    return this.fragment;
  }
}
