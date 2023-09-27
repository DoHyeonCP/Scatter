package com.example.data.di

import android.content.Context
import com.example.data.api.ApiResponse
import com.example.data.api.ApiService
import com.example.data.api.ApiServiceManager
import com.example.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("http://serverurl:port/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun proviceApiServiceManager(@ApplicationContext context: Context, db: AppDatabase, apiService: ApiService) : ApiServiceManager{
        return ApiServiceManager(context, db, apiService)
    }
}