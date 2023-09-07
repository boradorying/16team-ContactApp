package com.example.contacts.Notification


import android.Manifest
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager

import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.contacts.R


class NotificationHelper(private val context: Context) {
    private val CHANNEL_ID = "my_channel_id"
    private val NOTIFICATION_ID = 1
    private var lastScheduledRequestCode = -1
    private var alarmManager: AlarmManager? = null
    private var alarmIntent: PendingIntent? = null

    init {
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "My Channel"
            val descriptionText = "My Channel Description"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    // 알람 예약
    fun scheduleNotification(is5Seconds: Boolean = true) {
        val notificationIntent = Intent(context, NotificationReceiver::class.java)
        notificationIntent.action = "com.example.contacts.NOTIFICATION_ACTION"

        val requestCode = generateUniqueRequestCode()
        lastScheduledRequestCode = requestCode


        val notificationId = NOTIFICATION_ID + requestCode

        notificationIntent.putExtra(
            "message",
            if (is5Seconds) "Notification after 5 seconds" else "Notification after 1 minute"
        )
        notificationIntent.putExtra("notificationId", notificationId)
        notificationIntent.putExtra("is5Seconds", is5Seconds)

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            requestCode,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val delayMillis = if (is5Seconds) 5000L else 7000L
        val alarmTimeMillis = System.currentTimeMillis() + delayMillis
        alarmManager.set(AlarmManager.RTC_WAKEUP, alarmTimeMillis, pendingIntent)
        alarmIntent = pendingIntent
        Log.d(
            "jun",
            "Notification scheduled: ${notificationIntent.getStringExtra("message")}"
        )
    }


    fun showNotification(notificationId: Int, notificationText: String) {
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.donge)
            .setContentTitle("제발좀 대라")
            .setContentText(notificationText)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setAutoCancel(false)

        val notificationManager = NotificationManagerCompat.from(context)
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            return
        }
        notificationManager.notify(notificationId, builder.build())
    }


     fun cancelNotification() {
        alarmManager?.cancel(alarmIntent)
         alarmIntent = null
    }


    fun generateUniqueRequestCode(): Int {
        val currentTimeMillis = System.currentTimeMillis()
        return (currentTimeMillis % 10000).toInt() // 10000 이상의 중복되지 않는 값을 생성
    }
}



