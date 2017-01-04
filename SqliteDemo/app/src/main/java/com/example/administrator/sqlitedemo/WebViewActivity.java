package com.example.administrator.sqlitedemo;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class WebViewActivity extends AppCompatActivity {

    private Button androidCallJs;
    private WebView webView;
    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        androidCallJs = ((Button) findViewById(R.id.androidCallJs));
        webView = ((WebView) findViewById(R.id.webView));
        androidCallJs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.loadUrl("javascript:androidCallJs");
                Toast.makeText(WebViewActivity.this,"调用javascript:androidCallJs",Toast.LENGTH_LONG).show();
            }
        });
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        //webView.addJavascriptInterface(new JsInterface(),"wv");
        webView.addJavascriptInterface(this,"wv");
        webView.loadUrl("file:///android_asset/js_webView.html");
    }

    @JavascriptInterface
        public  void sayHello(String msg){
            Log.v("fwl","JsInterface--"+msg);

    }
}
