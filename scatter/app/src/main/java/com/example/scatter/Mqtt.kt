package com.example.scatter

import android.media.session.MediaSession.Token
import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence
import org.eclipse.paho.client.mqttv3.*


class Mqtt {
    private lateinit var mqttClient : MqttClient


    fun sendLocationToServer(latitude: Double, longitude: Double) {

        val brokerUrl = "tcp://115.21.135.45:1883"
        val clientId = "Phone_GPS"
//        val payload = "disconnected".toByteArray(Charsets.UTF_8)
        try {
            mqttClient = MqttClient(brokerUrl, clientId, MemoryPersistence())
            val mqttConnectOptions = MqttConnectOptions()
            mqttConnectOptions.connectionTimeout = 1000
//            mqttConnectOptions.setWill("location", payload, 1, false)
            mqttClient.connect(mqttConnectOptions)
        } catch(ex: MqttException){
            ex.printStackTrace()
        }

        FirebaseMessaging.getInstance().token
            .addOnSuccessListener { token ->
                var firebasetoken = token
                val topic = "location"
                var message = "$latitude" + "," +"$longitude" + "," + "$firebasetoken"
                Log.i("informatio", message)
                var mqttMessage = MqttMessage(message.toByteArray())
                mqttClient.publish(topic, mqttMessage)
            }
    }
}
