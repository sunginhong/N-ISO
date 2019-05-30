package com.example.n_iso;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Main_TabFragment_DetailView_Link extends AppCompatActivity implements View.OnClickListener {

    int width;
    int height;
    FrameLayout frag2_detail_linkbackbtn;
    TextView frag2_detail_linkttv;
    WebView frag2_detail_linkWv;

    private AppBarLayout frag2_detail_linkappbar;
    private boolean appbarVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_tab_fragment_detail_linkview);

        frag2_detail_linkbackbtn = (FrameLayout)findViewById(R.id.frag2_detail_linkbackbtn);
        frag2_detail_linkbackbtn.setOnClickListener(this);

        frag2_detail_linkappbar = (AppBarLayout)findViewById(R.id.frag2_detail_linkappbar);

        Intent intent = getIntent();
        String url = intent.getStringExtra("URL");
        String title = intent.getStringExtra("TITLE");

//        Log.d("sssssssssssssssssssss"+title, "sssssss"+url);

        frag2_detail_linkWv = (WebView)findViewById(R.id.frag2_detail_linkWv);

        frag2_detail_linkWv.getSettings().setJavaScriptEnabled(true);
        frag2_detail_linkWv.getSettings().setLoadWithOverviewMode(true);
        frag2_detail_linkWv.getSettings().setUseWideViewPort(true);

        frag2_detail_linkWv.getSettings().setSupportZoom(true);
        frag2_detail_linkWv.getSettings().setBuiltInZoomControls(true);
        frag2_detail_linkWv.getSettings().setDisplayZoomControls(false);

        frag2_detail_linkWv.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        frag2_detail_linkWv.setScrollbarFadingEnabled(false);

        frag2_detail_linkWv.loadUrl(url);
        frag2_detail_linkWv.setWebViewClient(new Main_TabFragment_DetailView_Link.WebViewClientClass());
        frag2_detail_linkWv.setWebChromeClient(new FullscreenableChromeClient(this));

        settingWebview(frag2_detail_linkWv);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;
        frag2_detail_linkttv = (TextView)findViewById(R.id.frag2_detail_linkttv);
        frag2_detail_linkttv.setText(title);

    }

    private class WebViewClientClass extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    private void settingWebview(WebView webView){
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.getSettings().setAppCacheEnabled(false);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setUserAgentString("app");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        frag2_detail_linkWv.stopLoading();
        ActivityCompat.finishAfterTransition(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onClick(View view) {
        if (!appbarVisible){
            frag2_detail_linkWv.stopLoading();
            ActivityCompat.finishAfterTransition(this);
        }
    }
}