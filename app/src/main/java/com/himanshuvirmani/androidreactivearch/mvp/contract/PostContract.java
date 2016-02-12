package com.himanshuvirmani.androidreactivearch.mvp.contract;

import com.himanshuvirmani.androidreactivearch.base.BaseContract;
import com.himanshuvirmani.androidreactivearch.data.entity.Post;
import java.util.List;

/**
 * Created by john.francis on 08/02/16.
 */
public interface PostContract {
 public interface PostView extends BaseContract.View{
  void showPost(Post post);
  void showPutPostSucess();
  void showPutPostError();
 }

  public interface PostUserActionListner extends BaseContract.UserActionsListener{
     void getPostById(int id);
     void putPostById(int id,Post post);
  }
}
