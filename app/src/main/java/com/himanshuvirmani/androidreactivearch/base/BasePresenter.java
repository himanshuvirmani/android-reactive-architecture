package com.himanshuvirmani.androidreactivearch.base;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import javax.inject.Inject;

/**
 * Created by rohith.mohan on 01/02/16.
 */
public class BasePresenter<V> {

  @android.support.annotation.Nullable private volatile V view;


  @CallSuper public void bindView(@NonNull V view) {
    final V previousView = this.view;

    if (previousView != null) {
      throw new IllegalStateException(
          "Previous view is not unbounded! previousView = " + previousView);
    }

    this.view = view;
  }

  @android.support.annotation.Nullable protected V view() {
    return view;
  }

  @CallSuper @SuppressWarnings("PMD.CompareObjectsWithEquals")
  public void unbindView(@NonNull V view) {
    final V previousView = this.view;

    if (previousView == view) {
      this.view = null;
    } else {
      throw new IllegalStateException(
          "Unexpected view! previousView = " + previousView + ", view to unbind = " + view);
    }
  }
}
