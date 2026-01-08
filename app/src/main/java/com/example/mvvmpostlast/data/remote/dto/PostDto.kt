package com.example.mylasttrainproject.data.remote

import com.google.gson.annotations.SerializedName

data class PostDto(
    @SerializedName("body")
    val body: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("userId")
    val userId: Int
)