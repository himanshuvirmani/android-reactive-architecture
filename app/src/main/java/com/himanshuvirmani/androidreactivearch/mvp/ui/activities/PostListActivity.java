package com.himanshuvirmani.androidreactivearch.mvp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import butterknife.Bind;
import com.himanshuvirmani.androidreactivearch.R;
import com.himanshuvirmani.androidreactivearch.base.BaseActivity;
import com.himanshuvirmani.androidreactivearch.base.MainApplication;
import com.himanshuvirmani.androidreactivearch.data.entity.Post;
import com.himanshuvirmani.androidreactivearch.mvp.contract.PostListContract;
import com.himanshuvirmani.androidreactivearch.mvp.mvpinjections.components.DaggerPostListComponent;
import com.himanshuvirmani.androidreactivearch.mvp.mvpinjections.components.PostListComponent;
import com.himanshuvirmani.androidreactivearch.mvp.presenter.PostListPresenter;
import com.himanshuvirmani.androidreactivearch.mvp.ui.adapters.PostListAdapter;
import com.himanshuvirmani.androidreactivearch.utils.Constants;
import java.util.List;
import javax.inject.Inject;

public class PostListActivity extends BaseActivity implements PostListContract.PostListView,PostListAdapter.PostInteractionListner{

  @Inject PostListPresenter postListPresenter;

  PostListAdapter postAdapter;

  @Bind(R.id.post_list_view) RecyclerView postListView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_post_list);
  }

  @Override protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);

    buildDaggerComponentAndInject();
    bindViewToPresenter();
    postListView.setHasFixedSize(true);
    LinearLayoutManager llm = new LinearLayoutManager(this);
    llm.setOrientation(LinearLayoutManager.VERTICAL);
    postListView.setLayoutManager(llm);
    postListPresenter.getPostsList();

  }

  @Override public void buildDaggerComponentAndInject() {
    PostListComponent postListComponent = DaggerPostListComponent.builder()
        .applicationComponent(MainApplication.get(this).component())
        .build();
    postListComponent.inject(this);
  }

  @Override public void bindViewToPresenter() {
    postListPresenter.bindView(this);
  }

  @Override public void showSucess() {
    Toast.makeText(PostListActivity.this, "Got Post List Successs !!", Toast.LENGTH_SHORT).show();
  }

  @Override public void showError() {
    Toast.makeText(PostListActivity.this, "Got Post List Error !!", Toast.LENGTH_SHORT).show();

  }

  @Override public void showPostList(List<Post> posts) {
    postAdapter = new PostListAdapter(posts,this);
    postListView.setAdapter(postAdapter);
  }

  @Override public void postClicked(int id) {
    Intent postIntent = new Intent(this,PostActivity.class);
    postIntent.putExtra(Constants.POST_ID,id);
    startActivity(postIntent);
  }
}
