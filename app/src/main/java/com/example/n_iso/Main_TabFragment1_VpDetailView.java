package com.example.n_iso;

import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityCompat;
import android.text.InputFilter;
import android.view.Display;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Main_TabFragment1_VpDetailView extends Activity implements View.OnClickListener {

    private Context context;
    AppBarLayout frag1_detail_vp_appbar;
    FrameLayout frag1_detail_vp__backbtn;
    TextView frag1_detail_detail_ttv;
    WebView frag1_detail_vp_Wv;

    private int HIDE_THRESHOLD = 20;
    private int scrolledDistance = 0;
    private boolean appbarVisible = false;
    private String scrollDirection = "none";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_tab_fragment_1_vp_detailview);

        frag1_detail_vp__backbtn = (FrameLayout)findViewById(R.id.frag1_detail_vp__backbtn);
        frag1_detail_vp__backbtn.setOnClickListener(this);

        frag1_detail_vp_appbar = (AppBarLayout)findViewById(R.id.frag1_detail_vp_appbar);

        Intent intent = getIntent();
        final String url = intent.getStringExtra("URL");
        final String title = intent.getStringExtra("TITLE");

        frag1_detail_vp_Wv = (WebView)findViewById(R.id.frag1_detail_vp_Wv);
        frag1_detail_vp_Wv.setWebViewClient(new Main_TabFragment1_VpDetailView.WebViewClientClass());
        frag1_detail_vp_Wv.setWebChromeClient(new FullscreenableChromeClient(this));
//
        frag1_detail_vp_Wv.getSettings().setLoadsImagesAutomatically(true);
        frag1_detail_vp_Wv.getSettings().setUseWideViewPort(true);
        frag1_detail_vp_Wv.getSettings().setSupportZoom(false);
        frag1_detail_vp_Wv.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        frag1_detail_vp_Wv.getSettings().setAppCacheEnabled(false);
        frag1_detail_vp_Wv.getSettings().setDomStorageEnabled(true);

        frag1_detail_vp_Wv.getSettings().setJavaScriptEnabled(true);
        frag1_detail_vp_Wv.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        frag1_detail_vp_Wv.getSettings().setAllowFileAccess(true);
        frag1_detail_vp_Wv.getSettings().setAllowFileAccessFromFileURLs(true);
        frag1_detail_vp_Wv.getSettings().setAllowUniversalAccessFromFileURLs(true);

        frag1_detail_vp_Wv.getSettings().setUseWideViewPort(true);
        frag1_detail_vp_Wv.getSettings().setLoadWithOverviewMode(true);
//
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Code for WebView goes here
//                frag1_detail_vp_Wv.clearCache(true);
//                frag1_detail_vp_Wv.clearHistory();
//                clearCookies(context);
//                frag1_detail_vp_Wv.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
                frag1_detail_vp_Wv.loadUrl(url);
            }
        });

        frag1_detail_detail_ttv = (TextView)findViewById(R.id.frag1_detail_detail_ttv);
        frag1_detail_detail_ttv.setText(title);

//
//        frag1_detail_vp_Wv.setWebViewClient(new WebViewClient() {
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                Intent intent = new Intent(view.getContext(), Main_TabFragment_DetailView_Link.class);
//                intent.putExtra("URL" , url);
//                intent.putExtra("TITLE" , title);
//                view.getContext().startActivity(intent);
//                return true;
//            }
//        });

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

//        frag1_detail_vp_Wv.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                scrolledDistance = scrollY;
//                if ((scrollY > oldScrollY) && (scrollY - oldScrollY) > 10) { scrollDirection = "UP"; }
//                else if((scrollY < oldScrollY) && (oldScrollY - scrollY) > 10) { scrollDirection = "DOWN"; }
//
//                if (scrollDirection == "DOWN" && scrolledDistance < HIDE_THRESHOLD && appbarVisible) {
//                    ScrollHederAnim.HeaderHide(frag1_detail_vp_appbar, -frag1_detail_vp_appbar.getHeight(), 0, 300);
//                    appbarVisible = false;
//                    scrolledDistance = 0;
//                } else if (scrollDirection == "UP" && scrolledDistance > HIDE_THRESHOLD && !appbarVisible) {
//                    ScrollHederAnim.HeaderShow(frag1_detail_vp_appbar, 0, -frag1_detail_vp_appbar.getHeight(), 300);
//                    appbarVisible = true;
//                    scrolledDistance = 0;
//                }
//                if (scrollDirection == "DOWN" && scrolledDistance > HIDE_THRESHOLD && appbarVisible){
//                    ScrollHederAnim.HeaderHide(frag1_detail_vp_appbar, -frag1_detail_vp_appbar.getHeight(), 0, 300);
//                    appbarVisible = false;
//                    scrolledDistance = 0;
//                }
//            }
//        });
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