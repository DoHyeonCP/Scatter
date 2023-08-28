package com.example.data.api

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.data.db.AppDatabase
import com.example.data.model.Hotspot
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiServiceManager(private val apiService: ApiService){
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://115.21.135.45:8000/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun callApi(areaName: String) {
        val call = apiService.getData()
        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    if(apiResponse != null){
                        val area = apiResponse.getClassField(areaName) // Reflectively get the field
                        val congestionLevel = area.congestionLevel
                        val datetime = area.datetime

//                        api호출 후 데이터 저장
//                        val areaData = Hotspot(areaName, congestionLevel, datetime)
//                        areaDataDao.insert(areaData)
                        // Your text setting logic here
                        // e.g.
                        // val congetioninfobody = textbody
                        // congetioninfobody.setTextColor(Color.BLACK)
                        // congetioninfobody.text = "기준시간:$datetime \n" +
                        //        "지역이름: $areaName \n" +
                        //        "위험도: $congestionLevel \n"
                    }
                } else {
                    Log.e("API Error", "Request failed with code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                // API 요청 실패 처리
                t.printStackTrace()
            }
        })
    }

    // This is a helper function to get the value of the class field using reflection
    private fun ApiResponse.getClassField(fieldName: String): Hotspot {
        val field = this::class.java.getDeclaredField(fieldName)
        return field.get(this) as Hotspot
    }
}

