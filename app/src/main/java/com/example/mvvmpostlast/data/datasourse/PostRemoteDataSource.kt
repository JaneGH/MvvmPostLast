package com.example.mvvmpostlast.data.datasourse

import com.example.mvvmpostlast.data.remote.ApiService
import com.example.mylasttrainproject.data.remote.PostDto
import javax.inject.Inject

class PostRemoteDataSource @Inject constructor(val api: ApiService) {
    suspend fun getPosts() : List<PostDto>{
        return api.getPost()
    }
}