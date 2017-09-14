package com.santalu.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.santalu.emptyview.EmptyView;

/**
 * Created by santalu on 31/08/2017.
 */

public class RecyclerViewSampleActivity extends AppCompatActivity {

    private static final int DELAY = 5000;

    public static void start(Context context) {
        context.startActivity(new Intent(context, RecyclerViewSampleActivity.class));
    }

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_recyclerview);
        final EmptyView emptyView = findViewById(R.id.empty_view);

        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        final SampleAdapter adapter = new SampleAdapter(this);
        recyclerView.setAdapter(adapter);

        emptyView.showLoading();
        emptyView.postDelayed(new Runnable() {
            @Override public void run() {
                emptyView.showContent();
            }
        }, DELAY);
    }

    private static class SampleAdapter extends Adapter<SampleAdapter.ViewHolder> {
        private final LayoutInflater inflater;

        SampleAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }

        @Override
        public SampleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(inflater.inflate(R.layout.item_recyclerview, parent, false));
        }

        @Override public void onBindViewHolder(SampleAdapter.ViewHolder holder, int position) {

        }

        @Override public int getItemCount() {
            return 20;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ViewHolder(View itemView) {
                super(itemView);
            }
        }
    }
}
