package com.example.scatter



import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.scatter.databinding.MainActivityBinding
import com.google.firebase.messaging.FirebaseMessaging
import com.google.android.material.navigation.NavigationView
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var mainbinding: MainActivityBinding
    private lateinit var locationManager: LocationManager
    private lateinit var ivMenu : ImageView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var button: Button
    private lateinit var textbody : TextView
    private lateinit var navigationView: NavigationView

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        mainbinding = MainActivityBinding.inflate(layoutInflater)

        setContentView(mainbinding.root)


        ivMenu = findViewById(R.id.iv_menu)
        drawerLayout = findViewById(R.id.drawblelayout)
        button = mainbinding.prediction
        textbody = mainbinding.textbody
        navigationView = findViewById(R.id.navigation)

        setSupportActionBar(findViewById(R.id.main_toolbar))

        button.setOnClickListener {
            val intent = Intent(this@MainActivity, CongetionPrediction::class.java)
            startActivity(intent)
        }






        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        LocationInfo().startLocationService(locationManager)


        FirebaseMessaging.getInstance().token
            .addOnSuccessListener { token ->
                Log.d("token", "$token")
            }


        navigationView.setNavigationItemSelectedListener(this)

        ivMenu.setOnClickListener{
            drawerLayout.openDrawer(Gravity.LEFT)
        }

    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//
//        when(item.itemId){
//            android.R.id.home->{ // 메뉴 버튼
//                drawerLayout.openDrawer(Gravity.LEFT)    // 네비게이션 드로어 열기
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }


    inner class NetworkThread: Thread(){
        override fun run(){
            val site = "http:127.0.0.1:8000/api-sk/"
            var url = URL(site)
            val conn = url.openConnection()
            var input = conn.getInputStream()
            var isr = InputStreamReader(input)
            var br = BufferedReader(isr)

            var str: String? = null
            var buf = StringBuffer()

            do{
                str = br.readLine()
                if(str!=null){
                    buf.append(str)
                }
            }while(str!=null)

            val root = JSONObject(buf.toString())
            val area = "롯데월드"
            val jObject = root.getJSONObject("$area")

            runOnUiThread{
                for(i in 0 until jObject.length()){
                    val item = jObject.getJSONObject(i.toString())

//                    textbody.append("$area")
                    textbody.append("${JSON_Parse(item, "datetime")}")
                    textbody.append("${JSON_Parse(item, "congestion_level")}")
                }
            }



        }
        fun JSON_Parse(obj:JSONObject, data : String): String {

            // 원하는 정보를 불러와 리턴받고 없는 정보는 캐치하여 "없습니다."로 리턴받는다.
            return try {

                obj.getString(data)

            } catch (e: Exception) {
                "없습니다."
            }
        }
    }

//    fun itemclickAction(area: String){
//        skApi = SkApi()
//        skApi.calljson(area)
//
//
//        val update_date = SkApi.update_date
//        val congestion_level = SkApi.congestion_level
//        Log.d(ContentValues.TAG, "update_date: $update_date")
//        Log.d(ContentValues.TAG, "area: $area")
//        Log.d(ContentValues.TAG, "congestion_level: $congestion_level")
//        val congetioninfobody = textbody
//        congetioninfobody.text = "기준시간:$update_date \n\n" +
//                    "지역 이름:     $area \n\n" +
//                    "위험도(혼잡도): $congestion_level \n\n"
//    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.lotteWorld -> {
                val thread = NetworkThread()
                thread.start()
                thread.join()
//                itemclickAction("롯데월드")
            }
        }
        drawerLayout.closeDrawers() // 기능을 수행하고 네비게이션을 닫아준다.
//        when(item.itemId){
//            R.id.bangiDong -> {
//                itemclickAction("방이동먹자골목")
//            }
//        }
//        drawerLayout.closeDrawers() // 기능을 수행하고 네비게이션을 닫아준다.
//        when(item.itemId){
//            R.id.lottedpartment -> {
//                itemclickAction("롯데백화점")
//            }
//        }
//        drawerLayout.closeDrawers() // 기능을 수행하고 네비게이션을 닫아준다.
//        when(item.itemId){
//            R.id.zplanet -> {
//                itemclickAction("롯데월드제타플레닛")
//            }
//        }
//        drawerLayout.closeDrawers() // 기능을 수행하고 네비게이션을 닫아준다.
//        when(item.itemId){
//            R.id.avenuelJamsil -> {
//                itemclickAction("에비뉴엘월드타워점")
//            }
//        }
//        drawerLayout.closeDrawers() // 기능을 수행하고 네비게이션을 닫아준다.
//        when(item.itemId){
//            R.id.lotteWorldmall -> {
//                itemclickAction("롯데월드몰")
//            }
//        }
//        drawerLayout.closeDrawers() // 기능을 수행하고 네비게이션을 닫아준다.
//        when(item.itemId){
//            R.id.oplmpicPark -> {
//                itemclickAction("올림픽공원")
//            }
//        }
//        drawerLayout.closeDrawers() // 기능을 수행하고 네비게이션을 닫아준다.
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


