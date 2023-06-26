package com.example.scatter

import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService

class MyFirebaseMessagingService : FirebaseMessagingService() {

    companion object {
        private const val TAG = "MyFirebaseMessagingService"
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(TAG, "Refreshed token: $token")

        // 서버로 토큰을 전송하는 로직을 구현하세요.
//        sendRegistrationToServer(token)
    }



//    private fun sendRegistrationToServer(token: String) {
//        // 서버로 토큰을 전송하는 로직을 구현하세요.
//    }
}