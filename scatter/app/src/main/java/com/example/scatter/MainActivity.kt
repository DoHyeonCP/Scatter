package com.example.scatter

import android.app.PendingIntent
import android.content.ContentValues.TAG
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
<<<<<<< HEAD
import android.location.LocationManager
import android.location.LocationListener
=======
import android.location.LocationListener
import android.location.LocationManager
>>>>>>> 3520a9f2c5e688241cfae49d72f3affa266f3fff
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts.*
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.scatter.databinding.MainActivityBinding
import com.example.scatter.ui.theme.ScatterTheme
import com.google.android.gms.common.internal.ImagesContract.URL
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.*
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence
import java.net.HttpURLConnection
import java.net.URL
import java.security.Security
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationManager : LocationManager
    private lateinit var locationCallback: LocationCallback
    private lateinit var locationRequest: LocationRequest
    private lateinit var gpsListener : GPSListener
<<<<<<< HEAD
=======
    private lateinit var mqttClient : MqttClient

>>>>>>> 3520a9f2c5e688241cfae49d72f3affa266f3fff
    private val LOCATION_PERMISSION_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(androidx.appcompat.R.style.Theme_AppCompat)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
<<<<<<< HEAD


=======
>>>>>>> 3520a9f2c5e688241cfae49d72f3affa266f3fff
    }

    override fun onResume() {
        super.onResume()
        requestlocationPermission()
    }

    private fun startLocationService(){
        try{
            var location: Location? = null
            val minTime: Long = 1000
            val minDistance = 0f

            gpsListener = GPSListener()

            if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)

                if(location != null){
                    val latitude = location.latitude
                    val longitude = location.longitude
                    val message = "최근위치1 -> Latitude : $latitude, Longitude : $longitude"

                    Log.i("MyLocTest", message)
                }
                locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    minTime,
                    minDistance,
                    gpsListener
                )
            }
            else if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

                if (location != null) {
                    val latitude = location.latitude
                    val longitude = location.longitude
                    val message = "최근 위치2 -> Latitude : $latitude\n Longitude : $longitude"
                    sendLocationToServer(latitude, longitude)
                    Log.i("MyLocTest", message)
                }

                // 위치 요청하기
                locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    minTime,
                    minDistance,
                    gpsListener
                )

                Log.i("MyLocTest", "requestLocationUpdates() 내 위치2에서 호출시작 ~~ ")
            }
        } catch (e: SecurityException){
            e.printStackTrace()
        }
    }

    inner class GPSListener : LocationListener {
        override fun onLocationChanged(location: Location) {
            val latitude = location.latitude
            val longitude = location.longitude
            val message = "내 위치는 Latitude : $latitude Longitude : $longitude"
            sendLocationToServer(latitude, longitude)
            Log.i("MyLocTest", message)
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}

        override fun onProviderEnabled(provider: String) {}

        override fun onProviderDisabled(provider: String) {}
    }


    private fun startLocationService(){
        try{
            var location: Location? = null
            val minTime: Long = 0
            val minDistance = 0f

            if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)

                if(location != null){
                    val latitude = location.latitude
                    val longitude = location.longitude
                    val message = "최근위치1 -> Latitude : $latitude, Longitude : $longitude"
                    Log.i("MyLocTest", message)
                }
                locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    minTime,
                    minDistance,
                    gpsListener
                )
            }
            else if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

                if (location != null) {
                    val latitude = location.latitude
                    val longitude = location.longitude
                    val message = "최근 위치2 -> Latitude : $latitude\n Longitude : $longitude"
                    Log.i("MyLocTest", message)
                }

                // 위치 요청하기
                locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    minTime,
                    minDistance,
                    gpsListener
                )
                Log.i("MyLocTest", "requestLocationUpdates() 내 위치2에서 호출시작 ~~ ")
            }
        } catch (e: SecurityException){
            e.printStackTrace()
        }
    }

    inner class GPSListener : LocationListener {
        override fun onLocationChanged(location: Location) {
            val latitude = location.latitude
            val longitude = location.longitude
            val message = "내 위치는 Latitude : $latitude Longitude : $longitude"

            Log.i("MyLocTest", message)
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}

        override fun onProviderEnabled(provider: String) {}

        override fun onProviderDisabled(provider: String) {}
    }

    private fun requestlocationPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
            &&ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_BACKGROUND_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            // 권한이 허용되지 않은경우
            if (shouldShowRequestPermissionRationale(android.Manifest.permission.ACCESS_FINE_LOCATION)
                && shouldShowRequestPermissionRationale(android.Manifest.permission.ACCESS_COARSE_LOCATION)
                && shouldShowRequestPermissionRationale(android.Manifest.permission.ACCESS_BACKGROUND_LOCATION)
            ) {
            } else {
                // 권한에 대한 설명이 필요하지 않은 경우(이미 이전에 권한 요청을 했고 거부당함)
                // 바로 권한 요청
                requestPermissions(
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    LOCATION_PERMISSION_REQUEST_CODE
                )
                requestPermissions(
                    arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION),
                    LOCATION_PERMISSION_REQUEST_CODE
                )
                requestPermissions(
                    arrayOf(android.Manifest.permission.ACCESS_BACKGROUND_LOCATION),
                    LOCATION_PERMISSION_REQUEST_CODE
                )
            }
        } else {
<<<<<<< HEAD
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
=======
            startLocationService()
            Log.i("MyLocTest", "onResume에서 requestLocationUpdates() 되었습니다.")
>>>>>>> 3520a9f2c5e688241cfae49d72f3affa266f3fff
        }
    }

    // 권한 요청 결과 처리를 위한 함수 (권한 요청 후 호출됨)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                return
            }
        }
    }

