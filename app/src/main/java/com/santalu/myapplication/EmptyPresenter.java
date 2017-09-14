package com.santalu.myapplication;

import android.text.TextUtils;

import com.santalu.emptyview.EmptyView;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

/**
 * Created by santalu on 31/08/2017.
 */

public class EmptyPresenter {

    private final EmptyView emptyView;

    public EmptyPresenter(EmptyView emptyView) {
        this.emptyView = emptyView;
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

    public void showEmpty() {
        if (emptyView != null) {
            if (Utils.isConnected(emptyView.getContext())) {
                //emptyView.setOnClickListener(this);
                emptyView.showEmpty();
            } else {
                showConnectionError();
            }
        }
    }

    public void showEmpty(CharSequence text) {
        if (emptyView != null) {
            //emptyView.setOnClickListener(this);
            emptyView.showEmpty(text);
        }
    }

    public void showEmpty(int textId) {
        if (emptyView != null) {
            //emptyView.setOnClickListener(this);
            emptyView.showEmpty(textId);
        }
    }

    public void showError() {
        if (emptyView != null) {
            //emptyView.setOnClickListener(this);
            emptyView.showError();
        }
    }

    public void showError(CharSequence text) {
        if (emptyView != null) {
            //emptyView.setOnClickListener(this);
            emptyView.showError(text);
        }
    }

    public void showError(int textId) {
        if (emptyView != null) {
            //emptyView.setOnClickListener(this);
            emptyView.showError(textId);
        }
    }

    public void showError(Throwable throwable) {
        if (emptyView != null) {
            if (throwable == null || TextUtils.isEmpty(throwable.getMessage())) {
                showError(R.string.emptyview_error_unknown);
                return;
            }
            Error error = Error.find(throwable);
            switch (error) {
                case ENDPOINT:
                    showEndpointError();
                    break;
                case TIMEOUT:
                    showTimeoutError();
                    break;
                case UNKNOWN:
                    showError(throwable.getMessage());
                    break;
            }
        }
    }

    public void showConnectionError() {
        showError(R.string.emptyview_error_connection_not_found);
    }

    public void showTimeoutError() {
        showError(R.string.emptyview_error_connection_timeout);
    }

    public void showEndpointError() {
        showError(R.string.emptyview_error_endpoint);
    }

    enum Error {
        ENDPOINT,
        TIMEOUT,
        UNKNOWN;

        public static Error find(Throwable e) {
            if (e instanceof UnknownHostException) {
                return ENDPOINT;
            } else if (e instanceof SocketTimeoutException || e instanceof TimeoutException || e instanceof ConnectException) {
                return TIMEOUT;
            } else {
                return UNKNOWN;
            }
        }
    }

}
