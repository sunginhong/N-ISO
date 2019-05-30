package com.example.n_iso;

import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
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
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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

        aboutView_vv.setVideoURI(Uri.parse("http://n-interaction.com/appData/reel.mp4"));
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

        mainBgColor = (View) findViewById(R.id.mainBgColor);
        mainBgColor.setBackgroundResource(R.color.gooeyview_bg_color);
        menuViewRl.setAlpha(0);
        MainBottom_GooeyView.gooeyview_canvas.setAlpha(0);

        MainBottom_CircleView.mainBottomMenu_CenterCircle.bringToFront();
    }




}