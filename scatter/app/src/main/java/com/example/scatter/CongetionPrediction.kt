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
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.compose.ui.graphics.Color
import com.example.scatter.databinding.CongetionPredictionBinding
import com.example.scatter.databinding.ToolbarBinding

class CongetionPrediction : AppCompatActivity() {
    private lateinit var predictionBinding: CongetionPredictionBinding
    private lateinit var spinner1 : Spinner
    private lateinit var spinner2 : Spinner
    private lateinit var graphview1 : ImageView
    private lateinit var graphview2 : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Scatter)
        super.onCreate(savedInstanceState)

        predictionBinding = CongetionPredictionBinding.inflate(layoutInflater)

        setContentView(predictionBinding.root)
        spinner1 = predictionBinding.spinner1
        graphview1 = predictionBinding.graph1
        val itemArray = arrayOf("지역을 선택해주세요", "롯데월드", "방이동먹자골목", "에비뉴엘월드타워점", "롯데월드몰", "올림픽공원")
        val spinnerAdapter1 = ArrayAdapter(
            this,
            R.layout.spinner_dropdown_item,
            itemArray
        )
        spinner1.adapter = spinnerAdapter1

        spinner2 = predictionBinding.spinner2
        graphview2 = predictionBinding.graph2
        val spinnerAdapter2 = ArrayAdapter(
            this,
            R.layout.spinner_dropdown_item,
            itemArray
        )
        spinner2.adapter = spinnerAdapter2
//        spinner1.viewTreeObserver.addOnGlobalLayoutListener {
//            (spinner.selectedView as TextView).setTextColor(Color.White)
//            (spinner.selectedView as TextView).setBackgroundResource(R.drawable.spinner_custom)
//        }
//        spinner.onItemSelectedListener =
//            object : AdapterView.OnItemSelectedListener {
//                override fun onItemSelected(
//                    parent: AdapterView<*>,
//                    view: View,
//                    position: Int,
//                    id: Long
//                ) {
//                    // 선택됬을 경우
//
//                }
//
//                override fun onNothingSelected(parent: AdapterView<*>) {}
//            }

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