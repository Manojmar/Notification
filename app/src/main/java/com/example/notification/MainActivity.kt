package com.example.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.notification.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val CHANNEL_ID = "channel_id"
    private val NOTIFICATION_ID=1
    private val NOTIFICATION_ID_BIG_STYLE=2
    private  val ACTION_SNOOZE=1
    private  val EXTRA_NOTIFICATION_ID=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.btn.setOnClickListener {
//            showNotification()
//        }
        binding.button2.setOnClickListener{
            bignotification()
        }
    }

    private fun bignotification() {
        val intent= Intent(this,MainActivity::class.java).apply {
            flags =Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent =PendingIntent.getActivity(
            this,0,intent,0)

        val bigstyle =NotificationCompat.BigTextStyle()
            .bigText("Codeing is everything" + "Codeing is everything" + "Codeing is everything" + "Codeing is everything" +
                    "Codeing is everything"+ "Codeing is everything"+ "Codeing is everything"+ "Codeing is everything"+ "Codeing is everything")

        val image =NotificationCompat.BigPictureStyle()
            .bigPicture(BitmapFactory.decodeResource(resources,R.drawable.download))
            .setBigContentTitle("Work hard")

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_message_24)
            .setContentTitle("Hard Working")
            .setContentText("Every Thing Is Good")
            .setPriority(NotificationCompat.PRIORITY_MAX)
            /*cancel the notification while touch it*/
            .setAutoCancel(true)
            .setStyle(image)
            .setContentIntent(pendingIntent)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val m = "Channel Name"
            val n = "Coding I sLife"
            val mimportance = NotificationManager.IMPORTANCE_HIGH

            val channel = NotificationChannel(CHANNEL_ID, m, mimportance).apply {
                description = n
            }
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
        with(NotificationManagerCompat.from(this)){
            notify(NOTIFICATION_ID_BIG_STYLE,builder.build())
        }
    }
    }


