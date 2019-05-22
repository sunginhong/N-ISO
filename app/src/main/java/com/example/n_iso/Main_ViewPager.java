package com.example.n_iso;

import android.content.Context;
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
    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }

}