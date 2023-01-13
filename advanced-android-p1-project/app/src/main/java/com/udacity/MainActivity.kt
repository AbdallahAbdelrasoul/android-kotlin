package com.udacity

import android.app.DownloadManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {

    private var downloadID: Long = 0
    private var downloadDesc: String = ""
    private var downloadUrl: String = ""

    private lateinit var notificationManager: NotificationManager

    private lateinit var radioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        radioGroup = findViewById(R.id.radio_group)

        registerReceiver(receiver, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))

        custom_button.setOnClickListener {
            download()
        }

        // Create an instance of NotificationManager
        notificationManager = ContextCompat.getSystemService(
            applicationContext, NotificationManager::class.java
        ) as NotificationManager

        // channelId = "packageName-ChannelName"
        val channelId =
            "${applicationContext.packageName}-${applicationContext.getString(R.string.download_channel_name)}"
        // Create Download Notification Channel
        createChannel(
            channelId,
            getString(R.string.download_channel_name)
        )

    }

    // unregister the Receiver
    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }

    private fun createChannel(channelId: String, channelName: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { // API 25 and above
            val notificationChannel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val id = intent?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
            try {
                if (id == downloadID) {
                    notificationManager.sendNotification(
                        downloadDesc,// as a notification content text
                        downloadID.toInt(),// as a notification id
                        getDownloadStatus(id),
                        context!!
                    )
//                    custom_button.hasCompletedDownload()
                }
            } catch (e: Exception) {
                Toast.makeText(context, e.message.toString(), Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun getDownloadStatus(id:Long):String {
        var status = ""
        val query = DownloadManager.Query()
        query.setFilterById(id)
        val downloadManager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        val cursor = downloadManager.query(query)
        if(cursor.moveToFirst()){
            val downloadStatus = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))
            status = when(downloadStatus){
                DownloadManager.STATUS_SUCCESSFUL -> "Success"
                DownloadManager.STATUS_FAILED -> "Fail"
                else -> "Fail"
            }
        }
        return status
    }

    private fun download() {
        try {
            val selectedRadioId = radioGroup.checkedRadioButtonId
            if (selectedRadioId == -1) {
                Toast.makeText(
                    applicationContext,
                    "Please select the file to download",
                    Toast.LENGTH_SHORT
                ).show()
//                custom_button.hasCompletedDownload()
            } else {
                val selectedRadioBtn = findViewById<RadioButton>(selectedRadioId)
                downloadDesc = selectedRadioBtn.text.toString()
                when (selectedRadioBtn.id) {
                    R.id.radio_1 -> downloadUrl = URL1
                    R.id.radio_2 -> downloadUrl = URL2
                    R.id.radio_3 -> downloadUrl = URL3
                }

                val request = DownloadManager.Request(Uri.parse(downloadUrl))
                    .setTitle(getString(R.string.app_name))
                    .setDescription(downloadDesc)
                    .setRequiresCharging(false)
                    .setAllowedOverMetered(true)
                    .setAllowedOverRoaming(true)

                val downloadManager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
                downloadID =
                    downloadManager.enqueue(request)// enqueue puts the download request in the queue.
            }
        } catch (e: Exception) {
            Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
        }

    }

    companion object {
        private const val URL1 = "https://github.com/bumptech/glide"
        private const val URL2 = "https://github.com/udacity/nd940-c3-advanced-android-programming-project-starter"
        private const val URL3 = "https://github.com/square/retrofit"
    }

}
