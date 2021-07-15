package com.yapue.appan.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yapue.appan.R;
import com.yapue.appan.models.LoginDTO;
import com.yapue.appan.models.MakeOrderDTO;
import com.yapue.appan.sharedprefrence.SharedPrefrence;
import com.yapue.appan.utils.Consts;
import com.yapue.appan.utils.ProjectUtils;

import java.util.HashMap;

public class PaymentViewActivity extends AppCompatActivity {
    private LinearLayout llBackMC;
    private WebView payment_us;
    private String url,orderID;
    Context mContext;
    private SharedPrefrence prefrence;
    private LoginDTO loginDTO;
    private String TAG = PaymentViewActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ProjectUtils.setStatusBarGradiant(PaymentViewActivity.this);
        setContentView(R.layout.activity_payment_view);
        mContext = PaymentViewActivity.this;

        prefrence = SharedPrefrence.getInstance(mContext);
        loginDTO = prefrence.getParentUser(Consts.LOGINDTO);
        payment_us = (WebView) findViewById(R.id.payment_us);
        url = getIntent().getStringExtra(Consts.PAYAMENT_URL);
        orderID = getIntent().getStringExtra(Consts.ORDER_ID);


        llBackMC = (LinearLayout) findViewById(R.id.llBackMC);

        llBackMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                // goHome();
            }
        });


        WebSettings settings = payment_us.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        payment_us.loadUrl(url);
        payment_us.setWebViewClient(new SSLTolerentWebViewClient());
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
