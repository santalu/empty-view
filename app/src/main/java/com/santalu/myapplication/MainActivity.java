package com.santalu.myapplication;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import com.santalu.emptyview.EmptyView;

/**
 * Created by santalu on 09/08/2017.
 */

public class MainActivity extends AppCompatActivity {

  private SwipeRefreshLayout refreshLayout;
  private EmptyView emptyView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    refreshLayout = findViewById(R.id.refresh_layout);
    emptyView = findViewById(R.id.empty_view);

    /*emptyView.error(new IllegalStateException())
        .setOnClickListener(v -> emptyView.showLoading())
        .show();*/

    /*emptyView.error(Error.CONNECTION)
        .setOnClickListener(v -> emptyView.showLoading())
        .show();*/

    // demonstrates how to include and exclude views from state changes
    /*emptyView.empty()
        .setEmptyDrawable(R.mipmap.ic_launcher)
        .setEmptyTitle("Empty Title")
        .setEmptyText("Empty Text")
        .setEmptyButtonText("Empty Button")
        .exclude(R.id.text)
        .setOnClickListener(v ->
            emptyView.loading()
                .include(R.id.text)
                .show())
        .show();*/

    refreshLayout.setOnRefreshListener(this::showLoading);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int itemId = item.getItemId();
    switch (itemId) {
      case R.id.show_progress:
        //emptyView.showLoading();
        emptyView.loading().show();
        break;
      case R.id.show_content:
        //emptyView.showContent();
        emptyView.content().show();
        break;
      case R.id.show_error:
        //emptyView.showError();
        emptyView.error()
            .setOnClickListener(v -> showLoading())
            .show();
        break;
      case R.id.show_empty:
        //emptyView.showEmpty();
        emptyView.empty()
            .setOnClickListener(v -> showLoading())
            .show();
        break;
    }
    return super.onOptionsItemSelected(item);
  }

  private void showLoading() {
    refreshLayout.setRefreshing(false);
    emptyView.showLoading();
    emptyView.postDelayed(() -> emptyView.showContent(), 2000);
  }
}
