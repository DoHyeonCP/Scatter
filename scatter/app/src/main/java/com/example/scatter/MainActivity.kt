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
import androidx.appcompat.app.ActionBar
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

class MainActivity : AppCompatActivity() {
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
    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        mainbinding = MainActivityBinding.inflate(layoutInflater)
        toolbarbinding = ToolbarBinding.inflate(layoutInflater)
        toolbarheadbinding = ToolbarHeadBinding.inflate(layoutInflater)

        setContentView(mainbinding.root)



        toolbar = findViewById(R.id.toolbar)
        ivMenu = findViewById(R.id.iv_menu)
        close_menu = toolbarheadbinding.closeMenu
        drawerLayout = findViewById(R.id.drawer_layout)
//        drawerbutton = findViewById(R.id.drawer_button)

        button = mainbinding.prediction

//        setSupportActionBar(toolbar)

        close_menu.setOnClickListener(){
            drawerLayout.closeDrawer(Gravity.RIGHT)
        }


        val items = arrayOf("롯데월드", "로그인")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)

        listView = findViewById(R.id.drawer_menulist)

        listView.setOnItemClickListener(AdapterView.OnItemClickListener{parent, view, position, id ->
            when(position){
                0 -> {
                    Log.d("success","success")
                }
            }
        })



        button.setOnClickListener {
            val intent = Intent(this@MainActivity, CongetionPrediction::class.java)
            startActivity(intent)
        }


//        val congetioninfohead = mainbinding.textHead
//        congetioninfohead.text = " \n\n" +
//                "지역 이름: \n\n" +
//                 "

        // 디버깅용 코드 3줄 SkApi에 지역을 넣었을 때 return 되도록 해야한다.

        val skApi = DjnagoApi()
        skApi.jsonPharshing()
        var u = skApi.update_date
        var a = skApi.area
        var c = skApi.congestion_level
        val congetioninfobody = mainbinding.textBody
        congetioninfobody.text =
                "기준시간:      $u \n\n" +
                "지역 이름:     $a \n\n" +
                "위험도(혼잡도): $c \n\n"


        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        LocationInfo().startLocationService(locationManager)




    }



//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu_navigation, menu)       // main_menu 메뉴를 toolbar 메뉴 버튼으로 설정
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId){
//            R.id.login ->{
//                val intent = Intent(this@MainActivity, LoginActivity::class.java)
//                return true
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }
//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        when(item.itemId){
//            R.id.login -> {
//                Log.d("NavigationView", "메뉴 아이템 클릭됨: ${item.itemId}")
//                val intent = Intent(this@MainActivity, LoginActivity::class.java)
//                startActivity(intent)
//            }
//            R.id.lotteWorld->Log.d("NavigationView", "메뉴 아이템 클릭됨: ${item.itemId}")
//        }
//        drawerLayout.closeDrawers() // 기능을 수행하고 네비게이션을 닫아준다.
//        return false
//    }

//    override fun onBackPressed() {
//        if(drawerLayout.isDrawerOpen(Gravity.LEFT)){
//            drawerLayout.closeDrawers()
//        }else{
//            super.onBackPressed()
//        }
//    }

    override fun onResume() {
        super.onResume()
        RequestPermissions().requestlocationpermission(this, this, locationManager)
        RequestPermissions().requestnotificationpermission(this, this)
        LocationInfo()
    }
}


