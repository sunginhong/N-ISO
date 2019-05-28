package com.example.n_iso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.view.Display;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.graphics.Point;

import com.bumptech.glide.Glide;
import com.example.n_iso.Utils_Folder.Utils_Anim;
import com.example.n_iso.Utils_Folder.Utils_Calc;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity_MainView extends AppCompatActivity {

    private ViewPager mViewPager;
    Toolbar myToolbar;


    static int currentPageIndex = 0;
    static int screenWidth;
    static int screenHeight;

    static RelativeLayout menuViewRl;
    static View mainBgColor;
    static View mainBottom_bgView;
    static ImageButton mainBottomMenu_Icn0;
    static RelativeLayout mainRvLayout;
    static RelativeLayout mainBottomMenu_CenterRlView;

    static ImageView mainBottomMenu_CenterView_IndexColor;
    static ImageView mainBottomMenu_CenterView_White;
    static RelativeLayout mainBottomMenu_CenterView_Icn;
    static ImageView mainBottomMenu_CenterView_Icn_blackLine;
    static ImageView mainBottomMenu_CenterView_Icn_whiteLine;

    private String URL_JSON = "http://rstudi0.cafe24.com/json/";
    private String URL_THUMB_IMG = "http://rstudi0.cafe24.com/json/img/";
    private String URL_LINK = "http://jjangik.com/";

    final ArrayList<FrameLayout> arrayBtmButton = new ArrayList<FrameLayout>();

    static boolean set_fg1 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_vp);

        screeSizeCalc();
//        mainBottom_bgView = (View) findViewById(R.id.mainBottom_bgView);

        ChangeBounds bounds = new ChangeBounds();
        bounds.setDuration(400);
        bounds.setInterpolator(new DecelerateInterpolator(1.5f));
        getWindow().setSharedElementEnterTransition(bounds);

        mViewPager = (ViewPager) findViewById(R.id.mainvp);

        mViewPager.setAdapter(new Main_PagerAdapter(getSupportFragmentManager()));
        mViewPager.setCurrentItem(0);
        Main_ViewPager Main_ViewPager = new Main_ViewPager(mViewPager);

        myToolbar = (Toolbar) findViewById(R.id.main_top_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        init_btn();
    }

    private void init_btn() {
        FrameLayout bottomMenu_btn_01 = (FrameLayout) findViewById(R.id.bottomMenu_btn_01);
        FrameLayout bottomMenu_btn_02 = (FrameLayout) findViewById(R.id.bottomMenu_btn_02);
        FrameLayout bottomMenu_btn_03 = (FrameLayout) findViewById(R.id.bottomMenu_btn_03);
        FrameLayout bottomMenu_btn_04 = (FrameLayout) findViewById(R.id.bottomMenu_btn_04);

        arrayBtmButton.add(bottomMenu_btn_01);
        arrayBtmButton.add(bottomMenu_btn_02);
        arrayBtmButton.add(bottomMenu_btn_03);
        arrayBtmButton.add(bottomMenu_btn_04);

        for (int i = 0; i < arrayBtmButton.size(); i++) {
            final int finalI = i;

            arrayBtmButton.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentPageIndex = finalI;
                    mViewPager.setCurrentItem(currentPageIndex);

                    switch (finalI) {
                        case 0:
                            Main_TabFragment1.main_tab_fragment_1_nestedScrollView.fullScroll(View.FOCUS_UP);
                            Main_TabFragment1.main_tab_fragment_1_nestedScrollView.smoothScrollTo(0,0);
                            /////
                            new Timer().schedule(
                                    new TimerTask(){
                                        @Override
                                        public void run(){
                                            Main_TabFragment1.main_tab_fragment_1_nestedScrollView.fullScroll(View.FOCUS_UP);
                                            Main_TabFragment1.main_tab_fragment_1_nestedScrollView.smoothScrollTo(0,0);
                                        }
                                    }, 100);
                            break;
                        case 1:

                            break;
                        case 2:

                            break;
                        case 3:

                            break;
                        default:
                            break;
                    }
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.

                return super.onOptionsItemSelected(item);

        }
    }

    @Override public void onLowMemory() {
        super.onLowMemory();
        Glide.get(this).clearMemory();
    }
    @Override public void onTrimMemory(int level) {
        super.onTrimMemory(level); Glide.get(this).trimMemory(level);
    }

    private void screeSizeCalc(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        menuViewRl = (RelativeLayout) findViewById(R.id.menuViewRl);
        mainBgColor = (View) findViewById(R.id.mainBgColor);
        mainBottomMenu_CenterRlView = (RelativeLayout) findViewById(R.id.mainBottomMenu_CenterRlView);
        mainBottomMenu_CenterView_IndexColor = (ImageView) findViewById(R.id.mainBottomMenu_CenterView_Index);
        mainBottomMenu_CenterView_White = (ImageView) findViewById(R.id.mainBottomMenu_CenterView_White);
        mainBottomMenu_CenterView_Icn = (RelativeLayout) findViewById(R.id.mainBottomMenu_CenterView_Icn);
        mainBottomMenu_CenterView_Icn_blackLine = (ImageView) findViewById(R.id.mainBottomMenu_CenterView_Icn_blackLine);
        mainBottomMenu_CenterView_Icn_whiteLine = (ImageView) findViewById(R.id.mainBottomMenu_CenterView_Icn_whiteLine);

        mainBottomMenu_CenterRlView.bringToFront();
        MainBottom_CircleView.mainBottomMenu_CenterCircle.bringToFront();

        mainBgColor = (View) findViewById(R.id.mainBgColor);
        mainBgColor.setBackgroundResource(R.color.gooeyview_bg_color);
        menuViewRl.setAlpha(0);
        MainBottom_GooeyView.gooeyview_canvas.setAlpha(0);
    }

}