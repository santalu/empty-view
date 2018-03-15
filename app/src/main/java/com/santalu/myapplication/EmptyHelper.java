package com.santalu.myapplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.view.View;
import com.santalu.emptyview.EmptyView;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

/**
 * Created by santalu on 31/08/2017.
 * A simple helper class to demonstrate general usage of empty view
 */

public class EmptyHelper {

  private final EmptyView emptyView;

  public EmptyHelper(EmptyView emptyView) {
    this.emptyView = emptyView;
  }

  public boolean isConnected(Context context) {
    ConnectivityManager manager =
        (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo info = manager.getActiveNetworkInfo();
    return info != null && info.isConnected();
  }

  public void showLoading() {
    if (emptyView != null) {
      emptyView.showLoading();
    }
  }

  public void showLoading(CharSequence text) {
    if (emptyView != null) {
      emptyView.showLoading(text);
    }
  }

  public void showLoading(int textId) {
    if (emptyView != null) {
      emptyView.showLoading(textId);
    }
  }

  public void showContent() {
    if (emptyView != null) {
      emptyView.showContent();
    }
  }

  public void showEmpty(View.OnClickListener listener) {
    if (emptyView != null) {
      if (isConnected(emptyView.getContext())) {
        emptyView.showEmpty(listener);
      } else {
        showConnectionError(listener);
      }
    }
  }

  public void showEmpty(CharSequence text, View.OnClickListener listener) {
    if (emptyView != null) {
      emptyView.showEmpty(text, listener);
    }
  }

  public void showEmpty(int textId, View.OnClickListener listener) {
    if (emptyView != null) {
      emptyView.showEmpty(textId, listener);
    }
  }

  public void showError(View.OnClickListener listener) {
    if (emptyView != null) {
      emptyView.showError(listener);
    }
  }

  public void showError(CharSequence text, View.OnClickListener listener) {
    if (emptyView != null) {
      emptyView.showError(text, listener);
    }
  }

  public void showError(int textId, View.OnClickListener listener) {
    if (emptyView != null) {
      emptyView.showError(textId, listener);
    }
  }

  public void showError(Throwable throwable, View.OnClickListener listener) {
    if (emptyView != null) {
      if (throwable == null || TextUtils.isEmpty(throwable.getMessage())) {
        showError(R.string.emptyview_error_unknown, listener);
        return;
      }
      Error error = Error.find(throwable);
      switch (error) {
        case ENDPOINT:
          showEndpointError(listener);
          break;
        case TIMEOUT:
          showTimeoutError(listener);
          break;
        case UNKNOWN:
          showError(throwable.getMessage(), listener);
          break;
      }
    }
  }

  public void showConnectionError(View.OnClickListener listener) {
    showError(R.string.emptyview_error_connection_not_found, listener);
  }

  public void showTimeoutError(View.OnClickListener listener) {
    showError(R.string.emptyview_error_connection_timeout, listener);
  }

  public void showEndpointError(View.OnClickListener listener) {
    showError(R.string.emptyview_error_endpoint, listener);
  }

  enum Error {
    ENDPOINT,
    TIMEOUT,
    UNKNOWN;

    public static Error find(Throwable e) {
      if (e instanceof UnknownHostException) {
        return ENDPOINT;
      } else if (e instanceof SocketTimeoutException
          || e instanceof TimeoutException
          || e instanceof ConnectException) {
        return TIMEOUT;
      } else {
        return UNKNOWN;
      }
    }
  }
}
