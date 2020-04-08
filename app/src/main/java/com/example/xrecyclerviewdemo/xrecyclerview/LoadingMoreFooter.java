package com.example.xrecyclerviewdemo.xrecyclerview;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.xrecyclerviewdemo.R;


public class LoadingMoreFooter extends LinearLayout {

    private SimpleViewSwitcher progressCon;
    public final static int STATE_LOADING = 0;
    public final static int STATE_COMPLETE = 1;
    public final static int STATE_NOMORE = 2;
    private TextView mText;
    private ImageView mImageView;
    public Context mContext;

    public LoadingMoreFooter(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public LoadingMoreFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public void initView() {
        setGravity(Gravity.CENTER);
        setLayoutParams(new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        progressCon = new SimpleViewSwitcher(getContext());
        progressCon.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        AVLoadingIndicatorView progressView = new AVLoadingIndicatorView(this.getContext());
        progressView.setIndicatorColor(0xffB5B5B5);
        progressView.setIndicatorId(ProgressStyle.BallSpinFadeLoader);
        progressCon.setView(progressView);

//        addView(progressCon);
//        mText = new TextView(getContext());
//        mText.setText("正在加载...");
//
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins((int) getResources().getDimension(R.dimen.textandiconmargin), 0, 0, 15);
//
//        mText.setLayoutParams(layoutParams);
//        addView(mText);
        View footView = View.inflate(mContext, R.layout.view_foot, this);
        mText = footView.findViewById(R.id.tvDesc);
        mImageView = footView.findViewById(R.id.imageView);

        mProgressDrawable.setColor(0xff666666);
        mProgressDrawable.start();
        mImageView.setImageDrawable(mProgressDrawable);
        //addView(footView, layoutParams);
    }
    ProgressDrawable mProgressDrawable = new ProgressDrawable();

    public void setProgressStyle(int style) {
//        if (style == ProgressStyle.SysProgress) {
//            progressCon.setView(new ProgressBar(getContext(), null, android.R.attr.progressBarStyle));
//        } else {
//            AVLoadingIndicatorView progressView = new AVLoadingIndicatorView(this.getContext());
//            progressView.setIndicatorColor(0xffB5B5B5);
//            progressView.setIndicatorId(style);
//            progressCon.setView(progressView);
//        }
    }

    public void setState(int state) {
        switch (state) {
            case STATE_LOADING:
//                progressCon.setVisibility(View.VISIBLE);
//                mText.setText(getContext().getText(R.string.listview_loading));
//                this.setVisibility(View.VISIBLE);
                mProgressDrawable.start();
                mImageView.setVisibility(View.VISIBLE);
                mText.setText(getContext().getText(R.string.start_loading));
                this.setVisibility(View.VISIBLE);
                break;
            case STATE_COMPLETE:
//                mText.setText(getContext().getText(R.string.listview_loading));
//                this.setVisibility(View.GONE);
                mProgressDrawable.stop();
                mText.setText(getContext().getText(R.string.complete_loading));
                this.setVisibility(View.GONE);
                break;
            case STATE_NOMORE:
//                mText.setText(getContext().getText(R.string.nomore_loading));
//                progressCon.setVisibility(View.GONE);
//                this.setVisibility(View.VISIBLE);
                mProgressDrawable.stop();
                mImageView.setVisibility(View.GONE);
                mText.setText(getContext().getText(R.string.nomore_loading));
                this.setVisibility(View.VISIBLE);
                break;
        }
    }
}
