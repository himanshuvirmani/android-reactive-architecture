package com.himanshuvirmani.androidreactivearch.mvp.mvpinjections.modules;

import android.support.annotation.NonNull;
import com.himanshuvirmani.androidreactivearch.base.dependencyinjection.qualifiers.PerActivity;
import com.himanshuvirmani.androidreactivearch.utils.AppUtils;
import dagger.Module;
import dagger.Provides;

/**
 * Created by john.francis on 09/02/16.
 */

@Module
public class AppUtilModule {

  @Provides @PerActivity @NonNull AppUtils provideApputils(){
    return new AppUtils();
  }
}
