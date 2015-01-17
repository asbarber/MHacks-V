package com.example.mray.mhacksvglass.temp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;



public class VenmoWebViewActivity extends Activity {

    Context mContext;
    private WebView mVenmoWebView;
    String mUrl;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        mUrl = getIntent().getExtras().getString("url");

        Log.d("poop", mUrl);

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(mUrl));
        startActivity(intent);
//        mVenmoWebView = (WebView)findViewById(R.id.venmo_wv);
//        mVenmoWebView.addJavascriptInterface(new VenmoJavaScriptInterface(this), "VenmoAndroidSDK");
//        mVenmoWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
//        mVenmoWebView.getSettings().setJavaScriptEnabled(true);
//        mVenmoWebView.getSettings().setUserAgentString("venmo-android-2.0");
//        mVenmoWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
//        mVenmoWebView.loadUrl(mUrl);

    }
}