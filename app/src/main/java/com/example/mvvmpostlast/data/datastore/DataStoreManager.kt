package com.example.mvvmpostlast.data.datastore

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore by preferencesDataStore("settings")

class DataStoreManager @Inject constructor(
    @ApplicationContext private val context: Context)
{

    fun getDarkMode(): Flow<Boolean> {
        return context.dataStore.data.map {
            it[PreferenceKeys.DARK_MODE] ?: false
        }
    }

    suspend fun setDarkMode(enabled: Boolean) {
        context.dataStore.edit {
            it[PreferenceKeys.DARK_MODE] = enabled
        }
    }
}
