package com.example.n_iso;

import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Main_TabFragment2_DetailView extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    AppBarLayout frag2_detail_appbar;
    FrameLayout frag2_detail_backbtn;
    TextView frag2_detail_ttv;
    WebView frag2_detailWv;

    private int HIDE_THRESHOLD = 20;
    private int scrolledDistance = 0;
    private boolean appbarVisible = false;
    private String scrollDirection = "none";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_tab_fragment_2_detailview);

        frag2_detail_backbtn = (FrameLayout)findViewById(R.id.frag2_detail_backbtn);
        frag2_detail_backbtn.setOnClickListener(this);

        frag2_detail_appbar = (AppBarLayout)findViewById(R.id.frag2_detail_appbar);

        Intent intent = getIntent();
        String url = intent.getStringExtra("URL");
        final String title = intent.getStringExtra("TITLE");

        frag2_detailWv = (WebView)findViewById(R.id.frag2_detailWv);
        frag2_detailWv.loadUrl(url);
        frag2_detailWv.setWebViewClient(new Main_TabFragment2_DetailView.WebViewClientClass());
        frag2_detailWv.setWebChromeClient(new FullscreenableChromeClient(this));

        frag2_detail_ttv = (TextView)findViewById(R.id.frag2_detail_ttv);
        frag2_detail_ttv.setText(title);

        frag2_detailWv.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Intent intent = new Intent(view.getContext(), Main_TabFragment_DetailView_Link.class);
                intent.putExtra("URL" , url);
                intent.putExtra("TITLE" , title);
                view.getContext().startActivity(intent);
                return true;
            }
        });

        frag2_detailWv.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                scrolledDistance = scrollY;
                if ((scrollY > oldScrollY) && (scrollY - oldScrollY) > 10) { scrollDirection = "UP"; }
                else if((scrollY < oldScrollY) && (oldScrollY - scrollY) > 10) { scrollDirection = "DOWN"; }

                if (scrollDirection == "DOWN" && scrolledDistance < HIDE_THRESHOLD && appbarVisible) {
                    ScrollHederAnim.HeaderHide(frag2_detail_appbar, -frag2_detail_appbar.getHeight(), 0, 300);
                    appbarVisible = false;
                    scrolledDistance = 0;
                } else if (scrollDirection == "UP" && scrolledDistance > HIDE_THRESHOLD && !appbarVisible) {
                    ScrollHederAnim.HeaderShow(frag2_detail_appbar, 0, -frag2_detail_appbar.getHeight(), 300);
                    appbarVisible = true;
                    scrolledDistance = 0;
                }
                if (scrollDirection == "DOWN" && scrolledDistance > HIDE_THRESHOLD && appbarVisible){
                    ScrollHederAnim.HeaderHide(frag2_detail_appbar, -frag2_detail_appbar.getHeight(), 0, 300);
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
        ActivityCompat.finishAfterTransition(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        finish();
    }

    @Override
    public void onClick(View view) {
        ActivityCompat.finishAfterTransition(this);
    }
}