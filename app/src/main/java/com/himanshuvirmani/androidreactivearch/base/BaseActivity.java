package com.himanshuvirmani.androidreactivearch.base;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import butterknife.ButterKnife;
import com.himanshuvirmani.androidreactivearch.base.dependencyinjection.components.ActivityComponent;
import com.himanshuvirmani.androidreactivearch.base.dependencyinjection.components.DaggerActivityComponent;
import com.himanshuvirmani.androidreactivearch.base.dependencyinjection.modules.ActivityModule;
import com.himanshuvirmani.androidreactivearch.utils.NetworkStateManager;
import javax.inject.Inject;

/**
 * Created by himanshu.virmani on 09/05/15.
 */
public class BaseActivity extends ActionBarActivity {

  @Inject protected NetworkStateManager networkStateManager;

  private ActivityComponent component;

  ActivityComponent component() {
    if (component == null) {
      component = DaggerActivityComponent.builder()
          .applicationComponent(((MainApplication) getApplication()).component())
          .activityModule(new ActivityModule(this))
          .build();
    }
    return component;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    component().inject(this);
  }

  @Override protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    ButterKnife.inject(this);
  }
}
