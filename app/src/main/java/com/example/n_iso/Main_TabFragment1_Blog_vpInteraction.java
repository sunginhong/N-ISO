package com.example.n_iso;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.n_iso.Utils_Folder.Utils_Anim;

import java.util.ArrayList;
import java.util.List;

public class Main_TabFragment1_Blog_vpInteraction implements ViewPager.OnPageChangeListener, ViewPager.PageTransformer, View.OnTouchListener {

    private ViewPager vp;
    private Context mContext = null;
    final int pageWidth;
    private boolean scrollBool = false;
    private float mLastOffset;

    static List<ImageView> thumbImage = new ArrayList<ImageView>();
    static List<LinearLayout> titlell = new ArrayList<LinearLayout>();
    private float startXPosition = 0;
    private float endXPosition = 0;
    private String vpDirection = "";
    private int selected_index = 0;
    private int selected_index_before = 0;
    private int currentIdx = 0;
    private int currentItemIdx = 0;
    private int positionNEXT = 0;
    private int positionMAX = MainActivity_Splash.display-1;
    private int currentItem_scroll_Idx = 0;
    private int itemLength = 2;
    static float titleXpos = 0;
    private double CAL_PAGING = 1.0/ (MainActivity_Splash.display-2);
    private double CAL_PAGING_F = 0;


    public Main_TabFragment1_Blog_vpInteraction(ViewPager viewPager) {
        vp = viewPager;
        viewPager.addOnPageChangeListener(this);
        viewPager.setOnTouchListener(this);

        pageWidth = vp.getWidth();
        titleXpos = Main_TabFragment1_VpAdapter.titleXpos;

        vp.setCurrentItem(0);

        String strNumber = String.format("%.2f", CAL_PAGING);
        float strNumber_n = Float.parseFloat(strNumber);
        CAL_PAGING_F = strNumber_n;
        Utils_Anim.SclaeAnim(Main_TabFragment1.main_frg1_vp_current_line, (1)*(float) CAL_PAGING, (1)*(float) CAL_PAGING_F, 1.0f, 1.0f, 0.0f, 0.5f, 0);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                scrollBool = true;
                startXPosition = event.getX();
                break;

            case MotionEvent.ACTION_UP:
                scrollBool = false;
                break;

            case MotionEvent.ACTION_MOVE:
                endXPosition = event.getX();

                if ((startXPosition > endXPosition) && (startXPosition - endXPosition) > 10) {
                    vpDirection = "RIGHT";
                }
                else if ((startXPosition < endXPosition) && (endXPosition - startXPosition) > 10) {
                    vpDirection = "LEFT";
                }
                break;
        }
        return false;
    }

    @Override
    public void transformPage(View page, float position) {

    }

    @Override
    public void onPageScrolled(final int position, float positionOffset, int positionOffsetPixels) {
        currentIdx = position;
        float pageScrollX = 0;

        positionNEXT = 0;
        if (position < positionMAX){
            positionNEXT = position+1;
        }
        currentItemIdx = position;

        if(positionOffset >= 0.0 && positionOffset < 0.5){
            if (currentItem_scroll_Idx > position){
                currentItem_scroll_Idx = currentItem_scroll_Idx-1;
            }
        }
        if(positionOffset > 0.5 && positionOffset < 1.0){
            if (currentItem_scroll_Idx < itemLength){
                currentItem_scroll_Idx = position+1;
            }
        }
        mLastOffset = positionOffset;

        for (int i = 0; i < Main_TabFragment1_Blog_vpInteraction.thumbImage.size(); i++) {
            if (i == position) {
                float nNumber = 1.2f - positionOffset;
                String strNumber = String.format("%.2f", nNumber);
                float strNumber_n = Float.parseFloat(strNumber);

                Main_TabFragment1_Blog_vpInteraction.thumbImage.get(position).setX(positionOffsetPixels/2);
                Main_TabFragment1_Blog_vpInteraction.thumbImage.get(position).setAlpha(strNumber_n);

                Utils_Anim.ModulatetTransXAnim(Main_TabFragment1_Blog_vpInteraction.titlell.get(position), positionOffsetPixels, 0, pageWidth, 0, -pageWidth/4);
                Main_TabFragment1_Blog_vpInteraction.titlell.get(position).setAlpha(1 - positionOffset);
            }
            if (i == positionNEXT) {
                Utils_Anim.ModulatetTransXAnim(Main_TabFragment1_Blog_vpInteraction.titlell.get(positionNEXT), positionOffsetPixels, 0, pageWidth, pageWidth/4, 0);
                Main_TabFragment1_Blog_vpInteraction.titlell.get(positionNEXT).setAlpha(positionOffset);
            }
        }
    }

    @Override
    public void onPageSelected(final int position) {
        final int pageWidth = vp.getWidth();
        selected_index = position;
        selected_index_before =  position;
//        Main_TabFragment1.main_frg1_vp_current_line.setScaleX((float) CAL_PAGING_F * (selected_index+1));

        if (positionNEXT == selected_index) {
            Utils_Anim.SclaeAnim(Main_TabFragment1.main_frg1_vp_current_line, (selected_index_before+0)*(float) CAL_PAGING_F, (selected_index+1)*(float) CAL_PAGING_F, 1.0f, 1.0f, 0.0f, 0.5f, 400);
        }
        if (positionNEXT != selected_index) {
            Utils_Anim.SclaeAnim(Main_TabFragment1.main_frg1_vp_current_line, (selected_index_before+2)*(float) CAL_PAGING_F, (selected_index+1)*(float) CAL_PAGING_F, 1.0f, 1.0f, 0.0f, 0.5f, 400);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
