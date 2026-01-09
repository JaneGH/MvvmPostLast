package com.example.mvvmpostlast.data.workmanager

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.mvvmpostlast.domain.usecases.RefreshPostsUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class PostUploadWork @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val refreshPostsUseCase: RefreshPostsUseCase
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        try {
            refreshPostsUseCase()
            return Result.success()
        }catch (e: Exception){
            return Result.failure()
        }
    }


}
