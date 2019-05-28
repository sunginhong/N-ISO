package com.example.n_iso;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.example.n_iso.Utils_Folder.Utils_Anim;
import com.example.n_iso.Utils_Folder.Utils_Calc;

import java.util.Timer;
import java.util.TimerTask;

public class MainBottom_CircleView extends android.support.v7.widget.AppCompatImageView implements View.OnClickListener {

    Context context;
    static boolean drag = false;
    static boolean dragENTER = false;
    private float dragStart_x = 0;
    private float dragStart_y = 0;
    static float dragMove_x = 0;
    static float dragMove_y = 0;
    static ImageView mainBottomMenu_CenterCircle;
    static float icnRotate_deg = 180+45;

    private float mainBottomMenu_CenterCircle_onriginY = 0;
    static int mainBottom_bgView_Height = 0;

    public MainBottom_CircleView(Context context) {
        super(context);
        initView();
    }

    public MainBottom_CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MainBottom_CircleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);
        initView();
    }

    private void initView(){
        mainBottomMenu_CenterCircle = (ImageView) findViewById(R.id.mainBottomMenu_CenterCircle);
        mainBottom_bgView_Height = Utils_Calc.dpToPx(75+10);
        mainBottomMenu_CenterCircle_onriginY = mainBottomMenu_CenterCircle.getY();

//        MainActivity_MainView.mainBottomMenu_CenterRlView.setOnClickListener(this);
        new Timer().schedule(
                new TimerTask(){
                    @Override
                    public void run(){
                        mainBottomMenu_CenterCircle_onriginY = mainBottomMenu_CenterCircle.getY();
                    }
                }, 200);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                if (!drag){
//                    dragMove_x = MainBottom_GooeyView.circle.getX();
                    dragMove_y = MainBottom_GooeyView.circle.getY();
                }
                dragStart_y = mainBottomMenu_CenterCircle.getY() - e.getRawY();
                MainBottom_GooeyView.dragStart_point_y = e.getY();
                mainBottomMenu_CenterCircle.setY(MainBottom_GooeyView.circleSetOriginY);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                drag = true;
                if (drag && !dragENTER){
//                    dragMove_x = e.getRawX() + dragStart_x;
                    dragMove_y = e.getRawY() + dragStart_y;
//                    System.out.println("ACTION_MOVE");
                    if (dragMove_y < MainActivity_MainView.screenHeight / 2 - MainBottom_GooeyView.circleWidth){
                        dragMove_y = MainActivity_MainView.screenHeight / 2 - MainBottom_GooeyView.circleWidth;
                        MainActivity_MainView.mainBottomMenu_CenterView_IndexColor.setAlpha(0);
                    }
//                    mainBottomMenu_CenterCircle.setX(dragMove_x);
                    mainBottomMenu_CenterCircle.setY(dragMove_y);
//                    MainActivity_MainView.mainBottomMenu_CenterRlView.setX(dragMove_x);
                    MainActivity_MainView.mainBottomMenu_CenterRlView.setY(dragMove_y);

//                    MainBottom_GooeyView.dragMove_point_x = dragMove_x;
                    MainBottom_GooeyView.dragMove_point_y = dragMove_y;

                    MainBottom_GooeyView.gooeyview_canvas.invalidate();
                    MainBottom_GooeyView.gooeyview_canvas.setAlpha(MainBottom_GooeyView.float_calcRate_alpha_in);

                    MainActivity_MainView.menuViewRl.setAlpha(MainBottom_GooeyView.float_calcRate_alpha_in);
                    MainActivity_MainView.mainBottomMenu_CenterView_White.setAlpha(MainBottom_GooeyView.float_calcRate_alpha_in);
                    MainActivity_MainView.mainBottomMenu_CenterView_IndexColor.setAlpha(MainBottom_GooeyView.float_calcRate_alpha_out);
                    MainActivity_MainView.mainBottomMenu_CenterView_Icn_whiteLine.setAlpha(MainBottom_GooeyView.float_calcRate_alpha_out);
                    MainActivity_MainView.mainBottomMenu_CenterView_Icn_blackLine.setAlpha(MainBottom_GooeyView.float_calcRate_alpha_in);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (drag){
//                    System.out.println("ACTION_UP");
                    if (MainActivity_MainView.mainBottomMenu_CenterRlView.getY() < MainActivity_MainView.screenHeight/1.8f){
                        if (!dragENTER) {
                            /// position - top
                            dragENTER = true;
                            Utils_Anim.TransAnim(MainActivity_MainView.mainBottomMenu_CenterRlView, 0, 0, -(MainActivity_MainView.screenHeight/2 - (MainBottom_GooeyView.circleWidth + dragMove_y)), 0, 100);
//                            Utils_Anim.TransAnim(MainActivity_MainView.mainBottomMenu_CenterRlView, -(MainBottom_GooeyView.circleSetOriginX - dragMove_x), 0, -(MainActivity_MainView.screenHeight / 2 - (MainBottom_GooeyView.circleWidth + dragMove_y)), 0, 100);
//                            MainActivity_MainView.mainBottomMenu_CenterRlView.setX(MainBottom_GooeyView.circleSetOriginX);
//                            MainActivity_MainView.mainBottomMenu_CenterRlView.setY(MainActivity_MainView.screenHeight/2 - MainBottom_GooeyView.circleWidth);

                            MainActivity_MainView.mainBottomMenu_CenterRlView.setY(0 + MainBottom_GooeyView.circleWidth+MainBottom_GooeyView.circleWidth/2);
                            MainActivity_MainView.mainBottomMenu_CenterRlView.invalidate();
                            Utils_Anim.drawableAlphaAnim(MainActivity_MainView.mainBottomMenu_CenterView_White, 1, 1, 0);

                            Utils_Anim.AlphaAnim(MainActivity_MainView.menuViewRl, MainActivity_MainView.menuViewRl.getAlpha(), 1, 200);
                            MainActivity_MainView.menuViewRl.setAlpha(1);
                            Utils_Anim.drawableAlphaAnim(MainActivity_MainView.mainBottomMenu_CenterView_White, MainActivity_MainView.mainBottomMenu_CenterView_White.getImageAlpha(), 1, 200);
                            Utils_Anim.drawableAlphaAnim(MainActivity_MainView.mainBottomMenu_CenterView_IndexColor, MainActivity_MainView.mainBottomMenu_CenterView_IndexColor.getImageAlpha(), 0, 200);
                            Utils_Anim.drawableAlphaAnim(MainActivity_MainView.mainBottomMenu_CenterView_Icn_whiteLine, MainActivity_MainView.mainBottomMenu_CenterView_Icn_whiteLine.getImageAlpha(), 0, 200);
                            Utils_Anim.drawableAlphaAnim(MainActivity_MainView.mainBottomMenu_CenterView_Icn_blackLine, MainActivity_MainView.mainBottomMenu_CenterView_Icn_blackLine.getImageAlpha(), 1, 200);

                            Utils_Anim.AlphaAnim(MainBottom_GooeyView.gooeyview_canvas, MainBottom_GooeyView.gooeyview_canvas.getAlpha(), 0, 400);

                            Utils_Anim.RotateAnim(MainActivity_MainView.mainBottomMenu_CenterView_Icn, 0, icnRotate_deg, 0.5f, 0.5f, 500);
                            MainActivity_MainView.mainBottomMenu_CenterRlView.setOnClickListener(this);
                            circle_originAnim(400);
                        }
                    } else {
                        /// position - bottom
                        if (drag){
                            dragENTER = false;
                            drag = false;
                            Utils_Anim.TransAnim(MainActivity_MainView.mainBottomMenu_CenterRlView, 0, 0, -(MainBottom_GooeyView.circleSetOriginY-dragMove_y), 0, 400);
//                            Utils_Anim.TransAnim(MainActivity_MainView.mainBottomMenu_CenterRlView, -(MainBottom_GooeyView.circleSetOriginX-dragMove_x), 0, -(MainBottom_GooeyView.circleSetOriginY-dragMove_y), 0, 500);
//                            MainActivity_MainView.mainBottomMenu_CenterRlView.setX(MainBottom_GooeyView.circleSetOriginX);

                            MainActivity_MainView.mainBottomMenu_CenterRlView.setY(MainBottom_GooeyView.circleSetOriginY);
                            MainActivity_MainView.mainBottomMenu_CenterRlView.invalidate();

                            Utils_Anim.AlphaAnim(MainActivity_MainView.menuViewRl, MainActivity_MainView.menuViewRl.getAlpha(), 0, 200);
                            Utils_Anim.drawableAlphaAnim(MainActivity_MainView.mainBottomMenu_CenterView_White, MainActivity_MainView.mainBottomMenu_CenterView_White.getImageAlpha(), 0, 200);
                            Utils_Anim.drawableAlphaAnim(MainActivity_MainView.mainBottomMenu_CenterView_IndexColor, MainActivity_MainView.mainBottomMenu_CenterView_IndexColor.getImageAlpha(), 1, 200);
                            Utils_Anim.drawableAlphaAnim(MainActivity_MainView.mainBottomMenu_CenterView_Icn_whiteLine, MainActivity_MainView.mainBottomMenu_CenterView_Icn_whiteLine.getImageAlpha(), 1, 200);
                            Utils_Anim.drawableAlphaAnim(MainActivity_MainView.mainBottomMenu_CenterView_Icn_blackLine, MainActivity_MainView.mainBottomMenu_CenterView_Icn_blackLine.getImageAlpha(), 0, 200);

                            MainActivity_MainView.mainBottomMenu_CenterView_White.setAlpha(0);

                            Utils_Anim.AlphaAnim(MainBottom_GooeyView.gooeyview_canvas, MainBottom_GooeyView.gooeyview_canvas.getAlpha(), 0, 200);

                            Utils_Anim.RotateAnim(MainActivity_MainView.mainBottomMenu_CenterView_Icn, MainActivity_MainView.mainBottomMenu_CenterView_Icn.getRotation(), 0, 0.5f, 0.5f, 300);
                            circle_originAnim(400);
                            Utils_Calc.delayMin(5, new Utils_Calc.DelayCallback() {
                                @Override
                                public void afterDelay() {
                                    MainBottom_GooeyView.gooeyview_canvas.setAlpha(0);
                                    mainBottomMenu_CenterCircle.setY(MainBottom_GooeyView.circleSetOriginY);
                                }
                            });
                        }
                    }
                } else {
                    /// position - top
                    drag = false;
                    dragENTER = true;
                    Utils_Anim.TransAnim(MainActivity_MainView.mainBottomMenu_CenterRlView, 0, 0, -(MainActivity_MainView.screenHeight/2 - (MainBottom_GooeyView.circleWidth + dragMove_y)), 0, 400);
                    MainActivity_MainView.mainBottomMenu_CenterRlView.setY(0 + MainBottom_GooeyView.circleWidth+MainBottom_GooeyView.circleWidth/2);
                    MainActivity_MainView.mainBottomMenu_CenterRlView.invalidate();
                    Utils_Anim.drawableAlphaAnim(MainActivity_MainView.mainBottomMenu_CenterView_White, 1, 1, 0);

                    Utils_Anim.AlphaAnim(MainActivity_MainView.menuViewRl, MainActivity_MainView.menuViewRl.getAlpha(), 1, 200);
                    MainActivity_MainView.menuViewRl.setAlpha(1);
                    Utils_Anim.drawableAlphaAnim(MainActivity_MainView.mainBottomMenu_CenterView_White, MainActivity_MainView.mainBottomMenu_CenterView_White.getImageAlpha(), 1, 200);
                    Utils_Anim.drawableAlphaAnim(MainActivity_MainView.mainBottomMenu_CenterView_IndexColor, MainActivity_MainView.mainBottomMenu_CenterView_IndexColor.getImageAlpha(), 0, 200);
                    Utils_Anim.drawableAlphaAnim(MainActivity_MainView.mainBottomMenu_CenterView_Icn_whiteLine, MainActivity_MainView.mainBottomMenu_CenterView_Icn_whiteLine.getImageAlpha(), 0, 200);
                    Utils_Anim.drawableAlphaAnim(MainActivity_MainView.mainBottomMenu_CenterView_Icn_blackLine, MainActivity_MainView.mainBottomMenu_CenterView_Icn_blackLine.getImageAlpha(), 1, 200);

                    Utils_Anim.RotateAnim(MainActivity_MainView.mainBottomMenu_CenterView_Icn, 0, icnRotate_deg, 0.5f, 0.5f, 500);
                    MainActivity_MainView.mainBottomMenu_CenterRlView.setOnClickListener(this);
                    circle_originAnim(400);
                    Utils_Calc.delayMin(5, new Utils_Calc.DelayCallback() {
                        @Override
                        public void afterDelay() {
                            mainBottomMenu_CenterCircle.setY(MainBottom_GooeyView.circleSetOriginY);
                        }
                    });
                }
                drag = false;
                break;
            default:
                break;
        }
        return true;
    }


    static void circle_originAnim(int duration) {
        float distance_calc = 0;
        if (!dragENTER){
            distance_calc = 0;
        } else {
            distance_calc = MainBottom_GooeyView.circleWidth;
        }
//        ValueAnimator animator_x = ValueAnimator.ofFloat(dragMove_x, MainBottom_GooeyView.circleSetOriginX);
        ValueAnimator animator_y = ValueAnimator.ofFloat(dragMove_y, MainBottom_GooeyView.circleSetOriginY+distance_calc);

//        animator_x.setDuration(duration/2);
//        animator_x.setInterpolator(new DecelerateInterpolator(Float.valueOf(String.valueOf(1.5))));
//        animator_x.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            public void onAnimationUpdate(ValueAnimator animation) {
//                float pos_origin_Xn = (float) animation.getAnimatedValue();
//                mainBottomMenu_CenterCircle.setX(pos_origin_Xn);
//            }
//        });
//        animator_x.start();

        animator_y.setDuration(duration);
        animator_y.setInterpolator(new DecelerateInterpolator(Float.valueOf(String.valueOf(1.5))));
        animator_y.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                float pos_origin_Yn = (float) animation.getAnimatedValue();
                mainBottomMenu_CenterCircle.setY(pos_origin_Yn);
                MainBottom_GooeyView.gooeyview_canvas.invalidate();
            }
        });
        animator_y.start();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mainBottomMenu_CenterRlView:
                if (dragENTER){
                    /// position - bottom
                    drag = false;
                    dragENTER = false;
                    Utils_Anim.TransAnim(MainActivity_MainView.mainBottomMenu_CenterRlView, 0, 0, -(MainBottom_GooeyView.circleSetOriginY-dragMove_y), 0, 400);
//
                    MainActivity_MainView.mainBottomMenu_CenterRlView.setX(MainBottom_GooeyView.circleSetOriginX);
                    MainActivity_MainView.mainBottomMenu_CenterRlView.setY(MainBottom_GooeyView.circleSetOriginY);
                    MainActivity_MainView.mainBottomMenu_CenterRlView.invalidate();
                    Utils_Anim.drawableAlphaAnim(MainActivity_MainView.mainBottomMenu_CenterView_White, 1, 0, 200);
                    Utils_Anim.drawableAlphaAnim(MainActivity_MainView.mainBottomMenu_CenterView_IndexColor, 0, 1, 200);
                    Utils_Anim.drawableAlphaAnim(MainActivity_MainView.mainBottomMenu_CenterView_Icn_whiteLine, 0, 1, 200);
                    Utils_Anim.drawableAlphaAnim(MainActivity_MainView.mainBottomMenu_CenterView_Icn_blackLine, 1, 0, 200);

                    Utils_Anim.drawableAlphaAnim(MainActivity_MainView.menuViewRl, 1, 0, 200);
                    Utils_Anim.RotateAnim(MainActivity_MainView.mainBottomMenu_CenterView_Icn, MainActivity_MainView.mainBottomMenu_CenterView_Icn.getRotation(), 0, 0.5f, 0.5f, 400);
                    MainActivity_MainView.menuViewRl.setAlpha(0);

                    MainActivity_MainView.mainBottomMenu_CenterRlView.setOnClickListener(null);
                    MainBottom_GooeyView.gooeyview_canvas.setAlpha(0);
                    circle_originAnim(0);
                    mainBottomMenu_CenterCircle.setY(MainBottom_GooeyView.circleSetOriginY);
                }
                break;
        }

    }
}