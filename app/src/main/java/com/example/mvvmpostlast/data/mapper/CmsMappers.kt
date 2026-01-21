package com.example.mvvmpostlast.data.mapper

import com.example.mvvmpostlast.data.remote.dto.cmspages.CmsBlockDto
import com.example.mvvmpostlast.data.remote.dto.cmspages.CmsPageDto
import com.example.mvvmpostlast.domain.model.CmsBlock
import com.example.mvvmpostlast.domain.model.CmsPage

object CmsMappers {

    fun CmsPageDto.toDomain(): CmsPage =
        CmsPage(blocks = blocks.map { it.toDomain() })

    fun CmsBlockDto.toDomain(): CmsBlock =
        CmsBlock(
            type = type,
            text = text,
            url = url,
            action = action
        )
}
