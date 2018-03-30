package com.santalu.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import com.santalu.emptyview.EmptyView;

/**
 * Created by santalu on 09/08/2017.
 */

public class MainActivity extends AppCompatActivity {

  private EmptyView emptyView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    emptyView = findViewById(R.id.empty_view);

    /*emptyView.error(new IllegalStateException())
        .setOnClickListener(v -> emptyView.showLoading())
        .show();*/

    /*emptyView.error(Error.CONNECTION)
        .setOnClickListener(v -> emptyView.showLoading())
        .show();*/

    /*emptyView.empty()
        .setEmptyDrawable(R.mipmap.ic_launcher)
        .setEmptyDrawableTint(0)
        .setEmptyTitle("Empty Title")
        .setEmptyText("Empty Text")
        .setEmptyButtonText("Empty Button")
        .setOnClickListener(v ->
            emptyView.loading()
                .exclude(0)
                .show())
        .exclude(R.id.text)
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
        emptyView.loading().show();
        break;
      case R.id.show_content:
        //emptyView.showContent();
        emptyView.content().show();
        break;
      case R.id.show_error:
        //emptyView.showError();
        emptyView.error()
            .setOnClickListener(v -> emptyView.showLoading())
            .show();
        break;
      case R.id.show_empty:
        //emptyView.showEmpty();
        emptyView.empty()
            .setOnClickListener(v -> emptyView.showLoading())
            .show();
        break;
    }
    return super.onOptionsItemSelected(item);
  }
}
