package com.cyberwalker.fashionstore.data

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.cyberwalker.fashionstore.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


private const val TAG = "MyFirebaseMessagingServ"

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(TAG, "Refreshed token: $token")
        sendRegistrationToServer(token)
    }

    private fun sendRegistrationToServer(token: String) {

    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.d(TAG, "From: ${message}")

//        // Check if message contains a data payload.
//        message?.data?.let {
//            Log.d(TAG, "Message data payload: " + message.data)
//        }
//        // Check if message contains a notification payload.
//        message?.notification?.let {
//            Log.d(TAG, "Message Notification Body: ${it.body}")
//            sendNotification(it.body!!)
//        }

        val notificationManager = ContextCompat.getSystemService(applicationContext, NotificationManager::class.java) as NotificationManager
        val NOTIFICATION_CHANNEL_ID = "Nilesh_channel"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                "Your Notifications",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationChannel.description = "Description"
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.vibrationPattern = longArrayOf(0, 1000, 500, 1000)
            notificationChannel.enableVibration(true)
            notificationManager.createNotificationChannel(notificationChannel)
        }

        // to diaplay notification in DND Mode
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val channel = notificationManager.getNotificationChannel(NOTIFICATION_CHANNEL_ID)
//            channel.canBypassDnd()
//        }

        val notificationBuilder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)

        notificationBuilder.setAutoCancel(true)
            .setColor(ContextCompat.getColor(this, R.color.black))
//            .setContentTitle(getString(R.string.app_name))
            .setContentTitle(message.notification!!.title)
            .setContentText(message.notification!!.body)
            .setDefaults(Notification.DEFAULT_ALL)
            .setWhen(System.currentTimeMillis())
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setAutoCancel(true)

        notificationManager.notify(1000, notificationBuilder.build())

        super.onMessageReceived(message)
    }

    private fun sendNotification(messageBody: String) {
        val notificationManager = ContextCompat.getSystemService(applicationContext, NotificationManager::class.java) as NotificationManager
        // notificationManager.sendNotification(messageBody, applicationContext)
    }

}