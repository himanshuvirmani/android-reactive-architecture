package com.himanshuvirmani.androidreactivearch.data.api;

import com.himanshuvirmani.androidreactivearch.data.entity.Post;
import java.util.List;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by rohith.mohan on 28/01/16.
 */
public interface ServerApi {

  @GET("/posts") Observable<List<Post>> getPosts();

  @GET("/posts/{id}") Observable<Post> getPostById(@Path("id") int id);

  @PUT("/posts/{id}") Observable<Post> putPostById(@Path("id") int id, @Body Post post);

}
