package com.santalu.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import com.santalu.emptyview.EmptyView;

public class SampleActivity extends AppCompatActivity implements OnClickListener {

    EmptyView mEmptyView;
    EmptyPresenter mEmptyPresenter;

    public static void start(Context context) {
        context.startActivity(new Intent(context, SampleActivity.class));
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        mEmptyView = findViewById(R.id.empty_view);
        mEmptyView.setOnClickListener(this);
        mEmptyPresenter = new EmptyPresenter(mEmptyView);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.show_progress:
                mEmptyPresenter.showLoading();
                break;
            case R.id.show_content:
                mEmptyPresenter.showContent();
                break;
            case R.id.show_error:
                mEmptyPresenter.showError();
                break;
            case R.id.show_empty:
                mEmptyPresenter.showEmpty();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override public void onClick(View view) {
        mEmptyPresenter.showLoading();
    }


}
