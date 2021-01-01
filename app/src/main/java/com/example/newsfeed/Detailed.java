package com.example.newsfeed;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Detailed extends AppCompatActivity {

    WebView webView;
    ProgressBar loader;
    Dialog dialog;
    Button back;
    TextView home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        webView = findViewById(R.id.webView);
        dialog = new Dialog(Detailed.this);

        home = findViewById(R.id.home2);

        loader = findViewById(R.id.webViewLoader);
        loader.setVisibility(View.VISIBLE);

        back = findViewById(R.id.back);

        Intent intent = getIntent();

        String url = intent.getStringExtra("url");

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detailed.this, MainActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });

        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

        if(webView.isShown()){
            loader.setVisibility(View.INVISIBLE);
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Detailed.super.onBackPressed();
            }
        });

    }
    public void ShowPopup(View v) {
        Button close;
        Button github;
        Button linkedIn;
        Button insta;
        dialog.setContentView(R.layout.popup);
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        close =(Button) dialog.findViewById(R.id.buttonclose);
        github = (Button) dialog.findViewById(R.id.github);
        linkedIn = (Button) dialog.findViewById(R.id.linkedIn);
        insta = (Button) dialog.findViewById(R.id.insta);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.github.com/PROFILE_ID");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.setPackage("com.github.android");
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.github.com/PROFILE_ID")));
                }
            }
        });

        linkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.linkedin.com/in/PROFILE_ID");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.setPackage("com.linkedin.android");
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.linkedin.com/in/PROFILE_ID")));
                }

            }
        });

        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://instagram.com/_u/PROFILE_ID");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.setPackage("com.instagram.android");
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/PROFILE_ID")));
                }
            }
        });
    }
}
