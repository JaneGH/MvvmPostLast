package com.example.mvvmpostlast.data.repository

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.example.mvvmpostlast.domain.repository.IAirplaneRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AirplaneRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : IAirplaneRepository {

    override val airplaneMode: Flow<Boolean> = callbackFlow {

        val receiver = object : BroadcastReceiver() {

            override fun onReceive(context: Context?, intent: Intent?) {

                if (intent?.action == Intent.ACTION_AIRPLANE_MODE_CHANGED) {

                    val enabled = intent.getBooleanExtra("state", false)

                    trySend(enabled)
                }
            }
        }

        context.registerReceiver(
            receiver,
            IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        )

        awaitClose {
            context.unregisterReceiver(receiver)
        }
    }
}
