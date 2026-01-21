package com.example.mvvmpostlast.domain.repository

import com.example.mvvmpostlast.domain.model.CmsPage

interface ICmsRepository {
    fun getPage(): Result<CmsPage>
}