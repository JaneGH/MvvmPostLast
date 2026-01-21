package com.example.mvvmpostlast.data.local
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AssetJsonReader @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun read(fileName: String): String =
        context.assets.open(fileName).bufferedReader().use { it.readText() }
}