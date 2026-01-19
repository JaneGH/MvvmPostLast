package com.example.mvvmpostlast.data.repository

import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.mvvmpostlast.data.workmanager.PostUploadWork
import com.example.mvvmpostlast.domain.repository.IUploadWorkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UploadWorkRepositoryImpl @Inject constructor(
    private val workManager: WorkManager
): IUploadWorkRepository {
    private val WORK_NAME = "post_upload"
    private val KEY_PROGRESS = "progress"

    override fun startUploadWork() {
      val constraints = Constraints.Builder()
           .setRequiredNetworkType(NetworkType.CONNECTED)
          .build()
        val req = OneTimeWorkRequestBuilder<PostUploadWork>()
            .setConstraints(constraints)
            .build()
        workManager.enqueueUniqueWork(
            "post_upload",
            ExistingWorkPolicy.KEEP,
            req
        )
    }

    override fun observeProgress(): Flow<Int> =
        workManager
            .getWorkInfosForUniqueWorkFlow(WORK_NAME)
            .map { infos ->
                infos.firstOrNull()
                    ?.progress
                    ?.getInt(KEY_PROGRESS, 0) ?: 0
            }

}