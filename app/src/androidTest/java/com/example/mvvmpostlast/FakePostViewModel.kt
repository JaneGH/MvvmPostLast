package com.example.mvvmpostlast

import com.example.mvvmpostlast.domain.model.Post
import com.example.mvvmpostlast.presentation.posts.PostUiState
import com.example.mvvmpostlast.presentation.posts.PostViewModelContract
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakePostViewModel(
    initialState: PostUiState
) : PostViewModelContract {

    private val _state = MutableStateFlow(initialState)
    override val postUiState: StateFlow<PostUiState> = _state

    override fun getPosts() {
        _state.value = PostUiState(
            loading = true
        )

        _state.value = PostUiState(
            data = listOf(
                Post("Body", 1, "Title", 1)
            )
        )
    }

}
