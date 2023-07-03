package com.example.scatter

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Request
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class SkApi{
    private lateinit var  apiService: ApiService




}