package com.example.mvvmpostlast.domain.repository

import kotlinx.coroutines.flow.Flow

interface IThemeRepository {

    fun getDarkMode(): Flow<Boolean>

    suspend fun setDarkMode(enabled: Boolean)

}
