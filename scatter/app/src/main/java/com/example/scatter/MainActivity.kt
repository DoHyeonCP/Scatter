package com.example.scatter



import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.activity.result.contract.ActivityResultContracts.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.scatter.databinding.MainActivityBinding
import com.example.scatter.LocationInfo
import com.example.scatter.Mqtt
import com.example.scatter.RequestPermissions
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var mainbinding: MainActivityBinding
    private lateinit var locationManager : LocationManager
    private lateinit var webView : WebView
    private lateinit var toolbar : Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(androidx.appcompat.R.style.Theme_AppCompat)
        mainbinding = MainActivityBinding.inflate(layoutInflater)
        setContentView(mainbinding.root)

//        webView = binding.webView
//        webView.settings.javaScriptEnabled = true
//        webView.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
////        webView.webViewClient = object : WebViewClient(){
////            override fun onRecievedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?){
////                handler?.proceed()
////            }
////        }
//        // sk Puzzle map
//        webView.loadUrl("https://puzzle.geovision.co.kr/map?lat=37.52772172963041&lng=127.03519374778266&zoom=14&poiId=0&overlayType=")


        val congetioninfotxt = mainbinding.textHead
        congetioninfotxt.text = "업데이트시간: " +
                "장소: " +
                "혼잡레벨: "


        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        LocationInfo().startLocationService(locationManager)
    }

    override fun onResume() {
        super.onResume()
        RequestPermissions().requestlocationpermission(this,this, locationManager)
    }

}


