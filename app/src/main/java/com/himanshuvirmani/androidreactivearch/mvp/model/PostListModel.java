package com.himanshuvirmani.androidreactivearch.mvp.model;

import android.support.annotation.NonNull;
import com.himanshuvirmani.androidreactivearch.data.api.ServerApi;
import com.himanshuvirmani.androidreactivearch.data.entity.Post;
import java.util.List;
import rx.Observable;

/**
 * Created by john.francis on 08/02/16.
 */
public class PostListModel {

  @NonNull private final ServerApi api;

  public PostListModel(@NonNull ServerApi api) {
    this.api = api;
  }

  public Observable<List<Post>> getPostList() {
    return api.getPosts();
  }
}
