package com.example.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.Hotspot

@Dao
interface AreaDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(areaData: Hotspot)

    @Query("DELETE FROM congestion_info WHERE areaName = :areaName")
    fun delete(areaName: String)
}

