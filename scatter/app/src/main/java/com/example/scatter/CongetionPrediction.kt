package com.example.scatter

import android.net.http.SslError
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.webkit.SslErrorHandler
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.scatter.databinding.CongetionPredictionBinding
import com.example.scatter.databinding.ToolbarBinding

class CongetionPrediction : AppCompatActivity() {
    private lateinit var predictionBinding: CongetionPredictionBinding
    private lateinit var dropdown : Spinner
    private lateinit var graphview : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Scatter)
        super.onCreate(savedInstanceState)



        predictionBinding = CongetionPredictionBinding.inflate(layoutInflater)

        setContentView(predictionBinding.root)
//        setSupportActionBar(findViewById(R.id.main_toolbar))
        dropdown = predictionBinding.spinner
        graphview = predictionBinding.graphview






    }
}

// webView = predictionBinding.predictionGraph
//        webView.settings.javaScriptEnabled = true
//        webView.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
//
//        webView.webViewClient = object : WebViewClient(){
//            override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?){
//                handler?.proceed()
//            }
//        }
//
//        webView.loadUrl("https://www.naver.com")