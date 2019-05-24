package com.example.n_iso;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;


import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity_MainView extends AppCompatActivity {

    private ViewPager mViewPager;
    Toolbar myToolbar;

    static int currentPageIndex = 0;
    private int screenHeight = 0;
    private int screenWidth = 0;
    private String URL_JSON = "http://rstudi0.cafe24.com/json/";
    private String URL_THUMB_IMG = "http://rstudi0.cafe24.com/json/img/";
    private String URL_LINK = "http://jjangik.com/";

    final ArrayList<FrameLayout> arrayBtmButton = new ArrayList<FrameLayout>();

    static boolean set_fg1 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_vp);

        ChangeBounds bounds = new ChangeBounds();
        bounds.setDuration(400);
        bounds.setInterpolator(new DecelerateInterpolator(1.5f));
        getWindow().setSharedElementEnterTransition(bounds);

        mViewPager = (ViewPager) findViewById(R.id.mainvp);

        screenHeight = Util_Calc.getScreenSize(this).y;
        screenWidth = Util_Calc.getScreenSize(this).x;

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


}