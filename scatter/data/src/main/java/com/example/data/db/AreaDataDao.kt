package com.example.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.api.Hotspot
import com.example.data.model.Congestion
import java.util.concurrent.Flow

@Dao
interface AreaDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(areaData: Congestion)

    @Query("DELETE FROM congestion WHERE areaName = :areaName")
    fun delete(areaName: String)


    @Query("SELECT * FROM Congestion Where areaName = :areaName ORDER BY datetime DESC LIMIT 1 ")
    fun getCongestion(areaName: String): List<Congestion>

}

