package com.example.scatter



import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.Gravity
import android.webkit.WebView
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.example.scatter.databinding.MainActivityBinding
import com.example.scatter.databinding.ToolbarBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainbinding: MainActivityBinding
    private lateinit var locationManager : LocationManager


    private lateinit var toolbarbinding : ToolbarBinding
    private lateinit var ivMenu : ImageView
    private lateinit var drawerLayout : DrawerLayout
    private lateinit var toolbar : Toolbar

    private lateinit var button : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        mainbinding = MainActivityBinding.inflate(layoutInflater)
        toolbarbinding = ToolbarBinding.inflate(layoutInflater)

        setContentView(mainbinding.root)

        ivMenu = toolbarbinding.ivMenu
        drawerLayout = mainbinding.root
        toolbar = toolbarbinding.root
        button = mainbinding.prediction

        setSupportActionBar(toolbar)

        ivMenu.setOnClickListener{
            drawerLayout.openDrawer(Gravity.LEFT)
        }

        button.setOnClickListener{
            val intent = Intent(this@MainActivity, CongetionPrediction::class.java)
            startActivity(intent)
        }

        val congetioninfotxt = mainbinding.textHead
        congetioninfotxt.text = "기준시간: \n\n" +
                "지역 이름: \n\n" +
                "위험도(혼잡도): "

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        LocationInfo().startLocationService(locationManager)
    }

    override fun onResume() {
        super.onResume()
        RequestPermissions().requestlocationpermission(this,this, locationManager)
    }

}


