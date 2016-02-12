package com.himanshuvirmani.androidreactivearch.mvp.presenter;

import com.himanshuvirmani.androidreactivearch.base.BasePresenter;
import com.himanshuvirmani.androidreactivearch.data.entity.Post;
import com.himanshuvirmani.androidreactivearch.mvp.contract.PostContract;
import com.himanshuvirmani.androidreactivearch.mvp.model.PostModel;
import javax.inject.Inject;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by john.francis on 08/02/16.
 */

// All the Business logic will be put in presenter. Thi decouples the view from the logic. So we can write testcases seperately.
public class PostPresenter extends BasePresenter<PostContract.PostView>
    implements PostContract.PostUserActionListner {
  PostModel postModel;

  //Constructor injection using Dagger
  @Inject public PostPresenter(PostModel postModel) {
    this.postModel = postModel;
  }

  @Override public void getPostById(int postId) {
    postModel.getPostById(postId)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<Post>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {

          }

          @Override public void onNext(Post post) {
            PostContract.PostView example1View = view();
            example1View.showPost(post);
          }
        });
  }

  @Override public void putPostById(int id, Post post) {
    final PostContract.PostView example1View = view();
    postModel.putPostById(id,post)
    .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<Post>() {
          @Override public void onCompleted() {
            example1View.showPutPostSucess();
          }

          @Override public void onError(Throwable e) {
            example1View.showPutPostError();
          }

          @Override public void onNext(Post post) {
            example1View.showPost(post);
          }
        });
  }
}
