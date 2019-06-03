package com.example.n_iso;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


public class Main_ViewPager implements ViewPager.OnPageChangeListener, ViewPager.PageTransformer, View.OnTouchListener {

    private Context context = null;
    private ViewPager vp;
    boolean scrollBool = false;
    final int pageWidth;

    public Main_ViewPager(ViewPager viewPager) {
        vp = viewPager;
        viewPager.addOnPageChangeListener(this);
        viewPager.setOnTouchListener(this);

        pageWidth = vp.getWidth();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                scrollBool = true;
                break;

            case MotionEvent.ACTION_UP:
                scrollBool = false;
                break;

            case MotionEvent.ACTION_MOVE:

                break;
        }
        return false;
    }

    @Override
    public void transformPage(View page, float position) {

    }

    @Override
    public void onPageScrolled(final int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(final int position) {
        MainActivity_MainView.currentPageIndex = position;
        Handler mHandler;
        Runnable mRunnable;

        mRunnable = new Runnable() {
            @Override
            public void run() { MainActivity_MainView.myToolbar.setY(-MainActivity_MainView.myToolbarHeight); }
        };

        MainActivity_MainView.bottomSel(position, 200);

        switch (position) {

            case 0:
                if (MainActivity_MainView.myToolbar.getY() == 0){
                    ScrollHederAnim.HeaderShow(MainActivity_MainView.myToolbar, 0, -MainActivity_MainView.myToolbarHeight, 400);
                    mHandler = new Handler();
                    mHandler.postDelayed(mRunnable, 400);
                }
                break;
            case 1:
                if (MainActivity_MainView.myToolbar.getY() == -MainActivity_MainView.myToolbarHeight){
                    ScrollHederAnim.HeaderShow(MainActivity_MainView.myToolbar, -MainActivity_MainView.myToolbarHeight, 0, 400);
                    MainActivity_MainView.myToolbar.setY(0);
                }
                break;
            case 2:
                if (MainActivity_MainView.myToolbar.getY() == -MainActivity_MainView.myToolbarHeight){
                    ScrollHederAnim.HeaderShow(MainActivity_MainView.myToolbar, -MainActivity_MainView.myToolbarHeight, 0, 400);
                    MainActivity_MainView.myToolbar.setY(0);
                }
                break;
            case 3:
                if (MainActivity_MainView.myToolbar.getY() == -MainActivity_MainView.myToolbarHeight){
                    ScrollHederAnim.HeaderShow(MainActivity_MainView.myToolbar, -MainActivity_MainView.myToolbarHeight, 0, 400);
                    MainActivity_MainView.myToolbar.setY(0);
                }
                break;
        }

    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }

}