// Use the new function like:
// val api = Retrofit()
// api.callApi("롯데월드")
// api.callApi("방이동먹자골목")
//class Retrofit(){
//    private lateinit var apiService: ApiService
//
//
//    fun call롯데월드(){
//        val retrofit = Retrofit.Builder()
//            .baseUrl("http://115.21.135.45:8000/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//        apiService = retrofit.create(com.example.data.api.ApiService::class.java)
//
//        val call = apiService.getData()
//        call.enqueue(object : Callback<ApiResponse> {
//            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
//                if (response.isSuccessful) {
//                    val apiResponse = response.body()
//                    if(apiResponse != null){
//                        val area = apiResponse.롯데월드
//                        val congestionLevel = area.congestionLevel
//                        val datetime = area.datetime
//
////                        val congetioninfobody = textbody
////                        congetioninfobody.setTextColor(Color.BLACK)
////                        congetioninfobody.text = "기준시간:${datetime} \n" +
////                                "지역이름: 롯데월드 \n" +
////                                "위험도: $congestionLevel \n"
//
//                    }
//                } else{
//                    Log.e("API Error", "Request failed with code: ${response.code()}")
//                }
//            }
//
//            override fun onFailure(call: Call<com.example.data.api.ApiResponse>, t: Throwable) {
//                // API 요청 실패 처리
//                t.printStackTrace()
//            }
//        })
//
//    }
//
//    fun call방이동먹자골목(){
//        val retrofit = Retrofit.Builder()
//            .baseUrl("http://115.21.135.45:8000/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//        apiService = retrofit.create(com.example.data.api.ApiService::class.java)
//
//        val call = apiService.getData()
//        call.enqueue(object : Callback<com.example.data.api.ApiResponse> {
//            override fun onResponse(call: Call<com.example.data.api.ApiResponse>, response: Response<com.example.data.api.ApiResponse>) {
//                if (response.isSuccessful) {
//                    val apiResponse = response.body()
//                    if(apiResponse != null){
//                        val area = apiResponse.방이동먹자골목
//                        val congestionLevel = area.congestionLevel
//                        val datetime = area.datetime
//
////                        val congetioninfobody = textbody
////                        congetioninfobody.setTextColor(Color.BLACK)
////                        congetioninfobody.text = "기준시간:$datetime \n" +
////                                "지역이름: 방이동먹자골목 \n" +
////                                "위험도:   $congestionLevel \n"
//
//                    }
//                } else{
//                    Log.e("API Error", "Request failed with code: ${response.code()}")
//                }
//            }
//
//            override fun onFailure(call: Call<com.example.data.api.ApiResponse>, t: Throwable) {
//                // API 요청 실패 처리
//                t.printStackTrace()
//            }
//        })
//    }
//    fun call에비뉴엘월드타워점(){
//        val retrofit = Retrofit.Builder()
//            .baseUrl("http://115.21.135.45:8000/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//        apiService = retrofit.create(com.example.data.api.ApiService::class.java)
//
//        val call = apiService.getData()
//        call.enqueue(object : Callback<com.example.data.api.ApiResponse> {
//            override fun onResponse(call: Call<com.example.data.api.ApiResponse>, response: Response<com.example.data.api.ApiResponse>) {
//                if (response.isSuccessful) {
//                    val apiResponse = response.body()
//                    if(apiResponse != null){
//                        val area = apiResponse.에비뉴엘월드타워점
//                        val congestionLevel = area.congestionLevel
//                        val datetime = area.datetime
//
////                        val congetioninfobody = textbody
////                        congetioninfobody.setTextColor(Color.BLACK)
////                        congetioninfobody.text = "기준시간:$datetime \n" +
////                                "지역이름: 에비뉴엘월드타워점 \n" +
////                                "위험도:   $congestionLevel \n"
//
//                    }
//                } else{
//                    Log.e("API Error", "Request failed with code: ${response.code()}")
//                }
//            }
//
//            override fun onFailure(call: Call<com.example.data.api.ApiResponse>, t: Throwable) {
//                // API 요청 실패 처리
//                t.printStackTrace()
//            }
//        })
//
//    }
//    fun call롯데월드몰(){
//        val retrofit = Retrofit.Builder()
//            .baseUrl("http://115.21.135.45:8000/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//        apiService = retrofit.create(com.example.data.api.ApiService::class.java)
//
//        val call = apiService.getData()
//        call.enqueue(object : Callback<com.example.data.api.ApiResponse> {
//            override fun onResponse(call: Call<com.example.data.api.ApiResponse>, response: Response<com.example.data.api.ApiResponse>) {
//                if (response.isSuccessful) {
//                    val apiResponse = response.body()
//                    if(apiResponse != null){
//                        val area = apiResponse.롯데월드몰
//                        val congestionLevel = area.congestionLevel
//                        val datetime = area.datetime
//
////                        val congetioninfobody = textbody
////                        congetioninfobody.setTextColor(Color.BLACK)
////                        congetioninfobody.text = "기준시간:$datetime \n" +
////                                "지역이름: 롯데월드몰 \n" +
////                                "위험도:   $congestionLevel \n"
//
//                    }
//                } else{
//                    Log.e("API Error", "Request failed with code: ${response.code()}")
//                }
//            }
//
//            override fun onFailure(call: Call<com.example.data.api.ApiResponse>, t: Throwable) {
//                // API 요청 실패 처리
//                t.printStackTrace()
//            }
//        })
//
//    }
//
//    fun call올림픽공원(){
//        val retrofit = Retrofit.Builder()
//            .baseUrl("http://115.21.135.45:8000/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//        apiService = retrofit.create(com.example.data.api.ApiService::class.java)
//
//        val call = apiService.getData()
//        call.enqueue(object : Callback<com.example.data.api.ApiResponse> {
//            override fun onResponse(call: Call<com.example.data.api.ApiResponse>, response: Response<com.example.data.api.ApiResponse>) {
//                if (response.isSuccessful) {
//                    val apiResponse = response.body()
//                    if(apiResponse != null){
//                        val area = apiResponse.올림픽공원
//                        val congestionLevel = area.congestionLevel
//                        val datetime = area.datetime
//
////                        val congetioninfobody = textbody
////                        congetioninfobody.setTextColor(Color.BLACK)
////                        congetioninfobody.text = "기준시간:$datetime \n" +
////                                "지역이름: 올림픽공원 \n" +
////                                "위험도:   $congestionLevel \n"
//
//                    }
//                } else{
//                    Log.e("API Error", "Request failed with code: ${response.code()}")
//                }
//            }
//
//            override fun onFailure(call: Call<com.example.data.api.ApiResponse>, t: Throwable) {
//                // API 요청 실패 처리
//                t.printStackTrace()
//            }
//        })
//
//    }
//}
//
//





