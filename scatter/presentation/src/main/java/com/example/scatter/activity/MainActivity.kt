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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.example.data.api.ApiResponse
import com.example.data.api.ApiServiceManager
import com.example.data.db.AppDatabase
import com.example.data.db.AreaDataDao
import com.example.data.model.Congestion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity: ComponentActivity(){
    private lateinit var locationManager: LocationManager
    private lateinit var apiServiceManager: ApiServiceManager
    private lateinit var appDatabase: AppDatabase




    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        appDatabase = AppDatabase.getDatabase(this.applicationContext)!!
        apiServiceManager = ApiServiceManager(this)

        val appDao = appDatabase.areaDataDao()


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
                    appDao.getCongestion(selectedItem.toString())
                }
                congestionDataList = data
                Toast.makeText(this@MainActivity, "$data", Toast.LENGTH_SHORT).show()
            }
        },
            congestions = congestionDataList)
    }
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
                    text = "지역: ${congestion.areaName}, 혼잡도: ${congestion.congestionLevel}, 날짜: ${congestion.datetime}",
                    style = TextStyle(fontSize = 20.sp, textAlign = TextAlign.Start)
                )
            }
        }
    }
}


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