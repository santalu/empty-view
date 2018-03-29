package com.santalu.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import com.santalu.emptyview.EmptyView;

public class MainActivity extends AppCompatActivity {

  private EmptyView emptyView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    emptyView = findViewById(R.id.empty_view);

    /*emptyView.setState(EmptyView.EMPTY)
        .setEmptyDrawable(R.mipmap.ic_launcher)
        .setEmptyDrawableTint(0)
        .setEmptyTitle("Empty Title")
        .setEmptyText("Empty Text")
        .setEmptyButtonText("Empty Button")
        .show();*/
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    int itemId = item.getItemId();
    switch (itemId) {
      case R.id.show_progress:
        //emptyView.showLoading();
        emptyView.setState(EmptyView.LOADING)
            .show();
        break;
      case R.id.show_content:
        //emptyView.showContent();
        emptyView.setState(EmptyView.CONTENT)
            .show();
        break;
      case R.id.show_error:
        //emptyView.showError();
        emptyView.setState(EmptyView.ERROR)
            .setOnClickListener(new OnClickListener() {
              @Override public void onClick(View v) {
                emptyView.showLoading();
              }
            })
            .show();
        break;
      case R.id.show_empty:
        //emptyView.showEmpty();
        emptyView.setState(EmptyView.EMPTY)
            .setOnClickListener(new OnClickListener() {
              @Override public void onClick(View v) {
                emptyView.showLoading();
              }
            })
            .show();
        break;
    }
    return super.onOptionsItemSelected(item);
  }
}
