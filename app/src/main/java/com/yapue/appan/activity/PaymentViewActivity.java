package com.yapue.appan.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.yapue.appan.R;
import com.yapue.appan.models.LoginDTO;
import com.yapue.appan.sharedprefrence.SharedPrefrence;
import com.yapue.appan.utils.Consts;
import com.yapue.appan.utils.ProjectUtils;

import java.util.Objects;

public class PaymentViewActivity extends AppCompatActivity {
    private LinearLayout llBackMC;
    private WebView payment_us;
    private String url,orderID;
    Context mContext;
    private SharedPrefrence prefrence;
    private LoginDTO loginDTO;
    private String TAG = PaymentViewActivity.class.getSimpleName();
    ProgressBar progressBar;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ProjectUtils.setStatusBarGradiant(PaymentViewActivity.this);
        setContentView(R.layout.activity_payment_view);
        progressBar = findViewById(R.id.progressBar);
        SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.webView1Refresher);
        mContext = PaymentViewActivity.this;

        prefrence = SharedPrefrence.getInstance(mContext);
        loginDTO = prefrence.getParentUser(Consts.LOGINDTO);
        payment_us = findViewById(R.id.payment_us);
        try {
            url = getIntent().getStringExtra(Consts.PAYAMENT_URL);
            orderID = getIntent().getStringExtra(Consts.ORDER_ID);
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                if (Objects.equals(bundle.getString("donateURL"), "") && Objects.equals(bundle.getString("donateID"), "")) {
                    url = bundle.getString("donateURL");
                    orderID = bundle.getString("donateID");
                }

            }
            WebSettings settings = payment_us.getSettings();
            settings.setJavaScriptEnabled(true);
            settings.setDomStorageEnabled(true);
            payment_us.loadUrl(url);
            payment_us.setWebViewClient(new SSLTolerentWebViewClient());
            payment_us.setWebChromeClient(new WebChromeClient() {
                @Override
                public void onProgressChanged(WebView view, int newProgress) {
                    if (newProgress >= 100) {
                        progressBar.setProgress(0);
                        swipeRefreshLayout.setRefreshing(false);
                    } else {
                        progressBar.setProgress(newProgress);
                    }
                    super.onProgressChanged(view, newProgress);
                }
            });
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    payment_us.loadUrl(url);
                    swipeRefreshLayout.setRefreshing(true);
                }
            });
        } catch (Exception error) {
            Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show();
        }


        llBackMC = findViewById(R.id.llBackMC);

        llBackMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                // goHome();
            }
        });


    }


    private class SSLTolerentWebViewClient extends WebViewClient {

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            super.onReceivedSslError(view, handler, error);
            // this will ignore the Ssl error and will go forward to your site
            handler.proceed();
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            Log.e("PageStarted", url);
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            Log.e("OverrideUrlLoading", url);
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            ProjectUtils.pauseProgressDialog();
            //Page load finished
            Log.e("PageFinished", url);
            if (url.contains(Consts.MERCADO_SUCCESS_URL)) {
                ProjectUtils.showToast(mContext, "Payment successful.");
                super.onPageFinished(view, Consts.MERCADO_SUCCESS_URL);
                goHome();

            } else if (url.equals(Consts.MERCADO_FAIL_URL)) {
                ProjectUtils.showToast(mContext, "Payment Unsuccessful.");
                super.onPageFinished(view, Consts.MERCADO_FAIL_URL);
                finish();

            } else {
                super.onPageFinished(view, url);
            }

        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            // TODO Auto-generated method stub
            super.onReceivedError(view, errorCode, description, failingUrl);
        }
    }

    public void goHome() {

        Intent intent = new Intent(mContext, BaseActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        // goHome();
        finish();

    }


}
