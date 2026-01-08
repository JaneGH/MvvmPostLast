package com.example.mvvmpostlast.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmpostlast.domain.usecases.ObservePostsUseCase
import com.example.mvvmpostlast.domain.usecases.RefreshPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val observePosts: ObservePostsUseCase,
    private val refreshPosts: RefreshPostsUseCase
): ViewModel() {
    private val _postUiState =  MutableStateFlow(PostUiState())
    val postUiState : StateFlow<PostUiState> = _postUiState


    init {
//        getPosts()
        loadPosts()
    }
    fun getPosts(){

        viewModelScope.launch {
            _postUiState.value = _postUiState.value.copy(loading = true)
            observePosts()
                .catch { e ->
                    _postUiState.value =
                        _postUiState.value.copy(
                            loading = false,
                            error = e.message
                        )
                }
                .collectLatest { posts ->

                    _postUiState.value = _postUiState.value.copy(loading = false, error = null, data = posts )
            }
        }
    }

    fun loadPosts() {
        viewModelScope.launch {
            _postUiState.value = _postUiState.value.copy(loading = true)
            try {
                refreshPosts()
            } catch (e: Exception) {
                _postUiState.value = _postUiState.value.copy(
                    error = e.message
                )
            } finally {
                _postUiState.value = _postUiState.value.copy(loading = false)
            }
        }
    }

}