package com.example.contacts.Notification


import android.Manifest
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class NotificationHelper(private val context: Context) {
    private val CHANNEL_ID = ""
    private val NOTIFICATION_ID = 1
    private var lastScheduledRequestCode = -1
    private var notificationJob: Job? = null

    init {
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "16조의 연락처 앱🚀"
            val descriptionText = "다같이 힘내서 만든 연락처 앱 입니다🚖"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }
    // 알람 예약
    fun scheduleNotification(is5Seconds: Boolean = true,name:String) {
        notificationJob =
            CoroutineScope(Dispatchers.Default).launch {//코루틴 스코프! 메인쓰레ㅐ드와 상관없이 새로운쓰레ㅐ드를 생성해서 실행을 시킨다,생명주기가 넓은 스코프
                // test 5초

                delay(if (is5Seconds) 5000 else 8000)//기다려
                Log.d("jun", "delay :$isActive")
                showNotification(name)
            }
    }

    fun showNotification(name: String) {
        val notificationIntent = Intent(context, NotificationReceiver::class.java)
        notificationIntent.action = "com.example.contacts.NOTIFICATION_ACTION"

        val requestCode = NOTIFICATION_ID
        val notificationId = NOTIFICATION_ID + requestCode

        notificationIntent.putExtra("notificationId", notificationId)

        val pendingIntent = PendingIntent.getActivity(//온리시브를 호출, 예약된 시간이 지나면
            context,
            requestCode,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )


        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_add_ic_call_24)
            .setContentTitle("새로운 연락처 등록 알람입니다")
            .setContentText(" $name 에게 전화걸 시간 입니다~")
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)

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
        notificationJob?.cancel()//훨씬더 간결해지기때문에 코루틴을 쓸 수 있는 경험!!
        Log.d("jun", "notification cancle: ${notificationJob}")
    }
}




