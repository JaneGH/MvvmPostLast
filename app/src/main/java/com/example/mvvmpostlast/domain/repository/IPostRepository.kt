package com.example.mvvmpostlast.domain.repository

import com.example.mvvmpostlast.domain.model.Post
import com.example.mylasttrainproject.data.remote.PostDto
import kotlinx.coroutines.flow.Flow

interface IPostRepository {

    fun observePosts(): Flow<List<Post>>

    suspend fun refreshPosts()
}