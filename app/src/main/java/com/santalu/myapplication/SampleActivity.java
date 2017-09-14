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

    EmptyView emptyView;
    EmptyPresenter emptyPresenter;

    public static void start(Context context) {
        context.startActivity(new Intent(context, SampleActivity.class));
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        emptyView = findViewById(R.id.empty_view);
        emptyView.setOnClickListener(this);
        emptyPresenter = new EmptyPresenter(emptyView);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.show_progress:
                emptyPresenter.showLoading();
                break;
            case R.id.show_content:
                emptyPresenter.showContent();
                break;
            case R.id.show_error:
                emptyPresenter.showError();
                break;
            case R.id.show_empty:
                emptyPresenter.showEmpty();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override public void onClick(View view) {
        emptyPresenter.showLoading();
    }


}
