package com.himanshuvirmani.androidreactivearch.mvp.model;

import android.support.annotation.NonNull;
import com.himanshuvirmani.androidreactivearch.data.api.ServerApi;
import com.himanshuvirmani.androidreactivearch.data.entity.Post;
import rx.Observable;

/**
 * Created by john.francis on 08/02/16.
 */
public class PostModel {

  @NonNull private final ServerApi api;

  public PostModel(@NonNull ServerApi api) {
    this.api = api;
  }

  public Observable<Post> getPostById(int id) {
    return api.getPostById(id);
  }

  public Observable<Post> putPostById(int id,Post post) {
    return api.putPostById(id,post);
  }

}
