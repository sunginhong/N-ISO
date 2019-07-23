package com.example.n_iso;

import android.animation.ValueAnimator;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.transition.ChangeBounds;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.n_iso.Utils_Folder.Utils_Anim;
import com.example.n_iso.Utils_Folder.Utils_Calc;

public class Main_AboutDetailView extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    private FrameLayout activity_about_fl;
    private RelativeLayout activity_about_rl;
    private View aboutView_dv;
    private FrameLayout aboutView_fl;
    private MyVideoView aboutView_vv;
    private ImageButton mPlayButton;

    private ScrollView aboutView_sv;
    private TextView about_title;
    private TextView about_ptag;
    private TextView about_footer;
    private AppBarLayout about_appbar;
    private FrameLayout about_detail_backbtn;
    private CardView about_card_view;

    float cardRound_result = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_about_view);

        ChangeBounds bounds = new ChangeBounds();
        bounds.setDuration(400);
        bounds.setInterpolator(new DecelerateInterpolator(1.5f));
        getWindow().setSharedElementEnterTransition(bounds);

        about_appbar = (AppBarLayout) findViewById(R.id.about_appbar);
        about_appbar.setY(-Utils_Calc.dpToPx(56));

        activity_about_fl = (FrameLayout) findViewById(R.id.activity_about_fl);
        activity_about_fl.setAlpha(1);
        activity_about_rl = (RelativeLayout) findViewById(R.id.activity_about_rl);

//        about_card_view = (CardView) findViewById(R.id.about_card_view);
//        about_card_view.setRadius(MainActivity_MainView.screenWidth/2);

//        parent_round_animator(MainActivity_MainView.screenWidth, 0, 400);

        aboutView_fl = (FrameLayout) findViewById(R.id.aboutView_fl);
        aboutView_fl.setAlpha(0);
        Utils_Anim.AlphaAnim(aboutView_fl, 0, 1, 200);
        Utils_Anim.AlphaAnim(activity_about_rl, 0, 1, 200);

        aboutView_dv = (View) findViewById(R.id.aboutView_dv);
        aboutView_vv = (MyVideoView) findViewById(R.id.aboutView_vv);
        about_detail_backbtn = (FrameLayout)findViewById(R.id.about_detail_backbtn);
        about_detail_backbtn.setOnClickListener(this);
//        aboutView_vv.requestFocus();


//        Uri aboutVideo = Uri.parse("android.resource://" + getPackageName()+ "/"+R.raw.reel);
//        aboutView_vv.setVideoURI(MainActivity_MainView.aboutVideo);
        aboutView_vv.setAlpha(0);
//        stopVideo();
//        playVideo();

        // 동영상 재생이 완료된걸 알수있는 리스너
        aboutView_vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            // 동영상 재생이 완료된후 호출되는 메서드
            public void onCompletion(MediaPlayer player) {
                playVideo();
            }
        });

        ///
        aboutView_sv = (ScrollView) findViewById(R.id.aboutView_sv);
        about_title = (TextView) findViewById(R.id.about_title);
        about_ptag = (TextView) findViewById(R.id.about_ptag);
        about_footer = (TextView) findViewById(R.id.about_footer);

        about_title.setText(MainActivity_Splash.aboutTitle);
        about_ptag.setText(MainActivity_Splash.aboutPtag_kor);
        about_footer.setText(MainActivity_Splash.aboutFooter);

        Utils_Calc.delayMin(50, new Utils_Calc.DelayCallback() {
            @Override
            public void afterDelay() {
                aboutView_fl.setAlpha(1);
//                Utils_Anim.AlphaAnim(aboutView_fl, 0, 1, 400);
                about_appbar.setY(-Utils_Calc.dpToPx(0));
                Utils_Anim.TransAnim(about_appbar, 0, 0, -Utils_Calc.dpToPx(56), 0, 400);
            }
        });

    }

    private void playVideo() {
        aboutView_vv.seekTo(0);
        aboutView_vv.start();
    }

    private void stopVideo() {
        aboutView_vv.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        outAnim();
        ActivityCompat.finishAfterTransition(this);
    }

    @Override
    public void onClick(View view) {
        outAnim();
        ActivityCompat.finishAfterTransition(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        finish();
    }

    private void outAnim(){

        Utils_Anim.TransAnim(about_appbar, 0, 0, 0, -Utils_Calc.dpToPx(56), 200);

        Utils_Anim.AlphaAnim(activity_about_fl, 1, 0, 200);
        Utils_Anim.AlphaAnim(aboutView_fl, 1, 0, 200);
        Utils_Anim.AlphaAnim(activity_about_rl, 1, 0, 200);

        Utils_Calc.delayMin(50, new Utils_Calc.DelayCallback() {
            @Override
            public void afterDelay() {

            }
        });

//        this.overridePendingTransition(R.anim.activity_slide_in_100p_backward, R.anim.activity_slide_out_50p_backward);
    }

    private void parent_round_animator(float radiusStart, float radiusEnd, int duration) {
        ValueAnimator animator = ValueAnimator.ofFloat(radiusStart, radiusEnd);
        animator.setDuration(duration);
        animator.setInterpolator(new DecelerateInterpolator(Float.valueOf(String.valueOf(1.5))));
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                cardRound_result = (float) animation.getAnimatedValue();
                about_card_view.setRadius(cardRound_result);
            }
        });
        animator.start();
    }

//    @Override
//    public void onClick(View view) {
//        ActivityCompat.finishAfterTransition(this);
//        outAnim();
//    }
}