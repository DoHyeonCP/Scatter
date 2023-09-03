package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "congestion")
data class Congestion(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val areaName: String,
    val congestionLevel: String,
    val datetime: String

)
