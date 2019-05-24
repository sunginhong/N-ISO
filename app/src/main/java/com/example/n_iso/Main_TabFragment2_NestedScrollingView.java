package com.example.n_iso;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

public class Main_TabFragment2_NestedScrollingView extends NestedScrollView {
    private int mState = RecyclerView.SCROLL_STATE_IDLE;
    static int ScrollY = 0;

    public interface NestedScrollViewScrollStateListener {
        void onNestedScrollViewStateChanged(int state);
    }

    public void setScrollListener(NestedScrollViewScrollStateListener scrollListener) {
        this.mScrollListener = scrollListener;
    }

    private NestedScrollViewScrollStateListener mScrollListener;

    public Main_TabFragment2_NestedScrollingView(Context context) {
        super(context);
    }

    public Main_TabFragment2_NestedScrollingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Main_TabFragment2_NestedScrollingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void stopNestedScroll() {
        super.stopNestedScroll();
        dispatchScrollState(RecyclerView.SCROLL_STATE_IDLE);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        dispatchScrollState(RecyclerView.SCROLL_STATE_DRAGGING);
        return super.onStartNestedScroll(child, target, nestedScrollAxes);
    }


    @Override
    public boolean startNestedScroll(int axes) {
        boolean superScroll = super.startNestedScroll(axes);
        dispatchScrollState(RecyclerView.SCROLL_STATE_DRAGGING);
        return superScroll;
    }

    private void dispatchScrollState(int state) {
        if (state == 1){
            /// scrollStart

        }
        if (state == 0){
            /// scrollEnd

        }
        if (mScrollListener != null && mState != state) {
            mScrollListener.onNestedScrollViewStateChanged(state);
            mState = state;
        }
    }

    @Override
    protected void onScrollChanged(int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        ScrollY = scrollY;
        super.onScrollChanged(scrollX, scrollY, oldScrollX, oldScrollY);
//        System.out.println("SSSSS"+scrollY);

    }

}
