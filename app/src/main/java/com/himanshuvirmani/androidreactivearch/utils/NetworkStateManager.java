package com.himanshuvirmani.androidreactivearch.utils;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by himanshu.virmani on 09/05/15.
 */
public class NetworkStateManager {

  private final ConnectivityManager connectivityManager;

  public NetworkStateManager(ConnectivityManager connectivityManager) {
    this.connectivityManager = connectivityManager;
  }

  public boolean isConnectedOrConnecting(){
    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
    return activeNetworkInfo.isConnectedOrConnecting();
  }
}
