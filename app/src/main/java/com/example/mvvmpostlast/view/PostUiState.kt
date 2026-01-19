package com.example.mvvmpostlast.view

import com.example.mvvmpostlast.domain.model.Post

data class PostUiState (
    val data:List<Post>? = null,
    var error: String? = null,
    val loading: Boolean = false,
    val progress: Int = 0,
)