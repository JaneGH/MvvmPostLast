package com.example.mvvmpostlast.domain.repository

import kotlinx.coroutines.flow.Flow

interface IUploadWorkRepository {
    fun startUploadWork()
    fun observeProgress(): Flow<Int>
}
