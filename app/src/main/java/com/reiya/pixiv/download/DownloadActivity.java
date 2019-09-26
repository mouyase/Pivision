package com.reiya.pixiv.download;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.reiya.pixiv.R;
import com.reiya.pixiv.adapter.DownloadAdapter;
import com.reiya.pixiv.bean.Theme;
import com.reiya.pixiv.service.DownloadService;

public class DownloadActivity extends AppCompatActivity {
    DownloadAdapter mAdapter;
    BroadcastReceiver mReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(Theme.getTheme());
        setContentView(R.layout.activity_download);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24px);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new DownloadAdapter(this, DownloadService.getTasksDoing());
        recyclerView.setAdapter(mAdapter);

        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                mAdapter.setItems(DownloadService.getTasksDoing());
            }
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.UPDATE_TASK");
        registerReceiver(mReceiver, filter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        try {
            unregisterReceiver(mReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
