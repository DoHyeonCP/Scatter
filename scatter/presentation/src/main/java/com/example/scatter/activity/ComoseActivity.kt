package com.example.scatter.activity

//import android.os.Bundle
//import android.util.Log
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.ExperimentalFoundationApi
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.text.ClickableText
//import androidx.compose.material.*
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Menu
//import androidx.compose.material3.FloatingActionButton
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBar
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.data.api.ApiResponse
//import com.example.data.api.ApiService
//import com.example.scatter.R
//import com.example.scatter.permission.RequestPermissions
//import com.google.firebase.messaging.FirebaseMessaging
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MessageCard("Hello world!")
        }
    }
}

@Composable
fun MessageCard(name: String) {
    Text(text = "Hello $name!")
}

@Preview
@Composable
fun PreviewMessageCard(){
    MessageCard("Android")
}
//class ComposeActivity : ComponentActivity() {
//
//    private lateinit var apiService: ApiService
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        setContent {
//            // 이 부분은 전체 UI의 루트를 구성합니다.
//            // 툴바, 바디, 내비게이션 등 모든 것을 여기에 배치하면 됩니다.
//            Column {
//                TopAppBar(title = { Text("App Title") }) // 툴바
//
//                // 여기에 바디 (예: 버튼, 텍스트 등) 컨텐츠를 배치
//                Text("Some body content")
//
//                // Floating action button 예
//                FloatingActionButton(onClick = { /* Handle FAB click */ }) {
//                    Text("FAB")
//                }
//            }
//
//            // 기타 UI 컴포넌트 구성
//        }
//
//        // 기타 초기화 코드...
//
//        FirebaseMessaging.getInstance().token
//            .addOnSuccessListener { token ->
//                Log.d("token", "$token")
//            }
//
//        // API 초기화 및 데이터 가져오기
//        initializeApi()
//        fetchSomeData()
//    }
//
//    // Compose에는 findViewById가 필요 없습니다. 대신 Composable 함수로 UI를 구성합니다.
//    @ExperimentalFoundationApi
//    @Composable
//    fun MyAppBar() {
//        TopAppBar(
//            title = {
//                Text("My App")
//            },
//            actions = {
//                IconButton(onClick = { /* Handle actions */ }) {
//                    Icon(Icons.Default.Menu, contentDescription = null)
//                }
//            }
//        )
//    }
//
//    @Composable
//    fun MyBodyContent() {
//        // 여기에 본문 내용을 작성하세요.
//    }
//
//    // 기타 Composable 함수들...
//
//    private fun initializeApi() {
//        val retrofit = Retrofit.Builder()
//            .baseUrl("http://115.21.135.45:8000/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//        apiService = retrofit.create(ApiService::class.java)
//    }
//
//    private fun fetchSomeData() {
//        // API 데이터 가져오기...
//    }
//}
