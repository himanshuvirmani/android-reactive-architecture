package com.himanshuvirmani.androidreactivearch.data.api;

import com.android.volley.GsonRequest;
import com.android.volley.Response;
import com.himanshuvirmani.androidreactivearch.data.ApiConfig;
import com.himanshuvirmani.androidreactivearch.data.entity.Post;

/**
 * Created by himanshu.virmani on 09/05/15.
 */
public class PutPostsById extends GsonRequest<Post, Post> {

  public PutPostsById(Response.Listener<Post> listener, Response.ErrorListener errorListener,
      int id, Post post) {
    super(Method.PUT,
        ApiConfig.BASE_URL + ApiConfig.GET_POST_BY_ID.replace("<ID>", String.valueOf(id)), listener,
        errorListener, post);
  }
}
