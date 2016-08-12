package com.himanshuvirmani.androidreactivearch.data.api;

import com.himanshuvirmani.androidreactivearch.data.ApiConfig;
import com.himanshuvirmani.androidreactivearch.data.entity.Post;
import java.util.List;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by himanshu.virmani on 28/08/15.
 */
public class PostService {
  PostRequest postRequest;

  public PostService() {
    Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build();

    postRequest = retrofit.create(PostRequest.class);
  }

  public interface PostRequest {
    @GET("/posts/{id}") Observable<Post> getPostById(@Path("id") int id);

    @GET("/posts") Observable<List<Post>> getPosts();

    @PUT("/posts/{id}") Observable<Post> putPostById(@Path("id") int id, @Body Post post);
  }

  public Observable<Post> getPostById(int id) {
    return postRequest.getPostById(id);
  }

  public Observable<List<Post>> getPosts() {
    return postRequest.getPosts();
  }

  public Observable<Post> putPostById(int id, Post post) {
    return postRequest.putPostById(id, post);
  }
}
