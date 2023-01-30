package com.udacity

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ObjectAnimator.ofArgb
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator.ofArgb
import android.app.NotificationManager
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var notificationManager: NotificationManager
    private lateinit var motionLayout: MotionLayout
    private lateinit var fileNameTextView: TextView
    private lateinit var statusTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        cancelDownloadNotification()

        // Set the File name
        fileNameTextView = findViewById(R.id.file_name)
        val fileName = intent?.getStringExtra("file_name")
        fileNameTextView.text = fileName

        // Set the Download State
        statusTextView = findViewById(R.id.status)
        val status = intent?.getStringExtra("status")
        statusTextView.text = status

        setStatusColor(status)

        okBtn.setOnClickListener {
            finish()
        }

    }

    private fun setStatusColor(status: String?) {
        val textColor: Int = when (status) {
            "Success" -> Color.GREEN
            else -> Color.RED
        }

        val colorize = PropertyValuesHolder.ofObject(
            "textColor",
            ArgbEvaluator(),
            Color.BLACK, textColor
        )
        val alpha = PropertyValuesHolder.ofFloat(View.ALPHA, 0f,1f)

        val animator = ObjectAnimator.ofPropertyValuesHolder(statusTextView, colorize, alpha)
        animator.duration = 3000
        animator.start()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun cancelDownloadNotification() {
        try {
            notificationManager = ContextCompat.getSystemService(
                applicationContext, NotificationManager::class.java
            ) as NotificationManager

            val id = intent?.getIntExtra("notification_id", -1)
            notificationManager.cancelNotification(id!!)

        } catch (e: Exception) {
            Toast.makeText(applicationContext, e.message.toString(), Toast.LENGTH_SHORT).show()
        }

    }

}
