package com.example.mvvmpostlast.data.repository

import com.example.mvvmpostlast.data.datasourse.CmsLocalDataSource
import com.example.mvvmpostlast.data.mapper.CmsMappers.toDomain
import com.example.mvvmpostlast.domain.model.CmsPage
import com.example.mvvmpostlast.domain.repository.ICmsRepository
import javax.inject.Inject

class CmsRepositoryImpl @Inject constructor(
    private val local: CmsLocalDataSource
) : ICmsRepository {

    override fun getPage(): Result<CmsPage> {
        return try {
            val fileName = "cms_pages.json"
            val dto = local.loadPageFromAssets(fileName)
            Result.success(dto.toDomain())
            Result.success(dto.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}