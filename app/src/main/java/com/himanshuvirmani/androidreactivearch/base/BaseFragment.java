package com.himanshuvirmani.androidreactivearch.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import butterknife.ButterKnife;
import com.himanshuvirmani.androidreactivearch.base.dependencyinjection.components.DaggerFragmentComponent;
import com.himanshuvirmani.androidreactivearch.base.dependencyinjection.components.FragmentComponent;
import com.himanshuvirmani.androidreactivearch.base.dependencyinjection.modules.FragmentModule;
import com.himanshuvirmani.androidreactivearch.data.api.ApiManager;
import javax.inject.Inject;

/**
 * Created by himanshu.virmani on 09/05/15.
 */
public class BaseFragment extends Fragment {


  private FragmentComponent component;

  FragmentComponent component() {
    if (component == null) {
      component = DaggerFragmentComponent.builder()
          .applicationComponent(((MainApplication) getActivity().getApplication()).component())
          .fragmentModule(new FragmentModule(this))
          .build();
    }
    return component;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    component().inject(this);
  }

  public View onCreateView(View view) {
    ButterKnife.bind(this, view);
    return view;
  }
}
