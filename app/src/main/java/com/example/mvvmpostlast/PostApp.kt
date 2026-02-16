package com.example.mvvmpostlast

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.example.mvvmpostlast.domain.repository.IAirplaneRepository
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class PostApp : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    @Inject
    lateinit var repository: IAirplaneRepository

//    private val receiver = object : BroadcastReceiver() {
//
//        override fun onReceive(context: Context?, intent: Intent?) {
//
//            Log.d("AIRPLANE_DEBUG", "Receiver triggered")
//
//            if (intent?.action == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
//
//                val enabled = intent.getBooleanExtra("state", false)
//
//                Log.d("AIRPLANE_DEBUG", "Receiver state = $enabled")
//
//                repository.update(enabled)
//            }
//        }
//    }

    override fun onCreate() {
        super.onCreate()

        val filter = IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)

//        registerReceiver(receiver, filter)

        Log.d("AIRPLANE_DEBUG", "Receiver registered in Application")
    }

}