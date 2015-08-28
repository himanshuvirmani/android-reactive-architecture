package com.himanshuvirmani.androidreactivearch.data.api;

import android.content.Context;
import com.himanshuvirmani.androidreactivearch.data.entity.Post;
import java.util.List;
import rx.Observable;

/**
 * Created by himanshu.virmani on 09/05/15.
 */
public class ApiManager {

  public ApiManager(Context context) {

  }

  public Observable<Post> getPostById(int id) {
    return new PostService().getPostById(id);
  }

  public Observable<List<Post>> getPosts() {
    return new PostService().getPosts();
  }

  public Observable<Post> putPostById(int id, Post post) {
    return new PostService().putPostById(id, post);
  }
}
