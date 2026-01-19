package com.example.mvvmpostlast.view

import kotlinx.coroutines.flow.StateFlow

interface PostViewModelContract {
    val postUiState: StateFlow<PostUiState>
    fun getPosts()
}