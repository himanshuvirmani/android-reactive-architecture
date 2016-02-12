package com.himanshuvirmani.androidreactivearch.mvp.mvpinjections.components;

import com.himanshuvirmani.androidreactivearch.base.dependencyinjection.components.ApplicationComponent;
import com.himanshuvirmani.androidreactivearch.base.dependencyinjection.qualifiers.PerActivity;
import com.himanshuvirmani.androidreactivearch.mvp.model.PostListModel;
import com.himanshuvirmani.androidreactivearch.mvp.ui.activities.PostListActivity;
import dagger.Component;

/**
 * Created by john.francis on 08/02/16.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class)
public interface PostListComponent {
 void inject(PostListActivity postListActivity);

 PostListModel providePostListModel();

}
