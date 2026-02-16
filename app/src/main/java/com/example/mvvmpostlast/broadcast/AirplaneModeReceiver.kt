package com.example.mvvmpostlast.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.mvvmpostlast.domain.repository.IAirplaneRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AirplaneModeReceiver : BroadcastReceiver() {

    @Inject
    lateinit var repository: IAirplaneRepository

    override fun onReceive(context: Context?, intent: Intent?) {

        Log.d("AIRPLANE_DEBUG", "Receiver triggered")


        if (intent?.action == Intent.ACTION_AIRPLANE_MODE_CHANGED) {

            val enabled = intent.getBooleanExtra("state", false)

            repository.update(enabled)
        }
    }
}
