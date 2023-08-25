package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Hotspot(
    @PrimaryKey val areaName: String,
    @SerializedName("congestion_level") val congestionLevel: String? = null,
    @SerializedName("datetime") val datetime: String? =null
)
