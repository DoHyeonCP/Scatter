package com.example.scatter.activity

import android.location.LocationManager
import com.example.scatter.R
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.data.api.ApiServiceManager
import com.example.data.db.AppDatabase
import com.example.data.db.AreaDataDao
import com.example.data.model.Congestion
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.example.scatter.module.UploadWorker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar
import java.util.concurrent.TimeUnit


class MainActivity: ComponentActivity(){
    private lateinit var locationManager: LocationManager
    private lateinit var apiServiceManager: ApiServiceManager
    private lateinit var appDatabase: AppDatabase




    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        appDatabase = AppDatabase.getDatabase(this.applicationContext)!!
        apiServiceManager = ApiServiceManager(this)
        val appDao = appDatabase.areaDataDao()

//        appDao.deleteAll()
        scheduleAPiCall()

        setContent{

            Column{
                Surface(
                    modifier = Modifier.fillMaxSize(),

                    ) {
                    MyApp(appDao)
                }
            }
        }
//        apiServiceManager.callApi()
    }



//    override fun onResume() {
//        super.onResume()
////        RequestPermissions().requestlocationpermission(this, this, locationManager)
////        RequestPermissions().requestnotificationpermission(this, this)
//    }





    @Composable
    fun MyApp(appDao: AreaDataDao) {
        var congestionDataList by remember { mutableStateOf(listOf<Congestion>()) }

        Main(onMenuItemClick = { selectedItem ->
            // 이 부분은 이제 Composable 함수 내부에 있으므로 Dispatchers.Main을 사용할 필요가 없습니다.
            lifecycleScope.launch {
                val data = withContext(Dispatchers.IO) {
                    appDao.getCongestion(selectedItem.name)
                }
                congestionDataList = data
                Toast.makeText(this@MainActivity, "불러오는 중입니다.", Toast.LENGTH_SHORT).show()
            }

        },
            congestions = congestionDataList)
    }

    private fun scheduleAPiCall(){
//        apiServiceManager.callApi()
        val repeatingRequest = PeriodicWorkRequestBuilder<UploadWorker>(1, TimeUnit.HOURS)
            // 정각에 실행되도록 지연 설정
            .setInitialDelay(getInitialDelayToHour(), TimeUnit.MILLISECONDS)
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "ApiCall",
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest
        )
    }
}
fun getInitialDelayToHour(): Long {
    val currentCalendar = Calendar.getInstance()
    val targetCalendar = Calendar.getInstance().apply {
        add(Calendar.HOUR_OF_DAY, 1)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }
    return targetCalendar.timeInMillis - currentCalendar.timeInMillis
}

@Composable
fun Main(onMenuItemClick: (MenuItems) -> Unit, congestions: List<Congestion>) {

    MyScaffoldLayout(onMenuItemClick = onMenuItemClick) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues),
            verticalArrangement = Arrangement.Top, //상단 컨텐츠 배치
            horizontalAlignment = Alignment.Start // 왼쪽 정렬
        ) {
            Image(
                painter = painterResource(id = R.drawable.jamsil_map),
                contentDescription = "jamsil_map",
                modifier = Modifier.fillMaxWidth() // 너비 최대
            )
            Spacer(modifier = Modifier.height(16.dp))
            congestions.forEach { congestion ->
                Text(
                    text = "지역: ${congestion.areaName}\n" +
                            "혼잡도: ${congestion.congestionLevel}\n" +
                            "날짜: ${congestion.datetime}",
                    style = TextStyle(fontSize = 30.sp,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                    )
                )
            }
        }
    }
}

//        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
//        com.example.data.mqtt.LocationInfo().startLocationService(locationManager)
//
//
//        FirebaseMessaging.getInstance().token
//            .addOnSuccessListener { token ->
//                Log.d("token", "$token")
//            }

//@Preview
//@Composable
//fun ToolbarSamplePreview() {
//    apise
//    Surface(
//            modifier = Modifier.fillMaxSize(),
//        ) {
//       Main()
//    }
//}