package com.udacity

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat


fun NotificationManager.sendNotification(
    msgBody: String,
    notificationId: Int,
    downloadStatus:String,
    appContext: Context
) {

    val contentIntent = Intent(appContext,DetailActivity::class.java)
    contentIntent.putExtra("notification_id",notificationId)
    contentIntent.putExtra("file_name",msgBody)
    contentIntent.putExtra("status",downloadStatus)


    val contentPendingIntent = PendingIntent.getActivity(
        appContext,
        notificationId,
        contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )
    // channelId = "packageName-ChannelName"
    val channelId = "${appContext.packageName}-${appContext.getString(R.string.download_channel_name)}"
    val builder = NotificationCompat.Builder(
        appContext,
        channelId
    )
        .setSmallIcon(R.drawable.ic_assistant_black_24dp)
        .setContentTitle(appContext.getString(R.string.notification_title))
        .setContentText("$msgBody is downloaded")
        .setContentIntent(contentPendingIntent)
        .setAutoCancel(true)
        .setPriority(NotificationCompat.PRIORITY_HIGH)// for API < 25
        .addAction(
            R.drawable.ic_assistant_black_24dp,
            appContext.getString(R.string.notification_button),
            contentPendingIntent
        )

    notify(notificationId, builder.build())
}

fun NotificationManager.cancelNotification(notificationId: Int){
    cancel(notificationId)
}

fun NotificationManager.cancelAllNotifications(){
    cancelAll()
}