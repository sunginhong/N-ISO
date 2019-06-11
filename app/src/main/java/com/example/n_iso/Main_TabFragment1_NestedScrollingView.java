package com.example.n_iso;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.n_iso.Utils_Folder.Utils_Anim;

import java.util.ArrayList;
import java.util.List;

public class Main_TabFragment1_NestedScrollingView extends NestedScrollView {
    private int mState = RecyclerView.SCROLL_STATE_IDLE;
    static int ScrollY = 0;
    static List<Main_TabFragment1_RecyclerAdapter.MyHolder> contents = new ArrayList<Main_TabFragment1_RecyclerAdapter.MyHolder>();
    int pos = 0;

    public interface NestedScrollViewScrollStateListener {
        void onNestedScrollViewStateChanged(int state);
    }

    public void setScrollListener(NestedScrollViewScrollStateListener scrollListener) {
        this.mScrollListener = scrollListener;
    }

    private NestedScrollViewScrollStateListener mScrollListener;

    public Main_TabFragment1_NestedScrollingView(Context context) {
        super(context);
    }

    public Main_TabFragment1_NestedScrollingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Main_TabFragment1_NestedScrollingView(Context context, AttributeSet attrs, int defStyleAttr) {
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
        int n = -1;
        while(n < MainActivity_Splash.display-1) {
            n = n + 1;
        }

        for (int i = 0; i < contents.size(); i++) {
            float scrollY_calc = ScrollY - contents.get(i).itemView.getY();
            if (scrollY_calc < 0 && scrollY_calc > -(MainActivity_MainView.screenHeight-MainActivity_MainView.main_bottom_actionbar_Height/4)) {
                scrollCardCheck(contents.get(i));
                break;
            }
        }
        cardAnim(pos, 400);
    }

    public void scrollCardCheck(Main_TabFragment1_RecyclerAdapter.MyHolder MyHolder){
        for (int i = 0; i < contents.size(); i++) {
            pos = MyHolder.getPosition();
            break;
        }
    }

    public void cardAnim(int index, int duration){
        for (int i = 0; i < contents.size(); i++) {
//            float scrollY_calc = ScrollY - contents.get(i).itemView.getY();

        }

        float scrollY_calc = ScrollY - contents.get(index).itemView.getY();

        if (scrollY_calc < MainActivity_MainView.screenHeight-MainActivity_MainView.main_bottom_actionbar_Height/4){
            System.out.println(index);
        }
//        Utils_Anim.TransAnim(MyHolder.container, 0, 0, 200, 0, duration);
    }

}
