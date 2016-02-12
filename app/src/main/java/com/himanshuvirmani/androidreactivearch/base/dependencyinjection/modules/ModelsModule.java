package com.himanshuvirmani.androidreactivearch.base.dependencyinjection.modules;

import android.support.annotation.NonNull;
import com.himanshuvirmani.androidreactivearch.data.api.ServerApi;
import com.himanshuvirmani.androidreactivearch.mvp.model.PostListModel;
import com.himanshuvirmani.androidreactivearch.mvp.model.PostModel;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by rohith.mohan on 03/02/16.
 */
@Module public class ModelsModule {
  @Provides @NonNull @Singleton
  public PostModel providesPostModel(@NonNull ServerApi api) {
    return new PostModel(api);
  }

  @Provides @NonNull @Singleton
  public PostListModel providePostListModel(@NonNull ServerApi api){
    return new PostListModel(api);
  }
}
