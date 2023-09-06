//package com.example.data.firebase
//
//import android.app.NotificationManager
//import android.app.PendingIntent
//import android.content.Context
//import android.content.Intent
//import android.media.RingtoneManager
//import android.util.Log
//
//import androidx.core.app.NotificationCompat
//import androidx.core.graphics.red
//import com.example.scatter.activity.MainActivity
//import com.example.scatter.R
//
//import com.google.firebase.messaging.RemoteMessage
//
//class FirebaseMessagingService : FirebaseMessagingService(){
//    private val TAG = "FirebasemsgService"
//    private var msg: String? = null
//    private var title: String? = null
//
//    override fun onMessageReceived(remoteMessage: RemoteMessage) {
//        Log.e(TAG, "onMessageRecived")
//        title = remoteMessage.notification?.title
//        msg = remoteMessage.notification?.body
//
//        val intent = Intent(this, MainActivity::class.java)
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//
//
//        val contentIntent = PendingIntent.getActivity(
//            this,
//            0,
//            Intent(this, MainActivity::class.java),
//            PendingIntent.FLAG_UPDATE_CURRENT// 뭔가 이상함
//        )
//
//        val mBuilder = NotificationCompat.Builder(this, "channal_id")
//            .setSmallIcon(R.drawable.congestion_emo)
//            .setContentTitle(title)
//            .setContentText(msg)
//            .setAutoCancel(true)
//
//
//            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
//            .setVibrate(longArrayOf(1,1000))
//            .extend(WearableExtender)
//
//        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//
//        notificationManager.notify(0, mBuilder.build())
//
//        mBuilder.setContentIntent(contentIntent)
//    }
//
//    val WearableExtender = NotificationCompat.WearableExtender()
//        .setContentAction(R.drawable.congestion_emo.red)
//
//}