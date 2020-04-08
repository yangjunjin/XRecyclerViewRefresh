package com.example.xrecyclerviewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;

import com.example.xrecyclerviewdemo.xrecyclerview.MyAdapter;
import com.example.xrecyclerviewdemo.xrecyclerview.ProgressStyle;
import com.example.xrecyclerviewdemo.xrecyclerview.XRecyclerView;

public class MainActivity extends AppCompatActivity {
    private XRecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private int refreshTime = 0;
    private int times = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView =  findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);

        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refreshTime++;
                times = 0;
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        mAdapter.setDatas(Utils.getDatas("after " + refreshTime, 0));
                    }
                }, 1000);
            }

            @Override
            public void onLoadMore() {
                if (times < 1) {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            mAdapter.addDatas(Utils.getDatas("", mAdapter.getItemCount()));
                        }
                    }, 1000);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            mAdapter.addDatas(null);
                        }
                    }, 1000);
                }
                times++;
            }
        });

        mRecyclerView.setAdapter(mAdapter = new MyAdapter(this));
        mAdapter.setDatas(Utils.getDatas("", 0));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
