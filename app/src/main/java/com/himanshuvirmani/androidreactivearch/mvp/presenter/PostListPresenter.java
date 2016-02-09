package com.himanshuvirmani.androidreactivearch.mvp.presenter;

import com.himanshuvirmani.androidreactivearch.base.BasePresenter;
import com.himanshuvirmani.androidreactivearch.data.entity.Post;
import com.himanshuvirmani.androidreactivearch.mvp.contract.PostContract;
import com.himanshuvirmani.androidreactivearch.mvp.contract.PostListContract;
import com.himanshuvirmani.androidreactivearch.mvp.model.PostListModel;
import com.himanshuvirmani.androidreactivearch.mvp.model.PostModel;
import java.util.List;
import javax.inject.Inject;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by john.francis on 08/02/16.
 */

// All the Business logic will be put in presenter. Thi decouples the view from the logic. So we can write testcases seperately.
public class PostListPresenter extends BasePresenter<PostListContract.PostListView>
    implements PostListContract.PostListUserActionListner {
  PostListModel postListModel;

  //Constructor injection using Dagger
  @Inject public PostListPresenter(PostListModel postListModel) {
    this.postListModel = postListModel;
  }

  @Override public void getPostsList() {
    final PostListContract.PostListView postListView = view();
    postListModel.getPostList()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<List<Post>>() {
          @Override public void onCompleted() {
            postListView.showSucess();
          }

          @Override public void onError(Throwable e) {
            postListView.showError();
          }

          @Override public void onNext(List<Post> post) {
            postListView.showPostList(post);
          }
        });
  }
}
