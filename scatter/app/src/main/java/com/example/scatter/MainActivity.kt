package com.example.scatter



import android.app.DownloadManager.Request
import android.content.ClipData.Item
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.location.LocationManager
import android.os.Bundle
import android.provider.ContactsContract.Data
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.scatter.ApiResponse
import com.google.gson.annotations.SerializedName
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var mainbinding: MainActivityBinding
    private lateinit var locationManager: LocationManager
    private lateinit var ivMenu : ImageView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var button: Button
    private lateinit var textbody : TextView
    private lateinit var navigationView: NavigationView
    private lateinit var  apiService: ApiService
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





    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.lotteWorld -> {
                call롯데월드()
            }
        }
        drawerLayout.closeDrawers() // 기능을 수행하고 네비게이션을 닫아준다.
        when(item.itemId){
            R.id.bangiDong -> {
                call방이동먹자골목()
            }
        }
        drawerLayout.closeDrawers() // 기능을 수행하고 네비게이션을 닫아준다.
        when(item.itemId){
            R.id.lottedpartment -> {
                call롯데백화점()
            }
        }
        drawerLayout.closeDrawers() // 기능을 수행하고 네비게이션을 닫아준다.
        when(item.itemId){
            R.id.zplanet -> {
                call롯데마트제타플레닛()
            }
        }
        drawerLayout.closeDrawers() // 기능을 수행하고 네비게이션을 닫아준다.
        when(item.itemId){
            R.id.avenuelJamsil -> {
                call에비뉴엘월드타워점()
            }
        }
        drawerLayout.closeDrawers() // 기능을 수행하고 네비게이션을 닫아준다.
        when(item.itemId){
            R.id.lotteWorldmall -> {
                call롯데월드몰()
            }
        }
        drawerLayout.closeDrawers() // 기능을 수행하고 네비게이션을 닫아준다.
        when(item.itemId){
            R.id.oplmpicPark -> {
                call올림픽공원()
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
    fun call롯데월드(){
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.20.27:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)

        val call = apiService.getData()
        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    if(apiResponse != null){
                        val area = apiResponse.롯데월드
                        val congestionLevel = area.congestionLevel
                        val datetime = area.datetime

                        val congetioninfobody = textbody
                        congetioninfobody.setTextColor(Color.BLACK)
                        congetioninfobody.text = "기준시간:${datetime} \n\n" +
                                "지역 이름: 롯데월드 \n\n" +
                                "위험도: $congestionLevel \n\n"

                    }
                } else{
                    Log.e("API Error", "Request failed with code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                // API 요청 실패 처리
                t.printStackTrace()
            }
        })

    }

    fun call방이동먹자골목(){
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.20.27:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)

        val call = apiService.getData()
        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    if(apiResponse != null){
                        val area = apiResponse.방이동먹자골목
                        val congestionLevel = area.congestionLevel
                        val datetime = area.datetime

                        val congetioninfobody = textbody
                        congetioninfobody.setTextColor(Color.BLACK)
                        congetioninfobody.text = "기준시간:$datetime \n" +
                                "지역이름: 방이동먹자골목 \n" +
                                "위험도:   $congestionLevel \n"

                    }
                } else{
                    Log.e("API Error", "Request failed with code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                // API 요청 실패 처리
                t.printStackTrace()
            }
        })

    }
    fun call롯데백화점(){
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.20.27:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)

        val call = apiService.getData()
        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    if(apiResponse != null){
                        val area = apiResponse.롯데백화점
                        val congestionLevel = area.congestionLevel
                        val datetime = area.datetime

                        val congetioninfobody = textbody
                        congetioninfobody.setTextColor(Color.BLACK)
                        congetioninfobody.text = "기준시간:$datetime \n" +
                                "지역이름: 롯데백화점 \n" +
                                "위험도:  $congestionLevel \n"

                    }
                } else{
                    Log.e("API Error", "Request failed with code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                // API 요청 실패 처리
                t.printStackTrace()
            }
        })

    }
    fun call롯데마트제타플레닛(){
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.20.27:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)

        val call = apiService.getData()
        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    if(apiResponse != null){
                        val area = apiResponse.롯데마트제타플레닛
                        val congestionLevel = area.congestionLevel
                        val datetime = area.datetime

                        val congetioninfobody = textbody
                        congetioninfobody.setTextColor(Color.BLACK)
                        congetioninfobody.text = "기준시간:$datetime \n" +
                                "지역이름: 롯데마트제타플레닛 \n" +
                                "위험도:   $congestionLevel \n"

                    }
                } else{
                    Log.e("API Error", "Request failed with code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                // API 요청 실패 처리
                t.printStackTrace()
            }
        })

    }
    fun call에비뉴엘월드타워점(){
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.20.27:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)

        val call = apiService.getData()
        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    if(apiResponse != null){
                        val area = apiResponse.에비뉴엘월드타워점
                        val congestionLevel = area.congestionLevel
                        val datetime = area.datetime

                        val congetioninfobody = textbody
                        congetioninfobody.setTextColor(Color.BLACK)
                        congetioninfobody.text = "기준시간:$datetime \n" +
                                "지역이름: 에비뉴엘월드타워점 \n" +
                                "위험도:   $congestionLevel \n"

                    }
                } else{
                    Log.e("API Error", "Request failed with code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                // API 요청 실패 처리
                t.printStackTrace()
            }
        })

    }
    fun call롯데월드몰(){
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.20.27:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)

        val call = apiService.getData()
        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    if(apiResponse != null){
                        val area = apiResponse.롯데월드몰
                        val congestionLevel = area.congestionLevel
                        val datetime = area.datetime

                        val congetioninfobody = textbody
                        congetioninfobody.setTextColor(Color.BLACK)
                        congetioninfobody.text = "기준시간:$datetime \n" +
                                "지역이름: 롯데월드몰 \n" +
                                "위험도:   $congestionLevel \n"

                    }
                } else{
                    Log.e("API Error", "Request failed with code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                // API 요청 실패 처리
                t.printStackTrace()
            }
        })

    }

    fun call올림픽공원(){
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.20.27:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)

        val call = apiService.getData()
        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    if(apiResponse != null){
                        val area = apiResponse.올림픽공원
                        val congestionLevel = area.congestionLevel
                        val datetime = area.datetime

                        val congetioninfobody = textbody
                        congetioninfobody.setTextColor(Color.BLACK)
                        congetioninfobody.text = "기준시간:$datetime \n" +
                                "지역이름: 올림픽공원 \n" +
                                "위험도:   $congestionLevel \n"

                    }
                } else{
                    Log.e("API Error", "Request failed with code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                // API 요청 실패 처리
                t.printStackTrace()
            }
        })

    }
    fun formatDateTime(dateTime: Date): String {
        val pattern = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        return sdf.format(dateTime)
    }
}


