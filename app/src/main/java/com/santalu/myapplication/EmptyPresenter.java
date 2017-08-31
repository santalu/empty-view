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

    private final EmptyView mEmptyView;

    public EmptyPresenter(EmptyView emptyView) {
        mEmptyView = emptyView;
    }

    public void showLoading() {
        if (mEmptyView != null) {
            mEmptyView.showLoading();
        }
    }

    public void showLoading(CharSequence text) {
        if (mEmptyView != null) {
            mEmptyView.showLoading(text);
        }
    }

    public void showLoading(int textId) {
        if (mEmptyView != null) {
            mEmptyView.showLoading(textId);
        }
    }

    public void showContent() {
        if (mEmptyView != null) {
            mEmptyView.showContent();
        }
    }

    public void showEmpty() {
        if (mEmptyView != null) {
            if (Utils.isConnected(mEmptyView.getContext())) {
                //mEmptyView.setOnClickListener(this);
                mEmptyView.showEmpty();
            } else {
                showConnectionError();
            }
        }
    }

    public void showEmpty(CharSequence text) {
        if (mEmptyView != null) {
            //mEmptyView.setOnClickListener(this);
            mEmptyView.showEmpty(text);
        }
    }

    public void showEmpty(int textId) {
        if (mEmptyView != null) {
            //mEmptyView.setOnClickListener(this);
            mEmptyView.showEmpty(textId);
        }
    }

    public void showError() {
        if (mEmptyView != null) {
            //mEmptyView.setOnClickListener(this);
            mEmptyView.showError();
        }
    }

    public void showError(CharSequence text) {
        if (mEmptyView != null) {
            //mEmptyView.setOnClickListener(this);
            mEmptyView.showError(text);
        }
    }

    public void showError(int textId) {
        if (mEmptyView != null) {
            //mEmptyView.setOnClickListener(this);
            mEmptyView.showError(textId);
        }
    }

    public void showError(Throwable throwable) {
        if (mEmptyView != null) {
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
