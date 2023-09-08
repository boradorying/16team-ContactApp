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
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.contacts.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch


class NotificationHelper(private val context: Context) {
    private val CHANNEL_ID = ""
    private val NOTIFICATION_ID = 1
    private var lastScheduledRequestCode = -1
    private var notificationJob : Job? = null
//    private var alarmIntent: PendingIntent? = null


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
    fun scheduleNotification(is5Seconds: Boolean = true) {

       notificationJob = CoroutineScope(Dispatchers.Default).launch {//코루틴 스코프! 메인쓰레ㅐ드와 상관없이 새로운쓰레ㅐ드를 생성해서 실행을 시킨다,생명주기가 넓은 스코프
           // test 5초

           delay(if (is5Seconds) 5000 else 8000)//기다려
            Log.d("jun","delay :$isActive")
           showNotification()
       }

//        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
//        val delayMillis = if (is5Seconds) 5000L else 7000L
//        val alarmTimeMillis = System.currentTimeMillis() + delayMillis
//        alarmManager.set(AlarmManager.RTC_WAKEUP, alarmTimeMillis, pendingIntent)
//        alarmIntent = pendingIntent
//        Log.d("jun", "Scheduled  time: $alarmTimeMillis")
//      노티피케이션은 버전마다 코드가 달라서...업데이트될때마다 코드를 신경써줘야한다..?!
//
//        Log.d("jun", "After scheduling: ${System.currentTimeMillis()}")

    }

    fun showNotification() {
        val notificationIntent = Intent(context, NotificationReceiver::class.java)
        notificationIntent.action = "com.example.contacts.NOTIFICATION_ACTION"

        val requestCode = NOTIFICATION_ID
        lastScheduledRequestCode = requestCode//리퀘스트코드를 마지막리퀘스트코드에 넣기
//        Log.d("jun", "Scheduled requestCode: $requestCode")//코드일치하는지 확인해볼라

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
            .setContentText("5분뒤 전화거세요")
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

//        if (lastScheduledRequestCode != -1) {
//            val notificationIntent = Intent(context, NotificationReceiver::class.java)
//            val pendingIntent = PendingIntent.getBroadcast(
//                context,
//                lastScheduledRequestCode,
//                notificationIntent,
//                PendingIntent.FLAG_UPDATE_CURRENT
//            )
//
//            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
//            alarmManager.cancel(pendingIntent)
//
//
//
//            Log.d("jun", "Canceled requestCode: $lastScheduledRequestCode")
//            Log.d("jun", "Cancel time : ${System.currentTimeMillis()}")
//
//            lastScheduledRequestCode = -1
//        }
//        Log.d("jun", "After canceling: ${System.currentTimeMillis()}")
    }
}




