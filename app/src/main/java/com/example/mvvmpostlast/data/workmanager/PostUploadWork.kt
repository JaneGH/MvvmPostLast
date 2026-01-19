package com.example.mvvmpostlast.data.workmanager

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.mvvmpostlast.domain.usecases.RefreshPostsUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay

@HiltWorker
class PostUploadWork @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val refreshPostsUseCase: RefreshPostsUseCase
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        try {
            refreshPostsUseCase()
            for (percent in 0 ..100 step 10){
                setProgress(workDataOf(KEY_PROGRESS to percent))
                delay(100)
            }
            return Result.success()
        }catch (e: Exception){
            return Result.failure()
        }
    }

    companion object {
        const val KEY_PROGRESS = "progress"
    }
}

