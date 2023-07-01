package com.example.scatter



import android.app.DownloadManager.Request
import android.content.ClipData.Item
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.drawerlayout.widget.DrawerLayout.LOCK_MODE_LOCKED_CLOSED
import com.example.scatter.databinding.MainActivityBinding
import com.example.scatter.databinding.ToolbarBinding
import com.google.firebase.messaging.FirebaseMessaging
import com.example.scatter.databinding.ToolbarHeadBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import org.eclipse.paho.client.mqttv3.MqttMessage

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var mainbinding: MainActivityBinding
    private lateinit var locationManager: LocationManager
    private lateinit var toolbarbinding: ToolbarBinding
    private lateinit var ivMenu : ImageView
    private lateinit var close_menu: ImageView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var toolbarheadbinding: ToolbarHeadBinding
    private lateinit var button: Button
    private lateinit var drawerbutton : Button
    private lateinit var listView: ListView
    private lateinit var textbody : TextView
    private lateinit var skApi : SkApi
    private lateinit var navigationView: NavigationView
    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        mainbinding = MainActivityBinding.inflate(layoutInflater)

        setContentView(mainbinding.root)


//        toolbar = findViewById(R.id.include)
//        ivMenu = findViewById(R.id.iv_menu)
        drawerLayout = findViewById(R.id.drawblelayout)
//        drawerbutton = findViewById(R.id.drawer_button)
//        button = mainbinding.prediction
        textbody = mainbinding.textbody
        navigationView = findViewById(R.id.navigation)
        skApi = SkApi()

        setSupportActionBar(findViewById(R.id.main_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 드로어를 꺼낼 홈 버튼 활성화
        supportActionBar?.setHomeAsUpIndicator(R.drawable.menu_burger) // 홈버튼 이미지 변경


//        button.setOnClickListener {
//            val intent = Intent(this@MainActivity, CongetionPrediction::class.java)
//            startActivity(intent)
//        }


//        val congetioninfohead = mainbinding.textHead
//        congetioninfohead.text = " \n\n" +
//                "지역 이름: \n\n" +
//                 "

        // 디버깅용 코드 3줄 SkApi에 지역을 넣었을 때 return 되도록 해야한다.




        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        LocationInfo().startLocationService(locationManager)


        FirebaseMessaging.getInstance().token
            .addOnSuccessListener { token ->
                Log.d("token", "$token")
            }


        navigationView.setNavigationItemSelectedListener(this)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            android.R.id.home->{ // 메뉴 버튼
                drawerLayout.openDrawer(Gravity.LEFT)    // 네비게이션 드로어 열기
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            R.id.lotteWorld -> {
                Toast.makeText(this,"불러오는 중",Toast.LENGTH_SHORT).show()
                skApi = SkApi()
                skApi.jsonPharshing()
                var u = skApi.update_date
                var a = skApi.area
                var c = skApi.congestion_level
                val congetioninfobody = textbody
                congetioninfobody.text =
                    "기준시간:      $u \n\n" +
                            "지역 이름:     $a \n\n" +
                            "위험도(혼잡도): $c \n\n"
                Toast.makeText(this,"불러왔습니다",Toast.LENGTH_SHORT).show()
            }
        }
        drawerLayout.closeDrawers() // 기능을 수행하고 네비게이션을 닫아준다.
        return true
    }

    override fun onBackPressed() {
        if(drawerLayout.isDrawerOpen(Gravity.LEFT)){
            drawerLayout.closeDrawers()
        }else{
            super.onBackPressed()
        }
    }

    override fun onResume() {
        super.onResume()
        RequestPermissions().requestlocationpermission(this, this, locationManager)
        RequestPermissions().requestnotificationpermission(this, this)
        LocationInfo()
    }
}


