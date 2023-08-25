package com.example.data.db

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import com.example.data.model.Hotspot

@Dao
interface AreaDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(areaData: Hotspot)

    @Query("DELETE FROM Hotspot WHERE areaName = :areaName")
    fun delete(areaName: String)
}

@Database(entities = [Hotspot::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase(){
    abstract fun areaDataDao(): AreaDataDao
}