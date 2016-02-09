package com.himanshuvirmani.androidreactivearch.mvp.contract;

import com.himanshuvirmani.androidreactivearch.base.BaseContract;
import com.himanshuvirmani.androidreactivearch.data.entity.Post;
import java.util.List;

/**
 * Created by john.francis on 08/02/16.
 */
public interface PostListContract {
 public interface PostListView extends BaseContract.View{
  void showSucess();
  void showError();
  void showPostList(List<Post> posts);
 }

  public interface PostListUserActionListner extends BaseContract.UserActionsListener{
     void getPostsList();
  }
}
