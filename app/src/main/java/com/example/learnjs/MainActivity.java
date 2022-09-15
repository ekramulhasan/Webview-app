package com.example.learnjs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    LinearLayout webviewint,noint;
    Button retry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.setweb);
        webviewint = findViewById(R.id.webviewint);
        noint = findViewById(R.id.noint);
        retry = findViewById(R.id.retry);
        checkConnection();


        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkConnection();
            }
        });


        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.loadUrl("https://js.zonayed.me/");


        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                webviewint.setVisibility(View.GONE);
                noint.setVisibility(View.VISIBLE);
                super.onReceivedError(view, request, error);
            }
        });

    }



    @Override
    public void onBackPressed() {
       if (webView.canGoBack())
           webView.goBack();
       else
           finish();
    }

    private void checkConnection(){

        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();

        if (info != null) {
            if (info.isConnected()){
                webviewint.setVisibility(View.VISIBLE);
                noint.setVisibility(View.GONE);
                webView.reload();
            }
            else{
                webviewint.setVisibility(View.GONE);
                noint.setVisibility(View.VISIBLE);

            }
        }
        else {
            webviewint.setVisibility(View.GONE);
            noint.setVisibility(View.VISIBLE);
        }
    }
}