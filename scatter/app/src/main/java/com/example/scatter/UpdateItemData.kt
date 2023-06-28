package com.example.scatter

import android.text.format.DateFormat

data class UpdateItemData(
//  json 데이터의 구조와 일치해야함
//    @SerializeName  val created_date:
    val area : String,
    val congestion_level: String,
)


