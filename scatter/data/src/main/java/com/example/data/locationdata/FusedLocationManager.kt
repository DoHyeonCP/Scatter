package com.example.data.locationdata

import android.Manifest
import android.util.Log
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class FusedLocationManager(
   private val context: Context
) {
   private val fusedLocationClient: FusedLocationProviderClient  = LocationServices.getFusedLocationProviderClient(context)

   fun getLastLocation(callback: (Location?) -> Unit ){
      if(checkLocationPermission()){
         try{
            fusedLocationClient.lastLocation
               .addOnSuccessListener{location ->
                  callback(location)
               }
               .addOnFailureListener{ exception ->
                  Log.e("FusedLocationManager", "Error getting location: ", exception)

               }
         } catch( securityException: SecurityException){
            Log.e("FusedLocationManager", "Permission denied: ", securityException)
         }

      }else{
         // Handle case where permission is not granted
         Log.e("FusedLocationManager", "Location permission not granted")
      }

   }

   private fun checkLocationPermission(): Boolean {
      return ContextCompat.checkSelfPermission(
         context,
         Manifest.permission.ACCESS_FINE_LOCATION
      ) == PackageManager.PERMISSION_GRANTED
   }
}