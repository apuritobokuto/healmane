package com.apuritobokuto.healmane;



import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class cl_information extends AppCompatActivity {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        //レイアウトで指定したWebViewのIDを指定する。
        WebView myWebView = (WebView)findViewById(R.id.webView1);

        //リンクをタップしたときに標準ブラウザを起動させない
        myWebView.setWebViewClient(new WebViewClient());

        //最初にgoogleのページを表示する。
        myWebView.loadUrl("http://10.0.2.2/apuritobokuto/androidphp/pdfjs-1/web/viewer.html?file=../../../image/info.pdf");

        //jacascriptを許可する
        myWebView.getSettings().setJavaScriptEnabled(true);
    }




}