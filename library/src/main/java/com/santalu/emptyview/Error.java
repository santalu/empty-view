package com.santalu.emptyview;

import android.content.Context;
import android.text.TextUtils;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

/**
 * Created by fatih.santalu on 24.01.2018.
 */

public enum Error {

  CONNECTION(R.string.error_connection_title, R.string.error_connection),
  TIMEOUT(R.string.error_connection_timeout_title, R.string.error_connection_timeout),
  SERVICE(0, 0),
  UNKNOWN(R.string.error_unknown_title, R.string.error_unknown);

  private final int titleResId;
  private final int messageResId;
  private String message;

  Error(int titleResId, int messageResId) {
    this.titleResId = titleResId;
    this.messageResId = messageResId;
  }

  public static Error get(Throwable e) {
    if (e instanceof ConnectException || e instanceof UnknownHostException) {
      return CONNECTION;
    } else if (e instanceof SocketTimeoutException || e instanceof TimeoutException) {
      return TIMEOUT;
    } else if (!TextUtils.isEmpty(e.getMessage())) {
      SERVICE.message = e.getMessage();
      return SERVICE;
    } else {
      return UNKNOWN;
    }
  }

  public int getTitleResId() {
    return titleResId;
  }

  public int getMessageResId() {
    return messageResId;
  }

  public String getTitle(Context context) {
    return titleResId == 0 ? null : context.getString(titleResId);
  }

  public String getMessage(Context context) {
    return messageResId == 0 ? message : context.getString(messageResId);
  }
}