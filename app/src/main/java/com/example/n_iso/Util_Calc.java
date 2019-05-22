package com.example.n_iso;

import android.content.Context;
import android.app.Activity;
import android.graphics.Point;
import android.view.Display;

public class Util_Calc {
    protected Context context;


    public Util_Calc(){

    }

    static public Point getScreenSize(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return  size;
    }
}
