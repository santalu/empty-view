package com.santalu.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import com.santalu.emptyview.EmptyView;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    EmptyView mEmptyView;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEmptyView = findViewById(R.id.empty_view);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.show_progress:
                showProgress();
                break;
            case R.id.show_content:
                showContent();
                break;
            case R.id.show_error:
                showError();
                break;
            case R.id.show_empty:
                showEmpty();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override public void onClick(View view) {
        showProgress();
    }

    public void showProgress() {
        mEmptyView.showLoading();
    }

    public void showProgress(CharSequence text) {
        mEmptyView.showLoading(text);
    }

    public void showProgress(int textId) {
        mEmptyView.showLoading(textId);
    }

    public void showContent() {
        mEmptyView.showContent();
    }

    public void showEmpty() {
        if (Utils.isConnected(this)) {
            mEmptyView.setOnClickListener(this);
            mEmptyView.showEmpty();
        } else {
            showConnectionError();
        }
    }

    public void showEmpty(CharSequence text) {
        mEmptyView.setOnClickListener(this);
        mEmptyView.showEmpty(text);
    }

    public void showEmpty(int textId) {
        mEmptyView.setOnClickListener(this);
        mEmptyView.showEmpty(textId);
    }

    public void showError() {
        mEmptyView.setOnClickListener(this);
        mEmptyView.showError();
    }

    public void showError(CharSequence text) {
        mEmptyView.setOnClickListener(this);
        mEmptyView.showError(text);
    }

    public void showError(int textId) {
        mEmptyView.setOnClickListener(this);
        mEmptyView.showError(textId);
    }

    public void showError(Throwable throwable) {
        if (throwable == null || TextUtils.isEmpty(throwable.getMessage())) {
            showError(R.string.error_unknown);
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

    public void showConnectionError() {
        showError(R.string.error_connection_not_found);
    }

    public void showTimeoutError() {
        showError(R.string.error_connection_timeout);
    }

    public void showEndpointError() {
        showError(R.string.error_endpoint);
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
