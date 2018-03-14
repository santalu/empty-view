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
  private EmptyHelper emptyHelper;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    emptyView = findViewById(R.id.empty_view);
    emptyHelper = new EmptyHelper(emptyView);
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    int itemId = item.getItemId();
    switch (itemId) {
      case R.id.show_progress:
        emptyHelper.showLoading();
        break;
      case R.id.show_content:
        emptyHelper.showContent();
        break;
      case R.id.show_error:
        emptyHelper.showError(new OnClickListener() {
          @Override public void onClick(View v) {
            emptyView.showLoading();
          }
        });
        break;
      case R.id.show_empty:
        emptyHelper.showEmpty(new OnClickListener() {
          @Override public void onClick(View v) {
            emptyView.showLoading();
          }
        });
        break;
    }
    return super.onOptionsItemSelected(item);
  }
}
