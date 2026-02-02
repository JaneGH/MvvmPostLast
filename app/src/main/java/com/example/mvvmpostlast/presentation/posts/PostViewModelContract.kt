package com.example.mvvmpostlast.presentation.posts

import kotlinx.coroutines.flow.StateFlow

interface PostViewModelContract {
    val postUiState: StateFlow<PostUiState>
    fun getPosts()
    fun onScreenOpened()
}