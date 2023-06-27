package com.example.scatter

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.EditText
import com.example.scatter.databinding.MainActivityBinding
import org.json.JSONObject

class SkApi {
    lateinit var edittext : EditText
    lateinit var data: String
    var update_date = ""
    var area = ""
    var congestion_level = ""

    fun calljson(){
        // 어떤 서버에서 어떤 db로 저장된 어떤 파일을 가져올 것인가.
    }

    fun jsonPharshing(){
        val testarea = "post1"
        val jsonString = """
        {
            "post1": {
                "update_date" : "20230627180000",
                "area" : "롯데월드",
                "congestion_level": "위험"
            },
            "post1": {
                "update_date" : "20230627180000",
                "area" : "방이동 먹자골목",
                "congestion_level": "안전"
            }
        }
    """.trimIndent()

        val jObject = JSONObject(jsonString)
        val post1Object = jObject.getJSONObject(testarea)
        Log.d(TAG, post1Object.toString())

        update_date = post1Object.getString("update_date")
        area = post1Object.getString("area")
        congestion_level = post1Object.getString("congestion_level")
        update_date = update_date
        area = area
        congestion_level = congestion_level
        Log.d(TAG, "update_date: $update_date")
        Log.d(TAG, "area: $area")
        Log.d(TAG, "congestion_level: $congestion_level")

    }

}