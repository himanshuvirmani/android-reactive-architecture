package com.himanshuvirmani.androidreactivearch.ui.fragments;

/**
 * Created by himanshu.virmani on 11/05/15.
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.InjectView;
import com.himanshuvirmani.androidreactivearch.R;
import com.himanshuvirmani.androidreactivearch.base.BaseFragment;
import com.himanshuvirmani.androidreactivearch.data.entity.Post;
import com.himanshuvirmani.androidreactivearch.logger.Log;
import com.himanshuvirmani.androidreactivearch.ui.activities.MainActivity;
import java.util.List;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A placeholder fragment containing a simple view.
 */
public class SampleBlogFragment extends BaseFragment {

  @InjectView(R.id.tv_title) TextView tvTitle;

  @InjectView(R.id.tv_body) TextView tvBody;

  private Observable fetchDataObservable;

  /**
   * The fragment argument representing the section number for this
   * fragment.
   */
  private static final String ARG_SECTION_NUMBER = "section_number";

  /**
   * Returns a new instance of this fragment for the given section
   * number.
   */
  public static SampleBlogFragment newInstance(int sectionNumber) {
    SampleBlogFragment fragment = new SampleBlogFragment();
    Bundle args = new Bundle();
    args.putInt(ARG_SECTION_NUMBER, sectionNumber);
    fragment.setArguments(args);
    return fragment;
  }

  public SampleBlogFragment() {
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_main, container, false);
    return super.onCreateView(rootView);
  }

  @Override public void onStart() {
    super.onStart();
    apiManager.getPosts().subscribeOn(Schedulers.io()).observeOn(
        AndroidSchedulers.mainThread()).subscribe(new Observer<List<Post>>() {
      @Override public void onCompleted() {

      }

      @Override public void onError(Throwable e) {

      }

      @Override public void onNext(List<Post> posts) {

      }
    });

    apiManager.getPosts().subscribeOn(Schedulers.io()).observeOn(
        AndroidSchedulers.mainThread()).subscribe(new Observer<List<Post>>() {
      @Override public void onCompleted() {

      }

      @Override public void onError(Throwable e) {

      }

      @Override public void onNext(List<Post> posts) {
        Log.i("Posts : - " + posts);
      }
    });

    apiManager.getPostById(1).subscribeOn(Schedulers.io()).observeOn(
        AndroidSchedulers.mainThread()).subscribe(new Observer<Post>() {
      @Override public void onCompleted() {

      }

      @Override public void onError(Throwable e) {
        e.printStackTrace();
      }

      @Override public void onNext(Post post) {
        tvTitle.setText(post.getTitle());
        tvBody.setText(post.getBody());
      }
    });

    Post post = new Post();
    post.setId(1);
    post.setUserId(1);
    post.setBody("Sample body for sample blog");
    post.setTitle("Sample title");
    apiManager.putPostById(1, post).subscribeOn(Schedulers.io()).observeOn(
        AndroidSchedulers.mainThread()).subscribe(new Observer<Post>() {
      @Override public void onCompleted() {

      }

      @Override public void onError(Throwable e) {
        e.printStackTrace();
      }

      @Override public void onNext(Post post) {
        tvTitle.setText(post.getTitle());
        tvBody.setText(post.getBody());
      }
    });

    /*
    apiManager.getPosts(new Response.Listener<List<Post>>() {
      @Override public void onResponse(List<Post> post) {
      }
    }, new Response.ErrorListener() {
      @Override public void onErrorResponse(VolleyError volleyError) {
        Log.e("Some error occurred" + volleyError.toString());
      }
    });
    apiManager.getPostById(new Response.Listener<Post>() {
      @Override public void onResponse(Post post) {
        tvTitle.setText(post.getTitle());
        tvBody.setText(post.getBody());
      }
    }, new Response.ErrorListener() {
      @Override public void onErrorResponse(VolleyError volleyError) {
        Log.e("Some error occurred" + volleyError.toString());
      }
    }, 1);

    Post post = new Post();
    post.setId(1);
    post.setUserId(1);
    post.setBody("Sample body for sample blog");
    post.setTitle("Sample title");
    apiManager.putPostById(new Response.Listener<Post>() {
      @Override public void onResponse(Post post) {
        tvTitle.setText(post.getTitle());
        tvBody.setText(post.getBody());
      }
    }, new Response.ErrorListener() {
      @Override public void onErrorResponse(VolleyError volleyError) {
        Log.e("Some error occurred" + volleyError.toString());
      }
    }, 1,post);*/
  }

  @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    ((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
  }
}
