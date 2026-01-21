package com.example.mvvmpostlast.domain.usecases

import com.example.mvvmpostlast.domain.model.CmsPage
import com.example.mvvmpostlast.domain.repository.ICmsRepository
import javax.inject.Inject

class  GetCmsPageUseCase @Inject constructor(
    private val repository: ICmsRepository
) {
    operator fun invoke(): Result<CmsPage> {
        return repository.getPage()
    }
}