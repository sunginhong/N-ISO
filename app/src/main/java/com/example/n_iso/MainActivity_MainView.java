package com.example.n_iso;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.app.Fragment;
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
import android.widget.FrameLayout;
import android.view.Display;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.graphics.Point;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.n_iso.Utils_Folder.Utils_Anim;
import com.example.n_iso.Utils_Folder.Utils_Calc;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity_MainView extends AppCompatActivity  {

    private ViewPager mViewPager;
    static Toolbar myToolbar;

    static int currentPageIndex = 0;
    static int myToolbarHeight = 0;
    static int screenWidth;
    static int screenHeight;
    static float alphaMin = 0.5f;
    static float scaleMin = 0.7f;

    static RelativeLayout main_fl;
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

    static View aboutView_dv;
    static FrameLayout aboutView_fl;
    static MyVideoView aboutView_vv;
    static ImageButton mPlayButton;

    static ScrollView aboutView_sv;
    static TextView about_title;
    static TextView about_ptag;
    static TextView about_footer;

    final ArrayList<FrameLayout> arrayBtmButton = new ArrayList<FrameLayout>();
    static final ArrayList<TextView> arrayBtmButton_Tv = new ArrayList<TextView>();
    static final ArrayList<View> arrayBtmButton_Cv = new ArrayList<View>();

    static boolean set_fg1 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_vp);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        screeSizeCalc();
