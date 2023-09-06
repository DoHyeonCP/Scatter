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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.api.ApiResponse
import com.example.data.api.ApiServiceManager
import com.example.data.db.AppDatabase
import com.example.data.db.AreaDataDao
import com.example.data.model.Congestion
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity: ComponentActivity(){
    private lateinit var locationManager: LocationManager
    private lateinit var apiServiceManager: ApiServiceManager
    private lateinit var appDatabase: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        appDatabase = AppDatabase.getDatabase(this.applicationContext)!!
        apiServiceManager = ApiServiceManager(this)



        setContent{
            Column{
                Surface(
                    modifier = Modifier.fillMaxSize(),

                    ) {
                    Main(onMenuItemClick = { selectedItem ->
                    })
                }
            }
        }

        apiServiceManager.callApi()

    }

//    override fun onResume() {
//        super.onResume()
////        RequestPermissions().requestlocationpermission(this, this, locationManager)
////        RequestPermissions().requestnotificationpermission(this, this)
//    }




}

//fun getCongestion(){
//    appDatabase.areaDataDao().getCongestion()
//}

@Composable
fun Main(onMenuItemClick: (MenuItems) -> Unit) {
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
            Text(
                text = "실시간 혼잡도",
                style = TextStyle(fontSize = 40.sp, textAlign = TextAlign.Start)

            )
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