package com.example.n_iso;

import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityCompat;
import android.view.Display;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Main_TabFragment3_DetailView extends Activity implements View.OnClickListener {

    private Context context;
    AppBarLayout frag3_detail_appbar;
    FrameLayout frag3_detail_backbtn;
    TextView frag3_detail_ttv;
    WebView frag3_detailWv;

    private int HIDE_THRESHOLD = 20;
    private int scrolledDistance = 0;
    private boolean appbarVisible = false;
    private String scrollDirection = "none";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_tab_fragment_3_detailview);

        frag3_detail_backbtn = (FrameLayout)findViewById(R.id.frag3_detail_backbtn);
        frag3_detail_backbtn.setOnClickListener(this);

        frag3_detail_appbar = (AppBarLayout)findViewById(R.id.frag3_detail_appbar);

        Intent intent = getIntent();
        final String url = intent.getStringExtra("URL");
        final String title = intent.getStringExtra("TITLE");

        frag3_detailWv = (WebView)findViewById(R.id.frag3_detailWv);
        frag3_detailWv.setWebViewClient(new Main_TabFragment3_DetailView.WebViewClientClass());
        frag3_detailWv.setWebChromeClient(new FullscreenableChromeClient(this));

        frag3_detailWv.getSettings().setLoadsImagesAutomatically(true);
        frag3_detailWv.getSettings().setUseWideViewPort(true);
        frag3_detailWv.getSettings().setSupportZoom(false);
        frag3_detailWv.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        frag3_detailWv.getSettings().setAppCacheEnabled(false);
        frag3_detailWv.getSettings().setDomStorageEnabled(true);

        frag3_detailWv.getSettings().setJavaScriptEnabled(true);
        frag3_detailWv.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        frag3_detailWv.getSettings().setAllowFileAccess(true);
        frag3_detailWv.getSettings().setAllowFileAccessFromFileURLs(true);
        frag3_detailWv.getSettings().setAllowUniversalAccessFromFileURLs(true);

        frag3_detailWv.getSettings().setUseWideViewPort(true);
        frag3_detailWv.getSettings().setLoadWithOverviewMode(true);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Code for WebView goes here
//                frag3_detailWv.clearCache(true);
//                frag3_detailWv.clearHistory();
//                clearCookies(context);
//                frag3_detailWv.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
                frag3_detailWv.loadUrl(url);
            }
        });

        frag3_detail_ttv = (TextView)findViewById(R.id.frag3_detail_ttv);
        frag3_detail_ttv.setText(title);

        frag3_detailWv.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Intent intent = new Intent(view.getContext(), Main_TabFragment_DetailView_Link.class);
                intent.putExtra("URL" , url);
                intent.putExtra("TITLE" , title);
                view.getContext().startActivity(intent);
                return true;
            }
        });

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        frag3_detailWv.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                scrolledDistance = scrollY;
                if ((scrollY > oldScrollY) && (scrollY - oldScrollY) > 10) { scrollDirection = "UP"; }
                else if((scrollY < oldScrollY) && (oldScrollY - scrollY) > 10) { scrollDirection = "DOWN"; }

                if (scrollDirection == "DOWN" && scrolledDistance < HIDE_THRESHOLD && appbarVisible) {
                    ScrollHederAnim.HeaderHide(frag3_detail_appbar, -frag3_detail_appbar.getHeight(), 0, 300);
                    appbarVisible = false;
                    scrolledDistance = 0;
                } else if (scrollDirection == "UP" && scrolledDistance > HIDE_THRESHOLD && !appbarVisible) {
                    ScrollHederAnim.HeaderShow(frag3_detail_appbar, 0, -frag3_detail_appbar.getHeight(), 300);
                    appbarVisible = true;
                    scrolledDistance = 0;
                }
                if (scrollDirection == "DOWN" && scrolledDistance > HIDE_THRESHOLD && appbarVisible){
                    ScrollHederAnim.HeaderHide(frag3_detail_appbar, -frag3_detail_appbar.getHeight(), 0, 300);
                    appbarVisible = false;
                    scrolledDistance = 0;
                }
            }
        });
    }

    private class WebViewClientClass extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @SuppressWarnings("deprecation")
    public static void clearCookies(Context context)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            CookieManager.getInstance().removeAllCookies(null);
            CookieManager.getInstance().flush();
        } else
        {
            CookieSyncManager cookieSyncMngr=CookieSyncManager.createInstance(context);
            cookieSyncMngr.startSync();
            CookieManager cookieManager=CookieManager.getInstance();
            cookieManager.removeAllCookie();
            cookieManager.removeSessionCookie();
            cookieSyncMngr.stopSync();
            cookieSyncMngr.sync();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        ActivityCompat.finishAfterTransition(this);
        outAnim();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        finish();
    }

    private void outAnim(){
        this.overridePendingTransition(R.anim.activity_slide_in_100p_backward, R.anim.activity_slide_out_50p_backward);
    }

    @Override
    public void onClick(View view) {
        ActivityCompat.finishAfterTransition(this);
        outAnim();
    }
}