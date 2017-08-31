package com.santalu.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Created by santalu on 31/08/2017.
 */

public class MainActivity extends AppCompatActivity {

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sampleNormal = findViewById(R.id.sample_normal);
        Button sampleRv = findViewById(R.id.sample_rv);

        sampleNormal.setOnClickListener(new OnClickListener() {
            @Override public void onClick(View view) {
                SampleActivity.start(MainActivity.this);
            }
        });

        sampleRv.setOnClickListener(new OnClickListener() {
            @Override public void onClick(View view) {
                RecyclerViewSampleActivity.start(MainActivity.this);
            }
        });
    }
}
