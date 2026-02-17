package com.example.mvvmpostlast.data.repository

import com.example.mvvmpostlast.data.datastore.DataStoreManager
import com.example.mvvmpostlast.domain.repository.IThemeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ThemeRepositoryImpl @Inject constructor (
    private val dataStoreManager: DataStoreManager
) : IThemeRepository {

    override fun getDarkMode(): Flow<Boolean> {
        return dataStoreManager.getDarkMode()
    }

    override suspend fun setDarkMode(enabled: Boolean) {
        dataStoreManager.setDarkMode(enabled)
    }
}
