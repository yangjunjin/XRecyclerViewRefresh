package com.example.xrecyclerviewdemo.xrecyclerview;

import android.content.Context;

import com.example.xrecyclerviewdemo.R;


public class MyAdapter extends CommomRecyclerAdapter<String> {

    public MyAdapter(Context context) {
        super(context);
    }

    @Override
    public void onBindView(ViewHolder mViewHolder, String data, int position) {
        mViewHolder.setText(R.id.text, data);
    }

    @Override
    public int onBindViewResource(int viewType) {
        return R.layout.item;
    }
}
