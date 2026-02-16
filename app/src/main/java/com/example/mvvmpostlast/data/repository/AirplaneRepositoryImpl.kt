package com.example.mvvmpostlast.data.repository

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import com.example.mvvmpostlast.domain.repository.IAirplaneRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
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

        val filter = IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.registerReceiver(
                receiver,
                filter,
                Context.RECEIVER_NOT_EXPORTED
            )
        } else {
            context.registerReceiver(receiver, filter)
        }

        awaitClose {
            context.unregisterReceiver(receiver)
        }
    }.distinctUntilChanged()
}
