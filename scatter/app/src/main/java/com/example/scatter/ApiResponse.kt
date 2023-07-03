package com.example.scatter

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("롯데월드") val 롯데월드: Hotspot,
    @SerializedName("방이동먹자골목") val 방이동먹자골목: Hotspot,
    @SerializedName("롯데백화점") val 롯데백화점: Hotspot,
    @SerializedName("롯데마트제타플레닛") val 롯데마트제타플레닛: Hotspot,
    @SerializedName("에비뉴엘월드타워점") val 에비뉴엘월드타워점: Hotspot,
    @SerializedName("롯데월드몰") val 롯데월드몰: Hotspot,
    @SerializedName("올림픽공원") val 올림픽공원: Hotspot,

)
