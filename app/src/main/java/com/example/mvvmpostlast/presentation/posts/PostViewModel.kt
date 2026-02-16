package com.example.mvvmpostlast.presentation.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmpostlast.analytics.AnalyticsManager
import com.example.mvvmpostlast.domain.usecases.ObserveAirplaneModeUseCase
import com.example.mvvmpostlast.domain.usecases.ObservePostsUseCase
import com.example.mvvmpostlast.domain.usecases.ObserveUploadProgressUseCase
import com.example.mvvmpostlast.domain.usecases.StartUploadWorkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class PostViewModel @Inject constructor(
    private val observePosts: ObservePostsUseCase,
    private val observeUploadProgress: ObserveUploadProgressUseCase,
    private val startUploadWorkUseCase: StartUploadWorkUseCase,
    private val analyticsManager: AnalyticsManager,
    private val observeAirplaneModeUseCase: ObserveAirplaneModeUseCase
):  ViewModel(), PostViewModelContract {
    private val _postUiState =  MutableStateFlow(PostUiState())
    override val postUiState : StateFlow<PostUiState> = _postUiState

    override val airplaneMode = observeAirplaneModeUseCase().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        false
    )

    init {
        viewModelScope.launch {
            observeUploadProgress()
                .collect { progress ->
                    _postUiState.value =
                        _postUiState.value.copy(progress = progress)
                }
        }
    }


    override fun onScreenOpened() {
        analyticsManager.logScreen("PostsScreen")
    }

    fun onAppStarted() {
        startUploadWorkUseCase()
    }

    override fun getPosts(){

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

//    fun loadPosts() {
//        viewModelScope.launch {
//            _postUiState.value = _postUiState.value.copy(loading = true)
//            try {
//                refreshPosts()
//            } catch (e: Exception) {
//                _postUiState.value = _postUiState.value.copy(
//                    error = e.message
//                )
//            } finally {
//                _postUiState.value = _postUiState.value.copy(loading = false)
//            }
//        }
//    }

}