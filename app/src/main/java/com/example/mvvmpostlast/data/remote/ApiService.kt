package com.example.mvvmpostlast.data.remote

import com.example.mylasttrainproject.data.remote.PostDto
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getPost(): List<PostDto>
}