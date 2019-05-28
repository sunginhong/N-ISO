package com.example.n_iso;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import java.util.Timer;
import java.util.TimerTask;

import com.example.n_iso.Utils_Folder.Utils_Calc;

public class MainBottom_GooeyView extends View {

    Context context;

    static float dragStart_point_x = 0;
    static float dragStart_point_y = 0;
    static float dragMove_point_x = 0;
    static float dragMove_point_y = 0;

    static Paint paint;
    static Path path;
    static MainBottom_GooeyView gooeyview_canvas;
    static int mainBottom_bgView_HeightSet = 0;

    static ImageView circle;
    static float circleWidth = 0;
    static float circleRate = 0;
    static float circleCenterX = 0;
    static float circleCenterY = 0;
    static float bottomIcnY = 0;
    static float calcCircleY = 0;
    static float calcJointX_Left = 0;
    static float calcJointX_Right = 0;
    static float circleSetOriginX = 0;
    static float circleSetOriginY = 0;
    static float float_calcRate_alpha_in = 0;
    static float float_calcRate_alpha_out = 1;
    static float float_calcRate_0 = 0;
    static float float_calcRate_1 = 0;
    static float float_calcRate_2 = 0;
    static float float_calcRate_3 = 0;
    static float float_calcRate_4 = 0;
    static float calcRateN_ALPHA_RATE = 0;
    static float calcRateN_ALPHA_RATE2 = 0;
    static float calcRateN_0_RATE = 0;
    static float calcRateN_1_RATE = 1;
    static float calcRateN_1_RATE_MAX = 4;
    static float calcRateN_2_RATE = 1.25f;
    static float calcRateN_3_RATE = 600;
    static float calcRateN_4_RATE = 16;
    static float calcRateN_4_RATE_MIN = 6;


    public MainBottom_GooeyView(Context context) {
        super(context);
        init();
    }

    public MainBottom_GooeyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MainBottom_GooeyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init(){
        paint = new Paint();

        paint.setStyle(Paint.Style.STROKE);
        gooeyview_canvas = (MainBottom_GooeyView) findViewById(R.id.gooeyview_canvas);
        new Timer().schedule(
                new TimerTask(){
                    @Override
                    public void run(){
                        circleSetOriginX = circleCenterX - circleWidth/2;
                        circleSetOriginY = circleCenterY - circleWidth/2;
                        mainBottom_bgView_HeightSet = MainActivity_MainView.screenHeight - MainBottom_CircleView.mainBottom_bgView_Height;

                    }
                }, 1000);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        // TODO Auto-generated method stub
        super.onDraw(canvas);
        circle = MainBottom_CircleView.mainBottomMenu_CenterCircle;
        View bottom_bgView = MainActivity_MainView.mainBottom_bgView;
        circleWidth = circle.getWidth();
        circleRate = circleWidth/8;
        circleCenterX = circle.getX()+circleWidth/2;
        circleCenterY = circle.getY()+circleWidth/2;

        pathDraw();

        canvas.drawPath(path, paint);
    }

    private Runnable r = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };

    static void pathDraw(){
        path = new Path();
        paint.setAntiAlias(true);
        paint.setColor(Color.rgb(95, 206, 85));
        paint.setStrokeWidth(0);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.rgb(255, 255, 255));
        paint.setAlpha(0xFF); // optional
        path.reset();

        bottomIcnY = MainActivity_MainView.screenHeight - circleWidth;
        calcCircleY = (bottomIcnY-circleCenterY)/1.75f;
        float calcCustomArc = calcCircleY/2f;
        float roundCircleLeft = circle.getY();
        float scrollRateN = 2.5f;

        //calc 계산_알파
        calcRateN_ALPHA_RATE = 1;
