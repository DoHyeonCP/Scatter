package com.example.domain.uscase

import android.content.Context
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.util.concurrent.TimeUnit

class MyWorker (context: Context, workerParams: WorkerParameters) : Worker(context, workerParams){
    //    한 시간마다 데이터 저장:
//    Android의 WorkManager를 사용하여 반복 작업을 스케줄링할 수 있습니다.


    override fun doWork(): Result {
        // 백그라운드 작업 수행

        val workRequest = PeriodicWorkRequestBuilder<MyWorker>(1, TimeUnit.HOURS).build()
        WorkManager
            .getInstance(applicationContext)
            .enqueue(workRequest)

        return Result.success()
    }


}
