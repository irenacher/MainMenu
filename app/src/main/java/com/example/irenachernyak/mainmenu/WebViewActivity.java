package com.example.irenachernyak.mainmenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import java.io.IOException;

/**
 * Created by irenachernyak on 11/24/15.
 */
public class WebViewActivity extends AppCompatActivity {

    String archiveFilename;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_layout);

        Intent intent = getIntent();
        if(intent.hasExtra("policy")){
            archiveFilename = intent.getStringExtra("policy");
        }

        WebView webview = (WebView)findViewById(R.id.webView);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new Callback());

        ImageView pdfView = (ImageView)findViewById(R.id.imageView);

        if(archiveFilename != null && !archiveFilename.isEmpty()){

            String filename = "file:///android_asset/" +  archiveFilename;
            String content = "";
            try{
                content = Utils.LoadFile(getResources(), archiveFilename, false);
                webview.loadDataWithBaseURL(filename, content, "application/html", "UTF-8", null);
             } catch(IOException ex) {
                // do nothing
            }
        }

    }

    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(
                WebView view, String url) {
            return(false);
        }
    }
}
