package com.example.data.api

import android.content.Context
import android.hardware.Camera.Area
import android.os.Bundle
import android.util.Log
import com.example.data.db.AppDatabase
import com.example.data.db.AreaDataDao
import com.example.data.model.Congestion
import dagger.Module
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


class ApiServiceManager @Inject constructor(
    private val context: Context,
    private val db: AppDatabase,
    private val apiService: ApiService
){

    fun callApi() {
        val call = apiService.getData()
        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    if(apiResponse != null){
                        areaparshing("롯데월드", apiResponse)
                        areaparshing("방이동먹자골목", apiResponse)
                        areaparshing("에비뉴엘월드타워점", apiResponse)
                        areaparshing("롯데월드몰", apiResponse)
                        areaparshing("올림픽공원", apiResponse)

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

    private fun areaparshing(areaName: String, apiResponse: ApiResponse){
        val area = apiResponse.getClassField(areaName) // Reflectively get the field
        val congestionLevel = area.congestionLevel
        val datetime = area.datetime

        val areaData = Congestion(0, areaName, congestionLevel!!, datetime!!)


        GlobalScope.launch{
            db.areaDataDao().insert(areaData)
        }
    }

    // This is a helper function to get the value of the class field using reflection
    private fun ApiResponse.getClassField(fieldName: String): Hotspot {
        val field = this::class.java.getDeclaredField(fieldName)
        field.isAccessible = true
        return field.get(this) as Hotspot
    }

}


