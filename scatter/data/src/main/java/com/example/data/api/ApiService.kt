package com.example.data.api



import com.example.data.api.ApiResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService{
    @GET("api-sk/") // 엔드포인트 경로
    fun getData(): Call<ApiResponse>
}