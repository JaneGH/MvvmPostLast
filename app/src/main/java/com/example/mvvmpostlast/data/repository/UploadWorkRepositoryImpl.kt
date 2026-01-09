package com.example.mvvmpostlast.data.repository

import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.mvvmpostlast.data.workmanager.PostUploadWork
import com.example.mvvmpostlast.domain.repository.IUploadWorkRepository
import javax.inject.Inject

class UploadWorkRepositoryImpl @Inject constructor(
    private val workManager: WorkManager
): IUploadWorkRepository {
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
}