<<<<<<< HEAD
=======

<<<<<<< HEAD
=======
    fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        val locationRequest = LocationRequest.create()?.apply {
            interval = 1000
            fastestInterval = 500
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
        fusedLocationClient.requestLocationUpdates(
            locationRequest!!,
            locationCallback,
            null
        )
    }


>>>>>>> 0465f0046ca59f8223c8ab9a1bb77619ff41f0db
    // 통합 위치 제공자 초기화


>>>>>>> 3520a9f2c5e688241cfae49d72f3affa266f3fff
    private fun sendLocationToServer(latitude: Double, longitude: Double) {
<<<<<<< HEAD
        val brokerUrl = "tcp://192.168.0.15:1883"
=======
        val brokerUrl = "tcp://115.24.135.45:1883"
>>>>>>> 0465f0046ca59f8223c8ab9a1bb77619ff41f0db
        val clientId = "Phone_GPS"
        val payload = "disconnected".toByteArray(Charsets.UTF_8)

        try {
            mqttClient = MqttClient(brokerUrl, clientId, MemoryPersistence())
            val mqttConnectOptions = MqttConnectOptions()
            mqttConnectOptions.connectionTimeout = 1000
            mqttConnectOptions.setWill("location", payload, 1, false)
            mqttClient.connect(mqttConnectOptions)
        } catch(ex: MqttException){
            ex.printStackTrace()
        }
        val topic = "location"
        val message = "$latitude : $longitude"
        val mqttMessage = MqttMessage(message.toByteArray())
        mqttClient.publish(topic, mqttMessage)
    }

    fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        val locationRequest = LocationRequest.create()?.apply {
            interval = 1000
            fastestInterval = 500
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
        fusedLocationClient.requestLocationUpdates(
            locationRequest!!,
            locationCallback,
            null
        )
    }


}
    // 통합 위치 제공자 초기화
    private fun initLocationClient() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val locationRequest = LocationRequest.create()?.apply {
            interval = 1000
            fastestInterval = 500
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest!!)
        val client = LocationServices.getSettingsClient(this)
        val task = client.checkLocationSettings(builder.build())
        task.addOnSuccessListener {
            Log.d(TAG, locationRequest.toString())
        }
        task.addOnFailureListener {
            Log.d(TAG, locationRequest.toString())
        }
    }






