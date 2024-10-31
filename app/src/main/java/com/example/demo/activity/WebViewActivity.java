package com.example.demo.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ClientCertRequest;
import android.webkit.CookieManager;
import android.webkit.HttpAuthHandler;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.SafeBrowsingResponse;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebHistoryItem;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.demo.R;

import java.util.HashMap;

public class WebViewActivity extends AppCompatActivity {

    private WebView mWebView;
    public class MyJSEvent{
        @JavascriptInterface
        public void toast(String toastString){
            Toast.makeText(WebViewActivity.this, toastString, Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        mWebView=findViewById(R.id.webView);
        mWebView.loadUrl("https://www.baidu.com");
        HashMap<String,String> map=new HashMap<>();
        map.put("token","******");
        map.put("my_header","header");
        mWebView.loadUrl("https://www.baidu.com",map);
//        mWebView.loadUrl("file:///android_asset/tanchishe.html");
        //debug 访问chrome://inspect/
//        mWebView.setWebContentsDebuggingEnabled(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        //JS 调用原生app
        mWebView.addJavascriptInterface(new MyJSEvent(),"myJSEvent");
        //app 调用JS
//        mWebView.loadUrl("javascript:javaCallJS()");

        //使用cookie
        CookieManager cookieManager=CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.setCookie("domin","cookie");
//        cookieManager.removeAllCookies();


        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //是不是重新加载 （拦截见面）
//                view.loadUrl("https://zhihu.com");
                if(url.contains("404")){
                    //进行登录操作
                }
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                //url 替换
                if(url.contains("logo.img")){

                }
                super.onLoadResource(view, url);
            }

            @Override
            public void onPageCommitVisible(WebView view, String url) {
                super.onPageCommitVisible(view, url);
            }

            @Nullable
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                //request hybrid离线网页
                return super.shouldInterceptRequest(view, request);
            }
        });
        mWebView.setWebChromeClient(new MyWebChromeClient());

    }
    public class MyWebChromeClient extends WebChromeClient{
        public MyWebChromeClient() {
            super();
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }

        @Override
        public void onHideCustomView() {
            super.onHideCustomView();
        }

        @Override
        public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
            return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
        }

        @Override
        public void onRequestFocus(WebView view) {
            super.onRequestFocus(view);
        }
    }

    @Override
    public void onBackPressed() {
        if(mWebView!=null&&mWebView.canGoBack()){
            //历史记录
            WebBackForwardList webBackForwardList=mWebView.copyBackForwardList();
            WebHistoryItem webHistoryItem=webBackForwardList.getItemAtIndex(0);
            String historyUrlrl=webHistoryItem.getUrl();
            //前进、后退
            mWebView.goBack();
//            mWebView.goForward();
//            mWebView.goBackOrForward(+1);
//            mWebView.reload();
        }else{
            super.onBackPressed();
        }
    }
}