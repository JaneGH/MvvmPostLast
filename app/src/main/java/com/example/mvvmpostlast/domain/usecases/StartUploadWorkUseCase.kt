package com.example.mvvmpostlast.domain.usecases

import com.example.mvvmpostlast.domain.repository.IUploadWorkRepository
import javax.inject.Inject

class StartUploadWorkUseCase @Inject constructor (
    private val repository: IUploadWorkRepository
){
    operator fun invoke (){
        repository.startUploadWork()
    }
}