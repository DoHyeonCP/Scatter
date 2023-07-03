package com.example.scatter

import com.google.gson.annotations.SerializedName

data class Hotspot(
    @SerializedName("congestion_level") val congestionLevel: Int? = null,
    @SerializedName("datetime") val datetime: String? =null
)
