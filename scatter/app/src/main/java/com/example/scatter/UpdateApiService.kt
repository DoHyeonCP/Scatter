package com.example.scatter

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface UpdateApiService {
    @GET("/api/data") // 엔드포인트 경로
    fun getData(
        @Header("create_date") create_date: String,
        @Header("area") area: String,
        @Query("congestion_level") congestion_level: String,
    ): List<UpdateItemData>
}