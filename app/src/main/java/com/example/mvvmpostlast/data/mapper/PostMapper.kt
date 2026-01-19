package com.example.mvvmpostlast.data.mapper

import com.example.mvvmpostlast.data.local.entity.PostEntity
import com.example.mvvmpostlast.domain.model.Post
import com.example.mylasttrainproject.data.remote.PostDto


    fun PostEntity.toDomain() = Post(
        id = this.id,
        title = this.title,
        body = this.body,
        userId = this.userId
    )

    fun PostDto.toEntity() = PostEntity(
        id = this.id,
        title = this.title,
        body = this.body,
        userId = this.userId
    )

