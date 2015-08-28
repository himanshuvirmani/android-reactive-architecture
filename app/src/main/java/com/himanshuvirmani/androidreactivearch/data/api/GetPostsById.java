package com.himanshuvirmani.androidreactivearch.data.api;

import com.android.volley.GsonRequest;
import com.android.volley.Response;
import com.himanshuvirmani.androidreactivearch.data.ApiConfig;
import com.himanshuvirmani.androidreactivearch.data.entity.Post;

/**
 * Created by himanshu.virmani on 09/05/15.
 */
public class GetPostsById extends GsonRequest<Void, Post> {

  public GetPostsById(Response.Listener<Post> listener, Response.ErrorListener errorListener,
      int id) {
    super(Method.GET,
        ApiConfig.BASE_URL + ApiConfig.GET_POST_BY_ID.replace("<ID>", String.valueOf(id)), listener,
        errorListener);
  }
}
