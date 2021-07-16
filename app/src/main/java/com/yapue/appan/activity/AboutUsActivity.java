package com.yapue.appan.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.yapue.appan.NavigationDrawerSettings;
import com.yapue.appan.R;
import com.yapue.appan.utils.ProjectUtils;

public class AboutUsActivity extends AppCompatActivity {
    private Context mContext;
    private LinearLayout llBackAboutUs;
    private WebView about_us;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ProjectUtils.setStatusBarGradiant(AboutUsActivity.this);
        setContentView(R.layout.activity_about_us);
        new NavigationDrawerSettings(this, R.id.nav_drawer_activity_about_us);
        initView();
    }

    public void initView() {

        about_us = findViewById(R.id.about_us);

        llBackAboutUs = findViewById(R.id.llBackAboutUs);

        about_us.setWebViewClient(new MyBrowser());
        about_us.getSettings().setLoadsImagesAutomatically(true);
        about_us.getSettings().setJavaScriptEnabled(true);
        about_us.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        //about_us.loadUrl("https://pet-fiesta.com");
        about_us.loadUrl("https://www.google.com");

        llBackAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}
