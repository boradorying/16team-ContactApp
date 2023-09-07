package com.example.contacts.Notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.contacts.Notification.NotificationHelper


class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == "com.example.contacts.NOTIFICATION_ACTION") {
            val message = intent.getStringExtra("message")
            val notificationId = intent.getIntExtra("notificationId", -1)


            if (notificationId != -1 && message != null) {
                val notificationHelper = NotificationHelper(context!!)
                notificationHelper.showNotification(notificationId, message)
                notificationHelper.cancelNotification()
            }
        }
    }
}