//        Log.d("sssssssssss"+circle.getY(), "sssssss"+circleSetOriginY);
        float calcRateN_alpha = Utils_Calc.ModulateCalc(circle.getY(), circleSetOriginY-circleWidth/2, MainActivity_MainView.screenHeight/2 - MainBottom_GooeyView.circleWidth, 0, calcRateN_ALPHA_RATE);
        String str_calcRate_alpha = String.format("%.2f", calcRateN_alpha);
        float_calcRate_alpha_in = Float.parseFloat(str_calcRate_alpha);
        if (float_calcRate_alpha_in <= 0){ float_calcRate_alpha_in = 0; }
        if (float_calcRate_alpha_in >= calcRateN_ALPHA_RATE){ float_calcRate_alpha_in = calcRateN_ALPHA_RATE; }

//        float_calcRate_alpha_out
        calcRateN_ALPHA_RATE2 = 1;
        float calcRateN_alpha2 = Utils_Calc.ModulateCalc(circle.getY(), circleSetOriginY-circleWidth/2, MainActivity_MainView.screenHeight/2 - MainBottom_GooeyView.circleWidth, calcRateN_ALPHA_RATE2, 0);
        String str_calcRate_alpha2 = String.format("%.2f", calcRateN_alpha2);
        float_calcRate_alpha_out = Float.parseFloat(str_calcRate_alpha2);
        if (float_calcRate_alpha_out <= 0){ float_calcRate_alpha_out = 0; }
        if (float_calcRate_alpha_out >= calcRateN_ALPHA_RATE2){ float_calcRate_alpha_out = calcRateN_ALPHA_RATE2; }

        if (!MainBottom_CircleView.drag){
            float_calcRate_alpha_in = 0;
            float_calcRate_alpha_out = 1;
        }

        calcRateN_0_RATE = MainBottom_CircleView.mainBottom_bgView_Height + circleWidth/2;
        float calcRateN_0 = Utils_Calc.ModulateCalc(circle.getY(), circleSetOriginY, MainActivity_MainView.screenHeight/scrollRateN, calcRateN_0_RATE, 0);
        String str_calcRate_0 = String.format("%.2f", calcRateN_0);
        float_calcRate_0 = Float.parseFloat(str_calcRate_0);
        if (float_calcRate_0 <= 0){ float_calcRate_0 = 0; }

        //calc 계산_1
        float calcRateN_1 = Utils_Calc.ModulateCalc(circle.getY(), MainActivity_MainView.screenHeight, MainActivity_MainView.screenHeight/scrollRateN, calcRateN_1_RATE, calcRateN_1_RATE_MAX);
        String str_calcRate_1 = String.format("%.2f", calcRateN_1);
        float_calcRate_1 = Float.parseFloat(str_calcRate_1);
        if (float_calcRate_1 >= calcRateN_1_RATE_MAX){ float_calcRate_1 = calcRateN_1_RATE_MAX; }

        //calc 계산_2
        float calcRateN_2 = Utils_Calc.ModulateCalc(circle.getY(), circleSetOriginY, MainActivity_MainView.screenHeight/scrollRateN, circleWidth/calcRateN_2_RATE, 0);
        String str_calcRate_2 = String.format("%.2f", calcRateN_2);
        float_calcRate_2 = Float.parseFloat(str_calcRate_2);
        if (float_calcRate_2 >= circleWidth/calcRateN_2_RATE){ float_calcRate_2 = circleWidth/calcRateN_2_RATE; } else if(float_calcRate_2 < 0){ float_calcRate_2 = 0; }

        //calc 계산_3
        float calcRateN_3 = Utils_Calc.ModulateCalc(circle.getY(), circleSetOriginY, MainActivity_MainView.screenHeight/scrollRateN, calcRateN_3_RATE, 0);
        String str_calcRate_3 = String.format("%.2f", calcRateN_3);
        float_calcRate_3 = Float.parseFloat(str_calcRate_3);

        float calcRateN_4 = Utils_Calc.ModulateCalc(circle.getY(), circleSetOriginY, MainActivity_MainView.screenHeight/scrollRateN, calcRateN_4_RATE, calcRateN_4_RATE_MIN);
        String str_calcRate_4 = String.format("%.2f", calcRateN_4);
        float_calcRate_4 = Float.parseFloat(str_calcRate_4);
        if (float_calcRate_4 <= calcRateN_4_RATE_MIN){ float_calcRate_4 = calcRateN_4_RATE_MIN; }

        if (!MainBottom_CircleView.drag){
            function_SetFloat_calcRate_reset();
        }

        calcJointX_Left = circleCenterX - MainActivity_MainView.screenWidth/float_calcRate_1 + calcCircleY;
        calcJointX_Right = circleCenterX + MainActivity_MainView.screenWidth/float_calcRate_1 - calcCircleY;

        float calcCurveLeft = circleCenterX - circleWidth/2;
        float calcCurveRight = circleCenterX + circleWidth/2;

        if (MainBottom_CircleView.drag) {
            // path start
            path.moveTo(0, bottomIcnY);

            // path bridge
            path.cubicTo(calcJointX_Left - float_calcRate_3, bottomIcnY, calcCurveLeft, circleCenterY + calcCustomArc + float_calcRate_0, circleCenterX - circleWidth / 2, circleCenterY - float_calcRate_2 / 8);

            path.cubicTo(circleCenterX - circleWidth / 2, circleCenterY, circleCenterX - circleWidth / 2, roundCircleLeft, circleCenterX + 8, roundCircleLeft);

            // path center
            path.cubicTo(circleCenterX, roundCircleLeft, circleCenterX + circleWidth / 2 - (float_calcRate_4*1), roundCircleLeft, circleCenterX + circleWidth / 2, circleCenterY - float_calcRate_2 / 8);

            path.cubicTo(calcCurveRight, circleCenterY + calcCustomArc + float_calcRate_0, calcJointX_Right + float_calcRate_3, bottomIcnY, MainActivity_MainView.screenWidth, bottomIcnY);
            // path end
            path.lineTo(MainActivity_MainView.screenWidth, MainActivity_MainView.screenHeight);
            path.lineTo(0, MainActivity_MainView.screenHeight);
            path.close();
        } else {
            float maxY = MainActivity_MainView.screenHeight;
            float maxX = MainActivity_MainView.screenWidth;
            float circleCenterY_reCalc = circleCenterY+circleWidth;
            float circleCenterY_reHeightCalc = bottomIcnY+circleWidth;

            path.moveTo(0, bottomIcnY);

            // path bridgex
            path.cubicTo(calcJointX_Left - float_calcRate_3, bottomIcnY, calcCurveLeft, circleCenterY + calcCustomArc + float_calcRate_0, circleCenterX - circleWidth / 2, circleCenterY+circleWidth/2);

            path.cubicTo(circleCenterX - circleWidth / 2, circleCenterY+circleWidth/2, circleCenterX - circleWidth / 2, roundCircleLeft+circleWidth/2, circleCenterX + 8, roundCircleLeft+circleWidth/2);

            // path center
            path.cubicTo(circleCenterX, roundCircleLeft+circleWidth/2, circleCenterX + circleWidth / 2 - (float_calcRate_4*0), roundCircleLeft+circleWidth/2, circleCenterX + circleWidth / 2, circleCenterY+circleWidth/2);

            path.cubicTo(calcCurveRight, circleCenterY + calcCustomArc + float_calcRate_0, calcJointX_Right + float_calcRate_3, bottomIcnY, MainActivity_MainView.screenWidth, bottomIcnY);
            // path end
            path.lineTo(MainActivity_MainView.screenWidth, MainActivity_MainView.screenHeight);
            path.lineTo(0, MainActivity_MainView.screenHeight);
            path.close();
        }

    }

    static void function_SetFloat_calcRate_reset() {
        float_calcRate_0 = calcRateN_0_RATE;
        float_calcRate_1 = calcRateN_1_RATE;
        float_calcRate_2 = circleWidth/calcRateN_2_RATE;
        float_calcRate_3 = calcRateN_3_RATE;
        float_calcRate_4 = calcRateN_4_RATE;
    }
}