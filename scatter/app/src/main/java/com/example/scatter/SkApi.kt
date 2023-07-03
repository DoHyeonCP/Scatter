package com.example.scatter

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.graphics.Color
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.scatter.databinding.MainActivityBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Request
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class SkApi : AppCompatActivity() {
    private lateinit var  apiService: ApiService


}






//val jsonString = """
//        {
//            "post1": {
//                "update_date" : "20230627180000",
//                "area" : "롯데월드",
//                "congestion_level": "위험"
//            },
//            "post1": {
//                "update_date" : "20230627180000",
//                "area" : "방이동 먹자골목",
//                "congestion_level": "안전"
//            }
//        }
//    """.trimIndent()