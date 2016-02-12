package com.himanshuvirmani.androidreactivearch.mvp.mvpinjections.components;

import com.himanshuvirmani.androidreactivearch.base.dependencyinjection.components.ApplicationComponent;
import com.himanshuvirmani.androidreactivearch.base.dependencyinjection.qualifiers.PerActivity;
import com.himanshuvirmani.androidreactivearch.mvp.model.PostModel;
import com.himanshuvirmani.androidreactivearch.mvp.mvpinjections.modules.AppUtilModule;
import com.himanshuvirmani.androidreactivearch.mvp.ui.activities.PostActivity;
import com.himanshuvirmani.androidreactivearch.utils.AppUtils;
import dagger.Component;

/**
 * Created by john.francis on 08/02/16.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = { AppUtilModule.class})
public interface PostComponent {
 void inject(PostActivity postActivity);
 AppUtils provideAppUtils();

 PostModel providePostModel();

}
