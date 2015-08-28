package com.himanshuvirmani.androidreactivearch.data.api;

import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.himanshuvirmani.androidreactivearch.data.entity.Post;
import java.util.List;

/**
 * Created by himanshu.virmani on 09/05/15.
 */
public class ApiManager {

  private RequestQueue mRequestQueue;
  private RequestQueue mImageLoaderQueue;
  private ImageLoader mImageLoader;

  public ApiManager(Context context) {
    VolleyLog.setVerbose(true);
    mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
    mImageLoaderQueue =
        Volley.newRequestQueue(context.getApplicationContext()); //not used as of now
  }

  public void getPostById(Response.Listener<Post> listener, Response.ErrorListener errorListener,
      int id) {
    mRequestQueue.add(new GetPostsById(listener, errorListener, id));
  }

  public void getPosts(Response.Listener<List<Post>> listener, Response.ErrorListener errorListener) {
    mRequestQueue.add(new GetPosts(listener, errorListener));
  }

  public void putPostById(Response.Listener<Post> listener, Response.ErrorListener errorListener,
      int id, Post post) {
    mRequestQueue.add(new PutPostsById(listener, errorListener, id, post));
  }
}
