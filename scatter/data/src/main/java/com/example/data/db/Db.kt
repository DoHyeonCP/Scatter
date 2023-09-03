package com.example.data.db

import android.content.Context
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Room

class Db {
    private lateinit var areaDataDao: AreaDataDao
    private lateinit var areaName: String


    fun dbinit(context: Context){
        val db = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "Congestion"
        ).build()

        val areaDataDao = db.areaDataDao()

    }

    fun dbdelete(){
        //    기존 데이터 삭제:
        areaDataDao.delete(areaName)
    }
}