package com.example.scatter

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
        val topic = "location"
        val message = "$latitude" + "," +"$longitude"
        val mqttMessage = MqttMessage(message.toByteArray())
        mqttClient.publish(topic, mqttMessage)
    }
}
