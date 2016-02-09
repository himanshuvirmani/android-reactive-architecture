package com.himanshuvirmani.androidreactivearch.mvp.ui.activities;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import com.himanshuvirmani.androidreactivearch.R;
import com.himanshuvirmani.androidreactivearch.base.BaseActivity;
import com.himanshuvirmani.androidreactivearch.base.MainApplication;
import com.himanshuvirmani.androidreactivearch.data.entity.Post;
import com.himanshuvirmani.androidreactivearch.logger.Log;
import com.himanshuvirmani.androidreactivearch.mvp.contract.PostContract;
import com.himanshuvirmani.androidreactivearch.mvp.mvpinjections.components.DaggerPostComponent;
import com.himanshuvirmani.androidreactivearch.mvp.mvpinjections.components.PostComponent;
import com.himanshuvirmani.androidreactivearch.mvp.mvpinjections.modules.AppUtilModule;
import com.himanshuvirmani.androidreactivearch.mvp.presenter.PostPresenter;
import com.himanshuvirmani.androidreactivearch.utils.AppUtils;
import com.himanshuvirmani.androidreactivearch.utils.Constants;
import javax.inject.Inject;

/**
 * Created by john.francis on 08/02/16.
 */
public class PostActivity extends BaseActivity implements PostContract.PostView {

  PostComponent example1Component;


  @Bind(R.id.tv_title) TextView tvTitle;

  @Bind(R.id.tv_body) TextView tvBody;


  @Inject PostPresenter postPresenter;

  @Inject AppUtils appUtils;


  // Override this method if you want to add extra components
  @Override public void buildDaggerComponentAndInject() {
      example1Component = DaggerPostComponent.builder()
          .appUtilModule(new AppUtilModule())                  // Component specific modules can be added here
          .applicationComponent(MainApplication.get(this).component()).build();
      example1Component.inject(this);
  }

  @Override public void bindViewToPresenter() {
    postPresenter.bindView(this);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_post_detail);
    buildDaggerComponentAndInject();             // These two methods should be called in oncreate of every subclass of BaseActivity
    bindViewToPresenter();                       //
    Log.d("" + appUtils.getAppVersion());

    int postId = getIntent().getIntExtra(Constants.POST_ID,1);
    postPresenter.getPostById(postId);

  }

  @Override public void showPost(Post post) {
    tvTitle.setText(post.getTitle());
    tvBody.setText(post.getBody());
  }

  @Override public void showPutPostSucess() {
    Toast.makeText(PostActivity.this, "Put post Success", Toast.LENGTH_SHORT).show();
  }

  @Override public void showPutPostError() {
    Toast.makeText(PostActivity.this, "Put post Error!!", Toast.LENGTH_SHORT).show();
  }
}
