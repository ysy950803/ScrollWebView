package com.ysy.scrollwebview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private FrameLayout mScrollContainer;
    private MyScrollView mScrollView;
    private MyWebView mWebView;
    private View mBottomListLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initWeb();
    }

    private void initView() {
        mScrollContainer = findViewById(R.id.scroll_container);
        mScrollView = findViewById(R.id.scroll_view);
        mWebView = findViewById(R.id.web_view);
        mBottomListLayout = findViewById(R.id.bottom_list_layout);

        mScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView nestedScrollView, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                mScrollView.setIsRecLayoutShow(UiUtil.isViewShowReally(mBottomListLayout));
            }
        });
    }

    private void initWeb() {
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
            }

            @Override
            public void onPageFinished(WebView view, String url) {
            }

            @Override
            public void onLoadResource(WebView view, String url) {
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
                handler.proceed();
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }
        });

        // 监听网页是否滑到底
        mWebView.setOnOverScrollListener(new MyWebView.OnOverScrollListener() {
            @Override
            public void onOverScrolled(MyWebView v, boolean onBottom) {
                mScrollView.setIsWebViewOnBottom(onBottom);
            }
        });
        mWebView.post(new Runnable() {
            @Override
            public void run() {
                if (mWebView != null) {
                    // WebView设置固定高度，避免各种嵌套问题
                    ViewGroup.LayoutParams lp = mWebView.getLayoutParams();
                    lp.height = mScrollContainer.getHeight();
                    mWebView.setLayoutParams(lp);
                }
            }
        });

        mWebView.loadUrl("http://test.yjzsapp.com/app/wap/article.htm?articleId=5ed57a23d63341ed87adb0fd81e6c47f");
    }
}
