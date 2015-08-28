package com.himanshuvirmani.androidreactivearch.base.dependencyinjection.components;

/**
 * Created by himanshu.virmani on 09/05/15.
 */

import android.app.Activity;
import com.himanshuvirmani.androidreactivearch.base.BaseActivity;
import com.himanshuvirmani.androidreactivearch.base.dependencyinjection.modules.ActivityModule;
import com.himanshuvirmani.androidreactivearch.base.dependencyinjection.qualifiers.PerActivity;
import dagger.Component;

/**
 * A base component upon which fragment's components may depend.
 * Activity-level components should extend this component.
 *
 * Subtypes of ActivityComponent should be decorated with annotation:
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
  void inject(BaseActivity baseActivity);
  //Exposed to sub-graphs.
  Activity activity();
}
