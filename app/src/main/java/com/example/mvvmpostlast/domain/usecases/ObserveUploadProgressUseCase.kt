package com.example.mvvmpostlast.domain.usecases

import com.example.mvvmpostlast.domain.repository.IUploadWorkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveUploadProgressUseCase @Inject constructor(
    private val repository: IUploadWorkRepository
) {
    operator fun invoke(): Flow<Int> =
        repository.observeProgress()
}