//        mainBottom_bgView = (View) findViewById(R.id.mainBottom_bgView);

        ChangeBounds bounds = new ChangeBounds();
        bounds.setDuration(400);
        bounds.setInterpolator(new DecelerateInterpolator(1.5f));
        getWindow().setSharedElementEnterTransition(bounds);

        main_fl = (RelativeLayout) findViewById(R.id.main_fl);
        mViewPager = (ViewPager) findViewById(R.id.mainvp);

        mViewPager.setAdapter(new Main_PagerAdapter(getSupportFragmentManager()));
        mViewPager.setCurrentItem(0);
        mViewPager.setOffscreenPageLimit(4);
        Main_ViewPager Main_ViewPager = new Main_ViewPager(mViewPager);

        myToolbar = (Toolbar) findViewById(R.id.main_top_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        myToolbarHeight = Utils_Calc.dpToPx(56);
        ScrollHederAnim.HeaderShow(myToolbar, 0, -myToolbarHeight, 0);
        myToolbar.setY(-myToolbarHeight);

        aboutView_fl = (FrameLayout) findViewById(R.id.aboutView_fl);
        aboutView_fl.setX(screenWidth);
        aboutView_dv = (View) findViewById(R.id.aboutView_dv);
        aboutView_vv = (MyVideoView) findViewById(R.id.aboutView_vv);
//        MediaController controller = new MediaController(this);
//        aboutView_vv.setMediaController(controller);
        aboutView_vv.requestFocus();

//        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(screenHeight, screenWidth);
//        aboutView_vv.setLayoutParams(lp);

        Uri aboutVideo = Uri.parse("android.resource://" + getPackageName()+ "/"+R.raw.reel);
        aboutView_vv.setVideoURI(aboutVideo);

//        aboutView_vv.setVideoURI(Uri.parse("http://n-interaction.com/appData/reel.mp4"));
        playVideo();
        stopVideo();

//        playVideo();
//        aboutView_vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            // 동영상 재생준비가 완료된후 호출되는 메서드
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                stopVideo();
//            }
//        });

        // 동영상 재생이 완료된걸 알수있는 리스너
        aboutView_vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            // 동영상 재생이 완료된후 호출되는 메서드
            public void onCompletion(MediaPlayer player) {
                playVideo();
            }
        });

        init_btn();

        /////
        aboutView_sv = (ScrollView) findViewById(R.id.aboutView_sv);
        about_title = (TextView) findViewById(R.id.about_title);
        about_ptag = (TextView) findViewById(R.id.about_ptag);
        about_footer = (TextView) findViewById(R.id.about_footer);

        about_title.setText(MainActivity_Splash.aboutTitle);
        about_ptag.setText(MainActivity_Splash.aboutPtag_kor);
        about_footer.setText(MainActivity_Splash.aboutFooter);
    }

    static void playVideo() {
        aboutView_vv.seekTo(0);
        aboutView_vv.start();
    }

    static void stopVideo() {
        aboutView_vv.pause();
//        aboutView_vv.stopPlayback();
    }

    private void init_btn() {
        TextView bottomMenu_btn_text_01 = (TextView) findViewById(R.id.bottomMenu_btn_text_01);
        TextView bottomMenu_btn_text_02 = (TextView) findViewById(R.id.bottomMenu_btn_text_02);
        TextView bottomMenu_btn_text_03 = (TextView) findViewById(R.id.bottomMenu_btn_text_03);
        TextView bottomMenu_btn_text_04 = (TextView) findViewById(R.id.bottomMenu_btn_text_04);

        arrayBtmButton_Tv.add(bottomMenu_btn_text_01);
        arrayBtmButton_Tv.add(bottomMenu_btn_text_02);
        arrayBtmButton_Tv.add(bottomMenu_btn_text_03);
        arrayBtmButton_Tv.add(bottomMenu_btn_text_04);

        View bottomMenu_btn_circle_01 = (View) findViewById(R.id.bottomMenu_btn_circle_01);
        View bottomMenu_btn_circle_02 = (View) findViewById(R.id.bottomMenu_btn_circle_02);
        View bottomMenu_btn_circle_03 = (View) findViewById(R.id.bottomMenu_btn_circle_03);
        View bottomMenu_btn_circle_04 = (View) findViewById(R.id.bottomMenu_btn_circle_04);

        arrayBtmButton_Cv.add(bottomMenu_btn_circle_01);
        arrayBtmButton_Cv.add(bottomMenu_btn_circle_02);
        arrayBtmButton_Cv.add(bottomMenu_btn_circle_03);
        arrayBtmButton_Cv.add(bottomMenu_btn_circle_04);

        FrameLayout bottomMenu_btn_01 = (FrameLayout) findViewById(R.id.bottomMenu_btn_01);
        FrameLayout bottomMenu_btn_02 = (FrameLayout) findViewById(R.id.bottomMenu_btn_02);
        FrameLayout bottomMenu_btn_03 = (FrameLayout) findViewById(R.id.bottomMenu_btn_03);
        FrameLayout bottomMenu_btn_04 = (FrameLayout) findViewById(R.id.bottomMenu_btn_04);

        arrayBtmButton.add(bottomMenu_btn_01);
        arrayBtmButton.add(bottomMenu_btn_02);
        arrayBtmButton.add(bottomMenu_btn_03);
        arrayBtmButton.add(bottomMenu_btn_04);

        bottomSel(0, 0);

        for (int i = 0; i < arrayBtmButton.size(); i++) {
            final int finalI = i;

            arrayBtmButton.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentPageIndex = finalI;
                    mViewPager.setCurrentItem(currentPageIndex);
                    bottomSel(finalI, 200);
                    switch (finalI) {
                        case 0:
//                            Main_TabFragment1.main_tab_fragment_1_nestedScrollView.fullScroll(View.FOCUS_UP);
//                            Main_TabFragment1.main_tab_fragment_1_nestedScrollView.smoothScrollTo(0,0);
//                            /////
//                            new Timer().schedule(
//                                    new TimerTask(){
//                                        @Override
//                                        public void run(){
//                                            Main_TabFragment1.main_tab_fragment_1_nestedScrollView.fullScroll(View.FOCUS_UP);
//                                            Main_TabFragment1.main_tab_fragment_1_nestedScrollView.smoothScrollTo(0,0);
//                                        }
//                                    }, 100);
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

        mainBgColor = (View) findViewById(R.id.mainBgColor);
        mainBgColor.setBackgroundResource(R.color.gooeyview_bg_color);
        menuViewRl.setAlpha(0);
        MainBottom_GooeyView.gooeyview_canvas.setAlpha(0);

        MainBottom_CircleView.mainBottomMenu_CenterCircle.bringToFront();
    }

    static void bottomSel(int index, int duration){
        for (int i = 0; i < arrayBtmButton_Tv.size(); i++) {
            if (i == index){
                if (arrayBtmButton_Tv.get(i).getAlpha() == alphaMin){
                    Utils_Anim.AlphaAnim(arrayBtmButton_Tv.get(i), alphaMin, 1, duration);
                    arrayBtmButton_Tv.get(i).setAlpha(1);
                    Utils_Anim.SclaeAlphaAnim(arrayBtmButton_Cv.get(i), scaleMin, 1, scaleMin, 1, 0.5f, 0.5f, alphaMin, 1, duration*2);
                    arrayBtmButton_Cv.get(i).setScaleX(1);
                    arrayBtmButton_Cv.get(i).setScaleY(1);
                    arrayBtmButton_Cv.get(i).setAlpha(1);
                }
            } else {
                if (arrayBtmButton_Tv.get(i).getAlpha() == 1) {
                    Utils_Anim.AlphaAnim(arrayBtmButton_Tv.get(i), 1, alphaMin, duration);
                    arrayBtmButton_Tv.get(i).setAlpha(alphaMin);
                    Utils_Anim.SclaeAlphaAnim(arrayBtmButton_Cv.get(i), 1, scaleMin, 1, scaleMin, 0.5f, 0.5f, 1, alphaMin, duration*2);
                    arrayBtmButton_Cv.get(i).setScaleX(scaleMin);
                    arrayBtmButton_Cv.get(i).setScaleY(scaleMin);
                    arrayBtmButton_Cv.get(i).setAlpha(alphaMin);
                }
            }
        }
    }


}