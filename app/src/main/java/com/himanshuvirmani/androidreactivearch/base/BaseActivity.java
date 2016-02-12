package com.himanshuvirmani.androidreactivearch.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;
import com.himanshuvirmani.androidreactivearch.base.dependencyinjection.components.ActivityComponent;
import com.himanshuvirmani.androidreactivearch.base.dependencyinjection.components.DaggerActivityComponent;
import com.himanshuvirmani.androidreactivearch.base.dependencyinjection.modules.ActivityModule;

/**
 * Created by himanshu.virmani on 09/05/15.
 */
public abstract class BaseActivity extends AppCompatActivity {


  public void buildDaggerComponentAndInject(){
    ActivityComponent activityComponent  = DaggerActivityComponent.builder()
        .activityModule(new ActivityModule(this))
        .applicationComponent(MainApplication.get(this).component()).build();
    activityComponent.inject(this);

  }

  //Abstract method, which all the sub activities should implement which binds to presenter
  public abstract void bindViewToPresenter();



  @Override protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    ButterKnife.bind(this);
  }
}
